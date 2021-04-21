package com.example.demo.test.Rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
//@RabbitListener(queues = "work") //注解在类上时，只能有一个方法需要加@RabbitHandler注解
public class MqService {

    @RabbitListener(queues = "work")
    public void work1(String text) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("消费者收到消息1:+++++" + text);
    }

    @RabbitListener(queues = "work")
    public void work2(String text) {
        System.out.println("消费者收到消息2:-----" + text);
    }

    //****************************************************************

    @RabbitListener(queues = "publish_queue1")
    public void publish1(String text) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("消费者收到消息1:+++++" + text);
    }

    @RabbitListener(queues = "publish_queue2")
    public void publish2(String text) {
        System.out.println("消费者收到消息2:-----" + text);
    }

    //****************************************************************

    @RabbitListener(queues = "routing_queue1")
    public void routing1(String text) {
        System.out.println("消费者收到消息1:+++++" + text);
    }

    @RabbitListener(queues = "routing_queue2")
    public void routing2(String text) {
        System.out.println("消费者收到消息2:-----" + text);
    }

    //****************************************************************

    @RabbitListener(queues = "topic_queue1")
    public void topic1(String text) {
        System.out.println("消费者收到消息1:+++++" + text);
    }

    @RabbitListener(queues = "topic_queue2")
    public void topic2(String text) {
        System.out.println("消费者收到消息2:-----" + text);
    }

    //****************************************************************

    @RabbitListener(queues = "delayed_queue")
    public void delayed(String text) {
        System.out.println("接收时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        System.out.println("消费者收到消息1:+++++" + text);
    }
}