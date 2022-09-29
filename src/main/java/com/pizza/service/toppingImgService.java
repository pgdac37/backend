package com.pizza.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pizza.dto.DtoEntityConvertor;
import com.pizza.dto.ToppingImageDto;
import com.pizza.entities.ToppingImages;
import com.pizza.entities.Toppings;
import com.pizza.repo.ToppingDao;
import com.pizza.repo.toppingImageDao;

@Service
@Transactional
public class toppingImgService {

	@Autowired
	private toppingImageDao topImgDao;
	@Autowired
	private DtoEntityConvertor convertor;
	@Autowired
	private ToppingDao toppingDao;
	
	public Map<String, Object> addToppingImage(int toppingId,ToppingImageDto dto){
		Toppings verifyItem = toppingDao.getReferenceById(toppingId);
		System.out.println(verifyItem);
		if (verifyItem != null) {
			ToppingImages checkImg = topImgDao.findByToppingId(toppingId);
			ToppingImages add = convertor.toToppingImageEntity(dto, toppingId);
			if(checkImg == null) {
				topImgDao.save(add);
				return Collections.singletonMap("insertedId", add.getToppings());
			}
			else {
				topImgDao.updateImg(add.getData(),toppingId);
				return Collections.singletonMap("insertedId", add.getToppings());
			}	
		}else {
			return null;
		}
	}
	

	public ToppingImages findByImageId(int toppingId)
	{
		ToppingImages result=topImgDao.findByToppingId(toppingId);
		System.out.println(result);
		return result;
	}
	
	public List<ToppingImages> showAll()
	{
		return topImgDao.findAll();
	}
	
}
