package com.example.commentsapi.feign;

import com.example.commentsapi.bean.PostBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("post")
public interface PostClient {
    @GetMapping("/identify/{postId}")
    public PostBean getPostById(@PathVariable int postId);
}
