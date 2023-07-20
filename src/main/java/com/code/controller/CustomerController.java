package com.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.code.entity.Customer;
import com.code.entity.Status;
import com.code.exceptions.CustomerIdNotFoundException;
import com.code.repository.ICustomerRepository;
import com.code.service.ICustomerService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("http://localhost:4200")
public class CustomerController {

	@Autowired
	ICustomerRepository repository;

	@Autowired
	private ICustomerService service;

	// Customer Login

	@PostMapping("/customer/login")
	public Status signIn(@Valid @RequestBody Customer customer) {

		return service.signIn(customer);

	}

	// Customer Logout

	@PostMapping("/customer/logout")
	public Status signOut(@Valid @RequestBody Customer customer) {
		return service.signOut(customer);
	}

	// Adding a Customer

	@PostMapping("/customer/add")
	public Customer addCustomer(@Valid @RequestBody Customer customer) {
		return service.addCustomer(customer);

	}

	// Deleting a Customer
	@DeleteMapping("/customer/remove/{customerId}")
	public ResponseEntity<?> removeCustomer(@PathVariable("customerId") int customer) {
		try {
			Customer cust = this.service.removeCustomer(customer);
			System.out.println("customer removed successfully");

			return new ResponseEntity<Customer>(cust, HttpStatus.OK);
		} catch (CustomerIdNotFoundException e) {
			// TODO Auto-generated catch block

			System.out.println(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	// Viewing a Customer
	@GetMapping("/customer/get/{customerId}")
	public ResponseEntity<?> getCustomer(@PathVariable("customerId") int customer) {
		Customer cust = null;
		try {
			cust = this.service.viewCustomer(customer);
			return new ResponseEntity<Customer>(cust, HttpStatus.OK);

		} catch (CustomerIdNotFoundException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	// Updating a Customer
	@PutMapping("/customer/update")
	public ResponseEntity<?> updatingCustomer(@RequestBody Customer cust) {
		try {
			this.service.updateCustomer(cust);
			return new ResponseEntity<Customer>(cust, HttpStatus.OK);

		} catch (CustomerIdNotFoundException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
