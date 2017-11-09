package oxymat.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserRepository implements ICrud<User> {

    @Autowired
    private JdbcTemplate jdbc;

    private ArrayList<User> users;

    @Override
    public void create(User user) {

    }

    @Override
    public void read(int id) {

    }

    @Override
    public ArrayList<User> readAll() {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {

    }
}
