package lhj.web.learn.springweb.test.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import lhj.web.learn.springweb.domain.Product;
import lhj.web.learn.springweb.repository.ProductDAO;
import lhj.web.learn.springweb.service.impl.SimpleProductManager;
import lhj.web.learn.springweb.test.repository.impl.InMemoryProductDAO;

import org.junit.Before;
import org.junit.Test;

/**
 * The manager of Entity product.
 * @author sealion
 *
 */
public class SimpleProductManagerTest {

    private SimpleProductManager productManager;
    private List<Product> products;
    private static int PRODUCT_COUNT = 2;
    private static Double CHAIR_PRICE = new Double(20.50);
    private static String CHAIR_DESCRIPTION = "Chair";
    
    private static Double TABLE_PRICE = new Double(150.10);
    private static String TABLE_DESCRIPTION = "Table";
    
    private static int POSITIVE_PRICE_INCREASE = 10;
    @Before
    public final void setUp() throws Exception {
        productManager = new SimpleProductManager();
        products = new ArrayList<Product>();
        
        Product product = new Product();
        product.setDescription(CHAIR_DESCRIPTION);
        product.setPrice(CHAIR_PRICE);
        products.add(product);
        
        product = new Product();
        product.setDescription(TABLE_DESCRIPTION);
        product.setPrice(TABLE_PRICE);
        products.add(product);
        
        ProductDAO productDAO = new InMemoryProductDAO(products);
        productManager.setProductDAO(productDAO);
    }

    @Test
    public final void testGetProductsWithNoProducts() {
        productManager = new SimpleProductManager();
        productManager.setProductDAO(new InMemoryProductDAO(null));
        assertNull(productManager.getProducts());
    }
    
    @Test
    public final void testGetProducts() {
        List<Product> products = productManager.getProducts();
        assertNotNull(products);
        assertEquals(PRODUCT_COUNT, products.size());
        
        Product product = products.get(0);
        assertEquals(CHAIR_DESCRIPTION, product.getDescription());
        assertEquals(CHAIR_PRICE, product.getPrice());
        
        product = products.get(1);
        assertEquals(TABLE_DESCRIPTION, product.getDescription());
        assertEquals(TABLE_PRICE, product.getPrice());
    }
    
    @Test
    public final void testIncreasePriceWithNullListOfProducts() {
        try {
            productManager = new SimpleProductManager();
            productManager.setProductDAO(new InMemoryProductDAO(null));
            productManager.increasePrice(POSITIVE_PRICE_INCREASE);
        } catch (NullPointerException e) {
            fail("Products list is null");
        }
    }
    
    @Test
    public final void testIncreasePriceWithEmptyListOfProducts() {
        try {
            productManager = new SimpleProductManager();
//            productManager.setProducts(new ArrayList<Product>());

            productManager.setProductDAO(new InMemoryProductDAO(new ArrayList<Product>()));
            productManager.increasePrice(POSITIVE_PRICE_INCREASE);
        } catch (NullPointerException e) {
            fail("Products list is null");
        }
        
    }
    
    @Test
    public final void testIncreasePriceWithPositivePercentage() {
        productManager.increasePrice(POSITIVE_PRICE_INCREASE);
        Double expectedChairPriceWithIncrease = 22.55;
        Double expectedTablePriceWithIncrease = 165.11;
        
        List<Product> products = productManager.getProducts();
        Product product = products.get(0);
        assertEquals(expectedChairPriceWithIncrease, product.getPrice());
        
        product = products.get(1);
        assertEquals(expectedTablePriceWithIncrease, product.getPrice());
    }

}
