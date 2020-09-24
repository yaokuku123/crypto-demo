package com.yqj.crypto.symmetric;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: DesDemo
 * Author: yaoqijun
 * Date: 2020/9/24 18:10
 */
//AES加解密
public class AesDemo {
    public static void main(String[] args) throws Exception{
        String input = "Hello World";
        //密钥(必须是16个字节)
        String key = "1234567812345678";
        //算法
        String transformation = "AES";
        //加密类型
        String algorithm = "AES";

        //加密
        String encryptAES = encrypt(input, key, transformation, algorithm);
        System.out.println(encryptAES);

        //解密
        String decryptAES = decrypt(encryptAES,key,transformation,algorithm);
        System.out.println(decryptAES);

    }

    /**
     * AES解密
     * @param encryptAES 密文
     * @param key 密钥
     * @param transformation 算法
     * @param algorithm 解密类型
     * @return 明文
     * @throws Exception
     */
    private static String decrypt(String encryptAES, String key, String transformation, String algorithm) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
        byte[] bytes = cipher.doFinal(Base64.decode(encryptAES));
        return new String(bytes);
    }

    /**
     * AES加密
     * @param input 明文
     * @param key 密钥
     * @param transformation 算法
     * @param algorithm 加密类型
     * @return 密文
     * @throws Exception
     */
    public static String encrypt(String input,String key,String transformation,String algorithm) throws Exception{
        //创建加密对象
        Cipher cipher = Cipher.getInstance(transformation);
        //创建加密规则
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        //加密初始化
        cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);
        //调用加密方法
        byte[] bytes = cipher.doFinal(input.getBytes());
        //乱码
        //System.out.println(new String(bytes));
        //base64转码（由于存在负数，超出ASCii码表示范围出现乱码）
        return Base64.encode(bytes);
    }
}
