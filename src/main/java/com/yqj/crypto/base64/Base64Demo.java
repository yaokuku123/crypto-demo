package com.yqj.crypto.base64;

import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: Base64Demo
 * Author: yaoqijun
 * Date: 2020/9/24 19:13
 */
/*
Base64说明
    1.Base64表示共有64个字符，包括 0-9,a-z,A-Z,+,/
    2.Base64原理：以3byte为一组分组，每组24bit。再将这24bit以6bit分成4组。每组6bit的高位补0，刚好可以使用64个字符表示
    3.若不足3byte，则Base64会使用=补齐
 */
public class Base64Demo {
    public static void main(String[] args) {
        //补两个=
        System.out.println(Base64.encode("1".getBytes()));
        //补一个=
        System.out.println(Base64.encode("12".getBytes()));
        //无=
        System.out.println(Base64.encode("123".getBytes()));
        //中文在utf-8编码下，占3个字节
        System.out.println(Base64.encode("你好".getBytes()));
    }
}
