package com.code;

 
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.code.entity.Customer;
import com.code.exceptions.CustomerIdNotFoundException;
import com.code.repository.ICustomerRepository;
import com.code.service.ICustomerService;

@SpringBootTest
public class CustomerTest {
	@Autowired
	private ICustomerService service;
	
	@MockBean
	ICustomerRepository repository;
 
	@Test
	public void testAddUser() {

		Customer customer=getCustomer();
		when(repository.save(customer)).thenReturn(customer);
		assertEquals(service.addCustomer(customer), customer);

	}
	@Test
	public void testUpdateCustomer() throws CustomerIdNotFoundException {

		Customer customer=getCustomer();
		when(repository.findById(1)).thenReturn(Optional.of(customer));
		customer.setName("abc");
		when(repository.save(customer)).thenReturn(customer);
	    assertThat(service.updateCustomer(customer)).isEqualTo(customer);
		
//		when(repository.save(customer)).thenReturn(customer);
//		try {
//			assertEquals(service.updateCustomer(customer),customer);
//		} catch (CustomerIdNotFoundException e) {
//			e.printStackTrace();
//		}

	}
	@Test
	public void testRemoveCustomer() {
		Customer customer=getCustomer();
		try {
			service.removeCustomer(customer.getCustomerId());
		} catch (CustomerIdNotFoundException e) {
			e.printStackTrace();
		}
		verify(repository, times(1)).findById(customer.getCustomerId());
	}
	
	
	private Customer getCustomer() {
		Customer customer = new Customer();

		customer.setCustomerId(1);
		customer.setPassword("Pas@123");
		customer.setName("John");
		customer.setMobileNo("9787564534");

		return customer;
	}
	}
