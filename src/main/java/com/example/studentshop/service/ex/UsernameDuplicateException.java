package com.example.studentshop.service.ex;

/**
 * Created by Intellij IDEA.
 * User:  haosen
 * Date:  2022/12/12
 */

/**
 * 用户名被占用的异常
 */
public class UsernameDuplicateException extends ServiceException{
    public UsernameDuplicateException() {
    }

    public UsernameDuplicateException(String message) {
        super(message);
    }

    public UsernameDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameDuplicateException(Throwable cause) {
        super(cause);
    }

    public UsernameDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
