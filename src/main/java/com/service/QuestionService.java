package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.QuestionDao;

import com.exam.online.entity.Question;

@Service
public class QuestionService {
     
	@Autowired
	QuestionDao dao;
	
	public List<Question> getAllQuestion(String subject){
		return dao.getAllQuestion(subject);
	}
}
