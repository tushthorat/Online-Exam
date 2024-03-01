package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.entity.User;

@Repository
public class LoginDao {
     
	@Autowired
	SessionFactory sf;
	
	
	public String getPasswordFromDatabase(String username) {
		Session ss=sf.openSession();
		User userfromdatabase=ss.get(User.class,username);
		return userfromdatabase.getPassword();
	}
}
