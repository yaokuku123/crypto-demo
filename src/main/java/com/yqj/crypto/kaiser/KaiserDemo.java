package com.yqj.crypto.kaiser;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: KeiserDemo
 * Author: yaoqijun
 * Date: 2020/9/24 15:45
 */
//凯撒加密
public class KaiserDemo {
    public static void main(String[] args) {
        //原文
        String origin = "Hello World";
        //移位位数
        int key = 3;
        //加密
        String encryptStr = encrypt(origin, key);
        System.out.println(encryptStr);
        //解密
        String decryptStr = decrypt(encryptStr,key);
        System.out.println(decryptStr);
    }

    /**
     * 凯撒解密
     * @param encryptStr 密文
     * @param key 密钥
     * @return 明文
     */
    public static String decrypt(String encryptStr, int key) {
        char[] encryptChars = encryptStr.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char encryptChar : encryptChars) {
            int encryptCharAscii = encryptChar;
            encryptCharAscii -= key;
            sb.append((char) encryptCharAscii);
        }
        return sb.toString();
    }

    /**
     * 凯撒加密
     * @param origin 明文
     * @param key 密钥
     * @return 密文
     */
    public static String encrypt(String origin,int key){
        //转换为字符数组
        char[] originChars = origin.toCharArray();
        //循环遍历后，移动ascii编码
        StringBuilder sb = new StringBuilder();
        for (char originChar : originChars) {
            int originCharAscii = originChar;
            originCharAscii += key;
            sb.append((char)originCharAscii);
        }
        return sb.toString();
    }
}
