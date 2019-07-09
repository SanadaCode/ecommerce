package com.sanada.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanada.dto.ProductDTO;
import com.sanada.entity.Product;
import com.sanada.entity.User;
import com.sanada.error.BadInputException;
import com.sanada.error.ProductNotFoundException;
import com.sanada.model.MessageDTO;
import com.sanada.model.MessageEnum;
import com.sanada.repository.ProductRepository;
import com.sanada.repository.UserRepository;
import com.sanada.utility.Checker;
import com.sanada.utility.Mapper;

@Service
public class ProductService {
	
	private ProductRepository productRepository;
	private UserRepository userRepository;
	
	@Autowired
	public ProductService(ProductRepository productRepository, 
			UserRepository userRepository) {
		this.productRepository = productRepository;
		this.userRepository = userRepository;
	}
	
	public MessageDTO addProduct(ProductDTO product,int id, String name , String type) {
		
		User user= this.userRepository.findById(id);
		MessageDTO message;
		Product theProduct = new Product();
		if(this.productRepository.findByProductName(product.getProductName()) != null) {
			throw new BadInputException(MessageEnum.PRODUCT_ALREADY_EXIST.getMessage());
		}
		Mapper.setDataProduct(theProduct,product, name , type);
		theProduct.setVenditoreId(id);
		theProduct.setDeleted(true);
		this.productRepository.save(theProduct);
		message= new MessageDTO(MessageEnum.SUCCES.getMessage());
		return  message;
	}
	
	public MessageDTO editProduct(ProductDTO product,int id,String name, String nameFile , String type) {
		User user= this.userRepository.findById(id);
		MessageDTO message;
		Product tempProduct= this.productRepository.findByProductName(name);
		System.out.println(tempProduct);
		if(!Checker.isDeleted(tempProduct)) {
			throw new ProductNotFoundException(MessageEnum.PRODUCT_NOT_FOUND.getMessage());
		}
		Mapper.setDataProduct(tempProduct , product, nameFile , type);
		this.productRepository.save(tempProduct);
		message= new MessageDTO(MessageEnum.SUCCES.getMessage());
		return message;
	}
	
	public List<ProductDTO> productOfSeller(int id){
		User user= this.userRepository.findById(id);
		List<ProductDTO> theProducts = new ArrayList<ProductDTO>();
		List<Product> products = this.productRepository.findByVenditoreId(id);
		Mapper.getListProductDTOFromProduct(theProducts, products);
		return theProducts;
	}

	
	
	public List<ProductDTO> productByName( String name){
		List<ProductDTO> products = new ArrayList<ProductDTO>();
		List<Product> tempProduct=this.productRepository.searchProductByName(name);
		Mapper.getListProductDTOFromProduct(products,tempProduct);
		return products;
		
	}
	
	public ProductDTO findProductByName(String name){
		Product tempProduct=this.productRepository.findByProductName(name);
		ProductDTO product =Mapper.ProductToProductDTO(tempProduct);
		return product;
	}
	
	
	public List<ProductDTO> getProducts() {
		List<ProductDTO> theProducts = new ArrayList<ProductDTO>();
		List<Product> products = this.productRepository.findAll();
		Mapper.getListProductDTOFromProduct(theProducts, products);
		return theProducts;
	}
	
	public MessageDTO deleteProductByName(int id, String name){
		MessageDTO message;
		Product product;
		product = this.productRepository.findByProductName(name);
		product.setDeleted(false);
		product.setQuantity(0);
		this.productRepository.save(product);
		message= new MessageDTO(MessageEnum.SUCCES.getMessage());
		return message;
	}
	
	public boolean isProductOfSeller(int id, String name) {
		Product product;			
		product = this.productRepository.findByProductName(name);
		if(product.getVenditoreId() != id) {
			return false;
		}else {
			return true;
		}
	}
	
}