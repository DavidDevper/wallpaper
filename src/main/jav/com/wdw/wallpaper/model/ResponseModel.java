package com.wdw.wallpaper.model;

import java.io.Serializable;

public class ResponseModel<T> implements Serializable {
    private int code = 200;
    private String msg;

    private Object data;
    public ResponseModel() {}

    public ResponseModel(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseModel(int code, String msg,Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
