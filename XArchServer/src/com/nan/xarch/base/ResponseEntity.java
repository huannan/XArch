package com.nan.xarch.base;

import com.nan.xarch.constant.ResponseCode;

public class ResponseEntity<T> {

    private String code;
    private String msg;
    private String redirect;
    private T value;

    private ResponseEntity() {

    }

    public static <T> ResponseEntity<T> ofSuccess(T value) {
        ResponseEntity<T> entity = new ResponseEntity<>();
        entity.code = ResponseCode.OK;
        entity.msg = "";
        entity.redirect = "";
        entity.value = value;
        return entity;
    }

    public static <T> ResponseEntity<T> ofFailure() {
        ResponseEntity<T> entity = new ResponseEntity<>();
        entity.code = ResponseCode.SERVER_ERROR;
        entity.msg = "服务器发生异常";
        entity.redirect = "";
        entity.value = null;
        return entity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
