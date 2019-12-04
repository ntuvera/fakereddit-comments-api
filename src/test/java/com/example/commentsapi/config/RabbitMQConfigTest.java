package com.example.commentsapi.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.core.Queue;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class RabbitMQConfigTest {
    @InjectMocks
    private RabbitMQConfig rabbitMQConfig;
    @Before
    public void init() {

    }
    @Test
    public void queue_queue_Success() {

        Queue newQueue = rabbitMQConfig.queue();

        assertNotNull(newQueue);
        assertEquals("queue1", newQueue.getName());
    }
}
