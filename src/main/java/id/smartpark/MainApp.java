/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.smartpark;

import objects.Mahasiswa;
import objects.GenericDAO;
import static com.mongodb.client.model.Filters.eq;

/**
 * MainApp - Entry Point Sistem SmartPark ID
 * Struktur penulisan disesuaikan dengan standar laboratorium Pemkom 2.
 */
public class MainApp {

    public static void main(String[] args) {
        try {
            System.out.println("=== Inisialisasi Sistem SmartPark ID ===");
            
            // 1. Simulasi Pembuatan Objek (Sprint 1)
            Mahasiswa mhs = new Mahasiswa("12345", "2409001", "Budi Darmawan", "Teknik Informatika");
            
            // 2. Validasi Objek sesuai standar dosen (instanceof)
            if (mhs instanceof Mahasiswa) {
                System.out.println("-----------------------------------------");
                System.out.println("STATUS: OBJEK VALID");
                System.out.println("Nama Mahasiswa: " + mhs.getNamaLengkap());
                System.out.println("-----------------------------------------");
            } else {
                System.err.println("STATUS: OBJEK TIDAK DIKENALI");
            }

            // 3. Persiapan DAO untuk Sprint berikutnya
            // Kita inisialisasi tanpa memanggil fungsi agar tidak error jika DB mati
            GenericDAO<Mahasiswa> mhsDAO = new GenericDAO<>("mahasiswa", Mahasiswa.class);
            System.out.println("Log: GenericDAO untuk koleksi 'mahasiswa' telah siap.");

        } catch (Exception e) {
            // Standar Debugging: Menangkap error yang tidak terduga
            System.err.println("=========================================");
            System.err.println("STATUS: SISTEM GAGAL DIMULAI!");
            System.err.println("Pesan Error: " + e.getMessage());
            System.err.println("=========================================");
        }
    }
}