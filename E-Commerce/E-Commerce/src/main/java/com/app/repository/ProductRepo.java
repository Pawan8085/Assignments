package com.app.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.Category;
import com.app.entities.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{
	
	Page<Product> findByCategory(Category category, Pageable pageable);
	
	@Query("Select p FROM Product p where p.name LIKE %:key% or p.subCategory LIKE %:key% or p.type LIKE %:key% or p.category.category LIKE %:key%")
	Page<Product> searchProductByKey(@Param("key") String key, Pageable pageable);

}
