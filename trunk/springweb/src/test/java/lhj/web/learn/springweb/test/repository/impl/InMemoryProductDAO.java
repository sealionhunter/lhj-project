package lhj.web.learn.springweb.test.repository.impl;

import java.util.List;

import lhj.web.learn.springweb.domain.Product;
import lhj.web.learn.springweb.repository.ProductDAO;

public class InMemoryProductDAO implements ProductDAO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1914596459742387052L;
	private List<Product> products;
	public InMemoryProductDAO(List<Product> products) {
		this.products = products;
	}
	
	@Override
	public List<Product> getProductList() {
		return this.products;
	}

	@Override
	public void saveProduct(Product prod) {
		
	}

	@Override
	public void delete(Product prod) {

	}

	@Override
	public Product load(int id) {
		for (Product prod : products) {
			if (id == prod.getId()) {
				return prod;
			}
		}
		return null;
	}

}
