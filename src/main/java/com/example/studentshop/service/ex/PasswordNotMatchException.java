package com.example.studentshop.service.ex;

/**
 * Created by Intellij IDEA.
 *
 * @Author haosen
 * Date:  2022/12/13
 */


public class PasswordNotMatchException extends ServiceException{
    public PasswordNotMatchException() {
    }

    public PasswordNotMatchException(String message) {
        super(message);
    }

    public PasswordNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNotMatchException(Throwable cause) {
        super(cause);
    }

    public PasswordNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
