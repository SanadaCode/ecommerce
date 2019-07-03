package com.sanada.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sanada.entity.Order;
import com.sanada.entity.OrderDetail;
import com.sanada.entity.Product;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

	@Query("Select o From OrderDetail o where o.order=:order and o.product=:product ")
	OrderDetail getProductOfOrder(Order order, Product product);
	
	List<OrderDetail>  findByOrder(Order order);
	
	@Query("Select o From OrderDetail o where o.order=:order and o.order.state.cod=:cod ")
	List<OrderDetail>  findExistingOrder(Order order, String cod);
	
	@Query("Select o From OrderDetail o where o.product.venditoreId=:id "
			+ "and o.order.state.cod!=:cod order by o.order.date DESC")
	List<OrderDetail> findOrderForSeller(int id, String cod);
	
	OrderDetail findById(int id);
	
}
