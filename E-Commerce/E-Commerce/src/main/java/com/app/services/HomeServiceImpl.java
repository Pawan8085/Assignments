package com.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dto.SellerSignUpDTO;
import com.app.dto.UserSignUpDTO;
import com.app.entities.Category;
import com.app.entities.Gender;
import com.app.entities.MyOrders;
import com.app.entities.Product;
import com.app.entities.Seller;
import com.app.entities.User;
import com.app.exceptions.CategoryException;
import com.app.exceptions.ProductException;
import com.app.repository.CategoryRepo;
import com.app.repository.ProductRepo;
import com.app.repository.SellerRepo;
import com.app.repository.UserRepo;

@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SellerRepo sellerRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ProductRepo productRepo;

	@Override
	public String userSignUp(UserSignUpDTO userData) {

		// set user data to user obj
		User user = new User();
		user.setRole("USER");
		user.setName(userData.getName());
		user.setLocation(userData.getLocation());
		user.setGender(userData.getGender());
		user.setPhoneNo(userData.getPhoneNo());
		user.setEmail(userData.getEmail());
		user.setPassword(passwordEncoder.encode(userData.getPassword()));
		
		user.setMyOrders(new MyOrders());

		userRepo.save(user);
		return "You have registered successfully";
	}

	@Override
	public String sellerSignUp(SellerSignUpDTO sellerData) {

		// set seller data to seller obj
		Seller seller = new Seller();
		seller.setRole("SELLER");
		seller.setName(sellerData.getName());
		seller.setLocation(sellerData.getLocation());
		seller.setGender(sellerData.getGender());
		seller.setPhoneNo(sellerData.getPhoneNo());
		seller.setEmail(sellerData.getEmail());
		seller.setPassword(passwordEncoder.encode(sellerData.getPassword()));

		sellerRepo.save(seller);
		return "You have registered successfully";
	}

	@Override
	public List<Category> getAllCategories(int page) throws CategoryException {

		// check for valid page
		if (page < 1) {
			throw new CategoryException("Invalid page !");
		}

		Pageable pageable = PageRequest.of(page - 1, 5); // 5 records per page
		Page<Category> categories = categoryRepo.findAll(pageable);

		// if there is not any record then throw the exception
		if (categories.getTotalPages() == 0) {
			throw new CategoryException("No Record Found !");
		}

		return categories.getContent();
	}

	@Override
	public List<Product> getProductsByCategoryId(Long categoryId, int page) throws CategoryException {

		// check for valid page
		if (page < 1) {
			throw new CategoryException("Invalid page !");
		}
		Optional<Category> optCategory = categoryRepo.findById(categoryId);

		// check for valid category
		if (optCategory.isEmpty()) {
			throw new CategoryException("Invalid category id : " + categoryId);
		}

		Pageable pageable = PageRequest.of(page - 1, 5);
		return productRepo.findByCategory(optCategory.get(), pageable).getContent();
	}

	@Override
	public Product getProductByProductId(Long productId) throws ProductException {
		
		Optional<Product> optProduct = productRepo.findById(productId);
		if(optProduct.isEmpty()) {
			throw new ProductException("Invalid product id : "+productId);
		}
		
		return optProduct.get();
	}

	@Override
	public List<Product> searchProduct(String key, int page) {
		
		Pageable pageable = PageRequest.of(page - 1, 5);
		return productRepo.searchProductByKey(key, pageable).getContent();
		
	}

}
