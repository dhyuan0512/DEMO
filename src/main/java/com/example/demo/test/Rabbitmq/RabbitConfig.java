package com.example.demo.test.Rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue getQueue() {
        Queue queue = new Queue("orderQueue", true);
        return queue;
    }

    @Bean
    public Queue workQueue() {
        return new Queue("publish_queue1", true);
    }

    @Bean
    public Queue workQueue1() {
        return new Queue("publish_queue2", true);
    }

    @Bean
    public Queue workQueue2() {
        return new Queue("routing_queue1", true);
    }

    @Bean
    public Queue workQueue3() {
        return new Queue("routing_queue2", true);
    }

    @Bean
    public Queue workQueue4() {
        return new Queue("topic_queue1", true);
    }

    @Bean
    public Queue workQueue5() {
        return new Queue("topic_queue2", true);
    }

    @Bean
    public Queue workQueue6() {
        return new Queue("delayed_queue", true);
    }

    /***
     * 定义一个队列，设置队列属性
     * @return
     */
    @Bean("queueA")
    public Queue queueA() {
        Map<String, Object> map = new HashMap<>();
        // 消息过期时长，10秒过期
        map.put("x-message-ttl", 10000);
        // 队列中最大消息条数，10条
        map.put("x-max-length", 10);
        // 第一个参数，队列名称
        // 第二个参数，durable：持久化
        // 第三个参数，exclusive：排外的，
        // 第四个参数，autoDelete：自动删除
        Queue queue = new Queue("first-queue", true, false, false, map);
        return queue;
    }

    @Bean("queueB")
    public Queue queueB() {
        Map<String, Object> map = new HashMap<>();
        // 消息过期时长，10秒过期
        map.put("x-message-ttl", 10000);
        // 队列中最大消息条数，10条
        map.put("x-max-length", 10);
        // 第一个参数，队列名称
        // 第二个参数，durable：持久化
        // 第三个参数，exclusive：排外的，
        // 第四个参数，autoDelete：自动删除
        Queue queue = new Queue("second-queue", true, false, false, map);
        return queue;
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        // 第一个参数，交换机名称
        // 第二个参数，durable，是否持久化
        // 第三个参数，autoDelete，是否自动删除
        FanoutExchange fanoutExchange = new FanoutExchange("exchangeName", true, false);
        return fanoutExchange;
    }


    /**
     * 绑定队列到交换机
     */
    @Bean
    public Binding bindingA(@Qualifier("queueA") Queue queueA, FanoutExchange fanoutExchange) {
        Binding binding = BindingBuilder.bind(queueA).to(fanoutExchange);
        return binding;
    }

    @Bean
    public Binding bindingB(@Qualifier("queueB") Queue queueB, FanoutExchange fanoutExchange) {
        Binding binding = BindingBuilder.bind(queueB).to(fanoutExchange);
        return binding;
    }


}

