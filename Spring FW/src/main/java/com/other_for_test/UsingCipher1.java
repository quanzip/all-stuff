package com.other_for_test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UsingCipher1 {
    public static void main(String[] args) {
        java.lang.String value = "Pham hong Quanzip";
        byte[] input = value.getBytes(StandardCharsets.UTF_8);
        try {
//            Key key = getKey("112*555*894*465*454*456*456*657"); // 24 chars
//            Key key1 = getKey("112*555*894*465*454*456*456*658"); // 24 chars
            Key key = getKey1("PhamPhamPhamPham");  // 16 chars
            Key key1 = getKey1("PhamPhamPhamPham"); // 16 chars
            // valid when key has length of : 128/ 192/ 256;
            IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            // AES/EBC/PKCS5PADDING"  then no need to use [iv] as 3rd in [init] method, but not safe
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);

            byte[] res = cipher.doFinal(input);

            Cipher cipher1 = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            // AES/EBC/PKCS5PADDING"  then no need to use [iv] as 3rd in [init] method, but not safe
            cipher1.init(Cipher.DECRYPT_MODE, key1, iv);

            byte[] res1 = cipher1.doFinal(res);

            System.out.println(Arrays.equals(input, res1));

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }

    private static Key getKey(java.lang.String token) {
        StringTokenizer st = new StringTokenizer(token, "*", false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(5);

        while (st.hasMoreElements()) {
            byte[] value = st.nextToken().getBytes(StandardCharsets.UTF_8);
            try {
                byteArrayOutputStream.write(value);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        byte[] b = byteArrayOutputStream.toByteArray();
        return new SecretKeySpec(b, "AES");
    }

    private static Key getKey1(java.lang.String token) {
        byte[] b = token.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(b, "AES");
    }

}