package com.pizza.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.pizza.entities.Item;
import com.pizza.entities.ItemImage;

public interface ItemImageDao extends JpaRepository<ItemImage, Integer>{
	ItemImage findByItem(Item item);
	
	@Modifying
	@Query(value="delete from itemimage WHERE itemId=?1",nativeQuery = true)
	void deleteByItemId(int itemId);

	@Modifying
	@Query(value="update itemimage set data=?2 WHERE itemImgId=?1",nativeQuery = true)
	void updateItemImage(int itemImgId, byte[] data);	
}
