package id.smartpark.services;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import id.smartpark.serial.SerialDataHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * SerialService - Singleton untuk menangani komunikasi Hardware RFID
 * Adaptasi dari referensi Pak Nishom
 */
public class SerialService {
    private static SerialService instance;
    private SerialPort activePort;
    private final List<SerialDataHandler<String>> handlers = new ArrayList<>();

    // Private constructor agar tidak bisa di-new secara bebas (Singleton)
    private SerialService() {}

    /**
     * Mengambil instance tunggal dari SerialService
     * @return SerialService
     */
    public static synchronized SerialService getInstance() {
        if (instance == null) {
            instance = new SerialService();
        }
        return instance;
    }

    // Mendaftarkan halaman yang ingin mendengarkan data RFID
    public void addHandler(SerialDataHandler<String> handler) {
        if (!handlers.contains(handler)) {
            handlers.add(handler);
        }
    }

    // Menghapus handler agar tidak memory leak
    public void removeHandler(SerialDataHandler<String> handler) {
        handlers.remove(handler);
    }

    public boolean connect(String portName, int baudRate) {
        if (activePort != null && activePort.isOpen()) {
            return true;
        }

        activePort = SerialPort.getCommPort(portName);
        activePort.setBaudRate(baudRate);
        
        // Timeout semi-blocking agar scanner lancar
        activePort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 1000, 0);

        if (activePort.openPort()) {
            System.out.println("INFO: RFID Reader terhubung di " + portName);
            setupListener();
            return true;
        } else {
            System.err.println("ERROR: Gagal membuka port RFID di " + portName);
            return false;
        }
    }

    private void setupListener() {
        activePort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) return;

                // Membaca input satu baris (ID Kartu)
                try (Scanner scanner = new Scanner(activePort.getInputStream())) {
                    if (scanner.hasNextLine()) {
                        String data = scanner.nextLine().trim();
                        if (!data.isEmpty()) {
                            broadcast(data); // Kirim data ke semua halaman
                        }
                    }
                } catch (Exception e) {
                    // Silently ignore errors
                }
            }
        });
    }

    private void broadcast(String data) {
        for (SerialDataHandler<String> handler : handlers) {
            handler.onDataReceived(data);
        }
    }

    public void disconnect() {
        if (activePort != null && activePort.isOpen()) {
            activePort.removeDataListener();
            activePort.closePort();
            System.out.println("INFO: Koneksi RFID diputus.");
        }
    }

    public boolean isConnected() {
        return activePort != null && activePort.isOpen();
    }
}