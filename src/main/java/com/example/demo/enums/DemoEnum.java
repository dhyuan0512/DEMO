package com.example.demo.enums;

public enum DemoEnum implements FastEnum {
    //成功处理
    SUCCESS("200","消息处理成功"),
    // 资源未找到
    NOT_FOUND("404", "请求找不到"),
    // 服务器错误
    SERVER_ERROR("500","系统繁忙,请稍后重试");
    /**
     * 响应状态码
     */
    private String code;
    /**
     * 响应信息
     */
    private String message;

    DemoEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DemoEnum{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
