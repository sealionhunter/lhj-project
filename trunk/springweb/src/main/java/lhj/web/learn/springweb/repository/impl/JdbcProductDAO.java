package lhj.web.learn.springweb.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import lhj.web.learn.springweb.domain.Product;
import lhj.web.learn.springweb.repository.ProductDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class JdbcProductDAO extends SimpleJdbcDaoSupport implements ProductDAO {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5268065965665718948L;
    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public List<Product> getProductList() {
        logger.info("Getting Products!");
        List<Product> products = getSimpleJdbcTemplate().query("SELECT id, description, price from product",
                new ProductMapper());
        return products;
    }

    @Override
    public void saveProduct(Product prod) {
        logger.info("Saving Product: " + prod.getDescription() + "!");
        int count = getSimpleJdbcTemplate().update(
                "update product set description = :description, price = :price where id = :id",
                new MapSqlParameterSource().addValue("description", prod.getDescription()).addValue("price",
                        prod.getPrice()).addValue("id", prod.getId()));
        logger.info("Rows affected: " + count);
    }

    @Override
    public void delete(Product prod) {
        logger.info("Delete Product: " + prod.getDescription() + "!");
        int count = getSimpleJdbcTemplate().update("DELETE FROM product where id = :id",
                new MapSqlParameterSource().addValue("id", prod.getId()));
        logger.info("Rows affected: " + count);
        // return products;
    }

    @Override
    public Product load(int id) {
        logger.info("Getting Products!");
        Product products = getSimpleJdbcTemplate().queryForObject(
                "SELECT id, description, price from product where id = :id", Product.class,
                new MapSqlParameterSource().addValue("id", id));
        return products;
    }

    private static class ProductMapper implements ParameterizedRowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int index) throws SQLException {
            Product product = new Product();
            product.setDescription(rs.getString("description"));
            product.setPrice(rs.getDouble("price"));
            product.setId(rs.getInt("id"));
            return product;
        }

    }

}
