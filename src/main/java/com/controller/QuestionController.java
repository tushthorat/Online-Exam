package com.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thekiranacademy.entity.Answer;
import com.thekiranacademy.entity.Question;
import com.thekiranacademy.entity.User;

@RestController
@CrossOrigin("http://localhost:4200")
public class QuestionController 
{
	@Autowired
	SessionFactory factory;
	
	// getFirstQuestion/java
	
	@RequestMapping("getFirstQuestion/{subject}")
	public Question getFirstQuestion(@PathVariable String subject)
	{
		Session session=factory.openSession();
		
		Query query=session.createQuery("from Question where subject=:subject");
		
		query.setParameter("subject",subject);
		
		List<Question> list=query.list();
		
		
		HttpSession httpsession=LoginController.httpsession;
		
		httpsession.setAttribute("list",list);
		
		Question firstQuestion=list.get(0);
		
		return firstQuestion;
		
	}
	
	
	
	
	// 1   2    3   	qno
	// 0   1    2       index
	
	@RequestMapping("nextQuestion")
	public Question nextQuestion()
	{
		HttpSession httpsession=LoginController.httpsession;
		
		List<Question> list=(List<Question>) httpsession.getAttribute("list");
		
		//questionIndex=1
		
		if((int)httpsession.getAttribute("questionIndex")<list.size()-1)
		{
			httpsession.setAttribute("questionIndex",(int)httpsession.getAttribute("questionIndex")+1);

			Question question=list.get((int)httpsession.getAttribute("questionIndex"));
			
			return question;
		}
		
		else
		{
			return list.get(list.size()-1);
		}
		
		
	}
	
	//{"qno":1,"qtext":"what is java","submittedAnswer":"A","originalAnswer":"B"}
		
@RequestMapping("saveAnswer")
public void saveAnswer(@RequestBody Answer answer)
{
	
	HttpSession httpsession=LoginController.httpsession;
	
	HashMap<Integer,Answer> hashMap =(HashMap<Integer, Answer>) httpsession.getAttribute("submittedDetails");	

    hashMap.put(answer.getQno(),answer);
    
    System.out.println(hashMap);
    
}


// 1   2    3   	qno
// 0   1    2       index

@RequestMapping("previousQuestion")
public Question previousQuestion()
{
	HttpSession httpsession=LoginController.httpsession;
	
	List<Question> list=(List<Question>) httpsession.getAttribute("list");
	
	//questionIndex=1
	
	if((int)httpsession.getAttribute("questionIndex")>0)
	{
		httpsession.setAttribute("questionIndex",(int)httpsession.getAttribute("questionIndex")-1);

		Question question=list.get((int)httpsession.getAttribute("questionIndex"));
		
		return question;
	}
	
	else
	{
		return list.get(0);
	}
	
}


@RequestMapping("saveQuestion")
public void saveQuestion(@RequestBody Question question)
{
	
	Session session=factory.openSession();
	
	
	Transaction tx=session.beginTransaction();
	
		session.save(question);
	
	tx.commit();
	
	
	System.out.println("Data saved");
	
	
}



@RequestMapping("getAllSubjects")
public List<String> getAllSubjects()
{
	Session session=factory.openSession();
		
	Query query=session.createQuery("select distinct subject from Question");
	
	List<String> list=query.list();
	
	return list;
	
}

@RequestMapping("endexam")
public ResponseEntity<Integer> endexam()
{
	
	HttpSession httpsession=LoginController.httpsession;
	
	// Object  getAttribute(String attribute)
	
	HashMap hashmap=(HashMap) httpsession.getAttribute("submittedDetails");
	
	Collection<Answer>  collection=hashmap.values();
		
	for (Answer answer : collection) 
	{
		if(answer.getSubmittedAnswer().equals(answer.getOriginalAnswer()))
		{
			httpsession.setAttribute("score",(int)httpsession.getAttribute("score")+1);
		}
	}
		
	int score=(int) httpsession.getAttribute("score");
		
	ResponseEntity<Integer> responseEntity=new ResponseEntity<Integer>(score,HttpStatus.OK);
	
	return responseEntity;
	
}


@RequestMapping("allAnswers")
public ResponseEntity<Collection<Answer>>  getAllAnswers()
{

	HttpSession httpsession=LoginController.httpsession;

	HashMap<Integer,Answer> hashMap=(HashMap<Integer, Answer>) httpsession.getAttribute("submittedDetails");
	
	Collection<Answer> collection=hashMap.values();
	
	ResponseEntity<Collection<Answer>> responseEntity=new ResponseEntity<>(collection,HttpStatus.OK);

	return responseEntity;
		
	
}

	
}

































