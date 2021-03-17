package com.example.demo.serviceimpl;

import com.example.demo.bean.UserBean;
import com.example.demo.bean.WechatUser;
import com.example.demo.mapper.UserBeanMapper;
import com.example.demo.mapper.WechatUserMapper;
import com.example.demo.service.UserBeanService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserBeanService {

    //将DAO注入Service层
    @Autowired
    private WechatUserMapper wechatUserMapper;

    @Autowired
    private UserBeanMapper userBeanMapper;

    @Autowired
    AmqpTemplate amqpTemplate;

    @Override
    public UserBean loginIn(String name, String password) {
        return userBeanMapper.getInfo(name, password);
    }

    @Override
    public int register(String name, String password) {
        return userBeanMapper.insertInfo(name, password);
    }

    @Override
    public List<UserBean> selectInfo(String name) {
        return userBeanMapper.selectInfo(name);
    }

    @Override
    public List<WechatUser> page() {
        amqpTemplate.convertAndSend("调用接口队列", "调用接口队列");
        return wechatUserMapper.page();
    }

    @Override
    public List<WechatUser> imageAll(Integer pageSize) {
        return wechatUserMapper.selectImage(pageSize);
    }

    @Override
    public List<WechatUser> pages(int bindex, int num) {
        return wechatUserMapper.pages(bindex, num);
    }

    @Override
    public int insert(UserBean record) {
        return userBeanMapper.insert(record);
    }

    @Override
    public List<UserBean> pageUsers(int bindex, int num) {
        return userBeanMapper.pageUsers(bindex, num);
    }

}
