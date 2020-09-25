package com.yqj.crypto.symmetric;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: DesDemo
 * Author: yaoqijun
 * Date: 2020/9/24 18:10
 */
//DES加解密
public class DesDemo {

    //DES加解密，默认的加密模式为EBC，填充模式为PKCS5Padding
    @Test
    public void desEncryptAndDecrypt() throws Exception{
        String input = "Hello World";
        //密钥(必须是8个字节)
        String key = "12345678";
        //算法
        String transformation = "DES";
        //加密类型
        String algorithm = "DES";
        //加密
        String encryptDES = encrypt(input, key, transformation, algorithm);
        System.out.println(encryptDES);
        //解密
        String decryptDES = decrypt(encryptDES,key,transformation,algorithm);
        System.out.println(decryptDES);

    }

    //DES加解密，使用CBC的加密模式，需要额外的输入向量
    @Test
    public void desEncryptAndDecryptMode() throws Exception{
        String input = "Hello World";
        //密钥(必须是8个字节)
        String key = "12345678";
        //算法
        String transformation = "DES/CBC/PKCS5Padding";
        //加密类型
        String algorithm = "DES";
        //加密
        String encryptDES = encryptMode(input, key, transformation, algorithm);
        System.out.println(encryptDES);
        //解密
        String decryptDES = decryptMode(encryptDES,key,transformation,algorithm);
        System.out.println(decryptDES);
    }

    /**
     * DES解密
     * @param encryptDES 密文
     * @param key 密钥
     * @param transformation 算法
     * @param algorithm 解密类型
     * @return 明文
     * @throws Exception
     */
    private static String decrypt(String encryptDES, String key, String transformation, String algorithm) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
        byte[] bytes = cipher.doFinal(Base64.decode(encryptDES));
        return new String(bytes);
    }

    /**
     * DES加密
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


    private static String decryptMode(String encryptDES, String key, String transformation, String algorithm) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        //创建iv向量
        IvParameterSpec iv = new IvParameterSpec(key.getBytes());
        cipher.init(Cipher.DECRYPT_MODE,secretKeySpec,iv);
        byte[] bytes = cipher.doFinal(Base64.decode(encryptDES));
        return new String(bytes);
    }

    public static String encryptMode(String input,String key,String transformation,String algorithm) throws Exception{
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec,iv);
        byte[] bytes = cipher.doFinal(input.getBytes());
        return Base64.encode(bytes);
    }
}
