/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.smartpark.util;

public class TestD {

    public static void main(String[] args) {

        String pwd = SecurityUtils.getHash(
                "123",
                SecurityUtils.SHA_256
        );

        System.out.println(pwd);
    }
}

