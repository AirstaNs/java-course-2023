package edu.hw8.task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("magicnumber")
public final class HashUtils {

    private static final ThreadLocal<MessageDigest> MESSAGE_DIGEST_THREAD_LOCAL = ThreadLocal.withInitial(() -> {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Could not create MessageDigest instance", e);
        }
    });

    private HashUtils() {
    }

    public static byte[] hashPassword(String password) {
        MessageDigest md = MESSAGE_DIGEST_THREAD_LOCAL.get();
        md.reset();
        return md.digest(password.getBytes());
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                  + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
