package com.codegym.casetemplate.utils;

public class SecurityUtils {
    public static String caesarEncode(String plainText, int shift) {
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char ch = (char) (plainText.charAt(i) + shift);
            if (ch > 'z') {
                cipherText.append((char) (plainText.charAt(i) - (26 - shift)));
            } else {
                cipherText.append(ch);
            }
        }
        return cipherText.toString();
    }

    public static String caesarDecode(String cipherText, int shift) {
        StringBuilder plainText = new StringBuilder();
        for (int i = 0; i < cipherText.length(); i++) {
            char ch = (char) (cipherText.charAt(i) - shift);
            if (ch < 'a') {
                plainText.append((char) (cipherText.charAt(i) + (26 - shift)));
            } else {
                plainText.append(ch);
            }
        }
        return plainText.toString();
    }





}
