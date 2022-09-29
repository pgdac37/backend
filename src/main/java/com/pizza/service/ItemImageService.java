package com.pizza.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pizza.dto.DtoEntityConvertor;
import com.pizza.dto.ItemImgFormDto;
import com.pizza.entities.Item;
import com.pizza.entities.ItemImage;
import com.pizza.repo.ItemDao;
import com.pizza.repo.ItemImageDao;

@Service
@Transactional
public class ItemImageService {
	@Autowired
	private ItemImageDao itemImgDao;
	@Autowired
	private DtoEntityConvertor convertor;
	@Autowired
	private ItemDao itemDao;

	// add item
	public Map<String, Object> addItemImg(int itemId, ItemImgFormDto addItemImg) {
		Optional<Item> verifyItem = itemDao.findById(itemId);
		
		System.out.println(verifyItem);
		if (verifyItem != null) {
			ItemImage findByItem = itemImgDao.findByItem(verifyItem.get());
			ItemImage add = convertor.toItemEntity(addItemImg, itemId);
			if(findByItem == null) {
				itemImgDao.save(add);
				return Collections.singletonMap("insertedId", add.getItem());
			}
			else {
				itemImgDao.updateItemImage(findByItem.getItemImgId(),add.getData());
				return Collections.singletonMap("insertedId",add.getItem());
			}

			// itemImgDao.save(add);
			//itemImgDao.saveByUserId(add.getData(),add.getItem(),add.getItemImgTime(),itemId);
			
		}else {
			return null;
		}
	}

	// get image of item
	public ItemImage findItemById(int itemId) {
		Item forImage = new Item();
		forImage.setItemid(itemId);
		ItemImage getItem = itemImgDao.findByItem(forImage);
		if (getItem != null) {
			System.out.println("====>>" + getItem.getData());
			return getItem;
		}
		return null;
	}
	
	/*
	 * 
	 * 
	 */



	
	// get all image
//	public List<byte[]> getAllImage() {
//		List<ItemImage> itemImgList = itemImgDao.findAll();
////		List<Blob> blobImg = convertor.toblob(itemImgList);
//		return null;
//	}
}

//List<byte[]> imgList = new LinkedList<byte[]>();
//for(int i = 0; i<itemImgList.size(); i++) {
//	ItemImage transfer = itemImgList.get(i);
//	byte[] img = transfer.getData();
//	imgList.add(img);
//}
