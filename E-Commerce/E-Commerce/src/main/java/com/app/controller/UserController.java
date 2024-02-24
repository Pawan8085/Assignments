package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.BuyProductDTO;
import com.app.entities.CartItem;
import com.app.entities.Order;
import com.app.services.UserService;

@RestController
@RequestMapping("/app/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/cart/add/{productId}")
	public ResponseEntity<CartItem> addProducToCartHandler(@PathVariable Long productId) {
		
		CartItem cartItem = userService.addProductToCart(productId);
		return new ResponseEntity<CartItem>(cartItem, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/cart/view/{cartItemId}")
	public ResponseEntity<CartItem> viewCartItemHandler(@PathVariable Long cartItemId){
		
		CartItem cartItem = userService.viewCartItem(cartItemId);
		return new ResponseEntity<CartItem>(cartItem, HttpStatus.OK);
		
	}
	
	@PutMapping("/cart/update-quantity/{cartItemId}/{quantity}")
	public void updateCartItemQuantityHandler(@PathVariable Long cartItemId, @PathVariable int quantity) {
		
		userService.updateCartItemQuantity(cartItemId, quantity);
		
	}
	
	@DeleteMapping("/cart/remove/{cartItemId}")
	public ResponseEntity<String> removeCartItemHandler(@PathVariable Long cartItemId){
		
		String msg = userService.removeCartItem(cartItemId);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
		
	}
	
	@GetMapping("/cart-items/{page}")
	public ResponseEntity<List<CartItem>> findMyCartItemsHandler(@PathVariable int page){
		
		List<CartItem> cartItems = userService.viewMyCartItems(page);
		return new ResponseEntity<List<CartItem>>(cartItems, HttpStatus.OK);
		
	}
	
	@GetMapping("/myorders/{page}")
	public ResponseEntity<List<Order>> findMyOrdersHandler(@PathVariable int page){
		
		List<Order> orders = userService.viewMyOrderHistory(page);
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
		
	}
	
	@GetMapping("/order/{orderId}")
	public ResponseEntity<Order> findOrderByIdHandler(@PathVariable Long orderId){
		
		Order order = userService.getOrderById(orderId);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
		
	}
	
	@PostMapping("/buy-product")
	public ResponseEntity<String> buyProductHandler(@RequestBody BuyProductDTO data){
		
		String msg = userService.buyProduct(data);
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		
	}
	
	
}
