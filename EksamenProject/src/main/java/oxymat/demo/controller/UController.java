package oxymat.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import oxymat.demo.User;
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


    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user){
        userRepository.create(user);
        return "redirect:/";
    }


    /** start here **/
    @GetMapping("/products")
    public String products(Model model) {

        model.addAttribute("us", model);
        return "products";
    }

    @GetMapping("/orders")
    public String orders(Model model) {

        model.addAttribute("us", model);
        return "orders";
    }

    @GetMapping("/users")
    public String users(Model model) {

        model.addAttribute("us", model);
        return "users";
    }

    @GetMapping("/stock")
    public String stock(Model model) {

        model.addAttribute("us", model);
        return "stock";
    }
    /** end here **/

}
