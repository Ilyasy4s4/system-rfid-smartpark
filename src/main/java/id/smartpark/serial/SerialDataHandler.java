/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package id.smartpark.serial;

/**
 * SerialDataHandler - Interface untuk menangani data dari Serial Port
 * Menggunakan Generic <T> agar fleksibel menangani berbagai tipe data.
 * @param <T>
 */
public interface SerialDataHandler<T> {
    void onDataReceived(T data);
}