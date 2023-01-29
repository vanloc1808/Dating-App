package com.example.datingapp.Utils;

// http://www.java2s.com/example/android/java.lang/sha256-hash-string.html

import android.os.Message;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
    private static final String LOG_TAG = "";
    public static String sha256(String str) {
        byte[] hash = null;
        String hashCode = null;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            hash = messageDigest.digest(str.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            Log.wtf(LOG_TAG, "Can't calculate SHA-256");
        }

        if (hash != null) {
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(hash[i]);

                if (hex.length() == 1) {
                    stringBuilder.append("0");
                    stringBuilder.append(hex.charAt(hex.length() - 1));
                } else {
                    stringBuilder.append(hex.substring(hex.length() - 2));
                }
            }

            hashCode = stringBuilder.toString();
        }

        return hashCode;
    }
}
