package com.pizza.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.pizza.dto.ItemSizeDto;
import com.pizza.entities.ItemSize;
import com.pizza.repo.ItemSizeDao;
import com.pizza.service.ItemSizeService;

@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*",allowCredentials ="true")
@Controller
@RestController
@RequestMapping("/itemSize")
public class ItemSizeController {
	@Autowired
	private ItemSizeService itemSizeService;
	
	@Autowired
	private ItemSizeDao itemSizeDao;
	
	// show itemSize by itemId
	@GetMapping("/{itemid}/{size}")
	public ResponseEntity<?> fetchByItemSizeId(@PathVariable("itemid") int itemId, @PathVariable("size") String size) {
		
		return ResponseEntity.ok().body(itemSizeService.SizeOfPizza(size, itemId));
		
//		try {
//			List<ItemSize> sizeOfPizza = itemSizeService.SizeOfPizza(size, itemId);
//			if(!sizeOfPizza.isEmpty()) {
//				return Response.success(sizeOfPizza);
//			}else {
//				return Response.error("This pizaa is not available in this Size");
//			}
//		} catch (Exception e) {
//			return Response.error(e.getMessage());
//		}
	}
	
	@PostMapping("/addItemSize")
	public ResponseEntity<?> addItem(@RequestBody ItemSizeDto newItemSize){
		
		System.out.println("in add item");
		return ResponseEntity.status(HttpStatus.CREATED).body(itemSizeService.addItemSize(newItemSize));
	} 
	

	
	@PutMapping("/update/{itemId}")
	public ResponseEntity<?> editItem(@PathVariable("itemId") int  itemId,
			@RequestBody ItemSize updateItem)
	{
		
		return ResponseEntity.ok().body(itemSizeService.editItemSize(itemId, updateItem));
	}
	
	@DeleteMapping("/delete/{itemId}")
	public ResponseEntity<?> delete(@PathVariable("itemId") int  itemId)
	{
		itemSizeDao.deleteById(itemId);
		return ResponseEntity.ok().body("Deleted");
	}
	
	@GetMapping("/itemsizeId/{itemId}")
	public ResponseEntity<?> getItemSize(@PathVariable("itemId") int  itemId)
	{
		
		return ResponseEntity.ok().body(itemSizeDao.findAllByItemId(itemId));
		//return ResponseEntity.ok().body(itemSizeDao.findById(itemId));
	}
}
