package com.example.commentsapi.bean;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserBeanTest {
    private UserBean newUser;

    @InjectMocks
    private UserBean user;

    @Before
    public void initUserBean() {
        newUser = new UserBean(1, "Batman");
    }

    @Test
    public void getUsername() {
        assertEquals("Batman", newUser.getUsername());
    }

    @Test
    public void setUsername() {
        newUser.setUsername("Robin");
        assertEquals("Robin", newUser.getUsername());
    }

    @Test
    public void getId() {
        assertEquals(1, newUser.getId());
    }

    @Test
    public void setId() {
        newUser.setId(2);
        assertEquals(2, newUser.getId());
    }

}
