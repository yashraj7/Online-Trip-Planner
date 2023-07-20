package com.code.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.code.entity.Feedback;
import com.code.exceptions.FeedbackNotFoundException;
import com.code.service.IFeedbackService;

@RestController

@CrossOrigin("http://localhost:4200")
public class FeedbackController {

	@Autowired
	IFeedbackService feedbackService;
	
	//Adding a Feedback
	
	@PostMapping("/feedback/add")
	public Feedback addFeedback(@RequestBody Feedback feedback) {
		return feedbackService.addFeedback(feedback);
	}
	
	//Viewing a feedback
	
	@GetMapping("/feedback/find/{feedbackId}")
	public Feedback findByFeedbackId(@PathVariable("feedbackId") int feedbackId) throws FeedbackNotFoundException {
		
		
		return feedbackService.findByFeedbackId(feedbackId);
	}

	//Getting all feedbacks
	
	@GetMapping("/feedback/findall")
	public List<Feedback> viewAllFeedbacks(){
		
		return feedbackService.viewAllFeedbacks();
	}


}