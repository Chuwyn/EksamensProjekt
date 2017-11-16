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
        jdbc.update("INSERT INTO users (firstname, lastname, username, password, mail, phone) VALUES ('"+ user.getFirstname() + "','" + user.getLastname() + "','" + user.getUsername() + "','" +user.getPassword()+"','"+user.getMail()+"','"+user.getPhone() +"')");
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
                "firstname = '" + user.getFirstname()   + "'," +
                "lastname  = '" + user.getLastname()    + "'," +
                "username  = '" + user.getUsername()    + "'," +
                "password  = '" + user.getPassword()    + "'," +
                "mail      = '" + user.getMail()       + "'," +
                "phone     = '" + user.getPhone() + "' WHERE id = '" + user.getId() +"'");
    }

    @Override
    public void delete(int id) {
        jdbc.update("DELETE FROM users WHERE id ='" + id +"'");
    }

    public User findUserByUsername(String username, String password){
        SqlRowSet user = jdbc.queryForRowSet("SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'");
        while(user.next()){
            return new User(user.getInt("id"), user.getString("firstname"), user.getString("lastname"), user.getString("mail"), user.getString("phone"), user.getString("username"), user.getString("password"));
        }
        return null;
    }
}
