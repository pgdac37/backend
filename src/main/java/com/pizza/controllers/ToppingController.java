package com.pizza.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizza.entities.Toppings;
import com.pizza.service.ToppingsService;

@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*",allowCredentials ="true")
@RestController
@RequestMapping("/toppings")
public class ToppingController {

	@Autowired
	private ToppingsService toppingsService;

	//get all toppings
	@GetMapping("/showAll")
	public ResponseEntity<?> findAllToppings() {
		
		return ResponseEntity.ok().body(toppingsService.findAllToppings());
		
//		try {
//			List<Toppings> checkTopping = toppingsService.findAllToppings();
//			if (checkTopping == null)
//				return Response.error("Empty");
//			return Response.success(checkTopping);
//		} catch (Exception e) {
//			return Response.error(e.getMessage());
//		}
	}

	//get topping by topping ID
	@GetMapping("/{toppingId}")
	public ResponseEntity<?> findByToppingId(@PathVariable("toppingId") int toppingId) {
		
		return ResponseEntity.ok().body(toppingsService.findByToppingId(toppingId));

//		try {
//			System.out.println("In controller");
//			List<Toppings> result = toppingsService.findByToppingId(toppingId);
//			System.out.println(result);
//			if (result == null)
//				return Response.error("No result");
//			return Response.success(result);
//		} catch (Exception e) {
//			return Response.error(e.getMessage());
//		}
	}
	
	/*
	 * 
	 * 
	 * 
	 */
		/////////////////LIST OG TOPPINGS//////////////
	@GetMapping("/toppingList")
	public ResponseEntity<?> gettoppingList() {
		
		return ResponseEntity.ok().body(toppingsService.findAllToppings());

	}
	/////////////////LIST OG TOPPING by ID//////////////
	@GetMapping("/toppingListbyId/{id}")
	public ResponseEntity<?> toppingListbyId(@PathVariable("id") int toppingId) {
		
		return ResponseEntity.ok().body(toppingsService.findByToppingId(toppingId));
	}
	/////////////////INSERT TOPPINGS//////////////
	@PostMapping("/addToppings")
	public ResponseEntity<?> addTopping(@RequestBody Toppings topping) {
		toppingsService.addTopping(topping);
		return ResponseEntity.ok().body("Topping added!!");
	}
	/////////////////Update TOPPINGS//////////////
	@PutMapping("/updateTopping/{id}")
	public ResponseEntity<?> updateTopping(@PathVariable("id") int toppingId, @RequestBody Toppings topping) {
		
		return ResponseEntity.ok().body(toppingsService.updateTopping(toppingId, topping));
	}
	/////////////////////DELETE TOPPINGS/////////////////////////
	@DeleteMapping("/deleteTopping/{id}")
	public ResponseEntity<?> deleteTopping(@PathVariable("id") int toppingId) {
		toppingsService.deleteTopping(toppingId);
		return ResponseEntity.ok().body("deleted");
	}

}
