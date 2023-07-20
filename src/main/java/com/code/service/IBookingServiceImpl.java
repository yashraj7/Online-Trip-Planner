package com.code.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.entity.Booking;
import com.code.exceptions.BookingNotFoundException;
import com.code.repository.IBookingRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class IBookingServiceImpl implements IBookingService {

	@Autowired
	IBookingRepository repo;

	@Override
	public String makeBooking(Booking booking) throws BookingNotFoundException {
		try {
			Booking booking1 = repo.save(booking);
			if (booking1 != null) {
				return "Booking added sucessfully";
			} else {
				throw new BookingNotFoundException("Cannot book.");
			}
		} catch (BookingNotFoundException e) {
			return e.getMessage();
		}
	}

	@Override
	public String cancelBooking(int bookingId) throws BookingNotFoundException {
		Optional<Booking> b3 = repo.findById(bookingId);
		try {
			if (b3.isPresent()) {
				repo.deleteById(bookingId);
			} else {
				throw new BookingNotFoundException("Booking for id : " + bookingId + " does not exist.");
			}
		} catch (BookingNotFoundException e) {
			return e.getMessage();
		}
		return "Booking deleted successfully.";
	}

	@Override
	public Booking viewBooking(int bookingId) throws BookingNotFoundException {
		Booking booking2;
		try {
			Optional<Booking> b2 = repo.findById(bookingId);
			if (b2.isPresent()) {
				booking2 = b2.get();
			} else
				throw new BookingNotFoundException("There is no booking with id : " + bookingId);

		} catch (BookingNotFoundException e) {
			throw e;
		}
		return booking2;
	}

	@Override
	public List<Booking> viewAllBookings() throws BookingNotFoundException {
		List<Booking> list = (List<Booking>) repo.findAll(); //
		if (list.size() == 0)
			throw new BookingNotFoundException();
		else
			return list;
	}

}