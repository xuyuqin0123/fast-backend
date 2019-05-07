package com.zx.fastbackend.utils;

/**
 * @author xuyuqin
 * @create 2019-04-25 14:25
 **/
public class ResBean {
    private String code;
    private String message;
    private Object content;

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    public ResBean(String code) {
        this.code = code;
    }

    public ResBean(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResBean(String code, String message, Object content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public static ResBean success() {
        return new ResBean(SUCCESS);
    }

    public static ResBean success(String message) {
        return new ResBean(SUCCESS, message);
    }

    public static ResBean success(Object content) {
        return new ResBean(SUCCESS, "", content);
    }

    public static ResBean success(String code, String message, Object content) {
        return new ResBean(code, message, content);
    }


    public static ResBean fail() {
        return new ResBean(FAIL);
    }

    public static ResBean fail(String message) {
        return new ResBean(FAIL, message);
    }

    public static ResBean customRes(String code, String message, Object content) {
        return new ResBean(code, message, content);
    }

}
