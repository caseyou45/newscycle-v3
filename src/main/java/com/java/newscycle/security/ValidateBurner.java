package com.java.newscycle.security;


import com.java.newscycle.entity.Users;
import com.java.newscycle.repository.UsersRepository;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.java.newscycle.security.SecurityConstants.BURNER_EXPIRATION;

@Component
public class ValidateBurner {

    UsersRepository usersRepository;

    @Autowired
    public ValidateBurner(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    public void validateBurner(String username) throws BurnerExpired {

        Users users = usersRepository.findUsersByUsername(username);

        long duration = new Date().getTime() - users.getCreationDate().getTime();

        if (duration > BURNER_EXPIRATION) {
            throw new BurnerExpired("Burner Has Expired");
        }

    }

    public class BurnerExpired extends ServletException {
        public BurnerExpired(String errorMessage) {
            super(errorMessage);
        }
    }
}
