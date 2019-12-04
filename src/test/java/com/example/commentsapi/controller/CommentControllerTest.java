package com.example.commentsapi.controller;

import com.example.commentsapi.bean.PostBean;
import com.example.commentsapi.bean.UserBean;
import com.example.commentsapi.model.Comment;
import com.example.commentsapi.repository.CommentRepository;
import com.example.commentsapi.service.CommentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CommentControllerTest {
    private MockMvc mockMvc;
    private Comment fakeComment;
    private PostBean fakePost;
    private UserBean fakeUser;

    @InjectMocks
    CommentsApiController commentsApiController;

    @InjectMocks
    Comment comment;

//    @InjectMocks
    @Mock
    CommentServiceImpl commentService;

    @Mock
    CommentRepository commentRepository;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(commentsApiController).build();

        fakeUser = new UserBean(1, "Freddie Mercury");
        fakePost = new PostBean(1, "A night at the Opera", "I love my car", fakeUser.getId());

        fakeComment = new Comment("Lorem Ipsum", fakePost.getId(), fakeUser.getId());
        fakeComment.setPost(fakePost);
        fakeComment.setUser(fakeUser);

    }

    @Test
    public void createComment_Comment_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("userId", fakeUser.getId())
                .header("username",fakeUser.getUsername())
                .content("{\"text\":\"foobar\"}");

        when(commentService.createComment(any(), anyInt(), anyInt(), anyString())).thenReturn(new Comment());

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    public void getCommentsByPostId_Comments_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/post/1/comment")
                .header("Authorization", "faketoken12345")
                .accept(MediaType.APPLICATION_JSON);

        when(commentService.listCommentsByPostId(anyInt())).thenReturn(new ArrayList<Comment>());

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

//    @Test
//    public void getCommentsByPostId_Comments_Failure() throws Exception {
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .get("/post/1/comment")
//                .header("Authorization", "faketoken12345")
//                .accept(MediaType.APPLICATION_JSON);
//
//        when(commentService.listCommentsByPostId(anyInt())).thenReturn(new Exception("Post not found"));
//
//        mockMvc.perform(requestBuilder)
//                .andExpect(status().isBadRequest());
//    }

    @Test
    public void getCommentsByUserId_Comments_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/user/comment")
                .header("userId", fakeUser.getId())
                .accept(MediaType.APPLICATION_JSON);

        when(commentService.listCommentsByUserId(anyInt())).thenReturn(new ArrayList<Comment>());

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void getCommentsByUserId_Comments_Failure() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/user/comment")
                .accept(MediaType.APPLICATION_JSON);

//        when(commentService.listCommentsByUserId(anyInt())).thenReturn(new ArrayList<Comment>());

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteByCommentId_String_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/" + fakeComment.getId())
                .header("userId", fakeUser.getId())
                .accept(MediaType.APPLICATION_JSON);

        when(commentService.deleteByCommentId(anyInt())).thenReturn("Delete comment succeeded");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

    }

    @Test
    public void deleteByCommentId_String_Failure() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/0")
                .header("userId", fakeUser.getId())
                .accept(MediaType.APPLICATION_JSON);

        when(commentService.deleteByCommentId(anyInt())).thenReturn("Delete comment failed");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

    }

    @Test
    public void deleteByPostId_Comment_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/purge/" + fakeComment.getPostId())
                .header("userId", fakeUser.getId())
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
//                .andExpect(content().json("[]"));

    }
}
