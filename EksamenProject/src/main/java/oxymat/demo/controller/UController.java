package oxymat.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import oxymat.demo.*;;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UController {


    @Autowired
    private UserRepository users = new UserRepository();

    @Autowired
    private ProductRepository products = new ProductRepository();


    @Autowired
    private OrderRepository orders = new OrderRepository();

    /** Create User object called activeUser. activeUser contains "null" and 0's, just to act as a temp loginSession.
        When the user login into the index page, this activeUser will be replaced with information from the logged in user
        making the login-menu disappear when the user go back to the homepage.
     **/
    private User activeUser = new User(0, "null", "null", "null", "null", "null", "null", 0);



    @GetMapping("/")
    public String index(Model model, User users) {
        users = activeUser;
        model.addAttribute("us", model);
        model.addAttribute("users", users);

        System.out.println("activeUser: " + activeUser.getFirstname());

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



        return "products";
    }


    @GetMapping("/orders")
    public String orders(Model model) {

        model.addAttribute("order", new Order());
        model.addAttribute("orders", orders.readAll());
        model.addAttribute("orderstop5", orders.gettop10());

        return "orders";
    }



    @GetMapping("/users")
    public String users(Model model) {

        System.out.println("Welcome " + activeUser.getFirstname());
        model.addAttribute("users", users.readAll());

        return "users";
    }







    @GetMapping("/search")
    public String searchOrdersById(@RequestParam (value= "id", required = true) String id, Model model) {

        model.addAttribute("search", orders.findByOrderNumber(id));
        return "orders";
    }



    @PostMapping("/search")
    public String searchOrdersById(@ModelAttribute Order order, Model model) {
        model.addAttribute("order", order);
        return "orders";
    }


    @GetMapping("/stock")
    public String stock(Model model) {

        model.addAttribute("us", model);

        return "stock";
    }

    /** end here **/

}