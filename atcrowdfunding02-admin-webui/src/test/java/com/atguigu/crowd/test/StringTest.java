package com.atguigu.crowd.test;


import com.atguigu.crowd.util.CrowdUtil;

public class StringTest {
    public static void main(String[] args) {
        String source = "1";
        String s = CrowdUtil.md5(source);
        System.out.println(s);
    }
}
