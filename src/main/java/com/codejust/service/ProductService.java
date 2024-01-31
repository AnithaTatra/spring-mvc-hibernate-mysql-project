package com.codejust.service;

import java.util.List;

import com.codejust.model.Product;

public interface ProductService {

	Product getProduct(Long id);

	Long saveProduct(Product st);

	List<Product> listAllProducts();

	void update(Long id, Product st);

	void delete(Long id);
	
	boolean isProductUnique(Long id);

}
