package com.pizza.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizza.dto.DeliveryDto;
import com.pizza.dto.PaymentDto;
import com.pizza.entities.DeliveryStatus;
import com.pizza.entities.Payments;
import com.pizza.service.DeliveryStatusService;
import com.pizza.service.PaymentService;

@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*",allowCredentials ="true")
@RestController
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	private PaymentService payService;
	@Autowired
	private DeliveryStatusService deliveryService;

	
	//add Payment
	@PostMapping("/addPayment")
	public ResponseEntity<?> addPayments(@RequestBody PaymentDto paymentDto)
	{
		try {
			Payments newPayment=payService.addPayments(paymentDto);

			DeliveryDto addDelivery = new DeliveryDto();
			addDelivery.setPayId(newPayment.getPayId());
			System.out.println("1");
			addDelivery.setDeliveryStatus("Order Received");
			System.out.println("2");
			addDelivery.setAddressid(paymentDto.getAddresspayId());
			System.out.println("3");
			addDelivery.setUserId(paymentDto.getUserId());
			System.out.println("4");
			addDelivery.setDeliveryTime(newPayment.getPayTimeStamp());
			System.out.println("5");
			
			
			DeliveryStatus added = deliveryService.addForDelivery(addDelivery);
			System.out.println("6");
			
			return ResponseEntity.ok().body(added);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
		}
	}
	
	@GetMapping("/showCurrent/{payId}")
	public ResponseEntity<?> showCurrentPayment(@PathVariable("payId")int payId)
	{
		try {
			
			Optional<Payments> curPayment = payService.findPayments(payId);
			
			if(curPayment.isEmpty()) return ResponseEntity.notFound().build();
			
			return ResponseEntity.ok().body(curPayment);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
	
	/*
	 * 
	 * 
	 */
	@GetMapping("/paymentList/{payId}")
	public ResponseEntity<?> getAllPayments	()
	{
//		try {
//			
			List<Payments> payments = payService.getAllPayments();

			return ResponseEntity.ok().body(payments);
			
//		} catch (Exception e) {
//			return ResponseEntity.internalServerError().body(e.getMessage());
//		}
	}
	@GetMapping("/paymentById/{payId}")
	public ResponseEntity<?> paymentById(@PathVariable("payId") int payId)
	{
//		try {
			
			Optional<Payments> payment = payService.getById(payId);
			
			if(payment.isPresent())
				return ResponseEntity.ok().body(payment);
			else
				return ResponseEntity.notFound().build();
			
//		} catch (Exception e) {
//			return ResponseEntity.internalServerError().body(e.getMessage());
//		}
	}
	@GetMapping("/paymentByUserId/{userId}")
	public ResponseEntity<?> paymentByUserId(@PathVariable("userId") int userId)
	{
//		try {
			
			List<Payments> payment = payService.getByUserId(userId);
			
//			if(payment.isPresent())
				return ResponseEntity.ok().body(payment);
//			else
//				return ResponseEntity.notFound().build();
//			
//		} catch (Exception e) {
//			return ResponseEntity.internalServerError().body(e.getMessage());
//		}
	}
	
	@GetMapping("/paymentListName/{payName}")
	public ResponseEntity<?> paymentListName(@PathVariable("payName") int payId)
	{
//		try {
			
			Optional<Payments> payment = payService.getById(payId);
			
			if(payment.isPresent())
				return ResponseEntity.ok().body(payment);
			else
				return ResponseEntity.notFound().build();
			
//		} catch (Exception e) {
//			return ResponseEntity.internalServerError().body(e.getMessage());
//		}
	}
	
	@PostMapping("/updatePayments/{id}")
	public ResponseEntity<?> updatePayment(@PathVariable("id") int payId, @RequestBody PaymentDto paymentDto)
	{
//		try {
			
			Payments payment = payService.updatePayment(payId, paymentDto.getPayStatus());
			
//			if(payment.isPresent())
				return ResponseEntity.ok().body(payment);
//			else
//				return ResponseEntity.notFound().build();
				
//		} catch (Exception e) {
//			return ResponseEntity.internalServerError().body(e.getMessage());
//		}
	}
	
}
