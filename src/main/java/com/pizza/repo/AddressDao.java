package com.pizza.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pizza.entities.Address;
import com.pizza.entities.Users;

public interface AddressDao extends JpaRepository<Address, Integer> {
	List<Address> findByUser(Users user);
}