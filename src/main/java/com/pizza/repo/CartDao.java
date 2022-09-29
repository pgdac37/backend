package com.pizza.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.pizza.entities.Cart;
import com.pizza.entities.DeliveryStatus;
import com.pizza.entities.Users;

public interface CartDao extends JpaRepository<Cart, Integer>{
	@Query(value = "select * from cart where userid=?1 and cartstatus=?2 order by cartid desc;", nativeQuery = true)
	List<Cart> findByUserStatus(Users user,int status);
	
	@Query(value = "select sum(price*quantity) as totalamount from cart where userid=?1", nativeQuery = true)
	int findTotalAmount(int userid);
	
	@Query(value = "update cart set cartstatus=0 where userId =?1", nativeQuery = true)
	@Modifying
	void changeStatus(int userId);
	
	@Query(value = "update cart set deliveryId=?1 where userId =?2 and deliveryId is null", nativeQuery = true)
	@Modifying
	int addForeign(int delid,int userid);
	
	List<Cart> findBydeliveryId(DeliveryStatus deliveryStatus);
}
