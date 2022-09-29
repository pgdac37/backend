package com.pizza.controllers;

import javax.validation.Valid;

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
import com.pizza.dto.AddressDto;
import com.pizza.service.AddressService;

@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*",allowCredentials ="true")
@RestController
@RequestMapping("/address")
public class AddressController {
	@Autowired
	private AddressService addressService;
	
	//get address by id
	@GetMapping("/getaddressbyid/{AddressId}")
	public ResponseEntity<?> getAddressById(@PathVariable("AddressId") int AddressId){
		    System.out.println("inside getAddress by ID");
			return ResponseEntity.status(HttpStatus.CREATED).body(addressService.getAddressById(AddressId));
	}
	
	//add new address of a particular user
	@PostMapping("/addAddress")
	public ResponseEntity<?> addAddress(@RequestBody @Valid AddressDto addressdto){
	    System.out.println("inside add address controller"+addressdto);
		return ResponseEntity.status(HttpStatus.CREATED).body(addressService.addAddress(addressdto));
	}
	
	//Update address of a particular user
	@PutMapping("/updateAddress/{AddressId}")
	public ResponseEntity<?> updateAddress(@PathVariable("AddressId")int addId, @RequestBody AddressDto addressdto){
		System.out.println("inside update address controller"+addressdto);
		System.out.println(addressService.updateAddress(addId, addressdto));
		return ResponseEntity.status(HttpStatus.CREATED).body("Address added successfully");	
	}
	
	//get all address of a user 
	@GetMapping("/getaddress/{userId}")
	public ResponseEntity<?> getAddress(@PathVariable("userId") int userid ){
		System.out.println("inside get address contoller of id" + userid);
		return ResponseEntity.status(HttpStatus.CREATED).body(addressService.getAddresses(userid));	
	}
	
	@DeleteMapping("/deleteByAddressId/{addressId}")
	public ResponseEntity<?> deleteAddress(@PathVariable ("addressId")int addressId)
	{
		int count=addressService.deleteByAddressId(addressId);
		return ResponseEntity.status(HttpStatus.CREATED).body(count + " Address deleted");	
	}
	
}








