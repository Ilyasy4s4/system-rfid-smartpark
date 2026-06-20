package id.smartpark.util;

import id.smartpark.services.AuthService;

public class UserInjector {

    public static void main(String[] args) {

        AuthService auth = new AuthService();

        auth.registerUser(
                "Administrator",
                "admin",
                "admin123"
        );

        System.out.println("Admin berhasil ditambahkan!");
    }
}