package com.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.code.entity.Booking;
import com.code.entity.Customer;
import com.code.entity.PackageDetails;
import com.code.exceptions.BookingNotFoundException;
import com.code.repository.IBookingRepository;
import com.code.service.IBookingService;
//import com.cg.otm.testcases.MockBean;

@SpringBootTest
class BookingTest {

	@Autowired
	private IBookingService ibookingservice;

	@MockBean
	private IBookingRepository repo;

	// Booking details
	private Booking getBooking() {
		Booking booking = new Booking();

		booking.setBookingId(10);
		booking.setBookingTitle("North");
		booking.setBookingType("Tatkal");
		booking.setDescription("North in December");
		booking.setBookingDate(getLocalDate());
		booking.setCustomer(getCustomer());
		booking.setPack(getPackage());

		return booking;
	}

	private PackageDetails getPackage() {
		PackageDetails pack = new PackageDetails();
		pack.setHotelName("Sun and Shine");
		pack.setPackageCost(25000);
		pack.setPackageDescription("North in December");
		pack.setPackageId(10);
		pack.setPackageName("Punjab");
		pack.setPackageType("Golden");

		return pack;
	}

	private Customer getCustomer() {
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

	@Test
	public void testAddBooking() throws BookingNotFoundException {

		Booking booking = getBooking();
		when(repo.save(booking)).thenReturn(booking);
		assertSame(ibookingservice.makeBooking(booking), "Booking added sucessfully");

	}

	@Test
	public void testCancelBooking() throws BookingNotFoundException {
		Booking booking = getBooking();
		/*
		 * ibookingservice.cancelBooking(booking.getBookingId()); verify(repo,
		 * times(1)).deleteById(booking.getBookingId());
		 */
		when(repo.findById(5)).thenReturn(Optional.of(booking));

		ibookingservice.cancelBooking(5);

		verify(repo, times(1)).deleteById(5);
	}

	@Test
	public void testViewBooking() throws BookingNotFoundException {
		Booking booking = getBooking();
		/*
		 * ibookingservice.viewBooking(booking.getBookingId()); verify(repo,
		 * times(1)).findById(booking.getBookingId());
		 */
		when(repo.findById(5)).thenReturn(Optional.of(booking));

		assertThat(ibookingservice.viewBooking(5)).isEqualTo(booking);
	}

	@Test
	public void testViewAllBookings() throws BookingNotFoundException {
		Booking booking = getBooking();
		when(repo.findAll()).thenReturn(Stream.of(getBooking()).collect(Collectors.toList()));
		assertEquals(1, ibookingservice.viewAllBookings().size());
	}

}