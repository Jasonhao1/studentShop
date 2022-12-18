package com.example.studentshop.util;



/**
 * Created by Intellij IDEA.
 * User:  haosen
 * Date:  2022/12/13
 */

/**
 * 用于向客户端响应结果的类型：状态，错误提示，数据
 * @param <T> 操作结果中的数据类型
 */
public class JsonResult<T> {
    private Integer state;
    private String message;
    private T data;

    public JsonResult(Integer state, String message) {
        this.state = state;
        this.message = message;
    }

    public JsonResult(Integer state, T data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public JsonResult() {
    }

    public JsonResult(T data) {
        this.data = data;
    }

    public JsonResult(String message) {
        this.message = message;
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Integer state, String message, T data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "state=" + state +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
}
