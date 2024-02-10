package com.java.newscycle.unit.service;

import com.java.newscycle.entity.Users;
import com.java.newscycle.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UsersServiceTest {

    @Autowired

    private UsersService usersService;

    @Test
    void getUserByUsername() {

        Users user = usersService.getUserByUsername("user");

        assertThat(user.getUsername()).isEqualTo("user").describedAs("Get User by Username");
    }

    @Test
    void userSignUp() {

        Users user = new Users();
        user.setUsername("testu");
        user.setPassword("testp");
        user.setId(6L);

        usersService.userSignUp(user);

        Users u = usersService.getUserByUsername("testu");

        assertThat(user.getUsername()).isEqualTo(u.getUsername()).describedAs("User Sign Up");

    }
}
