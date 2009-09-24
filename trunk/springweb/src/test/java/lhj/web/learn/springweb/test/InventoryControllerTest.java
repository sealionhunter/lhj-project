package lhj.web.learn.springweb.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Map;

import jp.co.dgic.testing.framework.DJUnitTestCase;

import lhj.web.learn.springweb.InventoryController;
import lhj.web.learn.springweb.domain.Product;
import lhj.web.learn.springweb.service.impl.SimpleProductManager;
import lhj.web.learn.springweb.test.repository.impl.InMemoryProductDAO;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

public class InventoryControllerTest extends DJUnitTestCase {

	@SuppressWarnings("unchecked")
	@Test
	public void testHandleRequest() throws Exception {
		InventoryController controller = new InventoryController();
		SimpleProductManager spm = new SimpleProductManager();
		spm.setProductDAO(new InMemoryProductDAO(new ArrayList<Product>()));
		controller.setProductManager(spm);
		ModelAndView modelAndView = controller.handleRequest(null, null);
		assertEquals("hello", modelAndView.getViewName());
		assertNotNull(modelAndView.getModel());
		
		Map<String, Object> model = (Map) modelAndView.getModel().get("model");
		String nowValue = (String) model.get("now");
		assertNotNull(nowValue);
	}

}
