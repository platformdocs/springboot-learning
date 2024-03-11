package com.example.demo.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SpringRabbitListener {

    @RabbitListener(queues = "MqTest1")
    public void listenSimpleQueueMessage(String msg){
        System.out.println("接收到的消息："+msg);
    }
}