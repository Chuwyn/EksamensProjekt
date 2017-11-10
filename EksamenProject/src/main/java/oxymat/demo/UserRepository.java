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
        jdbc.update("INSERT INTO users (firstname, lastname, username, password, mail, phone) VALUES ('"+ user.getFirstName() + "','" + user.getLastName() + "','" + user.getUserName() + "','" +user.getPassword()+"','"+user.getEmail()+"','"+user.getPhoneNumber() +"')");
    }

    @Override
    public User read(int id) {
        SqlRowSet user = jdbc.queryForRowSet("SELECT * FROM users WHERE id = '" + id + "'");
        while(user.next()){
            return new User(user.getInt("id"), user.getString("firstname"), user.getString("lastname"), user.getString("mail"), user.getString("phone"), user.getString("username"), user.getString("password"));
        }
        return null;
    }

    @Override
    public ArrayList<User> readAll() {
        ArrayList<User> userList = new ArrayList<User>();
        SqlRowSet user = jdbc.queryForRowSet("SELECT * FROM users");
        while(user.next()){
            userList.add(new User(user.getInt("id"), user.getString("firstname"), user.getString("lastname"), user.getString("mail"), user.getString("phone"), user.getString("username"), user.getString("password")));
        }


        return userList;
    }

    @Override
    public void update(User user) {
        jdbc.update("UPDATE users SET " +
                "firstname = '" + user.getFirstName()   + "'," +
                "lastname  = '" + user.getLastName()    + "'," +
                "username  = '" + user.getUserName()    + "'," +
                "password  = '" + user.getPassword()    + "'," +
                "mail      = '" + user.getEmail()       + "'," +
                "phone     = '" + user.getPhoneNumber() + "' WHERE id = '" + user.getId() +"'");
    }

    @Override
    public void delete(int id) {
        jdbc.update("DELETE FROM users WHERE id ='" + id +"'");
    }
}
