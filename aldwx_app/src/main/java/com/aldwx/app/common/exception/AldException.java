package com.aldwx.app.common.exception;

/**
 * 异常
 */
public class AldException extends RuntimeException {


    public AldException() {
        super();
    }

    public AldException(String message) {
        super(message);
    }


    public AldException(String message, Throwable cause) {
        super(message, cause);
    }


    public AldException(Throwable cause) {
        super(cause);
    }


    protected AldException(String message, Throwable cause,
                           boolean enableSuppression,
                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
