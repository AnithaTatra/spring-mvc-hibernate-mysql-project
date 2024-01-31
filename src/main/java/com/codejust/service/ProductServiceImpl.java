package com.codejust.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codejust.dao.ProductDao;
import com.codejust.model.Product;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public Product getProduct(Long id) {

		return productDao.getProduct(id);
	}

	@Override
	public Long saveProduct(Product st) {
		return productDao.saveProduct(st);
	}

	@Override
	public List<Product> listAllProducts() {
		return productDao.listAllProducts();
	}

	@Override
	public void update(Long id, Product st) {
		Product stEntity = productDao.getProduct(id);
		if (stEntity != null) {
			stEntity.setName(st.getName());
			stEntity.setDescription(st.getDescription());
			stEntity.setPrice(st.getPrice());
			productDao.updateProduct(stEntity);
		}

	}

	@Override
	public void delete(Long id) {
		Product stEntity = productDao.getProduct(id);
		if (stEntity != null) {
			productDao.deleteProduct(stEntity);
		}

	}

	@Override
	public boolean isProductUnique(Long id) {
		Product prod = productDao.getProduct(id);
		return (prod == null || (id != null & !id.equals(prod.getId())));
	}

}
