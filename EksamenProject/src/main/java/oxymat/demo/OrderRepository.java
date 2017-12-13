package oxymat.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import oxymat.demo.*;
import java.util.ArrayList;

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
        ArrayList<Order> orderList = new ArrayList<Order>();
        ArrayList<User> userList = new ArrayList<>();
        ArrayList<Model> modelList = new ArrayList<>();
        ArrayList<Customer> customerList = new ArrayList<>();
        ArrayList<String> statusList = new ArrayList<>();

        SqlRowSet model = jdbc.queryForRowSet("SELECT name FROM models");
        SqlRowSet user = jdbc.queryForRowSet("SELECT id,firstname FROM users");
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
            userList.add(new User(user.getInt("id"),user.getString("firstname"),"null","null","null","null","null",0));
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
            display.add(new OrderDisplay(order.getInt("id"), userList.get(order.getInt("user")).getFirstname(), customerList.get(order.getInt("customer")).getName(), modelList.get(order.getInt("model")).getName(), order.getDate("placement_date"), order.getDate("delivery_date"), statusList.get(order.getInt("status"))));
            System.out.println("order test: "+order.getInt("id")+" "+userList.get(order.getInt("user")).getFirstname()+" "+customerList.get(order.getInt("customer")).getName()+" "+modelList.get(order.getInt("model")).getName()+" "+ order.getDate("placement_date")+" "+order.getDate("delivery_date")+" "+statusList.get(order.getInt("status")));
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

    public ArrayList<Order> gettop10(){
        ArrayList<Order> orders = new ArrayList<>();
        SqlRowSet orderss = jdbc.queryForRowSet("SELECT * FROM orders WHERE placement_date>'2017-12-02' LIMIT 5");
        while(orderss.next()){
            orders.add(new Order(orderss.getInt("id"), orderss.getInt("user"),orderss.getInt("customer"), orderss.getInt("model"), orderss.getDate("placement_date"), orderss.getDate("delivery_date"), orderss.getInt("status")));
        }
        return orders;
    }

    public OrderDisplay findByOrderNumber(String orderId){
        ArrayList<OrderDisplay> display = new ArrayList<OrderDisplay>();
        ArrayList<Order> orderList = new ArrayList<Order>();
        ArrayList<User> userList = new ArrayList<>();
        ArrayList<Model> modelList = new ArrayList<>();
        ArrayList<Customer> customerList = new ArrayList<>();
        ArrayList<String> statusList = new ArrayList<>();

        SqlRowSet model = jdbc.queryForRowSet("SELECT name FROM models");
        SqlRowSet user = jdbc.queryForRowSet("SELECT id,firstname FROM users");
        SqlRowSet customer = jdbc.queryForRowSet("SELECT name FROM customers");

        SqlRowSet order = jdbc.queryForRowSet("SELECT * FROM orders WHERE id = '" + orderId + "'");

        int u = 0;
        int m = 0;
        int c = 0;

        statusList.add("Behandles");
        statusList.add("Afsendt");
        statusList.add("Leveret");
        statusList.add("Aflyst");

        while(user.next()){
            userList.add(new User(user.getInt("id"),user.getString("firstname"),"null","null","null","null","null",0));
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
            return new OrderDisplay(order.getInt("id"), userList.get(order.getInt("user")).getFirstname(), customerList.get(order.getInt("customer")).getName(), modelList.get(order.getInt("model")).getName(), order.getDate("placement_date"), order.getDate("delivery_date"), statusList.get(order.getInt("status")));
        }

        System.out.println("");

        return null;
    }
}