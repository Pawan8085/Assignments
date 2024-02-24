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

import com.app.entities.Category;
import com.app.entities.Product;
import com.app.services.SellerService;

@RestController
@RequestMapping("/app/seller")
public class SellerController {
	
	@Autowired
	private SellerService sellerService;
	
	@GetMapping("/hello")
	public String hello() {
		
		return "Hello Seller";
	}
	
	@PostMapping("/add-category")
	public ResponseEntity<String> addCategoryHandlder(@RequestBody Category category){
		
		String msg = sellerService.addCategory(category);
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		
	}
	
	
	@PostMapping("/add-product/{categoryId}")
	public ResponseEntity<Product> addProductHandler(@PathVariable("categoryId") long categoryId, @RequestBody Product product){
		
		Product savedProduct = sellerService.addProduct(product, categoryId);
		return new ResponseEntity<Product>(savedProduct, HttpStatus.CREATED);
		
	}
	

	
	
}
