package oxymat.demo.controller;

/*
import oxymat.demo.model.entities.User;
import oxymat.demo.model.repositories.IUserRepository;
import oxymat.demo.model.repositories.UserArrayRepository;
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import oxymat.demo.UserRepository;

@Controller
public class UController {

    @Autowired
    private UserRepository userRepository = new UserRepository();

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("us", model);
        return "index";
    }

}
