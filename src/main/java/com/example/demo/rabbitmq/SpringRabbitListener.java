package com.example.demo.rabbitmq;

import com.example.model.User;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SpringRabbitListener {

//    @RabbitListener(queues = "MqTest1")
//    public void listenSimpleQueueMessage(User user){
//
//        System.out.println("Receiver1 接收到的消息："+user);
//    }
//
//    @RabbitListener(queues = "MqTest1")
//    public void listenSimpleQueueMessage2(String msg){
//
//        System.out.println("Receiver2 接收到的消息："+msg);
//    }

    /**
     * 消费者监听，绑定交换机、队列、路由键
     */
    @RabbitListener(bindings = {
            @QueueBinding(
                    exchange = @Exchange(value = "directExchange"),
                    value = @Queue(value = "directQueue"),
                    key = "directRouting"
            )
    })
    @RabbitHandler
    public void receiveDirectMsg(Map message) {
        //接收消息message
        System.out.println("Direct模式消费者收到消息: " + message);
    }

    /**
     * 消费者监听，绑定队列
     */
    @RabbitListener(queues = "topicFirstQueue")
    @RabbitHandler
    public void receiveTopicFirstMsg(Map message) {
        //接收消息message
        System.out.println("Topic模式(topicFirstQueue)消费者收到消息: " + message);
    }

    /**
     * 消费者监听，绑定队列
     */
    @RabbitListener(queues = "topicSecondQueue")
    @RabbitHandler
    public void receiveTopicSecondMsg(Map message) {
        //接收消息message
        System.out.println("Topic模式(topicSecondQueue)消费者收到消息: " + message);
    }

    /**
     * 消费者监听，绑定队列
     */
    @RabbitListener(queues = "fanoutFirstQueue")
    @RabbitHandler
    public void receiveFanoutFirstMsg(Map message) {
        //接收消息message
        System.out.println("Fanout模式(fanoutFirstQueue)消费者收到消息: " + message);
    }

    /**
     * 消费者监听，绑定队列
     */
    @RabbitListener(queues = "fanoutSecondQueue")
    @RabbitHandler
    public void receiveFanoutSecondMsg(Map message) {
        //接收消息message
        System.out.println("Fanout模式(fanoutSecondQueue)消费者收到消息: " + message);
    }
}