package com.zzr.framework.common.util.provider;

/**
 * Created by zhaozhirong on 2019/8/9.
 */
public class ZzrException extends Exception {

    public ZzrException() {
        super();
    }

    public ZzrException(String message) {
        super(message);
    }

    public ZzrException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZzrException(Throwable cause) {
        super(cause);
    }

    public ZzrException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
