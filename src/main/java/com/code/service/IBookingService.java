package com.code.service;

import java.util.List;

import com.code.entity.Booking;
import com.code.exceptions.BookingNotFoundException;

public interface IBookingService {

	public String makeBooking(Booking booking) throws BookingNotFoundException;

	public String cancelBooking(int bookingId) throws BookingNotFoundException;

	public Booking viewBooking(int bookingId) throws BookingNotFoundException;

	public List<Booking> viewAllBookings() throws BookingNotFoundException;

}