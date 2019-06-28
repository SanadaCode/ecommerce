package com.sanada.utility;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.RequestBody;

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
import com.sanada.model.MessageEnum;

public class Mapper {
	
	private static final String UPLOADED_FOLDER= "C:\\Users\\davide.borgato\\Documents\\image\\";

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
		theOrder.setState(getDescrizioneFromCodice(order.getState()));
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
		theOrder.setDesc(order.getProduct().getDesc());
		theOrder.setImg(Mapper.getImageBase64(order.getProduct().getImg()));
		theOrder.setPrice(order.getProduct().getProductPrice());
		theOrder.setId(order.getId());
		String tempState=getDescrizioneFromCodice(order.getState());
		theOrder.setState(tempState);
		return theOrder;
	}

	public static ProductDTO ProductToProductDTO(Product product) {
		String desc = product.getDesc();
		String img = Mapper.getImageBase64(product.getImg());
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
	
	public static Product setDataProduct(Product theProduct,ProductDTO product, String name , String type) {
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
		if(product.getProductName().trim() != ""){
			theProduct.setProductName(product.getProductName());
		}else {
			throw new BadInputException("Uno o più valori inseriti non sono validi!");
		}
		if(product.getImg().trim() != null && product.getImg().trim() != ""){
			theProduct.setImg(
					Mapper.saveImageFromBase64(product.getImg(), name, type));
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
	
	private static String getDescrizioneFromCodice(String cod) {
		
		String tempDesc="";
		if(cod !=null) {
		switch(cod) {
		
		case "cnf":
			tempDesc= "Confermato";
			break;
		case "nbl":
			tempDesc= "Non disponibile";
			break;
		case "anlt": 
			tempDesc = "Annulato";
			break;
		case "cpt": 
			tempDesc= "Completato";
			break;
		case "sdt": 
			tempDesc = "Spedito";
			break;
		}
	}
		return tempDesc;
	}
	
	
	public static String saveImageFromBase64(String base64URL, String name, String type) {	
    	try {
    		Date date = new Date();
    		String[] onlyBase64= base64URL.split(",");
    		byte[] decoded = Base64.decodeBase64(onlyBase64[1]);
    		String tempName= name +  System.currentTimeMillis() + "." +type;
    		ByteArrayInputStream bis = new ByteArrayInputStream(decoded);
    		BufferedImage image =ImageIO.read(new ByteArrayInputStream(decoded)); 
    		File outputfile = new File(UPLOADED_FOLDER + tempName);
    		ImageIO.write(image, type , outputfile);
    		return tempName;	
		} catch (Exception e) {
			throw new BadInputException(MessageEnum.GENERIC.getMessage());
		}
    }
	
	public static String getImageBase64(String path) {
    	try {
    		String base64=null;
    		File outputfile = new File(UPLOADED_FOLDER + path);
    		int index = path.indexOf(".") +1;
    		String type = path.substring(index  ,path.length());
    		BufferedImage image= ImageIO.read(outputfile);
    		ByteArrayOutputStream bos = new ByteArrayOutputStream();
    		ImageIO.write(image, type , bos);
    		byte [] data = bos.toByteArray();
    		base64 = Base64.encodeBase64String(data);
    		return base64;
    	} catch (Exception e) {
    		throw new BadInputException(e.getMessage());
    	}
    }
	
}
