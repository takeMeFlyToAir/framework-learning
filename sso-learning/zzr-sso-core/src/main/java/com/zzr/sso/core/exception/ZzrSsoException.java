package com.zzr.sso.core.exception;

/**
 */
public class ZzrSsoException extends RuntimeException {

    private static final long serialVersionUID = 42L;

    public ZzrSsoException(String msg) {
        super(msg);
    }

    public ZzrSsoException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ZzrSsoException(Throwable cause) {
        super(cause);
    }

}
