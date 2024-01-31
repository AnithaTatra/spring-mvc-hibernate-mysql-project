package com.codejust.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import com.codejust.model.Product;

@Repository("productDao")
@Transactional
public class ProductDaoImpl extends AbstractDao<Long, Product> implements ProductDao {

	@Override
	public Product getProduct(Long id) {
		return getByKey(id);
	}

	@Override
	public Long saveProduct(Product pt) {
		persist(pt);
		return pt.getId();
	}

	@Override
	public List<Product> listAllProducts() {
		Criteria criteria = createEntityCriteria();
		return (List) criteria.list();
	}

	@Override
	public void updateProduct(Product pt) {
		update(pt);

	}

	@Override
	public void deleteProduct(Product pt) {
		delete(pt);

	}

}
