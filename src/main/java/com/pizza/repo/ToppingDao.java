package com.pizza.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pizza.entities.Toppings;


public interface ToppingDao extends JpaRepository<Toppings, Integer>{

}
