package oxymat.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class OrderRepository implements ICrud<Order> {

    @Autowired
    private JdbcTemplate jdbc;


    @Override
    public void create(Order order) {

    }

    @Override
    public Order read(int id) {
        return null;
    }

    @Override
    public ArrayList<Order> readAll() {
        return null;
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(int id) {

    }
}
