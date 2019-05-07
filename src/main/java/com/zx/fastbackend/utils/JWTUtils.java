package com.zx.fastbackend.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.UUID;

/**
 * @author xuyuqin
 * @create 2019-05-07 20:11
 **/
public class JWTUtils {
    public static String generateToken() {
        return UUID.randomUUID().toString();
    }

    public static String encrypt(String password, String salt) {
        return new Md5Hash(password, salt).toBase64();
    }

    public static String generateSalt() {
        return UUID.randomUUID().toString();
    }
}
