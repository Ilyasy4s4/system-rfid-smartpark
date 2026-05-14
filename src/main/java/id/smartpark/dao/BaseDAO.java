/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package id.smartpark.dao;

import java.util.List;
import org.bson.conversions.Bson;

public interface BaseDAO<T> {
    // Operasi CRUD Dasar menggunakan Bson Filter
    void save(T entity);
    void update(Bson filter, T entity);
    void delete(Bson filter);
    
    // Operasi Reading
    List<T> findAll();
    T findOne(Bson filter); // Mencari satu data (misal: berdasarkan NIM)
    List<T> findMany(Bson filter); // Mencari banyak data
}