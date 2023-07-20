package com.code.service;

import java.util.List;

import com.code.entity.Payment;
import com.code.exceptions.PaymentIdNotFoundException; 

public interface IPaymentService {

	public String addBill(Payment bill);
	public Payment updateBill(Payment bill) throws PaymentIdNotFoundException;
	public Payment viewBill(int billId) throws PaymentIdNotFoundException;
	public  List<Payment> viewAllPayments();
}