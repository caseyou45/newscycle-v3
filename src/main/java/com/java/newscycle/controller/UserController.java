//package com.java.newscycle.controller;
//
//
//import com.java.newscycle.entity.Users;
//import com.java.newscycle.repository.UsersRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//
//@RestController
//@RequestMapping(path = "/api")
//public class UserController {
//
//    @Autowired
//    UsersRepository usersRepository;
//
//
//    @PostMapping("/user/signup")
//    public ResponseEntity<Users> userSignUp(@RequestBody Users signUpUser) {
//        Users user = usersRepository.findUsersByUsername(signUpUser.getUsername());
//
//        if (user != null) {
//            return new ResponseEntity("Username already in use",
//                    HttpStatus.FORBIDDEN);
//        }
//
//        //Setting the user's account creation date
//        Date utilDate = new java.util.Date();
//        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//        signUpUser.setCreationDate(sqlDate);
//
//        Users newUser = usersRepository.save(signUpUser);
//
//        return new ResponseEntity<>(newUser, HttpStatus.OK);
//
//
//    }
//
//    @PostMapping("/user/signin")
//    public ResponseEntity<Users> userSignIn(@RequestBody Users unverifiedUser) {
//
//        Users user = usersRepository.findUsersByUsername(unverifiedUser.getUsername());
//
//        if (user == null) {
//            return new ResponseEntity("Username or Password Not Correct",
//                    HttpStatus.FORBIDDEN);
//
//        }
//
//
//        if (user.getPassword().equals(unverifiedUser.getPassword())) {
//            return new ResponseEntity<>(user, HttpStatus.OK);
//
//        } else {
//            return new ResponseEntity("Username or Password Not Correct",
//                    HttpStatus.FORBIDDEN);
//        }
//
//    }
//}
