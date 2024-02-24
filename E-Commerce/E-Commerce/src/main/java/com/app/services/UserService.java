package com.app.services;

import java.util.List;

import com.app.dto.BuyProductDTO;
import com.app.entities.CartItem;
import com.app.entities.Order;
import com.app.entities.Product;
import com.app.exceptions.CartException;
import com.app.exceptions.OrderException;
import com.app.exceptions.ProductException;

public interface UserService {
	
	/**
	 * 
	 * @param productId -> to fetch product obj
	 * @return added cartItem
	 * @throws ProductException
	 */
	public CartItem addProductToCart(Long productId)throws ProductException;
	
	
	/**
	 * 
	 * @param cartItemId -> to fetch cartitem
	 * @return cartItem
	 * @throws CartException
	 */
	public CartItem viewCartItem(Long cartItemId)throws CartException;
	
	
	/**
	 * 
	 * @param cartItemId -> to fetch cartitem
	 * @param quantity -> item quantity
	 * @throws CartException
	 */
	public void updateCartItemQuantity(Long cartItemId, int quantity) throws CartException;
	
	
	/**
	 * 
	 * @param cartItemId -> to fetch cartitem
	 * @return -> cartItem remove confirmation message
	 * @throws CartException
	 */
	public String removeCartItem(Long cartItemId)throws CartException;
	
	
	/**
	 * 
	 * @param page -> current page
	 * @return list of cart items
	 * @throws CartException
	 */
	public List<CartItem> viewMyCartItems(int page)throws CartException;
	
	
	/**
	 * 
	 * @param page -> current page
	 * @return list of orders
	 * @throws OrderException
	 */
	public List<Order> viewMyOrderHistory(int page)throws OrderException;
	
	
	/**
	 * 
	 * @param data -> to keep product buying data (productId, quantity)
	 * @return confirmation message after buying product
	 * @throws ProductException
	 */
	public String buyProduct(BuyProductDTO data)throws ProductException;
	
	
	/**
	 * 
	 * @param orderId -> to fetch order 
	 * @return order obj
	 * @throws OrderException
	 */
	public Order getOrderById(Long orderId)throws OrderException;
	
	
	
}
