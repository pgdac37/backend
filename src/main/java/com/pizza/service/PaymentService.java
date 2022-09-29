package com.pizza.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pizza.dto.DtoEntityConvertor;
import com.pizza.dto.PaymentDto;
import com.pizza.entities.Payments;
import com.pizza.repo.CartDao;
import com.pizza.repo.PaymentDao;

@Service
@Transactional
public class PaymentService {
	@Autowired
	private DtoEntityConvertor convertor;
	@Autowired
	private PaymentDao paymentDao;
	@Autowired
	private CartDao cartDao;
	
	public Payments addPayments(PaymentDto paymentDto)
	{
		int totalAmount = cartDao.findTotalAmount(paymentDto.getUserId());
		paymentDto.setMode("Card Payment");
		paymentDto.setPayStatus("success");
		paymentDto.setPayTimeStamp(new Date());
		System.out.println(totalAmount);
		if(totalAmount != 0) {
			Payments add=convertor.toPaymentEntity(paymentDto,totalAmount);
			paymentDao.save(add);
			return add;
		}
		return null;
	}
	
	public Optional<Payments> findPayments(int payId) {
		Optional<Payments> togetPayments = paymentDao.findById(payId);
		return togetPayments;

	}
	
	/*
	 * 
	 * 
	 * 
	 * 
	 */

	public List<Payments> getAllPayments() {
		
		return paymentDao.findAll();
	}

	public Optional<Payments> getById(int payId) {
	
		return paymentDao.findById(payId);
	}

	public List<Payments> getByUserId(int userId) {
		
//		return paymentDao.findByUsersId(userId);
		return null;
	}

	public Payments updatePayment(int payId, String payStatus) {
		Payments payment = paymentDao.findById(payId).get();
		payment.setPayStatus(payStatus);
		
		return paymentDao.save(payment);
	}
}
