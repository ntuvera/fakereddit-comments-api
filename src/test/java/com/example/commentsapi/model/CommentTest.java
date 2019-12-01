package com.example.commentsapi.model;

import com.example.commentsapi.bean.PostBean;
import com.example.commentsapi.bean.UserBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class CommentTest {
    private Comment tempComment;
    private UserBean tempUser;
    private PostBean tempPost;

    @InjectMocks
    private Comment comment;

    @Before
    public void initComment() {
        tempComment =  new Comment("Comment text", 1, 1);
        tempPost = new PostBean(1, "title", "description", 1);
        tempUser = new UserBean(1, "Batman");
        tempComment.setId(1);
        tempComment.setUser(tempUser);
        tempComment.setPost(tempPost);

    }

    @Test
    public void getId() {
        assertEquals(1, tempComment.getId());
    }

    @Test
    public void setId() {
        tempComment.setId(2);
        assertEquals(2, tempComment.getId());
    }

    @Test
    public void getText() {
        assertEquals("Comment text", tempComment.getText());
    }

    @Test
    public void setText() {
        tempComment.setText("new comment Text");
        assertEquals("new comment Text", tempComment.getText());
    }

    @Test
    public void getPostId() {
        assertEquals(1, tempComment.getPostId());
    }

    @Test
    public void setPostId() {
        tempComment.setPostId(2);
        assertEquals(2, tempComment.getPostId());
    }

    @Test
    public void getUserId() {
        assertEquals(1, tempComment.getUserId());
    }

    @Test
    public void setUserId() {
        tempComment.setUserId(2);
        assertEquals(2, tempComment.getUserId());
    }

    @Test
    public void getUser() {
        assertEquals(tempUser, tempComment.getUser());
    }

    @Test
    public void setUser() {
        tempComment.setUser(tempUser);
        assertEquals(tempUser, tempComment.getUser());
    }

    @Test
    public void getPost() {
        assertEquals(tempPost, tempComment.getPost());
    }

    @Test
    public void setPost() {
        tempComment.setPost(tempPost);
        assertEquals(tempPost, tempComment.getPost());
    }
}
