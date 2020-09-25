package com.yqj.crypto.asymmetric;

import org.junit.Test;

import java.security.KeyPair;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: RSADemo
 * Author: yaoqijun
 * Date: 2020/9/25 21:26
 */
//RSA非对称加密
public class RSADemo {

    @Test
    public void testRSA(){
        String input = "Hello World";
        //生成密钥对
        KeyPair keyPair = RSAUtils.generateKeyPair();
        //私钥加密
        String encryptCode = RSAUtils.encryptByPublicKey(input);
        System.out.println(encryptCode);
        //公钥解密
        String decryptCode = RSAUtils.decryptByPrivateKey(encryptCode);
        System.out.println(decryptCode);
    }
}
