package com.app.services;

import java.util.List;

import com.app.entities.Category;
import com.app.entities.Product;
import com.app.exceptions.CategoryException;

public interface SellerService {
	
	/**
	 * 
	 * @param category -> category obj
	 * @return confirmation message
	 */
	public String addCategory(Category category);
	
	/**
	 * 
	 * @param product -> product obj to save
	 * @param categoryId -> category id to find category
	 * @return added project details
	 * @throws CategoryException
	 */
	public Product addProduct(Product product, Long categoryId)throws CategoryException;
}
