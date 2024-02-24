package com.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.app.entities.Category;
import com.app.entities.Product;
import com.app.entities.Seller;
import com.app.exceptions.CategoryException;
import com.app.repository.CategoryRepo;
import com.app.repository.SellerRepo;

@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	private SellerRepo sellerRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	
	@Override
	public String addCategory(Category category) {

		// get current seller from authentication obj
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();

		Seller seller = sellerRepo.findByEmail(userName).get();

		category.setSeller(seller);
		seller.getCategories().add(category);

		sellerRepo.save(seller);

		return "Category added...";
	}

	@Override
	public Product addProduct(Product product, Long categoryId) throws CategoryException {
		// get current seller from authentication obj
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();

		Seller seller = sellerRepo.findByEmail(userName).get();
		
		// check for valid categoryId
		Optional<Category> optCategory = categoryRepo.findById(categoryId);
		if(optCategory.isEmpty()) {
			throw new CategoryException("Invalid category id : "+categoryId);
		}
		
		// check if category belongs to current seller or not
		Category category = optCategory.get();
		if(seller.getSellderId() != category.getSeller().getSellderId()) {
			throw new CategoryException("This category does not belong to you ! ");
		}
		
		
		// if seller Initially keeping product stock as 0 then set outOfStock true
		if(product.getNumberOfStocks() == 0) {
			product.setOutOfStcok(true);
		}
		
		product.setCategory(category);
		category.getProducts().add(product);
		
		categoryRepo.save(category);
		
		return product;
	}

}
