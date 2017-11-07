package oxymat.demo.controller;

/*
import oxymat.demo.model.entities.User;
import oxymat.demo.model.repositories.IUserRepository;
import oxymat.demo.model.repositories.UserArrayRepository;
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UController {



    @Autowired
    private JdbcTemplate jdbc;

    /*
    ArrayList<User> users = new ArrayList<User>();


    @Autowired
    IUserRepository userRepo = new UserArrayRepository();

    */

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("us", model);
        return "index";
    }

}
