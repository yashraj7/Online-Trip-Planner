package com.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.code.entity.Customer;
import com.code.entity.Feedback;
import com.code.exceptions.FeedbackNotFoundException;
import com.code.repository.IFeedbackRepository;
import com.code.service.IFeedbackService;
@SpringBootTest
class FeedbackTest {

	@Autowired
	IFeedbackService iFeedbackService;
	
	@MockBean
	IFeedbackRepository iFeedbackRepository;
	
	
	@BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }
	
	
	
	//Test case for viewing all feedback
	@Test
	public void testGetAllPackageDetails() {
		when(iFeedbackRepository.findAll()).thenReturn(Stream.of(getFeedback()).collect(Collectors.toList()));
		assertEquals(1,iFeedbackService.viewAllFeedbacks().size());
	}
	
	//Test case for viewing feedback by Id
	@Test
    public void testViewFeedbackById() throws FeedbackNotFoundException {
		Feedback feedback = getFeedback();
    	/*Mockito.when(iFeedbackRepository.findById(feedback.getFeedbackId())).thenReturn(Optional.of(feedback));	
    	Feedback result = iFeedbackService.findById(feedback.getFeedbackId());	
    	assertEquals(feedback, result, "Correct object is not received");*/
    	when(iFeedbackRepository.findById(3)).thenReturn(Optional.of(feedback));

        assertThat(iFeedbackService.findByFeedbackId(3)).isEqualTo(feedback);
    }
	
	//Test case adding feedback
	@Test
	public void testAddFeedback() {
		Feedback feedback = getFeedback();
		when(iFeedbackRepository.save(feedback)).thenReturn(feedback);
		Feedback result = iFeedbackService.addFeedback(feedback);
		assertEquals(feedback, result); 
	}
	
	
	private Feedback getFeedback() {
		Feedback feedback = new Feedback();
		feedback.setFeedbackId(3);
		feedback.getCustomer();
		feedback.setFeedback("Masti");
		feedback.setRating(5);
		feedback.setSubmitDate(getLocalDate());
		
		
		return feedback;
	}
	public Customer getCustomer() {
		Customer cust = new Customer();
		cust.setCustomerId(10);
        cust.setEmailId("abc@gmail.com");
        cust.setMobileNo("9999999999");
        cust.setName("abc");
        cust.setPassword("abcde!");
		return cust;
	}
	public LocalDate getLocalDate() {
        LocalDate date = LocalDate.parse("2021-12-10");
        return date;
    }


}