package lhj.web.learn.springweb.test.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import lhj.web.learn.springweb.domain.Product;


public class ProductTest {

	private Product product;
	@Before
	public void setUp() throws Exception {
		product = new Product();
	}

	@Test
	public void testGetDescription() {
        String testDescription = "aDescription";
        assertNull(product.getDescription());
        product.setDescription(testDescription);
        assertEquals(testDescription, product.getDescription());

	}

	@Test
	public void testGetPrice() {
        double testPrice = 100.00;
        assertEquals(0, 0, 0);    
        product.setPrice(testPrice);
        assertEquals(testPrice, product.getPrice(), 0);

	}

}
