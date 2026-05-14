/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.smartpark.objects;

/**
 *
 * @author LENOVO
 */

public class Mahasiswa {
    private String uidRfid;
    private String nim;
    private String namaLengkap;
    private String prodi;

    public Mahasiswa() {}

    public Mahasiswa(String uidRfid, String nim, String namaLengkap, String prodi) {
        this.uidRfid = uidRfid;
        this.nim = nim;
        this.namaLengkap = namaLengkap;
        this.prodi = prodi;
    }

    // Getter dan Setter (Enkapsulasi)
    public String getUidRfid() { return uidRfid; }
    public void setUidRfid(String uidRfid) { this.uidRfid = uidRfid; }
    public String getNim() { return nim; }
    public void setNim(String nim) { this.nim = nim; }
    public String getNamaLengkap() { return namaLengkap; }
    public void setNamaLengkap(String namaLengkap) { this.namaLengkap = namaLengkap; }
    public String getProdi() { return prodi; }
    public void setProdi(String prodi) { this.prodi = prodi; }
}