package edu.hw8.task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public final class HashUtils {

    private HashUtils() {
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashInBytes = md.digest(password.getBytes());
            return HexFormat.of().formatHex(hashInBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error computing hash", e);
        }
    }
}
