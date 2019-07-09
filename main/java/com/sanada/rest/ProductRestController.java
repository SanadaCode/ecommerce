package com.sanada.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sanada.dto.ProductDTO;
import com.sanada.error.UserNotAuthorizedException;
import com.sanada.model.MessageDTO;
import com.sanada.model.MessageEnum;
import com.sanada.service.FileService;
import com.sanada.service.ProductService;
import com.sanada.service.UserService;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductRestController {
	
	private ProductService productService;
	private UserService userService;

	@Autowired
	public ProductRestController(ProductService productService, UserService userService) {
		this.productService = productService;
		this.userService = userService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<MessageDTO> addProduct(@RequestBody ProductDTO product,
			@RequestParam("id") int id, 
			@RequestParam("name") String name , 
			@RequestParam("type") String type ) {
		if(!this.userService.authorizedSeller(id)) {
			throw new UserNotAuthorizedException(MessageEnum.NOT_AUTHORIZED.getMessage());
		}
		
		return new ResponseEntity<MessageDTO>(this.productService.addProduct(product, id , name , type),null,HttpStatus.OK);
	}
	
	@GetMapping("/seller")
	public ResponseEntity<List<ProductDTO>> getProductOfSeller(@RequestParam("id") int id) {
		if(!this.userService.authorizedSeller(id)) {
			throw new UserNotAuthorizedException(MessageEnum.NOT_AUTHORIZED.getMessage());
		}
		
		return new ResponseEntity<List<ProductDTO>>(this.productService.productOfSeller(id),null,HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<ProductDTO>> getProductByName(
			@RequestParam("name") String name) {
		return new ResponseEntity<List<ProductDTO>>(this.productService.productByName(name),null,HttpStatus.OK);
	}
	
	@GetMapping("/find")
	public ResponseEntity<ProductDTO> findProductByName(
			@RequestParam("name") String name) {
		return new ResponseEntity<ProductDTO>(this.productService.findProductByName(name),null,HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<ProductDTO>> getAllProducts() {
		
		return new ResponseEntity<List<ProductDTO>>(this.productService.getProducts(),null,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<MessageDTO> deleteProductsByName(@RequestParam("id") int id, 
			@RequestParam("name") String name){
		if(!this.userService.authorizedSeller(id)) {
			throw new UserNotAuthorizedException(MessageEnum.NOT_AUTHORIZED.getMessage());
		}else if(!this.productService.isProductOfSeller(id, name)) {
			throw new UserNotAuthorizedException(MessageEnum.NOT_AUTHORIZED.getMessage());
		}
		return new ResponseEntity<MessageDTO>(this.productService.deleteProductByName(id, name),null,HttpStatus.OK);
	}
	
	@PutMapping("/edit")
	public ResponseEntity<MessageDTO> editProductsByName(@RequestParam("id") int id, 
			@RequestParam("name") String name,
			@RequestParam("nameFile") String nameFile,
			@RequestParam("type") String type,
			@RequestBody ProductDTO product){
		
		if(!this.userService.authorizedSeller(id)) {
			throw new UserNotAuthorizedException(MessageEnum.NOT_AUTHORIZED.getMessage());
		}else if(!this.productService.isProductOfSeller(id, name)) {
			throw new UserNotAuthorizedException(MessageEnum.NOT_AUTHORIZED.getMessage());
			
		}
		System.out.println("qua");
		return new ResponseEntity<MessageDTO>(this.productService.editProduct(product, id, name, nameFile, type),null,HttpStatus.OK);
		
	}
	

	
	
}
