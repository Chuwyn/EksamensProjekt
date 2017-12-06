package oxymat.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import oxymat.demo.*;

import java.util.ArrayList;

@Controller
public class UController {


    @Autowired
    private UserRepository users = new UserRepository();

    @Autowired
    private ProductRepository products = new ProductRepository();


    @Autowired
    private OrderRepository orders = new OrderRepository();

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

        activeUser = users.findUserByUsername(login.getUsername(), login.getPassword());
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

        model.addAttribute("us", model);
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
