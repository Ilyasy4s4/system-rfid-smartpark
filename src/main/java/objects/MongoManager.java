/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author LENOVO
 */

package objects;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients; // Import yang benar
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class MongoManager {
    
    private static MongoClient mongoClient;
    private static final String DATABASE_NAME = "SmartPark_db";
    
    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            // PERBAIKAN 1: Gunakan 'CodecRegistries' (C Besar) bukan huruf kecil
            CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
                    MongoClientSettings.getDefaultCodecRegistry(),
                    CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
            );
            
            // PERBAIKAN 2: Gunakan 'MongoClients.create' (pakai S) 
            // Dan sebaiknya masukkan pengaturan koneksi ke dalam settings
            mongoClient = MongoClients.create("mongodb://localhost:27017");
            
            // PERBAIKAN 3: Pastikan database dipanggil dengan registry yang baru dibuat
            return mongoClient.getDatabase(DATABASE_NAME).withCodecRegistry(pojoCodecRegistry);
        }
        return mongoClient.getDatabase(DATABASE_NAME);
    }
}