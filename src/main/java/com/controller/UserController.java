package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;
import com.service.UserService;

@RestController
public class UserController {
   
	@Autowired
	UserService service;
	
	@GetMapping("getAllUsers")
	public List<User> getAllUsers(){
		return service.getAllUsers();
	}
	
	@RequestMapping("getUser/{username}")
	public User getUser(@PathVariable String username)
	{
		return service.getuser(username);
	}
	
	
	@DeleteMapping("deleteuser/{username}")
	public ResponseEntity<Boolean> deleteuser(@PathVariable String username) {
		service.deleteuser(username);
		ResponseEntity<Boolean> responseentity=new ResponseEntity<Boolean>(true,HttpStatus.OK);
		return responseentity;
	}
}
