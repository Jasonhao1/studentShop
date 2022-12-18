package com.example.studentshop.service.ex;

/**
 * Created by Intellij IDEA.
 *
 * @Author haosen
 * Date:  2022/12/13
 */


public class UpdateException extends ServiceException{
    public UpdateException() {
    }

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }

    public UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
