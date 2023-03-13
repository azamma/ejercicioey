package com.ey.ejercicio.utils;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class EncryptionUtils {

    public static String encrypt(String password) {
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        return encryptor.encryptPassword(password);
    }

    public static boolean checkPassword(String plainPassword, String encryptedPassword) {
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        return encryptor.checkPassword(plainPassword, encryptedPassword);
    }

}