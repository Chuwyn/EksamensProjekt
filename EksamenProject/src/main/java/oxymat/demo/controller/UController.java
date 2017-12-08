package oxymat.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import oxymat.demo.Login;
import oxymat.demo.ProductRepository;
import oxymat.demo.User;
import oxymat.demo.UserRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UController {


    @Autowired
    private UserRepository users = new UserRepository();

    @Autowired
    private ProductRepository products = new ProductRepository();

    private User activeUser = new User(0, "null", "null", "null", "null", "null", "null", 0);


    @GetMapping("/")
    public String index(Model model, User users) {
        users = activeUser;
        model.addAttribute("us", model);
        model.addAttribute("users", users);

        System.out.println("Welcome " + activeUser.getFirstname());

        return "index";
    }


    @ModelAttribute("login")
    public Login addEmptyLogin(){

        return new Login();
    }


    @PostMapping("/login")
    public String login(@ModelAttribute("login") Login login, Model model){

        activeUser = users.findUserByUsername(login.getUsername(), login.getPassword());
        System.out.println("Welcome " + activeUser.getFirstname());
            Date dNow = new Date( );
            SimpleDateFormat ft =
            new SimpleDateFormat ("E dd.MM.yyyy 'at' hh:mm:ss a" );
        System.out.println("You're logged in "+ft.format(dNow));
        System.out.println(" ");
        
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

        users.create(user);

        return "redirect:/";
    }


    /** start here **/
    @GetMapping("/products")
    public String products(Model model) {

        model.addAttribute("products", products.readAll());

        return "products";
    }


    @GetMapping("/orders")
    public String orders(Model model) {

        model.addAttribute("us", model);

        return "orders";
    }


    @GetMapping("/users")
    public String users(Model model) {

        System.out.println("Welcome " + activeUser.getFirstname());
        model.addAttribute("users", users.readAll());

        return "users";
    }


    @GetMapping("/stock")
    public String stock(Model model) {

        model.addAttribute("us", model);

        return "stock";
    }

    /** end here **/

}