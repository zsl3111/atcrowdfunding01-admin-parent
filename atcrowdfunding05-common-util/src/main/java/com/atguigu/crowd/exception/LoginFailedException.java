package com.atguigu.crowd.exception;

/**
 * 自定义异常
 * 登陆失败后抛出的异常
 * 继承runTimeException的好处是抛出异常不用处理，继承Exception的话抛出异常还得处理
 */
public class LoginFailedException  extends RuntimeException{

    public LoginFailedException() {
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailedException(Throwable cause) {
        super(cause);
    }


}
