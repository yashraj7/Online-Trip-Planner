package com.code.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.code.entity.Payment;
import com.code.exceptions.PaymentIdNotFoundException;
import com.code.service.IPaymentService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("http://localhost:4200")
public class PaymentController {

	@Autowired
	private IPaymentService service;

	// Adding a Payment

	@PostMapping("/billingdetails/add")
	public String addBill(@Valid @RequestBody Payment bill) {
		return service.addBill(bill);

	}

	// Viweing payment details

	@GetMapping("/billingdetails/get/{billId}")
	public ResponseEntity<?> viewBill(@PathVariable("billId") int id) {
		Payment pay = null;
		try {
			pay = this.service.viewBill(id);
			return new ResponseEntity<Payment>(pay, HttpStatus.OK);
		} catch (PaymentIdNotFoundException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	// Getting all Payment Details

	@GetMapping("/getAllPaymentDetails")
	public List<Payment> viewAllPayment() {
		return this.service.viewAllPayments();
	}

}
