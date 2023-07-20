package com.code.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.entity.Feedback;
import com.code.exceptions.FeedbackNotFoundException;
import com.code.repository.IFeedbackRepository;



@Service
public class IFeedbackServiceImpl implements IFeedbackService{
	@Autowired
	IFeedbackRepository feedbackRepo;
	
	
	public Feedback addFeedback(Feedback feedback) {
	// TODO Auto-generated method stub
		
		return feedbackRepo.save(feedback);
		
	}
	
	
	@Override
	public Feedback findByFeedbackId(int feedbackId)throws FeedbackNotFoundException {
		// TODO Auto-generated method stub
		Optional<Feedback> opt = feedbackRepo.findById(feedbackId);
		if (opt.isEmpty()) {
			throw new FeedbackNotFoundException("Customer with id " + feedbackId + " does not Exist");
		}

		Feedback feed = opt.get();
		return feed;
	}

	
	
	@Override
	public List<Feedback> viewAllFeedbacks() {
		// TODO Auto-generated method stub
		return feedbackRepo.findAll();
	}

}