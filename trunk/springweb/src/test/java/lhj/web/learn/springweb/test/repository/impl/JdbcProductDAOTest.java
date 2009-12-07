package lhj.web.learn.springweb.test.repository.impl;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public class JdbcProductDAOTest extends AbstractTransactionalDataSourceSpringContextTests {

//	private ProductDAO productDAO;
//	
//	public void setProductDAO(ProductDAO productDAO) {
//		this.productDAO = productDAO;
//	}
//	
//	@Override
//	protected String[] getConfigLocations() {
//		return new String[]{"classpath:test-context.xml"};
//	}
//	
//	@Override
//	protected void onSetUpInTransaction() throws Exception {
//		super.deleteFromTables(new String[]{"product"});
//		super.executeSqlScript("file:dbscript/load-data.sql", true);
//	}
//
//	@Test
//	public void testGetProductList() {
//		List<Product> products = productDAO.getProductList();
//		assertEquals("Wrong number of products!", 3, products.size());
//	}
//
//	@Test
//	public void testSaveProduct() {
//
//		List<Product> products = productDAO.getProductList();
//		for (Product product : products) {
//			product.setPrice(220.18);
//			productDAO.saveProduct(product);
//		}
//		products = productDAO.getProductList();
//		for (Product product : products) {
//			assertEquals("Wrong price of product!", 220.18, product.getPrice());
//		}
//	}

}
