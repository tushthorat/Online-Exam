package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.entity.User;



import org.hibernate.query.Query;

@Repository
public class UserDao {
     
	@Autowired
	SessionFactory sf;
	
	
	
	
	public List<User> getAllUsers()
	{
		
		Session session=sf.openSession();		
		Query<User> query=session.createQuery("from User");
		//Query<User> query=ss.createQuery("from User",User.class); //this is latest approach
		//List<User> list=query.list();
		return query.list();
		  
	}
	
	public User getUser(String username) {
		
	 Session ss=sf.openSession();
	 Query<User> query=ss.createQuery("from user where username=:username");
	 query.setParameter("username",username);
	 return query.list().get(0);
	}
	
	public void deleteuser(String username) {
		Session ss=sf.openSession();
		Query query=ss.createQuery("delete from User where username=:username");
		query.setParameter("username",username);
		Transaction tx=ss.beginTransaction();
		query.executeUpdate();
		tx.commit();
	}
	
}
