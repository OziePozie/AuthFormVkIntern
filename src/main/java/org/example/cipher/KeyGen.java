package org.example.cipher;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class KeyGen {

    public KeyGen()  {
    }
    static KeyGenerator keyGenerator;

    static {
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static SecretKey secretKey =  keyGenerator.generateKey();

    public static SecretKey getSecretKey() {
        return secretKey;
    }

    public void KeyGen() {
    }
    public static String encrypt(String plainText, SecretKey secretKey)
            throws Exception {
        Cipher cipher =Cipher.getInstance("AES");
        byte[] plainTextByte = plainText.getBytes();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedByte = cipher.doFinal(plainTextByte);
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(encryptedByte);
    }

    public static String decrypt(String encryptedText, SecretKey secretKey)
            throws Exception {
        Cipher cipher =Cipher.getInstance("AES");
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encryptedTextByte = decoder.decode(encryptedText);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
        return new String(decryptedByte);
    }
}
