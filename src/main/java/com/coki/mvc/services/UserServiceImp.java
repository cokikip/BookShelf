package com.coki.mvc.services;

import java.util.List;

import com.coki.mvc.dao.UserDao;
import com.coki.mvc.model.User;

public class UserServiceImp implements UserService{
	private UserDao userDao;

	public void save(User user) {
		userDao.save(user);
		
	}

	public List<User> list() {
		// TODO Auto-generated method stub
		return userDao.list();
	}

}
