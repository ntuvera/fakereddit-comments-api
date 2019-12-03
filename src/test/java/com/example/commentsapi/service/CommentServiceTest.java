package com.example.commentsapi.service;

import com.example.commentsapi.bean.PostBean;
import com.example.commentsapi.bean.UserBean;
import com.example.commentsapi.exception.EmptyInputException;
import com.example.commentsapi.feign.PostClient;
import com.example.commentsapi.feign.UserClient;
import com.example.commentsapi.model.Comment;
import com.example.commentsapi.repository.CommentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {
    private Comment tempComment;
    private Iterable<Comment> commentList;
    private PostBean tempPost;
    private UserBean tempUser;

    @InjectMocks
    private CommentServiceImpl commentService;

    @InjectMocks
    private Comment comment;

    @InjectMocks
    private UserBean User;

    @InjectMocks
    private PostBean Post;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    PostClient postClient;

    @Mock
    UserClient userClient;

    @Before
    public void init() {
        tempComment = new Comment("This is a comment", 1, 1);
        tempComment.setId(1);
        commentList = new ArrayList<Comment>();
        tempUser = new UserBean(1, "Ice T");
        tempPost = new PostBean(1, "Quote from Ice T.", "I make an effort to keep it as real as I possible can", tempUser.getId());
//        commentList.add(tempComment);
    }

    @Test
    public void createComment_Comment_Success() throws EmptyInputException {
        when(userClient.getUserById(anyInt())).thenReturn(tempUser);
        when(postClient.getPostById(anyInt())).thenReturn(tempPost);
        when(commentRepository.save(any())).thenReturn(tempComment);

        Comment createdComment = commentService.createComment(tempComment, tempPost.getId(), tempUser.getId(), tempUser.getUsername());

        assertEquals(tempComment.getText(), createdComment.getText());
        assertEquals(tempComment.getUserId(), createdComment.getUserId());
        assertEquals(tempComment.getPostId(), createdComment.getPostId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void createComment_Comment_Failure() throws EmptyInputException {
        when(postClient.getPostById(anyInt())).thenReturn(null);
        Comment failedComment = commentService.createComment(tempComment, tempPost.getId(), tempUser.getId(), tempUser.getUsername());
    }

    @Test
    public void listCommentsByPostId_Comments_Success() {
        // TODO: Check with someone this test is valid?
        when(commentRepository.listCommentsByPostId(anyInt())).thenReturn(commentList);

        Iterable<Comment> returnedComments = commentService.listCommentsByPostId(tempPost.getUser_id());

        assertEquals(commentList, returnedComments);
    }

    @Test
    public void listCommentsByUserId_Comments_Success() {
        when(commentRepository.listCommentsByUserId(anyInt())).thenReturn(commentList);

        Iterable<Comment> returnedComments = commentService.listCommentsByUserId(tempUser.getId());

        assertEquals(commentList, returnedComments);
    }

    @Test
    public void listComments_Comments_Success() {
        when(commentRepository.findAll()).thenReturn(commentList);
        when(userClient.getUserById(anyInt())).thenReturn(tempUser);
        when(postClient.getPostById(anyInt())).thenReturn(tempPost);

        verify(userClient, times(1)).getUserById(tempComment.getUserId());
        verify(postClient, times(1)).getPostById(tempComment.getPostId());

        Iterable<Comment> returnedComments = commentService.listComments();

        assertEquals(commentList, returnedComments);
    }

    @Test
    public void deleteByCommentId_String_Success() {
        when(commentRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(tempComment).empty());

        String response = commentService.deleteByCommentId(tempComment.getId());

        assertEquals("Delete comment succeeded", response);
    }

    @Test
    public void deleteByCommentId_String_Failure() {
        when(commentRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(tempComment));

        String response = commentService.deleteByCommentId(tempComment.getId());

        assertEquals("Delete comment failed", response);
    }

    @Test
    public void deleteByPostId() {
        commentService.deleteByPostId(tempComment.getId());

        verify(commentRepository, times(1)).purgeComments(eq(1));
    }
}
