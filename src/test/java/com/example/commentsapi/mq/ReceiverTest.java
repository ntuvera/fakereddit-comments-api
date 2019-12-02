package com.example.commentsapi.mq;

import com.example.commentsapi.service.CommentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReceiverTest {

    @InjectMocks
    private Receiver receiver;

    @Mock
    private CommentServiceImpl commentService;

    @Test
    public void receiver_Success() throws InterruptedException {
        receiver.receive("1");

       verify(commentService, times(1)).deleteByPostId(1);
    }
}
