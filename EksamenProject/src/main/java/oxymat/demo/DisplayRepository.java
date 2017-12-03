package oxymat.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class DisplayRepository implements ICrud<Display>{


    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public ArrayList<Display> readAll() {
        ArrayList<Display> displayList = new ArrayList<>();
        SqlRowSet product = jdbc.queryForRowSet("SELECT * FROM models");
        Display ds = new Display();
        while (product.next()) {
           displayList.add(new Display(ds.getDisplay()));
            if (ds.getDisplay() == 3) {
                ds.setDisplay(0);
            }
            int newDisplay = ds.getDisplay() + 1;
            ds.setDisplay(newDisplay);
            System.out.println("display: " + ds.getDisplay());
        }


        return displayList;
    }

    @Override
    public Display read(int id) {
        return null;
    }

    @Override
    public void create(Display display) {
    }

    @Override
    public void update(Display display) {}

    @Override
    public void delete(int id) {}
}


