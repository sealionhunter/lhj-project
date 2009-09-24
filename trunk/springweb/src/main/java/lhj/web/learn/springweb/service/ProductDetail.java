package lhj.web.learn.springweb.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProductDetail {
    protected final Log logger = LogFactory.getLog(getClass());
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int percentage) {
        this.id = percentage;
        logger.info("Percentage set to " + percentage);
    }
}
