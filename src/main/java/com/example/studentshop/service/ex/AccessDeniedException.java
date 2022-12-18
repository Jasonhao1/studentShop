package com.example.studentshop.service.ex;

/**
 * Created by Intellij IDEA.
 *
 * @Author hao-sen
 * Date:  2022/12/16
 */


public class AccessDeniedException extends ServiceException{
    public AccessDeniedException() {
    }

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessDeniedException(Throwable cause) {
        super(cause);
    }

    public AccessDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
