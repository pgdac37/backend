package com.pizza.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pizza.dto.ItemSizeDto;
import com.pizza.entities.Item;
import com.pizza.repo.ItemDao;
import com.pizza.repo.ItemImageDao;
import com.pizza.repo.ItemSizeDao;

@Service
@Transactional
public class ItemService {

	@Autowired
	private ItemDao itemDao;
	@Autowired
	private ItemSizeDao itemSizeDao;
	@Autowired
	private ItemImageDao itemImageDao;
	
	// show all items in the list
		public List<Item> findAllItem() {
			System.out.println("inside find all Item Method");
			return itemDao.findAllPizza();
		}

	// show selected item by it's Id
	public Item findByItemId(int itemId) {
		Item item = itemDao.getReferenceById(itemId);
		return item;
	}
	
	public List<Item> findByType(String type){
		List<Item> listBytype = itemDao.findByType(type);
		if(listBytype != null) {
			return listBytype;
		}
		return null;
	}
	
	/*
	 * 
	 * 
	 */
	
	public List<Item> getAllItems() {
		
		List<Item> listOfItemImages = itemDao.findAll();
		
		return listOfItemImages;
	}

	public  Integer getLatestItemId() {
		
		return itemDao.getLatestItemId();
	}

	public Item addItem(Item newItem) {
		
		return itemDao.save(newItem);

	}

	public Item editItem(int itemId, Item updateItem) {
		Optional<Item> tempItem = itemDao.findById(itemId);
		
		if(tempItem.isPresent()) {
			Item updatingItem = tempItem.get();
			updatingItem.setItemName(updateItem.getItemName());
			updatingItem.setDescription(updateItem.getDescription());
			updatingItem.setType(updateItem.getType());
			
			return itemDao.save(updatingItem);
			
		}
				
		return null;
	}

	public void deleteItem(int itemId) {
		itemSizeDao.deleteByItemId(itemId);
		itemImageDao.deleteByItemId(itemId); 
		itemDao.deleteById(itemId);
	}
}
