package com.code.service;

import java.util.List;

import com.code.entity.Feedback;
import com.code.exceptions.FeedbackNotFoundException;

public interface IFeedbackService {

	public Feedback addFeedback(Feedback feedback);

	public Feedback findByFeedbackId(int feedbackId) throws FeedbackNotFoundException;

	public List<Feedback> viewAllFeedbacks();

}