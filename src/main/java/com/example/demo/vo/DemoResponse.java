package com.example.demo.vo;

import com.alibaba.fastjson.JSON;
import com.example.demo.enums.DemoEnum;

import java.io.Serializable;

public class DemoResponse<E> implements Serializable {
    private static final long serialVersionUID = 7010064721473780599L;
    private String code;
    private String message;
    private String subMessage;
    private E result;

    public DemoResponse() {
    }

    public DemoResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public DemoResponse(String code, String message, String subMessage) {
        this.code = code;
        this.message = message;
        this.subMessage = subMessage;
    }

    public DemoResponse(String code, String message, E result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public DemoResponse(String code, String message, String subMessage, E result) {
        this.code = code;
        this.message = message;
        this.subMessage = subMessage;
        this.result = result;
    }

    public boolean isSuccess() {
        return DemoEnum.SUCCESS.getCode().equals(this.code);
    }

    public boolean fail() {
        return !this.isSuccess();
    }

    public String toString() {
        return "FastResponse [code=" + this.code + ", message=" + this.message + ", subMessage=" + this.subMessage + ", result=" + JSON.toJSONString(this.result) + "]";
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getSubMessage() {
        return this.subMessage;
    }

    public E getResult() {
        return this.result;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSubMessage(String subMessage) {
        this.subMessage = subMessage;
    }

    public void setResult(E result) {
        this.result = result;
    }
}
