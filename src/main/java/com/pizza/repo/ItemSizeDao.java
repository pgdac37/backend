package com.pizza.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.pizza.entities.ItemSize;

public interface ItemSizeDao extends JpaRepository<ItemSize, Integer>{
	@Query(value="SELECT * from itemsize WHERE size=?1 and itemId=?2",nativeQuery = true)
	List<ItemSize> getSizeOfPizza(String size,Integer itemId);
	
	//ItemSize findByItemId(int itemId);
	@Query(value="SELECT * from itemsize WHERE itemId=?1",nativeQuery = true)
	List<ItemSize> findAllByItemId(int itemId);
	
	@Modifying
	@Query(value="delete from itemsize WHERE itemId=?1",nativeQuery = true)
	void deleteByItemId(int itemId);
	
//	@Modifying
//	@Query("delete from ItemSize i where i.sizeId=?1")
//	int deleteSizeById(int sizeId);
}
