package com.yqj.crypto.asymmetric;

import org.junit.Test;

import java.security.KeyPair;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: SignDemo
 * Author: yaoqijun
 * Date: 2020/9/25 22:52
 */
//数字签名
public class SignDemo {

    @Test
    public void testSign(){
        String input = "Hello World";
        //生成密钥对
        KeyPair keyPair = RSAUtils.generateKeyPair();
        //生成签名
        String sign = RSAUtils.generateSign(input);
        System.out.println(sign);
        //校验签名
        System.out.println(RSAUtils.validSign(input, sign));
    }
}
