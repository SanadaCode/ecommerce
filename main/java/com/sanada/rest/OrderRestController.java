package com.sanada.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sanada.dto.OrderDTO;
import com.sanada.dto.OrderDetailUserDTO;
import com.sanada.dto.OrderSellerDTO;
import com.sanada.error.UserNotAuthorizedException;
import com.sanada.model.MessageDTO;
import com.sanada.model.MessageEnum;
import com.sanada.service.OrderService;
import com.sanada.service.UserService;

@RestController
@RequestMapping("/order")
public class OrderRestController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;

	@PostMapping("/add")
	private ResponseEntity<MessageDTO> addProductToCart(@RequestParam("name") String name, @RequestParam("id") int id,
			@RequestParam("quantity") int quantity) {
		if (!this.orderService.isUserAClient(id)) {
			throw new UserNotAuthorizedException(MessageEnum.NOT_AUTHORIZED.getMessage());
		} else {
			return new ResponseEntity<MessageDTO>(this.orderService.addProductToCart(id, name, quantity),
					HttpStatus.OK);
		}
	}

	@DeleteMapping("/delete")
	private ResponseEntity<MessageDTO> deleteProductFromCart(@RequestParam("name") String name,
			@RequestParam("id") int id) {

		if (!this.orderService.isUserAClient(id)) {
			throw new UserNotAuthorizedException(MessageEnum.NOT_AUTHORIZED.getMessage());
		} else {
			return new ResponseEntity<MessageDTO>(
					this.orderService.deleteProducFromCart(id, name), 
					HttpStatus.OK);
		}
	}

	@PutMapping("/change")
	private ResponseEntity<MessageDTO> modifyQuantityInOrder(@RequestParam("name") String name,
			@RequestParam("id") int id, @RequestParam("quantity") int quantity) {

		if (!this.orderService.isUserAClient(id)) {
			throw new UserNotAuthorizedException(MessageEnum.NOT_AUTHORIZED.getMessage());
		} else {
			return new ResponseEntity<MessageDTO>(
					this.orderService.modifyQuantityOfProductInOrder(id, quantity, name),
				 HttpStatus.OK);
		}
	}
	
	@PutMapping("/confirm")
	private ResponseEntity<MessageDTO> confirmOrder(@RequestParam("id") int id) {
		
		if (!this.orderService.isUserAClient(id)) {
			throw new UserNotAuthorizedException(MessageEnum.NOT_AUTHORIZED.getMessage());
		} else {
			return new ResponseEntity<MessageDTO>(
					this.orderService.confirmOrder(id),
					HttpStatus.OK);
		}
	}
	
	@GetMapping("/order")
	private ResponseEntity<List<OrderDTO>> orderOfUser(@RequestParam("id") int id) {
		if (!this.orderService.isUserAClient(id)) {
			throw new UserNotAuthorizedException(MessageEnum.NOT_AUTHORIZED.getMessage());
		} else {
			return new ResponseEntity<List<OrderDTO>>(
					this.orderService.getListOrderOfClient(id),
					HttpStatus.OK);
		}
	}
	
	@GetMapping("/detail")
	private ResponseEntity<List<OrderDetailUserDTO>> detailOrder(@RequestParam("id") int id,
			@RequestParam("orderId") int orderId) {
		if (!this.orderService.isUserAClient(id)) {
			throw new UserNotAuthorizedException(MessageEnum.NOT_AUTHORIZED.getMessage());
		} else {
			return new ResponseEntity<List<OrderDetailUserDTO>>(
					this.orderService.getOrderDetail(orderId),
					HttpStatus.OK);
		}
	}
	
	@GetMapping("/cart")
	private ResponseEntity<List<OrderDetailUserDTO>> detailOrder(@RequestParam("id") int id) {
		if (!this.orderService.isUserAClient(id)) {
			throw new UserNotAuthorizedException(MessageEnum.NOT_AUTHORIZED.getMessage());
		} else {
			return new ResponseEntity<List<OrderDetailUserDTO>>(
					this.orderService.getCart(id),
					HttpStatus.OK);
		}
	}
	
	@GetMapping("/seller")
	private ResponseEntity<List<OrderSellerDTO>> orderForSeller(@RequestParam("id") int id) {
		if (!this.userService.authorizedSeller(id)) {
			throw new UserNotAuthorizedException(MessageEnum.NOT_AUTHORIZED.getMessage());
		} else {
			return new ResponseEntity<List<OrderSellerDTO>>(
					this.orderService.getListOrderOfSeller(id),
					HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/cancel")
	private ResponseEntity<MessageDTO> cancelOrderForProduct(@RequestParam("id") int id,
			@RequestParam("orderId") int orderId) {
		if (!this.userService.foundUser(id)) {
			throw new UserNotAuthorizedException(MessageEnum.NOT_AUTHORIZED.getMessage());
		} else {
			return new ResponseEntity<MessageDTO>(
					this.orderService.cancelOrderForProduct(orderId, id),
					HttpStatus.OK);
		}
	}
	
	@PutMapping("/send")
	private ResponseEntity<MessageDTO> setOrderToSent(@RequestParam("id") int id,
			@RequestParam("orderId") int orderId) {
		if (!this.userService.foundUser(id)) {
			throw new UserNotAuthorizedException(MessageEnum.NOT_AUTHORIZED.getMessage());
		} else {
			return new ResponseEntity<MessageDTO>(
					this.orderService.setOrderToSent(orderId, id),
					HttpStatus.OK);
		}
	}

}
