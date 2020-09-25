package com.yqj.crypto.asymmetric;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.commons.io.FileUtils;

import javax.crypto.Cipher;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: RSAUtils
 * Author: yaoqijun
 * Date: 2020/9/25 21:26
 */
public class RSAUtils {

    /**
     * 生成密钥对，并存储到文件
     */
    public static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            //获取密钥对
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            //获取公私钥
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();
            //获取Base64编码的公私钥字符串
            String privateKeyString = Base64.encode(privateKey.getEncoded());
            String publicKeyString = Base64.encode(publicKey.getEncoded());
            //存到文件
            FileUtils.writeStringToFile(new File("id_rsa"),privateKeyString, Charset.forName("UTF-8"));
            FileUtils.writeStringToFile(new File("id_rsa.pub"),publicKeyString,Charset.forName("UTF-8"));
            return keyPair;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 公钥加密
     * @param input 明文
     * @return 密文
     */
    public static String encryptByPublicKey(String input) {
        try {
            PublicKey publicKey = getPublicKey();
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE,publicKey);
            byte[] encryptCode = cipher.doFinal(input.getBytes());
            return Base64.encode(encryptCode);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 私钥解密
     * @param encryptCode 密文
     * @return 明文
     */
    public static String decryptByPrivateKey(String encryptCode){
        try {
            PrivateKey privateKey = getPrivateKey();
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE,privateKey);
            byte[] decryptCode = cipher.doFinal(Base64.decode(encryptCode));
            return new String(decryptCode);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 读取文件获取私钥
     * @return 私钥
     */
    private static PrivateKey getPrivateKey() {
        try {
            String privateKeyString = FileUtils.readFileToString(new File("id_rsa"), Charset.defaultCharset());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKeyString));
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            return privateKey;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 读取文件获取公钥
     * @return 公钥
     */
    private static PublicKey getPublicKey() {
        try {
            String publicKeyString = FileUtils.readFileToString(new File("id_rsa.pub"), Charset.defaultCharset());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(publicKeyString));
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
