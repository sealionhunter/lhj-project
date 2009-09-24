package lhj.web.learn.springweb.repository;

import java.io.Serializable;
import java.util.List;

import lhj.web.learn.springweb.domain.Product;

public interface ProductDAO extends Serializable {
    public List<Product> getProductList();

    public void saveProduct(Product prod);

    public Product load(int id);

    public void delete(Product prod);
}
