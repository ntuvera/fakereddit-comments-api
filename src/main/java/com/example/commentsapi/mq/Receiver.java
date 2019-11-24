package com.example.commentsapi.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queue1")
public class Receiver {

    @RabbitHandler
    public void receive(String msg) throws InterruptedException {
        System.out.println("message received: " + msg);
    }
}
