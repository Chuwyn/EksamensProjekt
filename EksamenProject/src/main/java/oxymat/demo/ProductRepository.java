package oxymat.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;

public class ProductRepository implements ICrud<Product> {


    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public void create(Product product) {
        jdbc.update("INSERT into models (name, price) VALUES ('" + product.getTitle() + "','" + product.getPrice() + "')");
    }

    @Override
    public Product read(int id) {
        SqlRowSet product = jdbc.queryForRowSet("SELECT * FROM models WHERE id = '" + id + "'");
        while(product.next()){
            return new Product(product.getInt("id"), product.getString("name"), product.getString("price"));
        }
        return null;
    }

    @Override
    public ArrayList<Product> readAll() {
        ArrayList<Product> productList = new ArrayList<Product>();
        SqlRowSet product = jdbc.queryForRowSet("SELECT * FROM models");
        while(product.next()){
            productList.add(new Product(product.getInt("id"), product.getString("name"), product.getString("price")));
        }


        return productList;
    }

    @Override
    public void update(Product product) {
        jdbc.update("UPDATE models SET" +
                "name = '" + product.getTitle() +"'," +
                "price = '" + product.getPrice() + "' WHERE id='" + product.getId() + "'");
    }

    @Override
    public void delete(int id) {
        jdbc.update("DELETE FROM models WHERE id ='" + id +"'");
    }
}
