package oxymat.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserRepository implements ICrud<User> {

    @Autowired
    private JdbcTemplate jdbc;



    @Override
    public void create(User user) {
        int display = 1;
        SqlRowSet users = jdbc.queryForRowSet("SELECT * FROM users");
        while(users.next()){
            if(display>3){
               display = 1;
            }
            System.out.println("Display number: "+display);
            display++;
        }
        if(display>3){
            display = 1;
        }
        jdbc.update("INSERT INTO users (firstname, lastname, username, password, mail, phone, display) VALUES ('"+ user.getFirstname() + "','" + user.getLastname() + "','" + user.getUsername() + "','" +user.getPassword()+"','"+user.getMail()+"','"+user.getPhone() +"','"+display+"')");
    }

    @Override
    public User read(int id) {
        SqlRowSet user = jdbc.queryForRowSet("SELECT * FROM users WHERE id = '" + id + "'");
        while(user.next()){
            return new User(user.getInt("id"), user.getString("firstname"), user.getString("lastname"), user.getString("mail"), user.getString("phone"), user.getString("username"), user.getString("password"), user.getInt("display"));
        }
        return null;
    }

    @Override
    public ArrayList<User> readAll() {
        ArrayList<User> userList = new ArrayList<User>();
        SqlRowSet user = jdbc.queryForRowSet("SELECT * FROM users");
        User us = new User();
        int count = 0;
        System.out.println(" ");
        System.out.println("Display user: x  ###  ID  FULL NAME   EMAIL              PHONE");
        System.out.println(" ");
        while(user.next()){
            userList.add(new User(user.getInt("id"), user.getString("firstname"), user.getString("lastname"), user.getString("mail"), user.getString("phone"), user.getString("username"), user.getString("password"), user.getInt("display")));
            us.setDisplay(user.getInt("display"));
            count += 1;
            System.out.println("Display user: "+us.getDisplay()+"  ###  "+user.getInt("id")+"   "+user.getString("firstname")+" "+user.getString("lastname")+"  "+user.getString("mail")+"  "+user.getString("phone"));
        }
        System.out.println(" ");
        System.out.println("Display "+count+" users from database");
        System.out.println(" ");


        return userList;
    }

    @Override
    public void update(User user) {
        jdbc.update("UPDATE users SET " +
                "firstname = '" + user.getFirstname()   + "'," +
                "lastname  = '" + user.getLastname()    + "'," +
                "username  = '" + user.getUsername()    + "'," +
                "password  = '" + user.getPassword()    + "'," +
                "mail      = '" + user.getMail()        + "'," +
                "phone     = '" + user.getPhone()       + "'," +
                "display   = '" + user.getDisplay()     + "+" +
                "' WHERE id = '" + user.getId() +"'");
    }

    @Override
    public void delete(int id) {
        jdbc.update("DELETE FROM users WHERE id ='" + id +"'");
    }

    public User findUserByUsername(String username, String password){
        SqlRowSet user = jdbc.queryForRowSet("SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'");
        while(user.next()){
            return new User(user.getInt("id"), user.getString("firstname"), user.getString("lastname"), user.getString("mail"), user.getString("phone"), user.getString("username"), user.getString("password"), user.getInt("display"));
        }
        return null;
    }
}
