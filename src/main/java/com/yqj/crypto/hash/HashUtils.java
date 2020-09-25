package com.yqj.crypto.hash;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: HashUtils
 * Author: yaoqijun
 * Date: 2020/9/25 18:32
 */
//哈希函数工具类（utf-8）
public class HashUtils {
    /**
     * 字符串哈希算法(MD5,SHA-1,SHA-256,SHA-512)
     * @param input 明文
     * @param algorithm 哈希算法
     * @return 哈希值
     */
    public static String getHashByStr(String input,String algorithm) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] encryptCode = digest.digest(input.getBytes());
            return toHex(encryptCode);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 文件哈希算法
     * @param filename 文件路径
     * @param algorithm 算法
     * @return 哈希值
     */
    public static String getHashByFile(String filename,String algorithm){
        try{
            FileInputStream fis = new FileInputStream(filename);
            byte[] buffer = new byte[1024];
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            int numRead;
            do {
                numRead = fis.read(buffer);
                if (numRead > 0) {
                    digest.update(buffer, 0, numRead);
                }
            } while (numRead != -1);

            fis.close();
            return toHex(digest.digest());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * byte数组转为16进制字符串
     * @param code 字节数组
     * @return 16进制字符串
     */
    private static String toHex(byte[] code){
        StringBuilder sb = new StringBuilder();
        for (byte b : code) {
            String hexString = Integer.toHexString(b & 0xff);
            if (hexString.length() == 1){
                hexString = "0"+hexString;
            }
            sb.append(hexString);
        }
        return sb.toString();
    }
}
