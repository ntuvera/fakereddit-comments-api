package com.example.commentsapi.controller;

import com.example.commentsapi.model.Comment;
import com.example.commentsapi.service.CommentServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "Comment Management System")
public class CommentsApiController {

    @Autowired
    private CommentServiceImpl commentService;

    // TODO: reactivate when we find a way to go around swagger-ui.html
//    @PostMapping("/{postId}")
//    public Comment createComment(@RequestBody Comment comment, @PathVariable int postId, @RequestHeader("userId") int userId, @RequestHeader("username") String username) {
//        return commentService.createComment(comment, postId, userId, username);
//    }

    @GetMapping("/post/{postId}/comment")
    public Iterable<Comment> getCommentsByPostId(@PathVariable int postId) {
        return commentService.listCommentsByPostId(postId);
    }

    @GetMapping("/user/comment")
    public Iterable<Comment> getCommentsByUserId(@RequestHeader("userId") int userId) {
        return commentService.listCommentsByUserId(userId);
    }

    // TODO: reactivate when we find a way to go around swagger-ui.html
//    @DeleteMapping("/{commentId}")
//    public String deleteByCommentId(@PathVariable int commentId) {
//        return commentService.deleteByCommentId(commentId);
//    }

    @DeleteMapping("/purge/{postId}")
    public void deleteByPostId(@PathVariable int postId) {
        commentService.deleteByPostId(postId);
    }

}
