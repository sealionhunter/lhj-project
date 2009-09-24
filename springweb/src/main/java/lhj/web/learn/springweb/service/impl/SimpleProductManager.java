package lhj.web.learn.springweb.service.impl;

import java.util.List;

import lhj.web.learn.springweb.domain.Product;
import lhj.web.learn.springweb.repository.ProductDAO;
import lhj.web.learn.springweb.service.ProductManager;

public class SimpleProductManager implements ProductManager {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6514460724198011082L;

    // private List<Product> products;
    private ProductDAO productDAO = null;

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public List<Product> getProducts() {
        return productDAO.getProductList();
    }

    @Override
    public void increasePrice(int percentage) {
        List<Product> products = productDAO.getProductList();
        if (products != null) {
            for (Product product : products) {
                double newPrice = product.getPrice().doubleValue() * (100 + percentage) / 100;
                product.setPrice(newPrice);
                productDAO.saveProduct(product);
            }
        }
    }

    @Override
    public Product load(int id) {
        return productDAO.load(id);
    }

}
