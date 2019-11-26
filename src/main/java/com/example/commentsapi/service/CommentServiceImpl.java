package com.example.commentsapi.service;

import com.example.commentsapi.bean.PostBean;
import com.example.commentsapi.bean.UserBean;
import com.example.commentsapi.feign.PostClient;
import com.example.commentsapi.feign.UserClient;
import com.example.commentsapi.model.Comment;
import com.example.commentsapi.mq.Sender;
import com.example.commentsapi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private Sender sender;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private PostClient postClient;

    @Override
    public Comment createComment(Comment comment, int postId, int userId, String username) {
        HashMap<String, String> usernameMap = new HashMap<>();
        usernameMap.put("username", username);

        comment.setPostId(postId);
        comment.setUserId(userId);
        comment.setUser(userClient.getUserById(userId));
        comment.setPost(postClient.getPostById(postId));

        Comment newComment = commentRepository.save(comment);

        return newComment;
    }

    @Override
    public Iterable<Comment> listCommentsByPostId(int postId) {
        listComments();
        sender.sendPostId(postId);

        return commentRepository.listCommentsByPostId(postId);
    }

    @Override
    public Iterable<Comment> listCommentsByUserId(int userId) {
        listComments();

        return commentRepository.listCommentsByUserId(userId);
    }

    private Iterable<Comment> listComments() {
        Iterable<Comment> foundUserComments = commentRepository.findAll();

        foundUserComments.forEach((comment) -> {
            UserBean fetchedUser = userClient.getUserById(comment.getUserId());

            if(fetchedUser != null)
                comment.setUser(fetchedUser);

            PostBean fetchedPost = postClient.getPostById(comment.getPostId());

            if(fetchedPost != null)
                comment.setPost(fetchedPost);
        });
        return foundUserComments;
    }

    @Override
    public String deleteByCommentId(int commentId) {
        commentRepository.deleteById(commentId);
        Optional<Comment> foundComment = commentRepository.findById(commentId);
        if (foundComment.isPresent()) {
            return "Delete comment failed";
        }
        return "Delete comment succeeded";
    }

    @Override
    public void deleteByPostId(int postId) {
       commentRepository.purgeComments(postId);
    }
}
