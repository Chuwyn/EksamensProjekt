package oxymat.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import oxymat.demo.Login;
import oxymat.demo.User;
import oxymat.demo.UserRepository;

@Controller
public class UController {


    @Autowired
    private UserRepository userRepository = new UserRepository();

    private User activeUser;


    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("us", model);
        return "index";
    }



    @ModelAttribute("login")
    public Login addEmptyLogin(){
        return new Login();
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("login") Login login, Model model){

        activeUser = userRepository.findUserByUsername(login.getUsername(), login.getPassword());
        System.out.println("Welcome " + activeUser.getFirstname());

        
        return "orders";
    }


    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("login", new Login());
        return "index";
    }






    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("user", new User());
        return "create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute User user){
        userRepository.create(user);
        return "redirect:/";
    }

}
