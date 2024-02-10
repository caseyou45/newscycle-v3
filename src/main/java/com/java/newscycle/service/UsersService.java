package com.java.newscycle.service;

import com.java.newscycle.dto.Likes.LikeDTO;
import com.java.newscycle.entity.Comment;
import com.java.newscycle.entity.Users;
import com.java.newscycle.repository.CommentRepository;
import com.java.newscycle.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final CommentRepository commentRepository;

    public UsersService(UsersRepository usersRepository, CommentRepository commentRepository) {
        this.usersRepository = usersRepository;
        this.commentRepository = commentRepository;
    }

    public Users getUserByUsername(String username) {
        Users user = usersRepository.findUsersByUsername(username);
        List<LikeDTO> likes = new ArrayList<>();

        user.getLikes().forEach(like -> {
            Optional<Comment> optionalComment = commentRepository.findById(like.getCommentID());
            optionalComment.ifPresent(comment -> likes.add(new LikeDTO(like, comment)));
        });

        user.setLikeDTOs(likes);
        return user;
    }

    public ResponseEntity<?> userSignUp(Users user) {
        if (usersRepository.existsUsersByUsername(user.getUsername())) {
            return new ResponseEntity<>("Username already in use", HttpStatus.FORBIDDEN);
        }

        // Setting the user's account creation date
        user.setCreationDate(new Date());

        // Encrypting password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        usersRepository.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
