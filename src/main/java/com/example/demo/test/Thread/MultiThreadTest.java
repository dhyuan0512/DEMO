package com.example.demo.test.Thread;


import com.example.demo.bean.WechatUser;
import com.example.demo.mapper.UserBeanMapper;
import com.example.demo.serviceimpl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class MultiThreadTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserBeanMapper userBeanMapper;

    @Autowired
    private MoreThreadTest moreThreadTest;


    public void messageThread() throws Exception {
        long start = System.currentTimeMillis();
        List<WechatUser> idList = moreThreadTest.getMaxResult();
       // List<WechatUser> idList = userService.page();
        int threadNum = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        int perSize = idList.size() / threadNum;
        log.info("多线程Insert.SQL处理数据开始,数据总数{}条,线程数量{}个\r\n", idList.size(), threadNum);
        AtomicInteger successNum = new AtomicInteger(0);
        MultiThread thread = new MultiThread();
        for (int i = 0; i < threadNum; i++) {
            if (i == threadNum - 1) {
                thread.setIdList(idList.subList(i * perSize, idList.size()));
            } else {
                thread.setIdList(idList.subList(i * perSize, (i + 1) * perSize));
            }
            thread.setCountDownLatch(countDownLatch);
            thread.setSuccessNum(successNum);
            thread.setUserMapper(userBeanMapper);
            executorService.submit(thread);
        }
        countDownLatch.await();
        executorService.shutdown();
        long end = System.currentTimeMillis();
        log.info("线程池关闭,运行时长{}毫秒,处理数据{}条", (end - start), successNum);
    }
}

@Slf4j
class MultiThread implements Runnable {

     private List<WechatUser> idList;

//    private List<UserBean> idList;

    private CountDownLatch countDownLatch;

    private AtomicInteger successNum;

    private UserBeanMapper userMapper;

    public void setUserMapper(UserBeanMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void setIdList(List<WechatUser> idList) {
        this.idList = idList;
    }
//
//    public void setIdList(List<UserBean> idList) {
//        this.idList = idList;
//    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void setSuccessNum(AtomicInteger successNum) {
        this.successNum = successNum;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        try {
            //当前线程处理成功人数
            int numberSuccess = 0;
            for (WechatUser wechatUser : idList) {
                String nickName = wechatUser.getNickName();
                String imgUrl = wechatUser.getHeadImgUrl();
                userMapper.insertInfo(nickName, imgUrl);
                numberSuccess++;
            }
//            for (UserBean userBean : idList) {
//                userMapper.insertInfo(Thread.currentThread().getName(), userBean.getPassword());
//                numberSuccess++;
//            }
            int success = successNum.addAndGet(numberSuccess);
            log.info("当前已处理成功:{}条数据，线程名字：{}", success, Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (countDownLatch != null) {
                long end = System.currentTimeMillis();
                log.info("当前线程运行消耗时间：{}毫秒,线程名字：{}\r\n", (end - start), Thread.currentThread().getName());
                countDownLatch.countDown();
            }
        }
    }
}
