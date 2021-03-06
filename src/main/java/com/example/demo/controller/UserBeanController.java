package com.example.demo.controller;

import com.example.demo.bean.UserBean;
import com.example.demo.bean.WechatUser;
import com.example.demo.service.UserBeanService;
import com.example.demo.serviceimpl.UserServiceImpl;
import com.example.demo.test.Thread.MoreThreadTest;
import com.example.demo.test.Thread.MultiThreadTest;
import com.example.demo.util.SessionUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
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

    @GetMapping("/index")
    public String hello() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/loginIn")
    public String login(String name, String password, HttpServletRequest request) {
        UserBean userBean = userBeanServiceImpl.loginIn(name, password);
        if (userBean != null) {
            //添加登录用户的Session
            //log.info("sessionId：{}, sessionValue：{}",request.getSession().getId(),request.getSession()0);
            request.getSession().setAttribute(SessionUtils.USER_SESSION_KEY, userBean);
            return "success";
        } else {
            return "error";
        }
    }

    @PostMapping("/register")
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

    @GetMapping("/setThread")
    @ResponseBody
    @ApiOperation("多线程存入数据")
    public void setMessage() throws Exception {
        multiThreadTest.messageThread();
    }

    @GetMapping("/getThread")
    @ResponseBody
    @ApiOperation(value = "多线程获取数据")
    public List<WechatUser> getMessage() throws Exception {
        List<WechatUser> maxResult = moreThreadTest.getMaxResult();
        return maxResult;
    }

    @GetMapping("/get")
    @ResponseBody
    public List<WechatUser> get() throws Exception {
        long start = System.currentTimeMillis();
        List<WechatUser> page = userService.page();
        long end = System.currentTimeMillis();
        log.info("单线程获取数据{}条,处理时间{}ms", page.size(), (end - start));
        return page;
    }

    @GetMapping("/get/image")
    @ResponseBody
    public List<WechatUser> getImage(@RequestParam Integer pageSize) throws Exception {
        long start = System.currentTimeMillis();
        List<WechatUser> list = userService.imageAll(pageSize);
        list.removeAll(Collections.singleton(null));
        long end = System.currentTimeMillis();
        log.info("单线程获取数据{}条,处理时间{}ms", list.size(), (end - start));
        return list;
    }

}


