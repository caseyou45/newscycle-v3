package com.java.newscycle.service;


import com.java.newscycle.dto.Likes.LikeDTO;
import com.java.newscycle.entity.Comment;
import com.java.newscycle.entity.Like;
import com.java.newscycle.entity.Users;
import com.java.newscycle.repository.CommentRepository;
import com.java.newscycle.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class UsersService {

    private UsersRepository usersRepository;


    private CommentRepository commentRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository, CommentRepository commentRepository) {
        this.usersRepository = usersRepository;
        this.commentRepository = commentRepository;
    }

    public Users getUserByUsername(String username) {
        Users user = usersRepository.findUsersByUsername(username);
        List<LikeDTO> likes = new ArrayList<>();

        for (Like like : user.getLikes()) {
            Comment com = commentRepository.findById(like.getCommentID()).get();
            likes.add(new LikeDTO(like, com));
        }

        user.setLikeDTOs(likes);
        return user;

    }


    public ResponseEntity userSignUp(Users user) {
        Users userAlreadyExists = usersRepository.findUsersByUsername(user.getUsername());

        if (userAlreadyExists != null) {
            return new ResponseEntity("Username already in use",
                    HttpStatus.FORBIDDEN);
        }


        //Setting the user's account creation date
        Date utilDate = new Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        user.setCreationDate(sqlDate);

        //Encryption of password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);


        usersRepository.save(user);


        return new ResponseEntity(HttpStatus.OK);
    }


    /*    This UNUSED method performs a sign-in process. It does not use JWT and merely checks credentials.

     */
    public ResponseEntity<Users> userSignIn(Users unverifiedUser) {
        Users savedUser = usersRepository.findUsersByUsername(unverifiedUser.getUsername());

        if (savedUser == null) {
            return new ResponseEntity(
                    "User not found",
                    HttpStatus.BAD_REQUEST);

        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (passwordEncoder.matches(unverifiedUser.getPassword(), savedUser.getPassword())) {
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            return new ResponseEntity("Incorrect Password",
                    HttpStatus.FORBIDDEN);
        }

    }


}
