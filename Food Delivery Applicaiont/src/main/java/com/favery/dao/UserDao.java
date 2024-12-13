package com.favery.dao;

import java.util.List;

import com.favery.model.User;

public interface UserDao {
	int addUser(User user);
	User getUser(String email);
	int updateUser(User user);
	int deleteUser(String email);
	List<User> getAllUsers();
}
