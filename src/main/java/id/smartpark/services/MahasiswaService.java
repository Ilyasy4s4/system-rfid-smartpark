package id.smartpark.services;

import id.smartpark.gui.AdminPage;
import id.smartpark.dao.GenericDAO;
import id.smartpark.objects.Mahasiswa;
import com.mongodb.client.model.Filters;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.bson.conversions.Bson;

/**
 * MahasiswaService - Menangani logika CRUD dan render antarmuka Grid Mahasiswa.
 * Sesuai Milestone 1: Dashboard Admin (Sprint 2).
 */
public class MahasiswaService {

    private final GenericDAO<Mahasiswa> DAO;

    public MahasiswaService() {
        // Inisialisasi DAO dengan koleksi "mahasiswa" sesuai database SmartPark_db
        this.DAO = new GenericDAO<>("mahasiswa", Mahasiswa.class);
    }

    public void tambahMahasiswa(Mahasiswa m) {
        DAO.save(m);
    }

    /**
     * READ & GRID GENERATOR
     * Menampilkan data mahasiswa ke dalam panel target dengan desain putih bersih.
     */
    public void tampilMahasiswa(JPanel panelTarget, String key) {
        List<Mahasiswa> daftar;
        if (key.isEmpty()) {
            daftar = DAO.findAll();
        } else {
            daftar = cariMahasiswa(key);
        }

        // Membersihkan panel sebelum render ulang
        panelTarget.removeAll();
        panelTarget.setLayout(new BorderLayout());
        
        // MENGUBAH BACKGROUND MENJADI PUTIH BERSIH SESUAI FIGMA
        panelTarget.setBackground(Color.WHITE); 

        // Membuat Panel Grid (Dinamis: kolom 3 ke samping)
        JPanel gridPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        gridPanel.setOpaque(false); // Transparan agar mengikuti warna panelTarget (Putih)
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (Mahasiswa m : daftar) {
            // Membuat Card Panel dengan desain yang lebih modern
            JPanel cardPanel = new JPanel(new GridLayout(4, 1, 5, 5));
            cardPanel.setBackground(new Color(248, 249, 250)); // Abu-abu sangat muda (Soft)
            cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1, true),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
            ));

            // Label Informasi dengan warna teks yang lebih kontras (Hitam/Abu Gelap)
            JLabel lblNama = new JLabel("Nama: " + m.getNamaLengkap());
            lblNama.setFont(new Font("Segoe UI", Font.BOLD, 13));
            lblNama.setForeground(new Color(51, 51, 51));
            
            JLabel lblNIM = new JLabel("NIM: " + m.getNim());
            lblNIM.setForeground(new Color(102, 102, 102));
            
            JLabel lblProdi = new JLabel("Prodi: " + m.getProdi());
            lblProdi.setForeground(new Color(102, 102, 102));

            // Panel Tombol aksi
            JPanel controlPanel = new JPanel(new GridLayout(1, 2, 10, 0));
            controlPanel.setOpaque(false);

            JButton btnEdit = new JButton("Edit");
            btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnEdit.addActionListener((ActionEvent e) -> {
                // Mapping data ke form input AdminPage
                AdminPage.txtUID.setText(m.getUidRfid());
                AdminPage.txtKRID.setText(m.getNim()); 
                AdminPage.txtKRID.setEnabled(false); // NIM bersifat Primary (tidak bisa diedit)
                AdminPage.txtKRName.setText(m.getNamaLengkap());
                AdminPage.txtKRDept.setSelectedItem(m.getProdi());
                AdminPage.btnUpdate.setEnabled(true);
                AdminPage.btnSave.setEnabled(false);
            });

            JButton btnDel = new JButton("Hapus");
            btnDel.setBackground(new Color(231, 76, 60)); // Merah Alizarin
            btnDel.setForeground(Color.WHITE);
            btnDel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnDel.addActionListener((ActionEvent e) -> {
                int confirm = JOptionPane.showConfirmDialog(null, 
                        "Hapus data " + m.getNamaLengkap() + "?", "Konfirmasi", 
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    hapusMahasiswa(m.getNim());
                }
            });

            controlPanel.add(btnEdit);
            controlPanel.add(btnDel);

            cardPanel.add(lblNama);
            cardPanel.add(lblNIM);
            cardPanel.add(lblProdi);
            cardPanel.add(controlPanel);

            gridPanel.add(cardPanel);
        }

        // Membungkus grid ke bagian atas agar tidak tertarik ke tengah jika data sedikit
        panelTarget.add(gridPanel, BorderLayout.NORTH);
        
        // Re-render antarmuka
        panelTarget.revalidate();
        panelTarget.repaint();
    }

    /**
     * SEARCH: Mencari berdasarkan NIM, Nama, atau Prodi
     */
    public List<Mahasiswa> cariMahasiswa(String key) {
        List<Bson> filters = new ArrayList<>();
        // Mencari ke seluruh field kecuali UID
        for (Field field : Mahasiswa.class.getDeclaredFields()) {
            if (field.getName().equals("uidRfid")) continue;
            filters.add(Filters.regex(field.getName(), key, "i"));
        }
        return DAO.findMany(Filters.or(filters));
    }

    /**
     * UPDATE: Memperbarui dokumen di MongoDB berdasarkan NIM
     */
    public void updateMahasiswa(Mahasiswa m) {
        Bson filter = Filters.eq("nim", m.getNim());
        DAO.update(filter, m);
        AdminPage.showData(""); // Refresh grid
        JOptionPane.showMessageDialog(null, "Data berhasil diperbarui!");
    }

    /**
     * DELETE: Menghapus dokumen dari koleksi
     */
    public void hapusMahasiswa(String nim) {
        DAO.delete(Filters.eq("nim", nim));
        AdminPage.showData(""); // Refresh grid
        JOptionPane.showMessageDialog(null, "Data telah dihapus.");
    }
}