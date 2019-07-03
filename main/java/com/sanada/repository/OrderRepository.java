package com.sanada.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sanada.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	@Query("Select o From Order o where o.user.id=:id and o.state.cod=:cod ")
	Order getCartOfUser(int id, String cod);
	
	@Query("Select o From Order o where o.user.id=:id and o.state.cod!=:cod order by date DESC")
	List<Order> getListOrderOfClient(int id, String cod);
	
	Order findById(int id);
	
	
}
