package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.exam.online.entity.Question;



@Repository
public class QuestionDao {
	
	
	@Autowired
	SessionFactory sf;
	
	
   public List<Question> getAllQuestion(String subject){
	   Session ss=sf.openSession();
	   Query query=ss.createQuery("from Question where subject=:subject");
	   query.setParameter("subject",subject);
	   List<Question> list= query.list();
	   return list;
   }
}
