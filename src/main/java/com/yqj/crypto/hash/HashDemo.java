package com.yqj.crypto.hash;

import org.junit.Test;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: HashDemo
 * Author: yaoqijun
 * Date: 2020/9/25 18:31
 */
//哈希函数测试
public class HashDemo {

    //哈希算法测试字符串
    @Test
    public void testHashByStr(){
        String input = "你好";
        System.out.println(HashUtils.getHashByStr(input,"MD5"));
        System.out.println(HashUtils.getHashByStr(input,"SHA-1"));
        System.out.println(HashUtils.getHashByStr(input,"SHA-256"));
        System.out.println(HashUtils.getHashByStr(input,"SHA-512"));
    }

    //哈希算法测试输入文件
    @Test
    public void testHashByFile(){
        String fileName = ".\\hello.txt";
        System.out.println(HashUtils.getHashByFile(fileName,"MD5"));
    }


}
