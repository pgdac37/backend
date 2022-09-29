package com.pizza.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pizza.dto.DeliveryDto;
import com.pizza.dto.DtoEntityConvertor;
import com.pizza.entities.DeliveryStatus;
import com.pizza.entities.Users;
import com.pizza.repo.DeliveryStatusDao;
import com.pizza.repo.UserRepository;
import com.pizza.security.EmailSender;

@Service
@Transactional
public class DeliveryStatusService {
	@Autowired
	private DeliveryStatusDao statusDao;
	@Autowired
	private DtoEntityConvertor convertor;
	@Autowired
	private CartService cartService;
	
	@Autowired
    private ModelMapper modelMapper;

	@Autowired
	private EmailSender mailService;
	
	@Autowired
	private UserRepository userRepo;
	
	//get list of delivery by userid
	public List<DeliveryStatus> findByUserId(int userId) {
		Users getDeliveries = new Users();
		getDeliveries.setUserId(userId);
		List<DeliveryStatus> deliveryList = statusDao.findByUsers(getDeliveries);
		return deliveryList;
	}

	// get all delivery
	public List<DeliveryStatus> fetchAllDelivery() {
		List<DeliveryStatus> deliveryList = statusDao.findAll();
		return deliveryList;
	}

	public DeliveryStatus addForDelivery(DeliveryDto deliveryDto) {
		DeliveryStatus add = convertor.toDelivery(deliveryDto);
		//DeliveryStatus add = modelMapper.map(deliveryDto, DeliveryStatus.class);
		
		statusDao.save(add);
		cartService.addForeignKey(add.getDeliveryId(), deliveryDto.getUserId());
		return add;
	}
	
	public DeliveryStatus findByItemId(int itemId) {
		
		return statusDao.findById(itemId).get();
	}

	public String updateDeliveryStatus(int deliveryId, DeliveryStatus deliveryStatus) {
		
		DeliveryStatus updated = statusDao.findById(deliveryId).get();
		updated.setDeliveryStatus(deliveryStatus.getDeliveryStatus());
		statusDao.save(updated);
		System.out.println(deliveryStatus);
		
		Users user = updated.getUsers();
		mailService.sendEmail(user.getEmail(), "Changed Status: ", " delivery status updated to: " + deliveryStatus.getDeliveryStatus());
		
		return deliveryStatus.toString();
	}

//	@Autowired
//	private DeliveryStatusDao statusDao;
//	@Autowired
//	private DtoEntityConvertor convertor;
//	@Autowired
//	private CartService cartService;
//	
//	@Autowired
//    private ModelMapper modelMapper;
//
//	//get list of delivery by userid
//	public List<DeliveryStatus> findByUserId(int userId) {
//		Users getDeliveries = new Users();
//		getDeliveries.setUserId(userId);
//		List<DeliveryStatus> deliveryList = statusDao.findByUsers(getDeliveries);
//		return deliveryList;
//	}
//
//	// get all delivery
//	public List<DeliveryStatus> fetchAllDelivery() {
//		List<DeliveryStatus> deliveryList = statusDao.findAll();
//		return deliveryList;
//	}
//
//	public DeliveryStatus addForDelivery(DeliveryDto deliveryDto) {
//		DeliveryStatus add = convertor.toDelivery(deliveryDto);
//	//	DeliveryStatus add = modelMapper.map(deliveryDto, DeliveryStatus.class);
//		
//		statusDao.save(add);
//		cartService.addForeignKey(add.getDeliveryId(), deliveryDto.getUserId());
//		return add;
//	}
}
