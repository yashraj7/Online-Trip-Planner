package com.code.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.entity.Payment;
import com.code.exceptions.PaymentIdNotFoundException;
import com.code.repository.IPaymentRepository;

@Service
public class IPaymentServiceImpl implements IPaymentService {

	@Autowired
	IPaymentRepository repository;

	@Override
	public String addBill(Payment bill) {

		repository.save(bill);
		return "Payment Successfully completed";

	}

	@Override
	public Payment updateBill(Payment bill) throws PaymentIdNotFoundException {
		Optional<Payment> optional = repository.findById(bill.getPaymentId());

		if (optional.isPresent()) {
			repository.save(bill);
		} else
			throw new PaymentIdNotFoundException("bill with " + bill.getPaymentId() + " Id not found");

		return bill;
	}

	@Override
	public Payment viewBill(int billId) throws PaymentIdNotFoundException {
		Optional<Payment> optional = repository.findById(billId);
		if (optional.isEmpty()) {
			throw new PaymentIdNotFoundException("Bill with id " + billId + " does not Exist");
		}

		Payment bill = optional.get();
		return bill;

	}

	@Override
	public List<Payment> viewAllPayments() {
		List<Payment> list = repository.findAll();
		return list;
	}

}