package com.example.commentsapi.controller;

import com.example.commentsapi.exception.EmptyInputException;
import com.example.commentsapi.model.Comment;
import com.example.commentsapi.service.CommentServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "Comment Management System")
public class CommentsApiController {

    @Autowired
    private CommentServiceImpl commentService;

    // TODO: reactivate when we find a way to go around swagger-ui.html
    @ApiOperation(value = "Creates a comment for Post with postId", response = Comment.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created a Comment")
    })
    @PostMapping("/{postId}")
    public Comment createComment(@ApiParam(value="postId", required=true) @RequestBody Comment comment, @PathVariable int postId, @RequestHeader("userId") int userId, @RequestHeader("username") String username) throws EmptyInputException {
        return commentService.createComment(comment, postId, userId, username);
    }

    @ApiOperation(value = "Retrieves a list of comments for a Post", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of comments for a Post")
    })
    @GetMapping("/post/{postId}/comment")
    public Iterable<Comment> getCommentsByPostId(@ApiParam(value="postId", required=true) @PathVariable int postId) {
        return commentService.listCommentsByPostId(postId);
    }

    @ApiOperation(value = "Retrieves a list of logged in User's Comments", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of comments by User")
    })
    @GetMapping("/user/comment")
    public Iterable<Comment> getCommentsByUserId(@RequestHeader("userId") int userId) {
        return commentService.listCommentsByUserId(userId);
    }

    // TODO: reactivate when we find a way to go around swagger-ui.html
    @ApiOperation(value = "Deletes a User's comments by commentId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Delete Comment with commentId")
    })
    @DeleteMapping("/{commentId}")
    public String deleteByCommentId(@ApiParam(value="commentId", required=true) @PathVariable int commentId) {
        return commentService.deleteByCommentId(commentId);
    }

    @ApiOperation(value = "Deletes a User's comments through with PostId")
    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Successfully Comments with PostId")
    })
    @DeleteMapping("/purge/{postId}")
    public void deleteByPostId(@ApiParam(value="postId", required=true) @PathVariable int postId) {
        commentService.deleteByPostId(postId);
    }

}
