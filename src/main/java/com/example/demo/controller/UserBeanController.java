package com.example.demo.controller;

import com.example.demo.bean.UserBean;
import com.example.demo.bean.WechatUser;
import com.example.demo.service.UserBeanService;
import com.example.demo.serviceimpl.UserServiceImpl;
import com.example.demo.test.Thread.MoreThreadTest;
import com.example.demo.test.Thread.MultiThreadTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
public class UserBeanController {

    //将Service注入Web层
    @Autowired
    private UserBeanService userBeanServiceImpl;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MultiThreadTest multiThreadTest;

    @Autowired
    private MoreThreadTest moreThreadTest;

    @RequestMapping("/index")
    public String hello() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/loginIn", method = RequestMethod.POST)
    public String login(String name, String password) {
        UserBean userBean = userBeanServiceImpl.loginIn(name, password);
        if (userBean != null) {
            return "success";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String getString(String name, String password) {
        List<UserBean> user = userBeanServiceImpl.selectInfo(name);
        if (user.size() != 0) {
            return "RegisterError";
        }
        int userBean = userBeanServiceImpl.register(name, password);
        if (userBean == 1) {
            return "RegisterSuccess";
        } else {
            return "RegisterError";
        }
    }

    @RequestMapping(value = "/setThread", method = RequestMethod.POST)
    @ResponseBody
    public void setMessage() throws Exception {
        multiThreadTest.messageThread();
    }

    @RequestMapping(value = "/getThread", method = RequestMethod.POST)
    @ResponseBody
    public void getMessage() throws Exception {
        moreThreadTest.getMaxResult();
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public List<WechatUser> get() throws Exception {
        long start = System.currentTimeMillis();
        List<WechatUser> page = userService.page();
        long end = System.currentTimeMillis();
        log.info("单线程获取数据{}条,处理时间{}ms",page.size(),(end-start));
        return page ;
    }
}


