package com.yqj.crypto.bytebit;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: ByteBit
 * Author: yaoqijun
 * Date: 2020/9/24 16:48
 */
public class ByteBit {

    public static void main(String[] args) {
        String origin = "Hello World";
        byte[] originBytes = origin.getBytes();
        for (byte originByte : originBytes) {
            //byte
            System.out.println(originByte);
            String binaryString = Integer.toBinaryString(originByte);
            //bit
            System.out.println(binaryString);
        }
    }
}
