/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package id.smartpark.dao;

import id.smartpark.dao.BaseDAO;
import com.mongodb.client.MongoCollection;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import java.util.List;
import id.smartpark.util.MongoManager;

public class GenericDAO<T> implements BaseDAO<T> {
    // Constructor untuk inisialisasi nama koleksi dan koneksi MongoDB via MongoManager
    private final MongoCollection<T> collection;
    private final Class<T> clazz;

    public GenericDAO(String collectionName, Class<T> clazz) {
        this.clazz = clazz;
        // Mengambil koneksi langsung dari MongoManager kamu
        this.collection = MongoManager.getDatabase().getCollection(collectionName, clazz);
    }

    @Override
    // Menyimpan satu dokumen/objek baru ke dalam koleksi MongoDB
    public void save(T entity) {
        collection.insertOne(entity);
    }

    @Override
    // Memperbarui dokumen lama dengan data baru berdasarkan kriteria filter
    public void update(Bson filter, T entity) {
        collection.replaceOne(filter, entity);
    }

    @Override
    // Menghapus satu dokumen dari database berdasarkan kriteria filter
    public void delete(Bson filter) {
        collection.deleteOne(filter);
    }

    @Override
    // Mengambil semua dokumen yang ada di dalam koleksi tanpa filter
    public List<T> findAll() {
        return collection.find().into(new ArrayList<>());
    }

    @Override
    // Mencari dan mengambil satu dokumen pertama yang cocok dengan filter
    public T findOne(Bson filter) {
        return collection.find(filter).first();
    }

    @Override
    // Mencari dan mengambil banyak dokumen sekaligus yang cocok dengan filter
    public List<T> findMany(Bson filter) {
        return collection.find(filter).into(new ArrayList<>());
    }
}