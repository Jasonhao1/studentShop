package com.example.studentshop.service.ex;

/**
 * Created by Intellij IDEA.
 *
 * @Author haosen
 * Date:  2022/12/14
 */


public class FileUploadIOException extends FileUploadException{
    public FileUploadIOException() {
    }

    public FileUploadIOException(String message) {
        super(message);
    }

    public FileUploadIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadIOException(Throwable cause) {
        super(cause);
    }

    public FileUploadIOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
