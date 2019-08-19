package com.kotenkov.security;

import java.io.UnsupportedEncodingException;

public class SimpleCrypto {
    public static String code(String string){

        String s = null;
        try {
            byte [] bytes = string.getBytes("utf8");
            for (int i = 0; i < bytes.length; i++) {
                bytes[i]++;
            }
            s = new String(bytes,"utf8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static String decode(String string){
        StringBuilder s = new StringBuilder();
        try {
            byte [] bytes = string.getBytes("utf8");
            for (int i = 0; i < bytes.length; i++) {
                bytes[i]--;
            }
            s.append(new String(bytes, "utf8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s.toString();
    }
}
