package lhj.web.learn.springweb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lhj.web.learn.springweb.domain.Product;
import lhj.web.learn.springweb.service.ProductDetail;
import lhj.web.learn.springweb.service.ProductManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

public class ProductDetailController extends AbstractCommandController {

    private ProductManager productManager = null;

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }

    @Override
    protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object command,
            BindException errors) throws Exception {
        ProductDetail detail = (ProductDetail) command;
        Product product = productManager.load(detail.getId());
        return new ModelAndView("detail", "product", product);
    }

}
