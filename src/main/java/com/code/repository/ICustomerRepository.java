package com.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.entity.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer , Integer>{

		

}