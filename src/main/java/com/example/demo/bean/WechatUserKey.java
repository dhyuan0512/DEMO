package com.example.demo.bean;

import java.io.Serializable;

public class WechatUserKey implements Serializable {
    private String epNum;

    private String openId;

    private static final long serialVersionUID = 1L;

    public String getEpNum() {
        return epNum;
    }

    public void setEpNum(String epNum) {
        this.epNum = epNum == null ? null : epNum.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }
}