package com.sanada.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanada.dto.OrderDTO;
import com.sanada.dto.OrderDetailDTO;
import com.sanada.dto.OrderDetailUserDTO;
import com.sanada.dto.OrderSellerDTO;
import com.sanada.entity.Order;
import com.sanada.entity.OrderDetail;
import com.sanada.entity.Product;
import com.sanada.entity.Transaction;
import com.sanada.entity.User;
import com.sanada.error.BadInputException;
import com.sanada.error.ProductNotFoundException;
import com.sanada.error.UserNotAuthorizedException;
import com.sanada.model.MessageDTO;
import com.sanada.model.MessageEnum;
import com.sanada.model.RoleCod;
import com.sanada.model.StateOfOrder;
import com.sanada.repository.OrderDetailRepository;
import com.sanada.repository.OrderRepository;
import com.sanada.repository.ProductRepository;
import com.sanada.repository.StateOrderRepository;
import com.sanada.repository.UserRepository;
import com.sanada.utility.Mapper;

@Service
public class OrderService {

	private OrderDetailRepository orderDetailRepository;
	private OrderRepository orderRepository;
	private ProductRepository productRepository;
	private UserRepository userRepository;
	private StateOrderRepository stateOrderRepository;

	@Autowired
	public OrderService(OrderDetailRepository orderDetailRepository, OrderRepository orderRepository,
			ProductRepository productRepository, UserRepository userRepository,
			StateOrderRepository stateOrderRepository) {
		this.orderDetailRepository = orderDetailRepository;
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
		this.stateOrderRepository = stateOrderRepository;
	}

	public MessageDTO addProductToCart(int id, String name, int quantity) {
		Product product=getProductIfExist(name);
		Order order = this.getCartOfUser(id);
		if (alreadyProductInCart(order, product)) {
			throw new BadInputException(MessageEnum.PRODUCT_ALREADY_IN_CART.getMessage());
		} else {
			if(productQuantityIsRight(quantity, name)) {
				OrderDetail orderDetail = setOrderDetail(quantity, product, order);
				this.orderDetailRepository.save(orderDetail);
			}else {
				throw new BadInputException(MessageEnum.PRODUCT_NOT_ENOUGH.getMessage());
			}
		}
		return new MessageDTO(MessageEnum.SUCCES.getMessage());
	}

	
	public MessageDTO deleteProducFromCart(int id, String name) {
		int quantity = 0;
		Order order = this.getCartOfUser(id);
		Product product= getProductIfExist(name);
		if (!alreadyProductInCart(order, product)) {
			throw new BadInputException(MessageEnum.PRODUCT_ALREADY_IN_CART.getMessage());
		} else {
			OrderDetail orderDetail= this.orderDetailRepository.getProductOfOrder(order, product);
			updatePrice(quantity, order, product, orderDetail);
			this.orderDetailRepository.delete(orderDetail);
			return new MessageDTO(MessageEnum.SUCCES.getMessage());
		}
		
	}
	
	public MessageDTO modifyQuantityOfProductInOrder(int id, int quantity, String name) {
		Order order = this.getCartOfUser(id);
		Product product= getProductIfExist(name);
		if (!alreadyProductInCart(order, product)) {
			throw new BadInputException(MessageEnum.PRODUCT_ALREADY_IN_CART.getMessage());
		} else {
			if(productQuantityIsRight(quantity, name)) {
				OrderDetail orderDetail= this.orderDetailRepository.getProductOfOrder
						(order, product);
				updatePrice(quantity, order, product, orderDetail);
				this.orderDetailRepository.save(orderDetail);
			}else {
				throw new BadInputException(MessageEnum.PRODUCT_NOT_ENOUGH.getMessage());
			}
			return new MessageDTO(MessageEnum.SUCCES.getMessage());
		}
	}

