package com.other_for_test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class test1 {
    public static void main(String[] args) {
        try {
            KeyGenerator keygen = KeyGenerator.getInstance("DES");
//            keygen.init(56);
            SecretKey desKey = keygen.generateKey();
            byte[] bytes = desKey.getEncoded();

            System.out.println(getString(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }

        getKey();
    }

    private static void getKey() {
        SecretKey s = null;
        String tk = "";
        try {
            java.io.File file = new java.io.File("./conf/keys.properties");
            java.io.FileInputStream fileInputStream = new java.io.FileInputStream(file);
            java.util.Properties properties = new java.util.Properties();
            properties.load(fileInputStream);

            tk = properties.getProperty("ENC_KEY");
            System.out.println(tk);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            sb.append(0x00FF & b);
            if ((i + 1) < bytes.length) {
                sb.append("-");
            }
        }
        return sb.toString();
    }

    public static void main1(String[] args) {
        int[] a = new int[10];
        final int[] x = {100};
        Arrays.setAll(a, operand -> {
            System.out.println(operand);
            if (x[0] == 100) {
                x[0] = 80;
                return 5;
            } else {
                x[0] = 100;
                return 2;
            }
        });
//        Arrays.fill(a, 5);

//        for(int b: a){
//            System.out.println(b);
//        }

        ArrayList<String> list = new ArrayList(Arrays.asList(new Integer[5]));
        Collections.fill(list, "Hello");
        System.out.println(list);
    }

}
