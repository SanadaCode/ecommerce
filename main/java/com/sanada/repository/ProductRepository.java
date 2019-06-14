package com.sanada.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sanada.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	 
	List<Product> findByVenditoreId(int id);
	Product findByProductName(String name);
	@Query("select p from Product p where p.productName like %:name%")
	List<Product> searchProductByName(String name);
	
	
}