	private void updatePrice(int quantity, Order order, Product product, OrderDetail orderDetail) {
		float pastPrice= product.getProductPrice() * orderDetail.getAmount();
		float newPrice = product.getProductPrice() * quantity;
		float total = order.getTransaction().getPrice();
		order.getTransaction().setPrice(total +(newPrice - pastPrice));
		orderDetail.setAmount(quantity);
	}
	
	public MessageDTO confirmOrder(int id) {
		Order order = this.getCartOfUser(id);
		float total=0;
		String message= MessageEnum.SUCCES.getMessage();
		if(order != null) {
			List<OrderDetail> ordersDeail = this.orderDetailRepository.findByOrder(order);
			for (OrderDetail orderDetail : ordersDeail) {
				int amount =orderDetail.getAmount();
				int quantity = orderDetail.getProduct().getQuantity();
				Product product = orderDetail.getProduct();
				if(productQuantityIsRight(amount, 
						orderDetail.getProduct().getProductName())) {
					System.out.println("qui" + order);
					product.setQuantity(quantity-amount);
					orderDetail.setState(StateOfOrder.CONFERMATO.getCod());
					total = total +(amount * product.getProductPrice());
				}else {
					orderDetail.setState(StateOfOrder.NOT_AVAILEABLE.getCod());
					message=MessageEnum.NOT_EVERY_PRODUCT_AVALAIBLE.getMessage();
				}
				order.setState(this.stateOrderRepository.findByCod(StateOfOrder.CONFERMATO.getCod()));
				order.getTransaction().setPrice(total);
				this.orderDetailRepository.save(orderDetail);
			}
		}else {
			throw new  ProductNotFoundException("Nessun prodototo nel carrello!");
		}
		
		return new MessageDTO(message);
	}
	
	public List<OrderDTO>  getListOrderOfClient(int id) {
		
		List<Order> orders = this.orderRepository.getListOrderOfClient(id, StateOfOrder.CARRELLO.getCod());
		List<OrderDTO> theOrders = new ArrayList<OrderDTO>();
		if(orders != null) {
				for (Order order : orders) {
					theOrders.add(Mapper.OrderToOrderDTO(order));
				}		
		}
		return theOrders;
		
	}
	
	public List<OrderSellerDTO>  getListOrderOfSeller(int id) {
		List<OrderDetail> orders = this.orderDetailRepository.findOrderForSeller(
						id, StateOfOrder.CARRELLO.getCod());
		List<OrderSellerDTO> theOrders = new ArrayList<OrderSellerDTO>();
		if(orders != null) {
			for (OrderDetail order : orders) {
				theOrders.add(Mapper.orderDetailToOrderSellerDTO(order));
			}		
		}
		return theOrders;
	}
	
	public List<OrderDetailUserDTO>  getOrderDetail(int id) {
		Order order=this.orderRepository.findById(id);
		List<OrderDetail> orders = this.orderDetailRepository.findByOrder(order);
		List<OrderDetailUserDTO> theOrders = new ArrayList<OrderDetailUserDTO>();
		if(orders != null) {
			for (OrderDetail theOrder : orders) {
				theOrders.add(Mapper.orderDetailToOrderDetailUserDTO(theOrder));
			}		
		}
		return theOrders;
	}
	
	public List<OrderDetailUserDTO> getCart(int id){
		Order order= getCartOfUser(id);
		return getOrderDetail(order.getId());
	}
	
	public MessageDTO cancelOrderForProduct(int id, int userId) {
		OrderDetail order = this.orderDetailRepository.findById(id);
		if(order.getProduct().getVenditoreId() == userId || 
				order.getOrder().getUser().getId() == userId) {
			if(order.getState().equals(StateOfOrder.CONFERMATO.getCod())) {
				order.setState(StateOfOrder.ANNULATO.getCod());
				Product product = order.getProduct();
				int quantity = order.getAmount();
				int pastQuantity = product.getQuantity();
				product.setQuantity(quantity + pastQuantity);
				setToComplete(order);
				this.productRepository.save(product);
				this.orderDetailRepository.save(order);
			}else {
				throw new BadInputException(MessageEnum.IMPOSSIBLE_TO_DELETE.getMessage());
			}
		}else {
			throw new UserNotAuthorizedException(MessageEnum.NOT_AUTHORIZED.getMessage());
		}
		return new MessageDTO(MessageEnum.SUCCES.getMessage());
	}
	
