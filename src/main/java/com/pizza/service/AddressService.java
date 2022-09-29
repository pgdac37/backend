package com.pizza.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pizza.repo.AddressDao;
import com.pizza.repo.UserRepository;
import com.pizza.dto.AddressDto;
import com.pizza.entities.Address;
import com.pizza.entities.Users;

@Service
@Transactional
public class AddressService {
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private UserRepository userDao;
	
	//findByAddressId
	public Address getAddressById(int id) {
		System.out.println("In service");
		Address getAddressById = addressDao.getReferenceById(id);
		 if(getAddressById != null) {
			 return getAddressById;
		 }
		 return null;
	}
	
	//save address of a users
	public Address addAddress(AddressDto addressdto) {
		int userId = addressdto.getUserId();
		Users user = userDao.getReferenceById(userId);
		Address address = mapper.map(addressdto, Address.class);
		address.setUser(user);
		//Address add = convertor.toAddressEntity(addressdto);
		return addressDao.save(address);
	}
	
	//Update address of a users
	public Address updateAddress(int AddressId, AddressDto addressdto) {
		Address addressById =  getAddressById(AddressId);
		if(addressById != null){
			Users user = userDao.getReferenceById(addressdto.getUserId());
			Address updatedAddress =  mapper.map(addressdto, Address.class);
			updatedAddress.setUser(user);
			updatedAddress.setAddressId(addressById.getAddressId());
			System.out.println("--->"+updatedAddress);
			addressDao.save(updatedAddress);
			return updatedAddress;
		}
		return null;
	}
	
	// to get addresses of user
	public List<Address> getAddresses(int userid){
		Users user = userDao.getReferenceById(userid);
//		Address toGetAddress = new Address();
//		Users getUserAdd= new Users();
//		getUserAdd.setUserId(userid);
//		toGetAddress.setUser(getUserAdd);
		List<Address> getFinalAddress = addressDao.findByUser(user);
		return getFinalAddress;
	}
	
	//delete certain address
	public int deleteByAddressId(int addressId) {
		addressDao.deleteById(addressId);
		return 1;
	}
}

//public List<Address> getAddresses(int userid){
//	Address toGetAddress = new Address();
//	Users getUserAdd= new Users();
//	getUserAdd.setUserId(userid);
//	toGetAddress.setUser(getUserAdd);
//	List<Address> getFinalAddress = addressDao.findByUser(getUserAdd);
//		return getFinalAddress;
//}







