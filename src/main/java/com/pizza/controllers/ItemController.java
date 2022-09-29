	package com.pizza.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizza.entities.Item;
import com.pizza.service.ItemService;
//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true") 
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*",allowCredentials ="true")
@RestController
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	// to get all items
	@GetMapping("/showAll")
	public ResponseEntity<?> findAllItems() {
		List<Item> items = itemService.findAllItem();
		System.out.println(items);
		return ResponseEntity.ok().body(items);

	}

	// get item by itemId
	@GetMapping("/byid/{itemId}")
	public ResponseEntity<?> findByItemId(@PathVariable("itemId") int itemId) {

		return ResponseEntity.ok().body(itemService.findByItemId(itemId));
		
	}
	
	//get all by type
	@GetMapping("/bytype/{type}")
	public ResponseEntity<?> getByType(@PathVariable("type") String type){
		
		return ResponseEntity.ok().body(itemService.findByType(type));	
	}
	
	/*
	 * 
	 * 
	 */
	
	@GetMapping("/itemList")
	public ResponseEntity<?> getItemsList(){
		
		return ResponseEntity.ok().body(itemService.getAllItems());
	}
	
	@GetMapping("/latest")
	public ResponseEntity<?> getLatestItemId(){
		
		return ResponseEntity.ok().body(itemService.getLatestItemId());
	}
	
	@PostMapping("/addItem")
	public ResponseEntity<?> addItem(@RequestBody Item newItem){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(itemService.addItem(newItem));
	} 
	
	@PutMapping("/update/{itemId}")
	public ResponseEntity<?> editItem(@PathVariable("itemId") int  itemId,
			@RequestBody Item updateItem)
	{
		
		return ResponseEntity.ok().body(itemService.editItem(itemId, updateItem));
//		try {
//			Item editItem = itemService.editItem(itemId, updateItem);
//			
//			if(editItem != null) {
//				return ResponseEntity.ok(editItem);
//			}else {
//				return ResponseEntity.internalServerError().body("Something went Wrong");
//			}
//		} catch (Exception e) {
//			return Response.error(e.getMessage());
//		}
	}
	
	@DeleteMapping("/deleteAll/{itemId}")
	public ResponseEntity<?> deleteItem(@PathVariable("itemId") int  itemId)
	{
		
		itemService.deleteItem(itemId);
		return ResponseEntity.ok().body("Item with id "+ itemId + " deleted");

//		try {
//			itemService.deleteItem(itemId);
//			
//			return ResponseEntity.ok("Item with id "+ itemId + " deleted");			
//				//return ResponseEntity.internalServerError().body("Something went Wrong");
//		} catch (Exception e) {
//			return Response.error(e.getMessage());
//		}
	}
	
	
	
	
	
}
