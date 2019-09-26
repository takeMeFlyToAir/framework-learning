package com.zzr.spring.mybatis.common;

/**
 * Created by qiuyujiang on 2018/11/10.
 */
public class JsonResp {

    public final static Integer SUCCESS = 0;
    public final static Integer FAIL = 1;

    private Integer code;
    private String message;
    private Object result;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
