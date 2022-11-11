package ru.kpfu.itis.lovchitskiy.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MyEncoder {

    private static final String SALT = "1234321";

    public static String encryptPassword(String password) {
        return DigestUtils.md5Hex(password + SALT);
    }

    public static void main(String[] args) {
        System.out.println(encryptPassword("Xre123!%$dg"));
    }
}
