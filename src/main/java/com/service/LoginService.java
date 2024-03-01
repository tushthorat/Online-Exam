package com.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dao.LoginDao;

import com.entity.User;

public class LoginService {
    
	@Autowired
	SessionFactory sf;
	
	@Autowired
	LoginDao dao;
	
	public boolean validate(User userfrombrowser) {
		String dbpassword=dao.getPasswordFromDatabase(userfrombrowser.getPassword());
		if(dbpassword.equals(userfrombrowser.getPassword())) {
			return true;
		}else {
			return false;
		}
	}
}
