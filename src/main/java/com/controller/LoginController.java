package com.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Answer;
import com.entity.User;
import com.service.LoginService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;



@RestController
public class LoginController {
    
	static HttpSession httpsession;
	
	@Autowired
	LoginService service;
	
	@GetMapping("validate")
	public ResponseEntity<Boolean> validate(@RequestBody User user,HttpServletRequest request){
		httpsession=request.getSession();
		boolean answer=service.validate(user);
		if(answer) {
			httpsession.setAttribute("score",0);
			
			HashMap<Integer,Answer> hashmap=new HashMap<>();
			httpsession.setAttribute("submittedDetails", request);
			httpsession.setAttribute("QuestionIndex",0);
			
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		}else {
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
	}
}
