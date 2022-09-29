package com.pizza.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizza.entities.DeliveryStatus;
import com.pizza.service.DeliveryStatusService;
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*",allowCredentials ="true")
@RestController
@RequestMapping("/DeliveryStatus")
public class DeliveryStatusController {
	
	@Autowired
	private DeliveryStatusService deliveryService;
	
	@GetMapping("/alldelivery")
	public ResponseEntity<?> findAllToppings() {
		
		return ResponseEntity.ok().body(deliveryService.fetchAllDelivery());
		
//		try {
//			List<Toppings> checkTopping = toppingsService.findAllToppings();
//			if (checkTopping == null)
//				return Response.error("Empty");
//			return Response.success(checkTopping);
//		} catch (Exception e) {
//			return Response.error(e.getMessage());
//		}
	}
	
	@GetMapping("/dStatus/{deliveryId}")
	public ResponseEntity<?> findByItemId(@PathVariable("deliveryId") int deliveryId) {

		return ResponseEntity.ok().body(deliveryService.findByItemId(deliveryId));
		
	}
	
	@PutMapping("/dStatus/{deliveryId}")
	public ResponseEntity<?> updateDeliveryStatus(@PathVariable("deliveryId") int deliveryId, @RequestBody DeliveryStatus deliveryStatus) {
		System.out.println(deliveryStatus);
		return ResponseEntity.ok().body(deliveryService.updateDeliveryStatus(deliveryId, deliveryStatus));
		
	}

}
