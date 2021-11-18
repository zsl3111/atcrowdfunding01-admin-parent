package com.atguigu.crowd.util;

/**
 * 统一整个项目中ajax请求返回的结果（未来也可以用于分布式架构各个模块间调用时返回统一类型）
 * @param <T>
 */
public class ResultEntity<T> {

    private static final String SUCCESS = "SUCCESS";
    private static final String FAILED = "FAILED";

    //用来封装当前请求处理的结果是成功还是失败
    private String result;

    //请求处理失败时返回的错误消息
    private String message;

    //要返回的数据（可以是ajax，也可以是一个模块调另一个模块（分布式架构））
    private T data;

    /**
     * 请求处理成功且不需要返回数据时使用的工具方法
     * 带泛型的方法是泛型方法，前面的<Type>是声明一个泛型
     * @param <Type>
     * @return
     */
    public static <Type> ResultEntity<Type> successWithoutData(){
        return new ResultEntity<Type>(SUCCESS,null,null);
    }

    /**
     * 请求处理成功且需要返回数据时使用的方法
     * @param data 要返回的数据
     * @param <Type> 泛型类型的参数
     * @return
     */
    public static <Type> ResultEntity<Type> successWithData(Type data){
        return new ResultEntity<Type>(SUCCESS,null,data);
    }

    /**
     * 请求处理失败时使用的工具方法
     * @param message 失败的错误消息
     * @param <Type> 泛型类型的参数
     * @return
     */
    public static <Type> ResultEntity<Type> failed(String message){
        return new ResultEntity<Type>(FAILED,message,null);
    }


    @Override
    public String toString() {
        return "ResultEntity{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultEntity() {
    }

    public ResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }
}
