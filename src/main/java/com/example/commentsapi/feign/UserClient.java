package com.example.commentsapi.feign;

import com.example.commentsapi.bean.UserBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user")
public interface UserClient {
    @GetMapping("/identify/{userId}")
    public UserBean getUserById(@PathVariable int userId);
}
