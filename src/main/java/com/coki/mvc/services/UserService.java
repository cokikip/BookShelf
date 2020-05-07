package com.coki.mvc.services;

import java.util.List;

import com.coki.mvc.model.User;

public interface UserService {
	void save(User user);
	List<User> list();

}
