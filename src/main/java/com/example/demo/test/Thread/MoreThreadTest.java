package com.example.demo.test.Thread;

import com.example.demo.bean.WechatUser;
import com.example.demo.mapper.WechatUserMapper;
import com.example.demo.serviceimpl.UserServiceImpl;
import com.example.demo.vo.DemoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
@Service
@Slf4j
public class MoreThreadTest {
    @Autowired
    private UserServiceImpl myService;

    @Autowired
    private WechatUserMapper WechatUserMapper;

    public DemoResult<List<WechatUser>> getMaxResult() throws Exception {

        long start = System.currentTimeMillis();
        List<WechatUser> result = new ArrayList<>();//返回结果
        int count = WechatUserMapper.conunt();//通过count查到数据总量
        int num = 1000;//每次查询的条数
        //需要查询的次数
        int times = count / num;
        if (count != 0) {
            times = times + 1;
        }
        //开始查询的行数
        int bindex = 0;

        List<Callable<List<WechatUser>>> tasks = new ArrayList<Callable<List<WechatUser>>>();//添加任务
        Callable<List<WechatUser>> qfe = new ThredQuery(myService,bindex, num);
        for (int i = 0; i < times; i++) {
            tasks.add(qfe);
            bindex = bindex + num;
        }
        //定义固定长度的线程池  防止线程过多
        int ThreadNum=10;
        ExecutorService execservice = Executors.newFixedThreadPool(ThreadNum);
        List<Future<List<WechatUser>>> futures = execservice.invokeAll(tasks);
        // 处理线程返回结果
        if (futures != null && futures.size() > 0) {
            for (Future<List<WechatUser>> future : futures) {
                result.addAll(future.get());
            }
        }
        execservice.shutdown();  // 关闭线程池
        long end = System.currentTimeMillis();
        log.info("多线程Get.SQL获取数据结束，{}个线程获取数据{}条,处理数据过程时长{}毫秒\r\n",ThreadNum,count,(end-start));
        return DemoResult.success(result);
    }
}