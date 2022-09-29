package com.pizza.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.pizza.entities.ToppingImages;

public interface toppingImageDao extends JpaRepository<ToppingImages,Integer>{

	ToppingImages findByToppingImgId(int toppingImgId);
	
	@Query(value="select * from toppingimage where toppingId=?1",nativeQuery = true)
	ToppingImages findByToppingId(int toppingId);
	
	@Modifying
	@Query(value="update toppingimage set data=?1 where toppingId=?2",nativeQuery = true)
	void updateImg(byte[] data,int toppingId);
	
	@Modifying
	@Query(value="delete from toppingimage where toppingId=?1",nativeQuery = true)
	void deleteByToppingId(int toppingId);
}
