package com.example.demo.test.Rabbitmq;

import com.example.demo.util.DemoResponseUtils;
import com.example.demo.vo.DemoResponse;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 安装完erlang再安装rabbitmq，然后开启界面管理。如果用户无法登录，命令行添加用户再配置权限然后登录
 *
 * @author LL
 */
@RestController
public class MqController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * work模式：只有一个消费者能收到消息，当一个消费者较忙时，消息将被另一个不忙的消费者接收
     * 在rabbitmq中要创建一个队列
     *
     * @return
     */
    @GetMapping("/work")
    public DemoResponse<String> work() {
        for (int i = 0; i < 10; i++) {
            amqpTemplate.convertAndSend("work", "发送消息" + i);
            System.out.println("生产者发送消息1:-----" + i);
        }
        return DemoResponseUtils.success("","ok");
    }

    // ****************************************************************

    /**
     * publish模式：多个消费者同时接收到消息
     * 在rabbitmq中要创建一个交换机(fanout)和两个队列，两个队列要绑定到交换机
     *
     * @return
     */
    @GetMapping("/publish")
    public DemoResponse<String> publish() {
        for (int i = 0; i < 10; i++) {
            amqpTemplate.convertAndSend("publish_exchange", "", "发送消息" + i);
            System.out.println("生产者发送消息2:-----" + i);
        }
        return DemoResponseUtils.success("","ok");
    }

    // ****************************************************************

    /**
     * 路由模式：具有routing_key的将同时接收到消息(完全匹配)
     * 在rabbitmq创建1个交换机(direct)和两个队列，两个队列绑定到交换机，并且配置routing_key，根据routing_key发送消息到队列
     *
     * @return
     */
    @GetMapping("/routing1")
    public DemoResponse<String> routing1() {
        amqpTemplate.convertAndSend("routing_exchange", "routing_key1", "发送消息+routing_key1");
        return DemoResponseUtils.success("","ok");
    }

    @GetMapping("/routing2")
    public DemoResponse<String> routing2() {
        amqpTemplate.convertAndSend("routing_exchange", "routing_key2", "发送消息+routing_key2");
        return DemoResponseUtils.success("","ok");
    }

    // ****************************************************************

    /**
     * topic模式：根据routing_key匹配的队列将同时接收到消息(通配符匹配)
     * rabbitmq配置一个交换机(topic)和两个队列，两个队列绑定交换机，并配置通配routing_key
     *
     * @return
     */
    @GetMapping("/topic1")
    public DemoResponse<String> topic1() {
        amqpTemplate.convertAndSend("topic_exchange", "key1", "发送消息+key1");
        return DemoResponseUtils.success("","ok");
    }

    @GetMapping("/topic2")
    public DemoResponse<String> topic2() {
        amqpTemplate.convertAndSend("topic_exchange", "topic", "发送消息+topic");
        return DemoResponseUtils.success("","ok");
    }

    // ****************************************************************

    /**
     * 需要开启延时插件功能，arguments配置{"x-delayed-type"="topic(公共模式publish、路由模式direct、通配符模式topic)"}
     * rabbitmq创建一个交换机(x-delayed-message)和一个队列，队列绑定到交换机，routing_key根据arguments配置的模式进行配置
     *
     * @return
     */
    @GetMapping("/delayed")
    public DemoResponse<String>  delayed() {
        amqpTemplate.convertAndSend("delayed_exchange", "delayed_key", "发送消息+delayed", new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                System.out.println("发送时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                message.getMessageProperties().setDelay(5000);
                return message;
            }
        });
        return DemoResponseUtils.success("","ok");
    }
}