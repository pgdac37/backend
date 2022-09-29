package com.pizza.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pizza.entities.Toppings;
import com.pizza.repo.ToppingDao;
import com.pizza.repo.toppingImageDao;

@Service
@Transactional
public class ToppingsService {

	@Autowired
	private ToppingDao toppingDao;
	
	@Autowired
	private toppingImageDao toppingImgDao;

	public List<Toppings> findAllToppings() {
		return toppingDao.findAll();
	}

	public List<Toppings> findByToppingId(int toppingId) {
		Optional<Toppings> toppings = toppingDao.findById(toppingId);
		List<Toppings> toppingList = new ArrayList<Toppings>();
		toppingList.add(toppings.get());
		return toppingList;
	}

	public Toppings addTopping(Toppings topping) {
		
		return toppingDao.save(topping);
	}

	public Toppings updateTopping(int toppingId, Toppings topping2) {
		
		Optional<Toppings> temp = toppingDao.findById(toppingId);
		if(temp.isPresent()) {
			temp.get().setPrice(topping2.getPrice());
			temp.get().setToppingName(topping2.getToppingName());
			return temp.get();
		}
		
		
		return topping2;
	}

	public void deleteTopping(int toppingId) {
		
		Toppings topping = toppingDao.findById(toppingId).get();
		
		if(toppingImgDao.existsByToppings(topping))
			toppingImgDao.deleteByToppingId(toppingId);
		
		toppingDao.deleteById(toppingId);
	}
}
