package com.pizza.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pizza.entities.DeliveryStatus;
import com.pizza.entities.Users;

public interface DeliveryStatusDao extends JpaRepository<DeliveryStatus	,Integer>{
	List<DeliveryStatus> findByUsers(Users user);
}
