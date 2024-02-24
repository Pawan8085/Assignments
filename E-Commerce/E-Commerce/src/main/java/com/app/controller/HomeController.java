package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.SellerSignUpDTO;
import com.app.dto.UserSignUpDTO;
import com.app.entities.Category;
import com.app.entities.Order;
import com.app.entities.Product;
import com.app.services.HomeService;

@RestController
@RequestMapping("/app")
public class HomeController {
	
	@Autowired
	private HomeService homeService;
	
	@GetMapping("/")
	public String String() {
		
		return "Hello unauthenticated nigga";
	}
	
	@PostMapping("/user-signup")
	public ResponseEntity<String> userSignUpHandler(@RequestBody UserSignUpDTO user){
		
		String msg = homeService.userSignUp(user);
		return new ResponseEntity<String>(msg, HttpStatus.CREATED); 
		
	}
	
	@PostMapping("/seller-signup")
	public ResponseEntity<String> sellerSignUpHandler(@RequestBody SellerSignUpDTO seller){
		
		String msg = homeService.sellerSignUp(seller);
		return new ResponseEntity<String>(msg, HttpStatus.CREATED); 
		
	}
	
	@GetMapping("/category/{page}")
	public ResponseEntity<List<Category>> getCategoriesHandler(@PathVariable("page") int page){
		
		List<Category> categories = homeService.getAllCategories(page);
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}
	
	@GetMapping("/category/product/{categoryId}/{page}")
	public ResponseEntity<List<Product>> findProductsByCategoryIdHandler(@PathVariable Long categoryId, @PathVariable int page){
		
		List<Product> products = homeService.getProductsByCategoryId(categoryId, page);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<Product> findProductByProductIdHandler(@PathVariable Long productId){
		
		Product savedProduct = homeService.getProductByProductId(productId);
		return new ResponseEntity<Product>(savedProduct, HttpStatus.OK);
		
	}
	
	@GetMapping("/product/search/{key}/{page}")
	public ResponseEntity<List<Product>> searchProductByKey(@PathVariable String key, @PathVariable int page){
		
		List<Product> products = homeService.searchProduct(key, page);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		
	}
}
