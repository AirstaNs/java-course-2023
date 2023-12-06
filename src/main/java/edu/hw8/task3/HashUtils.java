package edu.hw8.task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public final class HashUtils {

    private static final HexFormat FORMAT = HexFormat.of();

    private static final ThreadLocal<MessageDigest> MESSAGE_DIGEST_THREAD_LOCAL = ThreadLocal.withInitial(() -> {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Could not create MessageDigest instance", e);
        }
    });

    private HashUtils() {
    }

    public static String hashPassword(String password) {
        MessageDigest md = MESSAGE_DIGEST_THREAD_LOCAL.get();
        md.reset();
        byte[] hashInBytes = md.digest(password.getBytes());
        return FORMAT.formatHex(hashInBytes);
    }
}
