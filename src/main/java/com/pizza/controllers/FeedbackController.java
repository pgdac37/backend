package com.pizza.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizza.entities.Feedback;
import com.pizza.repo.FeedBackDao;
import com.pizza.service.FeedBackService;

@RestController
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*",allowCredentials ="true")
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	private FeedBackService feedBackService;
	
	@Autowired
	private FeedBackDao feedBackDao;
	
	//add new feedback to list
	@PostMapping("/addFeedback")
	public ResponseEntity<?> addFeedback(@RequestBody Feedback feedback)
	{
		feedBackService.addNewFeedback(feedback);
		return ResponseEntity.status(HttpStatus.CREATED).body("Feedback added");
	}
	
	@GetMapping("/feedbackList")
	public ResponseEntity<?> feedBackList(){
		return ResponseEntity.ok().body(feedBackService.getAll());
	}
}
