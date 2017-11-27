package oxymat.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
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
        Product prod = new Product();
        while(product.next()){
            return new Product(product.getInt("id"), product.getString("name"), product.getString("price"), prod.getDisplay());
        }
        return null;
    }

    @Override
    public ArrayList<Product> readAll() {
        ArrayList<Product> productList = new ArrayList<Product>();
        SqlRowSet product = jdbc.queryForRowSet("SELECT * FROM models");
        Product prod = new Product();
        while(product.next()){
            productList.add(new Product(product.getInt("id"), product.getString("name"), product.getString("price"), prod.getDisplay()));
            if(prod.getDisplay()==3) {prod.setDisplay(0);}
            int newDisplay = prod.getDisplay()+1;
            prod.setDisplay(newDisplay);
            System.out.println("display: "+prod.getDisplay());
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
