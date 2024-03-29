package lhj.web.learn.springweb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import lhj.web.learn.springweb.service.PriceIncrease;
import lhj.web.learn.springweb.service.ProductManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

public class PriceIncreaseFormController extends SimpleFormController {

    private ProductManager productManager = null;
    protected final Log logger = LogFactory.getLog(getClass());

    public ModelAndView onSubmit(Object command) throws ServletException {
        int increase = ((PriceIncrease) command).getPercentage();
        logger.info("Increasing prices by " + increase + "%.");
        productManager.increasePrice(increase);
        logger.info("returning from PriceIncreaseForm view to " + getSuccessView());
        return new ModelAndView(new RedirectView(getSuccessView()));

    }

    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        PriceIncrease priceIncrease = new PriceIncrease();
        priceIncrease.setPercentage(20);
        return priceIncrease;
    }

    public ProductManager getProductManager() {
        return productManager;
    }

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }

}
