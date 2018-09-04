package com.hong.service;

import com.hong.bean.User;
import com.hong.dao.UserDao;

public class UserService {

	private UserDao dao = new UserDao();
	public boolean login(User user){
		return dao.query(user);
	}
	
	public boolean regist(User user){
		return dao.add(user);
	}

	public boolean saveUser(User user) {
		return dao.update(user);
	}

	public boolean editPass(User user) {
		return dao.update(user);
	}
}
