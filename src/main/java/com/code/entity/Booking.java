package com.code.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "booking_details")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull(message = "Booking Id cannot be null.")
	private int bookingId;

	@NotBlank(message = "Booking type cannot be null.")
	private String bookingType;

	private String description;

	@NotBlank(message = "Booking title cannot be null.")
	private String bookingTitle;

	@NotNull(message = "Booking Date cannot be null.")
	private LocalDate bookingDate;

	@NotNull(message = "Please specify the number of bookings to be done.")
	@Column(name = "numberOfBookings")
	private int numberOfBookings;

	// Mapping with Packages

	@OneToOne
	@JoinColumn(name = "packageId")
	private PackageDetails pack;

	// Mapping with Customer
	@OneToOne
	@JoinColumn(name = "customerId")
	private Customer customer;

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getBookingType() {
		return bookingType;
	}

	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBookingTitle() {
		return bookingTitle;
	}

	public void setBookingTitle(String bookingTitle) {
		this.bookingTitle = bookingTitle;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public PackageDetails getPack() {
		return pack;
	}

	public void setPack(PackageDetails pack) {
		this.pack = pack;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public boolean isPresent() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getNumberOfBookings() {
		return numberOfBookings;
	}

	public void setNumberOfBookings(int numberOfBookings) {
		this.numberOfBookings = numberOfBookings;
	}

	public Booking() {
		super();
	}

}