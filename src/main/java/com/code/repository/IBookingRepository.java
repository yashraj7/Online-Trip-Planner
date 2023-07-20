package com.code.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.code.entity.Booking;


@Repository
public interface IBookingRepository extends CrudRepository<Booking, Integer>{
	
}