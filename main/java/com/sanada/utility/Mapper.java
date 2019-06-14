package com.sanada.utility;

import java.util.List;

import com.sanada.dto.InformationUserDTO;
import com.sanada.dto.OrderDTO;
import com.sanada.dto.OrderDetailDTO;
import com.sanada.dto.OrderDetailUserDTO;
import com.sanada.dto.OrderSellerDTO;
import com.sanada.dto.ProductDTO;
import com.sanada.dto.RoleDTO;
import com.sanada.dto.StateOrderDTO;
import com.sanada.dto.TransactionDTO;
import com.sanada.dto.UserDTO;
import com.sanada.entity.InformazioniUtente;
import com.sanada.entity.Order;
import com.sanada.entity.OrderDetail;
import com.sanada.entity.Product;
import com.sanada.entity.Role;
import com.sanada.entity.StateOrder;
import com.sanada.entity.Transaction;
import com.sanada.entity.User;
import com.sanada.error.BadInputException;

public class Mapper {

	public static UserDTO UserToUserDTO(User user) {
		int id = user.getId();
		String email = user.getEmail();
		UserDTO theUser = new UserDTO(id, email);
		if (user.getRole() != null) {
			theUser.setRole(Mapper.RoleToRoleDTO(user.getRole()));
		}
		if (user.getInfo() != null) {
			theUser.setInfo(InformationToInformationUserDTO(user.getInfo()));
		}
		return theUser;
	}

	public static RoleDTO RoleToRoleDTO(Role role) {
		String cod = role.getCod();
		String desc = role.getDesc();
		RoleDTO theRole = new RoleDTO(cod, desc);
		return theRole;
	}
	
	public static InformationUserDTO InformationToInformationUserDTO(InformazioniUtente info) {
		String lastName= info.getLastName();
		String firstName=info.getFirstName();
		String address=info.getAddress();
		String country=info.getCountry();
		String city=info.getCity();
		String phone=info.getPhone();
		String cap=info.getCap();
		InformationUserDTO tempInfo= new InformationUserDTO(lastName, firstName, address, country, city, phone, cap);
		return tempInfo;
	}
	
	public static StateOrderDTO stateOrderToStateOrderDTO (StateOrder state) {
		
		StateOrderDTO theState = new StateOrderDTO(state.getCod(), state.getDesc());
		return theState;
	}
	
	public static TransactionDTO TransactionToTransactionDTO(Transaction transaction) {
		TransactionDTO theTransaction = new TransactionDTO(transaction.getPrice());
		return theTransaction;
	}
	
	public static OrderDTO OrderToOrderDTO(Order order) {
		
		StateOrderDTO state= stateOrderToStateOrderDTO(order.getState());
		TransactionDTO transaction = TransactionToTransactionDTO(order.getTransaction());
		OrderDTO theOrder = new OrderDTO(order.getId(),order.getDate(),
				state,transaction);
		
		return theOrder;
	}
	
	public static OrderDetailDTO OrderDetailToOrderDetailDTO(OrderDetail order) {	
		
		return null;
	}
	
	public static OrderSellerDTO orderDetailToOrderSellerDTO(OrderDetail order) {
		OrderSellerDTO theOrder=new OrderSellerDTO();
		theOrder.setName(order.getProduct().getProductName());
		theOrder.setQuantity(order.getAmount());
		theOrder.setDate(order.getOrder().getDate());
		theOrder.setState(order.getState());
		theOrder.setAddress(order.getOrder().getUser().getInfo().getAddress());
		theOrder.setCity(order.getOrder().getUser().getInfo().getCity());
		theOrder.setCountry(order.getOrder().getUser().getInfo().getCountry());
		theOrder.setId(order.getId());
		return theOrder;
	}
	
	public static OrderDetailUserDTO orderDetailToOrderDetailUserDTO(OrderDetail order) {
		OrderDetailUserDTO theOrder=new OrderDetailUserDTO();
		theOrder.setName(order.getProduct().getProductName());
		theOrder.setQuantity(order.getAmount());
		theOrder.setImg(order.getProduct().getImg());
		theOrder.setPrice(order.getProduct().getProductPrice());
		theOrder.setId(order.getId());
		return theOrder;
	}


	public static ProductDTO ProductToProductDTO(Product product) {
		String desc = product.getDesc();
		String img = product.getImg();
		String productName = product.getProductName();
		int quantity = product.getQuantity();
		float productPrice = product.getProductPrice();
		ProductDTO theProduct = new ProductDTO(productName, productPrice, desc, img, quantity);
		return theProduct;
	}

	public static void getInformationFromInformationDTO(InformationUserDTO info, InformazioniUtente infoUtente) {
		if(info ==null) {
			throw new BadInputException("Input vuoto");
		}
		if(info.getAddress() == null || info.getCap() == null ||
				info.getCity() == null || info.getCountry() == null ||
				info.getFirstName() == null || info.getLastName() == null) {
			throw new BadInputException("Uno o più valori inseriti non sono validi!");
		}
		if (info.getAddress().trim() != "") {
			infoUtente.setAddress(info.getAddress());
		}
		if (info.getCap().trim() != "" && info.getCap().trim().length() <= 5) {
			infoUtente.setCap(info.getCap());
		}
		if (info.getCity().trim() != "") {
			infoUtente.setCity(info.getCity());
		}
		if (info.getCountry().trim() != "") {
			infoUtente.setCountry(info.getCountry());
		}
		if (info.getFirstName().trim() != "") {
			infoUtente.setFirstName(info.getFirstName());
		}
		if (info.getLastName().trim() != "") {
			infoUtente.setLastName(info.getLastName());
		}
		if (info.getPhone().trim() != "" && info.getPhone() != null && info.getPhone().length() <= 10) {
			infoUtente.setPhone(info.getPhone());
		}
	}
	
	public static Product setDataProduct(Product theProduct,ProductDTO product) {
		if(product == null) {
			throw new BadInputException("Input vuoto");
		}
		if(product.getDesc() ==null || product.getProductName() == null 
				|| product.getProductPrice() == 0) {
			throw new BadInputException("Uno o più valori inseriti non sono validi!");
		}
		if(product.getDesc().trim() != ""){
			theProduct.setDesc(product.getDesc());
		}
		if(product.getImg().trim() != null && product.getImg().trim() != ""){
			theProduct.setImg(product.getImg());
		}
		if(product.getProductName().trim() != ""){
			theProduct.setProductName(product.getProductName());
		}
		theProduct.setProductPrice(product.getProductPrice());
		theProduct.setQuantity(product.getQuantity());
		return theProduct;
	}
	
	public static  void getListProductDTOFromProduct(List<ProductDTO> theProducts, List<Product> products) {
		if(products != null) {
			for (Product product : products) {
				if(Checker.isDeleted(product)) {
					ProductDTO produc = Mapper.ProductToProductDTO(product);
					theProducts.add(produc);
				}
			}
		}
	}
	
	
	
	
	
}
