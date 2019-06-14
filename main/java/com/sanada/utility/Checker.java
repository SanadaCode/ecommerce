package com.sanada.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sanada.entity.Order;
import com.sanada.entity.Product;
import com.sanada.entity.User;
import com.sanada.repository.OrderDetailRepository;
import com.sanada.repository.OrderRepository;

@Component
public class Checker {

	public static boolean isDeleted(Product product) {
		if(product.getDeleted()) {
			return true;
		}else {
			return false;
		}
	}
	


}
