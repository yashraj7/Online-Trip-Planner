package com.code.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.code.entity.Booking;
import com.code.exceptions.BookingNotFoundException;
import com.code.service.IBookingService;

import jakarta.validation.Valid;



@RestController
@CrossOrigin("http://localhost:4200")
public class BookingController {
	
	@Autowired
	IBookingService ibookingservice;
	
	//Adding a booking
	
	@PostMapping(path = "/makebooking")
	public String makeBooking(@Valid @RequestBody Booking booking) throws BookingNotFoundException {

			return ibookingservice.makeBooking(booking);
		
		}
	
	//Get all bookings
	
	@GetMapping("/getallbookings")
	public List<Booking> getAllBookings() throws BookingNotFoundException{
		List<Booking> list=null;
		
			list=ibookingservice.viewAllBookings();
		
		return list;
	}
	
	//View booking by id
	
		@GetMapping("/viewbooking/{id}")
		public ResponseEntity<?> viewBooking(@Valid @PathVariable("id") int id) throws BookingNotFoundException  {
			try {
				return new ResponseEntity<>(ibookingservice.viewBooking(id), HttpStatus.CREATED);
			} 
			catch (BookingNotFoundException e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
			}
		}
		
		//Deleting a booking
		
		@DeleteMapping("/deletebooking/{id}")
		public String cancelBooking(@Valid @PathVariable("id") int id) throws BookingNotFoundException{
			
			String cancelResult = ibookingservice.cancelBooking(id);
			return cancelResult;
		}
}