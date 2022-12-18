package com.example.studentshop.service.ex;

/**
 * Created by Intellij IDEA.
 *
 * @Author haosen
 * Date:  2022/12/15
 */


public class AddressSizeLimitException extends ServiceException{
    public AddressSizeLimitException() {
    }

    public AddressSizeLimitException(String message) {
        super(message);
    }

    public AddressSizeLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressSizeLimitException(Throwable cause) {
        super(cause);
    }

    public AddressSizeLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
