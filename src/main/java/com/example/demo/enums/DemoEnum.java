package com.example.demo.enums;

public enum DemoEnum implements FastEnum {
    //成功处理
    SUCCESS("200","消息处理成功"),
    // 资源未找到
    NOT_FOUND("404", "请求找不到"),
    // 服务器错误
    EXCEPTION("500","系统繁忙,请稍后重试");
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
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return String.format("DemoEnum name = %s code = %s message = %s", this.name(), this.code, this.message);
    }
}
