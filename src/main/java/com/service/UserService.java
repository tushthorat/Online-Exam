package com.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.entity.User;

@Service
public class UserService {
	
	@Autowired
	SessionFactory sf;
	
	@Autowired
	UserDao dao;
	
	
    public List<User> getAllUsers(){
    	return dao.getAllUsers();
    }
    
    public User getuser(String username) {
    	
    	return dao.getUser(username);
    }
    
    public void deleteuser(String username) {
    	dao.deleteuser(username);
    }
}
