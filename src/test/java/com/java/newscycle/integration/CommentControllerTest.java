package com.java.newscycle.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.newscycle.dto.Auth.LoginDto;
import com.java.newscycle.entity.Comment;
import com.java.newscycle.security.JWTGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerTest {

    private Comment comment;

    private Comment reply;

    private String token;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private AuthenticationManager authenticationManager;

    private JWTGenerator jwtGenerator;

    private LoginDto loginDto;


    @Autowired
    public CommentControllerTest(AuthenticationManager authenticationManager, JWTGenerator jwtGenerator, ObjectMapper objectMapper, MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
    }


    String randomStringGenerator(int length) {

        int leftLimit = 97;
        int rightLimit = 122;

        Random random = new Random();

        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            stringBuilder.append((char) randomLimitedInt);
        }

        return stringBuilder.toString();

    }


    void createToken() {

        loginDto = new LoginDto();
        loginDto.setUsername("value1");
        loginDto.setPassword("value2");


        //Get authentication token
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        token = "Bearer " + jwtGenerator.generateToken(authentication);


    }


    void makeTestComment() {
        String commentContent = randomStringGenerator(10);
        comment = new Comment();
        comment.setArticle(1L);
        comment.setAuthor(1L);
        comment.setContent(commentContent);
        comment.setUsername(loginDto.getUsername());

    }

    void makeTestReply() {
        String commentContent = randomStringGenerator(10);
        reply = new Comment();
        reply.setArticle(1L);
        reply.setAuthor(1L);
        reply.setContent(commentContent);
        reply.setParentComment(1L);
        reply.setUsername(loginDto.getUsername());

    }


    @Test
    void createComment() {

        createToken();
        makeTestComment();


        assertDoesNotThrow(() -> mockMvc.perform(post("/api/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("authorization", token)
                        .content(objectMapper.writeValueAsString(comment)))
                .andExpect(status().isCreated()));


    }


    @Test
    void createReply() {

        createToken();
        makeTestReply();

        assertDoesNotThrow(() -> mockMvc.perform(post("/api/comment/reply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("authorization", token)
                        .content(objectMapper.writeValueAsString(reply)))
                .andExpect(status().isCreated()));

    }

    @Test
    void updateComment() {

        createToken();
        makeTestComment();

        comment.setContent(randomStringGenerator(100));
        comment.setId(154L);

        assertDoesNotThrow(() -> mockMvc.perform(patch("/api/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("authorization", token)
                        .content(objectMapper.writeValueAsString(comment)))
                .andExpect(status().isOk()));
    }

    @Test
    void removeComment() {


    }

    @Test
    void getComment() {

        createToken();


        assertDoesNotThrow(() -> mockMvc.perform(get("/comment?commentID=1")
                        .header("authorization", token))
                .andExpect(status().isOk()));
    }
}