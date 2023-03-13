package com.ey.ejercicio.utils;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class EncryptionUtils {

    /**
     * Encripta una contraseña utilizando Jasypt
     * @param password La contraseña a encriptar.
     * @return La contraseña encriptada.
     */
    public static String encrypt(String password) {
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        return encryptor.encryptPassword(password);
    }

    /**
     * Verifica si una contraseña en texto plano coincide con una contraseña encriptada.
     * @param plainPassword La contraseña en texto plano.
     * @param encryptedPassword La contraseña encriptada.
     * @return Verdadero si la contraseña en texto plano coincide con la contraseña encriptada.
     */
    public static boolean checkPassword(String plainPassword, String encryptedPassword) {
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        return encryptor.checkPassword(plainPassword, encryptedPassword);
    }


}