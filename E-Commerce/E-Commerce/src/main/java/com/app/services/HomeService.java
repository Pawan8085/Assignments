package com.app.services;

import java.util.List;

import com.app.dto.SellerSignUpDTO;
import com.app.dto.UserSignUpDTO;
import com.app.entities.Category;
import com.app.entities.Product;
import com.app.exceptions.CategoryException;
import com.app.exceptions.ProductException;

public interface HomeService {
	
	/**
	 * 
	 * @param userData -> user sign up data 
	 * @return sign up confirmation message
	 */
	String userSignUp(UserSignUpDTO userData);
	
	/**
	 * 
	 * @param sellerData -> seller signup data
	 * @return sign up confirmation message
	 */
	String sellerSignUp(SellerSignUpDTO sellerData);
	
	
	/**
	 * 
	 * @param page -> current page number
	 * @return list of categories
	 * @throws CategoryException
	 */
	List<Category> getAllCategories(int page)throws CategoryException;
	
	
	/**
	 * 
	 * @param categoryId -> to get category 
	 * @param page -> current page
	 * @return list of product
	 * @throws CategoryException
	 */
	List<Product> getProductsByCategoryId(Long categoryId, int page)throws CategoryException;
	
	
	/**
	 * 
	 * @param productId -> to fetch product
	 * @return Product obj
	 * @throws ProductException
	 */
	Product getProductByProductId(Long productId)throws ProductException;
	
	
	/**
	 * 
	 * @param key -> to search product
	 * @param page -> current page
	 * @return list of products
	 */
	List<Product> searchProduct(String key, int page);
	
	
}
