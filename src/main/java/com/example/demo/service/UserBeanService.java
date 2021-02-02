package com.example.demo.service;

import com.example.demo.bean.UserBean;
import com.example.demo.bean.WechatUser;

import java.util.List;


public interface UserBeanService {

    UserBean loginIn(String name, String password);

    int register(String name, String password);

    List<UserBean> selectInfo(String name);

    List<WechatUser> page();

    List<WechatUser>  imageAll(Integer pageSize);

    List<WechatUser> pages(int bindex, int num);

    int insert(UserBean record);

    List<UserBean> pageUsers(int bindex, int num);

}