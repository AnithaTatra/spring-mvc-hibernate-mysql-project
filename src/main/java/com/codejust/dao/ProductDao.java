package com.codejust.dao;

import java.util.List;

import com.codejust.model.Product;

public interface ProductDao {

	Product getProduct(Long id);

	Long saveProduct(Product pt);

	List<Product> listAllProducts();

	void updateProduct(Product pt);

	void deleteProduct(Product pt);

}
