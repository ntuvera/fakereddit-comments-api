package com.example.commentsapi.mq;

import com.example.commentsapi.service.CommentServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queue1")
public class Receiver {
    @Autowired
    CommentServiceImpl commentService;

    @RabbitHandler
    public void receive(String msg) throws InterruptedException {
        System.out.println("post id: " + msg + " deleted.  Purging comments for post");
        commentService.deleteByPostId(Integer.parseInt(msg));
    }
}
