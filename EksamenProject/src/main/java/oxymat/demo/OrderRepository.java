package oxymat.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class OrderRepository implements ICrud<Order> {

    @Autowired
    private JdbcTemplate jdbc;


    @Override
    public void create(Order order) {
        jdbc.update("INSERT INTO orders (user, customer, model, placement_date, delivery_date, status) VALUES ('" + order.getUserId() + "','" + order.getCustomerId() + "','" + order.getModelId() + "','" + order.getPlacementDate() + "','" + order.getDeliveryDate() + "','" + order.getStatus() +"')");
    }

    @Override
    public Order read(int id) {
        SqlRowSet order = jdbc.queryForRowSet("SELECT * FROM models WHERE id = '" + id +"'");
        while(order.next()){
            return new Order(order.getInt("id"), order.getInt("user"), order.getInt("customer"), order.getInt("model"), order.getDate("placement_date"), order.getDate("delivery_date"), order.getInt("status"));
        }
        return null;
    }

    @Override
    public ArrayList<Order> readAll() {
        ArrayList<Order> orders = new ArrayList<Order>();
        SqlRowSet order = jdbc.queryForRowSet("SELECT * FROM orders");
        while(order.next()){
            orders.add(new Order(order.getInt("id"), order.getInt("user"), order.getInt("customer"), order.getInt("model"), order.getDate("placement_date"), order.getDate("delivery_date"), order.getInt("status")));
        }

        return orders;
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(int id) {

    }

    public Order findByOrderNumber(String orderId){
        SqlRowSet order = jdbc.queryForRowSet("SELECT * FROM orders WHERE id = '" + orderId + "'");
        while(order.next()){
            return new Order(order.getInt("id"), order.getInt("user"),order.getInt("customer"), order.getInt("model"), order.getDate("placement_date"), order.getDate("delivery_date"), order.getInt("status"));
        }
        return null;
    }

    public ArrayList<Order> gettop10(){
        ArrayList<Order> orders = new ArrayList<>();
        SqlRowSet orderss = jdbc.queryForRowSet("SELECT * FROM orders WHERE placement_date>'2017-12-02' LIMIT 5");
        while(orderss.next()){
            orders.add(new Order(orderss.getInt("id"), orderss.getInt("user"),orderss.getInt("customer"), orderss.getInt("model"), orderss.getDate("placement_date"), orderss.getDate("delivery_date"), orderss.getInt("status")));
        }
        return orders;
    }
}
