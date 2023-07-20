package com.code.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.entity.Customer;
import com.code.entity.Status;
import com.code.exceptions.CustomerIdNotFoundException;
import com.code.repository.ICustomerRepository;

@Service
public class ICustomerServiceImpl implements ICustomerService {

	@Autowired
	ICustomerRepository repository;

	@Override
	public Customer addCustomer(Customer customer) {

		return repository.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerIdNotFoundException {
		Optional<Customer> optional = repository.findById(customer.getCustomerId());

		if (optional.isPresent()) {
			repository.save(customer);
		} else
			throw new CustomerIdNotFoundException("Customer " + customer.getCustomerId() + " Id not found");

		return customer;
	}

	@Override
	public Customer removeCustomer(int customer) throws CustomerIdNotFoundException {
		Optional<Customer> optional = repository.findById(customer);

		if (optional.isEmpty()) {
			throw new CustomerIdNotFoundException("Customer with id " + customer + " does not Exist");
		}
		Customer cust = optional.get();
		repository.delete(optional.get());
		return cust;

	}

	@Override
	public Customer viewCustomer(int customer) throws CustomerIdNotFoundException {

		Optional<Customer> optional = repository.findById(customer);
		if (optional.isEmpty()) {
			throw new CustomerIdNotFoundException("Customer with id " + customer + " does not Exist");
		}
		Customer cust = optional.get();
		return cust;
	}

	@Override
	public Status signIn(Customer customer) {
		List<Customer> users = repository.findAll();
		for (Customer other : users) {
			if (other.equals(customer)) {
				customer.setLoggedIn(true);
				repository.save(customer);
				return Status.SUCCESS;
			}
		}
		return Status.FAILURE;
	}

	@Override
	public Status signOut(Customer customer) {
		List<Customer> users = repository.findAll();
		for (Customer other : users) {
			if (other.equals(customer)) {
				customer.setLoggedIn(false);
				repository.save(customer);
				return Status.SUCCESS;
			}
		}
		return Status.FAILURE;
	}

}