/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author LENOVO
 */

package id.smartpark.util;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients; 
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class MongoManager {
    private static MongoClient mongoClient;
    private static MongoDatabase database; // Simpan database sebagai static variable
    private static final String DATABASE_NAME = "smartPark_db";

    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
                    MongoClientSettings.getDefaultCodecRegistry(),
                    CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
            );

            mongoClient = MongoClients.create("mongodb://localhost:27017");
            // Pasang codec sekali saja di sini
            database = mongoClient.getDatabase(DATABASE_NAME).withCodecRegistry(pojoCodecRegistry);
        }
        return database;
    }
}