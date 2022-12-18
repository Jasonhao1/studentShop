package com.example.studentshop.service.ex;

/**
 * Created by Intellij IDEA.
 * User:  haosen
 * Date:  2022/12/12
 */

/**
 * 插入数据异常
 */
public class InsertException extends ServiceException{
    public InsertException() {
    }

    public InsertException(String message) {
        super(message);
    }

    public InsertException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertException(Throwable cause) {
        super(cause);
    }

    public InsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
