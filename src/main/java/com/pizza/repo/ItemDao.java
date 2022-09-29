package com.pizza.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pizza.entities.Item;

public interface ItemDao extends JpaRepository<Item, Integer>{
	
	//@Query(value="SELECT * from item WHERE type = ?1",nativeQuery = true)
	@Query("select i from Item i where i.type =?1")
	List<Item> findByType(String type);
	// "SELECT * from item WHERE type like '%veg'"
	
	List<Item> findAll();

	@Query(value="select itemId from item order by itemId desc limit 1",nativeQuery = true)
	Integer getLatestItemId();
	
	@Query(value="select * from item where type in ('veg' , 'Nonveg')",nativeQuery = true)
	List<Item> findAllPizza();
}
