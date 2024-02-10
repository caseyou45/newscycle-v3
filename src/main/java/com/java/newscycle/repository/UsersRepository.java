package com.java.newscycle.repository;

import com.java.newscycle.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findUsersByUsername(String username);

    boolean existsUsersByUsername(String username);

}
