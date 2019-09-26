package com.zzr.framework.common.util.provider;

/**
 * Created by zhaozhirong on 2019/8/9.
 */
public class ZzrRunException extends RuntimeException {

    public ZzrRunException() {
        super();
    }

    public ZzrRunException(String message) {
        super(message);
    }

    public ZzrRunException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZzrRunException(Throwable cause) {
        super(cause);
    }

    public ZzrRunException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
