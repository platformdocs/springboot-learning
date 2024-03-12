package com.example.demo.controller;

import com.example.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author : JCccc
 * @CreateTime : 2019/9/3
 * @Description :
 **/
@RestController
public class SendMessageController {

    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    interface  Sender {
        void send(int index);
    }

    @GetMapping("/sendTestMessage")
    public String sendTestMessage() throws Exception {

//        String queue="MqTest1";
//        String message="message1";
//        rabbitTemplate.convertAndSend(queue,message);


//        Sender sender = new Sender() {
//            @Override
//            public void send(int index) {
//                        String queue="MqTest1";
//        String message="message";
//        rabbitTemplate.convertAndSend(queue,message+index);
//            }
//        };
//        Sender sender2 = new Sender() {
//            @Override
//            public void send(int index) {
//                String queue="MqTest1";
//                String message="message";
//                rabbitTemplate.convertAndSend(queue,message+index);
//            }
//        };
//
//        for (int i=0;i<100;i++){
//            sender.send(i);
//            sender2.send(i);
//        }

        String queue="MqTest1";
        User user = new User();
        user.setUsername("tesst");
        user.setPassword("1234");
        ObjectMapper objectMapper=new ObjectMapper();
        rabbitTemplate.convertAndSend(queue, user);

        return "ok";
    }

    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() throws Exception {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "Test message, hello world!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        ObjectMapper objectMapper=new ObjectMapper();
        rabbitTemplate.convertAndSend("directExchange", "directRouting", map);
        return "ok";
    }

    @GetMapping("/sendTopicFirstMessage")
    public String sendTopicFirstMessage() throws Exception {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: first";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> firstMap = new HashMap<>();
        firstMap.put("messageId", messageId);
        firstMap.put("messageData", messageData);
        firstMap.put("createTime", createTime);
        ObjectMapper objectMapper=new ObjectMapper();
        rabbitTemplate.convertAndSend("topicExchange", "topic.first", firstMap);
        return "ok";
    }

    @GetMapping("/sendTopicSecondMessage")
    public String sendTopicSecondMessage() throws Exception {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: second";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> secondMap = new HashMap<>();
        secondMap.put("messageId", messageId);
        secondMap.put("messageData", messageData);
        secondMap.put("createTime", createTime);
        ObjectMapper objectMapper=new ObjectMapper();
        rabbitTemplate.convertAndSend("topicExchange", "topic.second", secondMap);
        return "ok";
    }

    @GetMapping("/sendFanoutMessage")
    public String sendFanoutMessage() throws Exception {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: all";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> secondMap = new HashMap<>();
        secondMap.put("messageId", messageId);
        secondMap.put("messageData", messageData);
        secondMap.put("createTime", createTime);
        ObjectMapper objectMapper=new ObjectMapper();
        rabbitTemplate.convertAndSend("fanoutExchange", "fanout.send", secondMap);
        return "ok";
    }
}
