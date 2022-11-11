package ru.kpfu.itis.lovchitskiy.exceptions;

public class UserUniqueException extends RuntimeException{
    public UserUniqueException() {
        super();
    }

    public UserUniqueException(String message) {
        super(message);
    }

    public UserUniqueException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserUniqueException(Throwable cause) {
        super(cause);
    }

    protected UserUniqueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
