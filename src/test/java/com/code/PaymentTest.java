package com.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.code.entity.Payment;
import com.code.exceptions.PaymentIdNotFoundException;
import com.code.repository.IPaymentRepository;
import com.code.service.IPaymentService;

@SpringBootTest
public class PaymentTest {
	
	@Autowired
	private IPaymentService service;
	@MockBean
	IPaymentRepository repository;
	public LocalDate getDate() {
        LocalDate date = LocalDate.parse("2021-12-10");
        return date;
    }
	
	private Payment getPayment() {
		Payment payment = new Payment();

		payment.setPaymentId(9);
		payment.setPaymentMode("Pa@123");
		payment.setPaymentDate(getDate());
		payment.setBankName("BOI");
		payment.setCardNo("121112");
		
		return payment;
		
	}
	@Test
	public void testAddBill() {

		Payment bill = getPayment();
		when(repository.save(bill)).thenReturn(bill);
		assertEquals(service.addBill(bill), "Payment Successfully completed");

	}
	
	@Test
	public void testViewBill() throws PaymentIdNotFoundException {
		Payment bill = getPayment();
		when(repository.findById(9)).thenReturn(Optional.of(bill));
		assertThat(service.viewBill(9)).isEqualTo(bill);
		//Optional<Payment> bill = Optional.of(getPayment());
		//when(repository.findById(9)).thenReturn(bill);
		//assertThat( service.viewBill(9)).isEqualTo(bill);
	}


}