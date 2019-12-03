package com.example.commentsapi.service;

import com.example.commentsapi.exception.EmptyInputException;
import com.example.commentsapi.model.Comment;

import javax.persistence.EntityNotFoundException;

public interface CommentService {

    public Comment createComment(Comment comment, int postId, int userId, String username) throws EntityNotFoundException, EmptyInputException;

    public Iterable<Comment> listCommentsByPostId(int postId);

    public Iterable<Comment> listCommentsByUserId(int userId);

    public String deleteByCommentId(int commentId);

    public void deleteByPostId(int postId);

}
