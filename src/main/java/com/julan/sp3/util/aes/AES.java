package com.julan.sp3.util.aes;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;

public class AES {
    //加密
    public static String encrypt(String sSrc, String secretKey) throws Exception {
        byte[] raw = secretKey.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes(StandardCharsets.UTF_8));
        return new Base64().encodeToString(encrypted);
    }

    //解密
    public static String decrypt(String sSrc, String secretKey) {
        try {
            byte[] raw = secretKey.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] encrypted1 = new Base64().decode(sSrc);
            byte[] original = cipher.doFinal(encrypted1);
            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            return null;
        }
    }

    //测试
    public static void main(String[] args) {
        String decryptString = AES.decrypt("wdlFmbzSLNZBaV5FELc692p2PLhGRxHpiG17Sw3QGVo=", "HuYJ3nTV6VVeryHH");
        System.out.println("解密结果集：" + decryptString);
    }
}

