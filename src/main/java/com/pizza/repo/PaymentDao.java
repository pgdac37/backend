package com.pizza.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pizza.entities.Payments;

public interface PaymentDao extends JpaRepository<Payments, Integer>{

//	List<Payments> findByUsersId(int userId);

}
