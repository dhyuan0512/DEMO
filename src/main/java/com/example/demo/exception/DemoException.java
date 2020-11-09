package com.example.demo.exception;

import com.example.demo.enums.FastEnum;

public class DemoException extends RuntimeException {
    private static final long serialVersionUID = 946098281133332679L;
    private String code;
    private String subMessage;

    public DemoException(FastEnum fastEnum) {
        this(fastEnum.getCode(), fastEnum.getMessage());
    }

    public DemoException(String code, String message) {
        super(message);
        this.code = "";
        this.subMessage = "";
        this.code = code;
    }

    public DemoException(String code, String message, String subMessage) {
        super(message);
        this.code = "";
        this.subMessage = "";
        this.code = code;
        this.subMessage = subMessage;
    }

    public DemoException(String code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = "";
        this.subMessage = "";
        this.code = code;
    }

    public DemoException(String code, String message, String subMessage, Throwable throwable) {
        super(message, throwable);
        this.code = "";
        this.subMessage = "";
        this.code = code;
        this.subMessage = subMessage;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSubMessage() {
        return this.subMessage;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DemoException [code=");
        builder.append(this.getCode());
        builder.append(", message=");
        builder.append(this.getMessage());
        builder.append(", subMessage=");
        builder.append(this.getSubMessage());
        builder.append("]");
        return builder.toString();
    }
}
