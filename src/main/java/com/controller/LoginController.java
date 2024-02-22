package com.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thekiranacademy.entity.Answer;
import com.thekiranacademy.entity.User;

@RestController
@CrossOrigin("http://localhost:4200")
public class LoginController
{
	@Autowired
	SessionFactory factory;
	
	static HttpSession httpsession;
	
	@PostMapping("validate")
	public boolean validate(@RequestBody User userFromAngular,HttpServletRequest request)
	{
		
		Session session=factory.openSession();
	
		String usernameFromAngular=userFromAngular.username;
		
		User UserFromDatabase=session.get(User.class,usernameFromAngular);
		
		// If usernameFromAngular is not present in database , it means given username is wrong
		// and we will get null value from get() methods
		
		//  userFromAngular==>[username='suresh', password='xyz'] User class object from client
		// UserFromDatabase==>[username='suresh', password='suresh'] User class object from database 
		
		if(UserFromDatabase==null)
		{
			return false;
		}
		else
		{
			if(UserFromDatabase.password.equals(userFromAngular.password))
			{
				
				httpsession=request.getSession();
				
				
				httpsession.setAttribute("score",0);
				
				httpsession.setAttribute("questionIndex",0);
								
				HashMap<Integer,Answer> hashMap=new HashMap<Integer, Answer>();
				
				httpsession.setAttribute("submittedDetails",hashMap);

							
				return true;
			}
			else
			{
				return false;
			}
		}
			
	}

	/*{"username":"suresh","password":"suresh","mobno":78688,"emailid":"xys@hhh.com"} */

	@RequestMapping("saveUser")
	public void saveUser(@RequestBody User user)
	{
		
		Session session=factory.openSession();
		
		
		Transaction tx=session.beginTransaction();
		
			session.save(user);
		
		tx.commit();
		
		
		System.out.println("Data saved");
		
		
	}
	}
