package com.coki.mvc;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coki.mvc.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
