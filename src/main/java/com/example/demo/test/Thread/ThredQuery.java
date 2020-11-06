package com.example.demo.test.Thread;

import com.alibaba.fastjson.JSON;
import com.example.demo.bean.WechatUser;
import com.example.demo.serviceimpl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.Callable;
@Slf4j
public  class ThredQuery implements Callable<List<WechatUser>> {

    @Autowired
    private UserServiceImpl myService;

    private int bindex;//分页index
    private int num;//数量

    /**
     * 重新构造方法
     *
     * @param myService
     * @param bindex
     * @param num
     */
    public ThredQuery(UserServiceImpl myService, int bindex, int num) {
        this.myService = myService;
        this.bindex = bindex;
        this.num = num;
    }

    @Override
    public List<WechatUser> call() throws Exception {
        long start = System.currentTimeMillis();
        //通过service查询得到对应结果
        List<WechatUser> list = myService.pages(bindex, num);
        for (WechatUser wechatUser : list) {
            log.info("线程{},处理数据{}", Thread.currentThread().getName(),JSON.toJSONString(wechatUser));
        }
        long end = System.currentTimeMillis();
        log.info("线程池中,单线程{}处理数据{}条消耗{}毫秒\r\n",Thread.currentThread().getName(),list.size(),(end-start));
        return list;
    }

}
