package com.coki.mvc.dao;

import java.util.List;

import com.coki.mvc.model.User;

public interface UserDao {
	void save(User user);
	List<User> list();
}
