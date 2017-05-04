package com.mickaborscha.repository;

import com.mickaborscha.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
    User findByEmail(String email);

}
