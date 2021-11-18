package com.atguigu.crowd.util;

import com.atguigu.crowd.constant.CrowdConstant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 尚筹网项目通用的工具类
 */
public class CrowdUtil {

    /**
     * 判断当前请求是不是Ajax请求
     * @param request request请求对象
     * @return
     *      true：是ajax请求
     *      false：不是ajax请求
     */
    public static boolean judgeRequestType(HttpServletRequest request){

        //1.获取请求消息头
        String acceptHeader = request.getHeader("Accept");
        String xRequestHeader = request.getHeader("X-Requested-With");

        //2.判断
        return (acceptHeader != null && acceptHeader.contains("application/json"))
                ||
                //xRequestHeader只有一个所以直接判断相等
                ("XMLHttpRequest".equals(xRequestHeader));
    }

    /**
     * 将明文字符串进行MD5加密
     * @param source 传入的明文字符串
     * @return 加密的结果
     */
    public static String md5(String source){

        // 1.判断source是否有效
        if(source == null || source.length() == 0){

            // 2.如果不是有效字符则抛出异常
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
        }


        try {
            // 3.获取messageDigest对象，用来加密的对象
            String algorithm = "md5";
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

            // 4.获取明文字符串对应的字节数组
            byte[] bytes = source.getBytes();

            // 5.执行加密(加密后的字符数组存的是数字)
            byte[] digest = messageDigest.digest(bytes);

            // 6.创建bigInteger对象
            // 1代表正数，0代表0，-1代表负数
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum,digest);

            // 7.按照16进制将bigInteger转换成字符串
            int radix = 16;
            String string = bigInteger.toString(radix).toUpperCase();

            return string;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
