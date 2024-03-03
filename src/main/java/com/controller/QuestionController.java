package com.controller;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.entity.Question;
import com.service.QuestionService;

import jakarta.servlet.http.HttpSession;

public class QuestionController {
     
	@Autowired
	SessionFactory sf;
	
	@Autowired
	QuestionService service;
	
	@GetMapping("getAllQuestions{subject}")
	public List<Question> list(@PathVariable String subject){
List<Question> list=service.getAllQuestion(subject);
		
		HttpSession httpsession=LoginController.httpsession;
		
		httpsession.setAttribute("allquestions", list);
		
		return list;
	}
}
