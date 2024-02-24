package com.app.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.app.dto.BuyProductDTO;
import com.app.entities.CartItem;
import com.app.entities.Order;
import com.app.entities.Product;
import com.app.entities.User;
import com.app.exceptions.CartException;
import com.app.exceptions.OrderException;
import com.app.exceptions.ProductException;
import com.app.repository.CartRepo;
import com.app.repository.MyOrdersRepo;
import com.app.repository.OrderRepo;
import com.app.repository.ProductRepo;
import com.app.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private CartRepo cartRepo;

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private OrderRepo orderRepo;

	@Override
	public CartItem addProductToCart(Long productId) throws ProductException {

		// get current user from authentication obj
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();

		User user = userRepo.findByEmail(userName).get();

		// check for valid product
		Optional<Product> optProduct = productRepo.findById(productId);
		if (optProduct.isEmpty()) {
			throw new ProductException("Invalid product id : " + productId);
		}

		Product product = optProduct.get();

		// create cart item
		CartItem cartItem = new CartItem();
		cartItem.setProductId(productId);
		cartItem.setProductName(product.getName());
		cartItem.setProductPrice(product.getPrice());
		cartItem.setProductQuantity(1); // set initially quantity as 1

		cartItem.setUser(user);
		user.getCartItems().add(cartItem);

		userRepo.save(user);

		return cartItem;
	}

	@Override
	public CartItem viewCartItem(Long cartItemId) throws CartException {

		// get current user from authentication obj
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();

		User user = userRepo.findByEmail(userName).get();

		// check for valid cart Item
		Optional<CartItem> optCartItem = cartRepo.findById(cartItemId);
		if (optCartItem.isEmpty()) {
			throw new CartException("Invalid cart id : " + cartItemId);
		}

		// check cart item belongs to current user or not
		CartItem cartItem = optCartItem.get();
		if (cartItem.getUser().getUserId() != user.getUserId()) {
			throw new CartException("Cart item does not belong to you !");
		}

		return cartItem;
	}

	@Override
	public void updateCartItemQuantity(Long cartItemId, int quantity) throws CartException {

		// get current user from authentication obj
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();

		User user = userRepo.findByEmail(userName).get();

		// check for valid cart Item
		Optional<CartItem> optCartItem = cartRepo.findById(cartItemId);
		if (optCartItem.isEmpty()) {
			throw new CartException("Invalid cart id : " + cartItemId);
		}

		// check cart item belongs to current user or not
		CartItem cartItem = optCartItem.get();
		if (cartItem.getUser().getUserId() != user.getUserId()) {
			throw new CartException("Cart item does not belong to you !");
		}

		// check for valid quantity
		if (quantity < 1) {
			throw new CartException("Invalid quantity : " + quantity);
		}

		// update the quantity
		cartItem.setProductQuantity(quantity);
		cartRepo.save(cartItem);

	}

	@Override
	public String removeCartItem(Long cartItemId) throws CartException {

		// get current user from authentication obj
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();

		User user = userRepo.findByEmail(userName).get();

		// check for valid cart Item
		Optional<CartItem> optCartItem = cartRepo.findById(cartItemId);
		if (optCartItem.isEmpty()) {
			throw new CartException("Invalid cart id : " + cartItemId);
		}

		// check cart item belongs to current user or not
		CartItem cartItem = optCartItem.get();
		if (cartItem.getUser().getUserId() != user.getUserId()) {
			throw new CartException("Cart item does not belong to you !");
		}

		cartRepo.delete(cartItem);

		return "Item removed successfully";
	}

	@Override
	public List<CartItem> viewMyCartItems(int page) throws CartException {

		// get current user from authentication obj
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();

		User user = userRepo.findByEmail(userName).get();

		// check for valid page
		if (page < 1) {
			throw new CartException("Invalid page : " + page);
		}

		Pageable pageable = PageRequest.of(page - 1, 5);
		return cartRepo.findByUser(user, pageable).getContent();
	}

	@Override
	public List<Order> viewMyOrderHistory(int page) throws OrderException {

		// get current user from authentication obj
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();

		User user = userRepo.findByEmail(userName).get();

		// check for valid page
		if (page < 1) {
			throw new CartException("Invalid page : " + page);
		}

		Pageable pageable = PageRequest.of(page - 1, 5);
		return orderRepo.findByMyOrders(user.getMyOrders(), pageable).getContent();

	}

	@Override
	public String buyProduct(BuyProductDTO data) throws ProductException {

		// validate data
		// check if product id is valid or not
		Optional<Product> optProduct = productRepo.findById(data.getProductId());
		if (optProduct.isEmpty()) {
			throw new ProductException("Invalid product id : " + data.getProductId());
		}

		// check if quantity is valid or not
		if (data.getQuantity() < 1) {
			throw new ProductException("Invalid quantity !");
		}

		Product product = optProduct.get();

		// check if product is out of stock
		if (product.isOutOfStcok()) {
			throw new ProductException("Product out of stock");
		}

		// check if user buying more than available product stock
		if (data.getQuantity() > product.getNumberOfStocks()) {
			throw new ProductException("There is only " + product.getNumberOfStocks() + " stocks!");

		}

		// reduce stock by number of quantity
		product.setNumberOfStocks(product.getNumberOfStocks() - data.getQuantity());
		product.setSold(product.getSold() + 1);

		// if product stock becomes 0 save outOfStock true
		if (product.getNumberOfStocks() == 0) {
			product.setOutOfStcok(true);
		}

		// get current user from authentication obj
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();

		User user = userRepo.findByEmail(userName).get();

		// create order
		Order order = new Order();
		order.setProductId(product.getProductId());
		order.setProductName(product.getName());
		order.setProductPrice(product.getPrice());
		order.setProductQuantity(data.getQuantity());
		order.setOrderDate(LocalDate.now());
		order.setTotalOrderAmount(product.getPrice() * data.getQuantity());

		order.setMyOrders(user.getMyOrders());
		user.getMyOrders().getOrders().add(order);

		userRepo.save(user);
		productRepo.save(product);

		return "Your Product will be delivered soon ";

	}

	@Override
	public Order getOrderById(Long orderId) throws OrderException {

		// check if order is valid or not
		Optional<Order> optOrder = orderRepo.findById(orderId);
		if (optOrder.isEmpty()) {
			throw new OrderException("Invalid order id : " + orderId);
		}

		// get current user from authentication obj
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();

		User user = userRepo.findByEmail(userName).get();

		// check if order belongs to current user or not
		Order order = optOrder.get();
		if (user.getUserId() != order.getMyOrders().getUser().getUserId()) {
			throw new OrderException("Order does not belong to your !");
		}

		return order;

	}

}
