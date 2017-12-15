package oxymat.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import oxymat.demo.*;
import java.util.ArrayList;

/**
 * The order repository class, which handle the connection to the orders table in the database. All sql statements
 * that has something to do with orders get handled here.
 * @Author ALS
 * Edited by: PTJ
 */
@Repository
public class OrderRepository implements ICrud<Order>  {

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
        ArrayList<OrderDisplay> display = new ArrayList<OrderDisplay>();
        ArrayList<Order> orders = new ArrayList<Order>();
        SqlRowSet order = jdbc.queryForRowSet("SELECT * FROM orders");
        while(order.next()){
            orders.add(new Order(order.getInt("id"), order.getInt("user"), order.getInt("customer"), order.getInt("model"), order.getDate("placement_date"), order.getDate("delivery_date"), order.getInt("status")));
        }

        return orders;
    }

    public ArrayList<OrderDisplay> displayAll() {
        ArrayList<OrderDisplay> display = new ArrayList<OrderDisplay>();
        ArrayList<User> userList = new ArrayList<>();
        ArrayList<Model> modelList = new ArrayList<>();
        ArrayList<Customer> customerList = new ArrayList<>();
        ArrayList<String> statusList = new ArrayList<>();

        SqlRowSet model = jdbc.queryForRowSet("SELECT name FROM models");
        SqlRowSet user = jdbc.queryForRowSet("SELECT id,firstname,lastname FROM users");
        SqlRowSet customer = jdbc.queryForRowSet("SELECT name FROM customers");

        SqlRowSet order = jdbc.queryForRowSet("SELECT * FROM orders");

        int u = 0;
        int m = 0;
        int o = 0;
        int c = 0;

        statusList.add("Behandles");
        statusList.add("Afsendt");
        statusList.add("Leveret");
        statusList.add("Aflyst");

        while(user.next()){
            userList.add(new User(user.getInt("id"),user.getString("firstname"),user.getString("lastname"),"null","null","null","null",0));
            System.out.println(userList.get(u).getId()+" "+userList.get(u).getFirstname());
            u++;
        }
        System.out.println("users: "+u);
        System.out.println("");

        while(model.next()){
            modelList.add(new Model((m+1),model.getString("name"),0));
            System.out.println(modelList.get(m).getId()+" "+modelList.get(m).getName());
            m++;
        }
        System.out.println("models: "+m);
        System.out.println("");

        while(customer.next()){
            customerList.add(new Customer((c+1),customer.getString("name"),0,"null","null","null"));
            System.out.println(customerList.get(c).getId()+" "+customerList.get(c).getName());
            c++;
        }
        System.out.println("customers: "+c);
        System.out.println("");

        while(order.next()) {
            String fullname = (userList.get(order.getInt("user")-1).getFirstname()+" "+ userList.get(order.getInt("user")-1).getLastname());
            display.add(new OrderDisplay(order.getInt("id"), fullname, customerList.get(order.getInt("customer")).getName(), modelList.get(order.getInt("model")).getName(), order.getDate("placement_date"), order.getDate("delivery_date"), statusList.get(order.getInt("status"))));
            System.out.println("order: "+order.getInt("id")+" "+fullname+" "+customerList.get(order.getInt("customer")).getName()+" "+modelList.get(order.getInt("model")).getName()+" "+ order.getDate("placement_date")+" "+order.getDate("delivery_date")+" "+statusList.get(order.getInt("status")));
            o++;
        }

        System.out.println("orders: "+o);
        System.out.println("");

        return display;
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(int id) {

    }

    public OrderDisplay findByOrderNumber(String orderId){
        ArrayList<OrderDisplay> display = new ArrayList<OrderDisplay>();
        ArrayList<Order> orderList = new ArrayList<Order>();
        ArrayList<User> userList = new ArrayList<>();
        ArrayList<Model> modelList = new ArrayList<>();
        ArrayList<Customer> customerList = new ArrayList<>();
        ArrayList<String> statusList = new ArrayList<>();

        SqlRowSet model = jdbc.queryForRowSet("SELECT name FROM models");
        SqlRowSet user = jdbc.queryForRowSet("SELECT id,firstname,lastname FROM users");
        SqlRowSet customer = jdbc.queryForRowSet("SELECT name FROM customers");


        SqlRowSet allorder = jdbc.queryForRowSet("SELECT * FROM orders");

        int n = 0;
        int orderInt = Integer.parseInt(orderId);

        while(allorder.next()) {
            n++;
        }
        if(orderInt>n){
            orderInt=n;
        }

        SqlRowSet order = jdbc.queryForRowSet("SELECT * FROM orders WHERE id = '" + orderInt + "'");

        int u = 0;
        int m = 0;
        int c = 0;

        statusList.add("Behandles");
        statusList.add("Afsendt");
        statusList.add("Leveret");
        statusList.add("Aflyst");

        while(user.next()){
            userList.add(new User(user.getInt("id"),user.getString("firstname"),user.getString("lastname"),"null","null","null","null",0));
            System.out.println(userList.get(u).getId()+" "+userList.get(u).getFirstname()+" "+userList.get(u).getLastname());
            u++;
        }
        System.out.println("users: "+u);
        System.out.println("");

        while(model.next()){
            modelList.add(new Model((m+1),model.getString("name"),0));
            System.out.println(modelList.get(m).getId()+" "+modelList.get(m).getName());
            m++;
        }
        System.out.println("models: "+m);
        System.out.println("");

        while(customer.next()){
            customerList.add(new Customer((c+1),customer.getString("name"),0,"null","null","null"));
            System.out.println(customerList.get(c).getId()+" "+customerList.get(c).getName());
            c++;
        }
        System.out.println("customers: "+c);
        System.out.println("");

        while(order.next()) {
            String fullname = (userList.get(order.getInt("user")-1).getFirstname()+" "+ userList.get(order.getInt("user")-1).getLastname());
            System.out.println("name "+fullname);
            return new OrderDisplay(order.getInt("id"), fullname, customerList.get(order.getInt("customer")).getName(), modelList.get(order.getInt("model")).getName(), order.getDate("placement_date"), order.getDate("delivery_date"), statusList.get(order.getInt("status")));
        }

        System.out.println("");

        return null;
    }



}