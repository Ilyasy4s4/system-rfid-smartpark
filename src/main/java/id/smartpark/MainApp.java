/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.smartpark;

/**
 *
 * @author LENOVO
 */

import objects.Mahasiswa;

public class MainApp {
    public static void main(String[] args) {
        Mahasiswa mhs = new Mahasiswa("12345", "2409001", "Budi Darmawan", "Teknik Informatika");
        
        System.out.println("=== Sistem SmartPark ID Siap ===");
        System.out.println("Mencoba akses data: " + mhs.getNamaLengkap());
        
        if (mhs instanceof Mahasiswa) {
            System.out.println("Validasi Objek: Mahasiswa Terdeteksi");
        }
    }
}