package com.example.demo.test.Rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Author Great
 * @Date 2021/3/16 15:01
 * @Version 1.0
 */
public class Test5 {

        public static void main(String[] args) throws Exception {
            ConnectionFactory f = new ConnectionFactory();
            f.setHost("47.116.140.127");
            f.setUsername("admin");
            f.setPassword("admin");
            Connection c = f.newConnection();
            Channel ch = c.createChannel();

            //第二个参数设置队列持久化
            ch.queueDeclare("task_queue",true,false,false,null);

            System.out.println("等待接收数据");

            ch.basicQos(1); //一次只接收一条消息

            //收到消息后用来处理消息的回调对象
            DeliverCallback callback = new DeliverCallback() {
                @Override
                public void handle(String consumerTag, Delivery message) throws IOException {
                    String msg = new String(message.getBody(), "UTF-8");
                    System.out.println("收到: "+msg);
                    for (int i = 0; i < msg.length(); i++) {
                        if (msg.charAt(i)=='.') {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                            }
                        }
                    }
                    System.out.println("处理结束");
                    //发送回执
                    ch.basicAck(message.getEnvelope().getDeliveryTag(), false);
                }
            };

            //消费者取消时的回调对象
            CancelCallback cancel = new CancelCallback() {
                @Override
                public void handle(String consumerTag) throws IOException {
                }
            };

            //autoAck设置为false,则需要手动确认发送回执
            ch.basicConsume("task_queue", false, callback, cancel);
        }
    }
