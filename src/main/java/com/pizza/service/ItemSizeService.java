package com.pizza.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pizza.dto.ItemSizeDto;
import com.pizza.entities.Item;
import com.pizza.entities.ItemSize;
import com.pizza.repo.ItemDao;
import com.pizza.repo.ItemSizeDao;

@Service
@Transactional
public class ItemSizeService {
	@Autowired
	private ItemSizeDao itemSizeDao;
	
	@Autowired
	private ItemDao itemDao;
	
	@Autowired
    private ModelMapper modelMapper;
	
	//show by sizeId
	public List<ItemSize> SizeOfPizza(String size, int itemId){
		List<ItemSize> thisSize = itemSizeDao.getSizeOfPizza(size, itemId);
		return thisSize;
	}
	
	public ItemSize addItemSize(ItemSizeDto newItemSizeDto) {
		Optional<Item> i = itemDao.findById(newItemSizeDto.getItemId());
		Item n = new Item();
		n.setItemid(i.get().getItemid());
		ItemSize is = new ItemSize(n, newItemSizeDto.getSize(), newItemSizeDto.getPrice());
		return itemSizeDao.save(is);
	}

	public Object editItemSize(int itemId, ItemSize updateItem) {
		Optional<ItemSize> tempItem = itemSizeDao.findById(itemId);

		if (tempItem.isPresent()) {
			ItemSize updatingItem = tempItem.get();
			updatingItem.setPrice(updateItem.getPrice());
			updatingItem.setSize(updateItem.getSize());

			return itemSizeDao.save(updatingItem);

		}

		return null;
	}

	public ItemSize addItemSize2(ItemSize newItemSize) {
		
		return itemSizeDao.save(newItemSize);
	}
}