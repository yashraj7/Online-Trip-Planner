package com.code.service;

import com.code.entity.Customer;
import com.code.entity.Status;
import com.code.exceptions.CustomerIdNotFoundException;

public interface ICustomerService {
	public Customer addCustomer(Customer customer);

	public Customer updateCustomer(Customer customer) throws CustomerIdNotFoundException;

	public Customer removeCustomer(int customer) throws CustomerIdNotFoundException;

	public Customer viewCustomer(int customer) throws CustomerIdNotFoundException;

	public Status signIn(Customer customer);

	public Status signOut(Customer customer);

}