	public MessageDTO setOrderToSent(int id, int userId) {
		OrderDetail order = this.orderDetailRepository.findById(id);
		if(order.getProduct().getVenditoreId() == userId) {
			if(order.getState().equals(StateOfOrder.CONFERMATO.getCod())) {
				order.setState(StateOfOrder.SPEDITO.getCod());
				setToComplete(order);
				this.orderDetailRepository.save(order);
			}else {
				throw new BadInputException(MessageEnum.IMPOSSIBLE_TO_SEND.getMessage());
			}
		}else {
			throw new UserNotAuthorizedException(MessageEnum.NOT_AUTHORIZED.getMessage());
		}
		return new MessageDTO(MessageEnum.SUCCES.getMessage());
	}
	
	private void setToComplete(OrderDetail order) {
		Order theOrder = order.getOrder();
		List<OrderDetail> orders = this.orderDetailRepository.findByOrder(theOrder);
		boolean flag = true;
		for (OrderDetail orderDetail : orders) {
			if(orderDetail.getState() == null ||
					orderDetail.getState().equals(StateOfOrder.CONFERMATO.getCod())) {
				flag=false;
			}
		}
		if(flag) {
			theOrder.setState(
					this.stateOrderRepository.findByCod(StateOfOrder.COMPLETO.getCod()));
			this.orderRepository.save(theOrder);
		}
		
	}
	
	private OrderDetail setOrderDetail(int quantity, Product product, Order order) {
		OrderDetail orderDetail = new OrderDetail();
		float total = order.getTransaction().getPrice();
		order.getTransaction().setPrice(total +(product.getProductPrice() * quantity));
		orderDetail.setOrder(order);
		orderDetail.setProduct(product);
		orderDetail.setAmount(quantity);
		return orderDetail;
	}

	private boolean alreadyProductInCart(Order order, Product product) {
		OrderDetail orderDetail = this.orderDetailRepository.getProductOfOrder(order, product);
		if (orderDetail == null) {
			return false;
		}
		return true;
	}

	private Order getCartOfUser(int id) {
		Order theOrder = this.orderRepository.getCartOfUser(id, StateOfOrder.CARRELLO.getCod());
		if (theOrder == null) {
			theOrder = new Order();
			theOrder.setDate(new Date(System.currentTimeMillis()));
			theOrder.setUser(this.userRepository.findById(id));
			theOrder.setState(this.stateOrderRepository.findByCod(StateOfOrder.CARRELLO.getCod()));
			theOrder.setTransaction(new Transaction());
			System.out.println(theOrder.getState());
			this.orderRepository.save(theOrder);
		}
		return theOrder;
	}

	private List<OrderDetail> getOrderDetailOfOrder(Order order) {
		List<OrderDetail> theOrderDetail = this.orderDetailRepository.findByOrder(order);
		return theOrderDetail;

	}

	private Product getProductIfExist(String name) {
		Product product=this.productRepository.findByProductName(name);
		if (product == null) {
			throw new ProductNotFoundException(MessageEnum.PRODUCT_NOT_FOUND.getMessage());
		} else {
			product = this.productRepository.findByProductName(name);
		}
		return product;
	}

	private boolean productQuantityIsRight(int quantity, String name) {
		Product product = this.productRepository.findByProductName(name);
		int amount = product.getQuantity();
		if (quantity <= amount) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isUserAClient(int id) {
		User user = this.userRepository.findById(id);
		if (user != null) {
			if (user.getRole().getCod().equals(RoleCod.CLIENTE.getCod())) {
				return true;
			} else {
				return false;
			}
		}else {
			throw new UserNotAuthorizedException(MessageEnum.USER_NOT_FOUND.getMessage());
		}
	}
	

}
