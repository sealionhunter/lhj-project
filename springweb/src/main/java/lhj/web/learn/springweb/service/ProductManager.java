package lhj.web.learn.springweb.service;

import java.io.Serializable;
import java.util.List;

import lhj.web.learn.springweb.domain.Product;

public interface ProductManager extends Serializable {
    public void increasePrice(int percentage);

    public List<Product> getProducts();

    public Product load(int id);
}
