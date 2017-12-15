package oxymat.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import oxymat.demo.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The UController is the controller class, here we map the different repositories to the views.
 * This is the heart of the system, that connects all the classes.
 * @Author ALS and PTJ
 */
@Controller
public class UController {


    /**
     * The user repository. Connects to user table in the database.
     */
    @Autowired
    private UserRepository users = new UserRepository();

    /**
     * The product repository. Connects to the product table in the database.
     */
    @Autowired
    private ProductRepository products = new ProductRepository();

    /**
     * The order repository. Connects to the orders table in the database.
     */
    @Autowired
    private OrderRepository orders = new OrderRepository();

    /**
     * A list of order display, used for showing the search result, when you search for a order.
     */
    private List<OrderDisplay> searchResults = new ArrayList<OrderDisplay>();

    /** Create User object called activeUser. activeUser contains "null" and 0's, just to act as a temp loginSession.
        When the user login into the index page, this activeUser will be replaced with information from the logged in user
        making the login-menu disappear when the user go back to the homepage.
     **/
    private User activeUser = new User(0, "null", "null", "null", "null", "null", "null", 0);


    /**
     * The get mapping function for the index page, here the active user gets mapped to the model.
     * @param model the model
     * @param users the active user
     * @return "index" the mapping to index.html
     */
    @GetMapping("/")
    public String index(Model model, User users) {
        users = activeUser;
        model.addAttribute("users", users);
        model.addAttribute("us", model);
        model.addAttribute("logout", activeUser);

        System.out.println("activeUser: " + activeUser.getFirstname());

        return "index";
    }


    /**
     * Logout function, sets the active user to null user.
     * @param model The model where the active user is changed.
     * @return a redirect to the index page.
     */
    @PostMapping("/logout")
    public String logout(Model model){
        System.out.println("Goodbye " + activeUser.getFirstname());
        activeUser = new User(0, "null", "null", "null", "null", "null", "null", 0);
        Date dNow = new Date( );
        SimpleDateFormat ft =
                new SimpleDateFormat ("E dd.MM.yyyy 'at' hh:mm:ss a" );
        System.out.println("You're logged out "+ft.format(dNow));
        System.out.println(" ");

        return "redirect:/";
    }


    /**
     * A method that returns an empty login object, to be used as placeholder.
     * @return
     */
    @ModelAttribute("login")
    public Login addEmptyLogin(){

        return new Login();
    }


    /**
     * Login function, this gets the user from the db if login credentials is right.
     * @param login The login object, containing username and password for login.
     * @param model The model where the active user gets added. Is null if the user dont exist in db.
     * @return a redirect to the index page.
     */
    @PostMapping("/login")
    public String login(@ModelAttribute("login") Login login, Model model){

        activeUser = users.findUserByUsername(login.getUsername(), login.getPassword());
        System.out.println("Welcome " + activeUser.getFirstname());
            Date dNow = new Date( );
            SimpleDateFormat ft =
            new SimpleDateFormat ("E dd.MM.yyyy 'at' hh:mm:ss a" );
        System.out.println("You're logged in "+ft.format(dNow));
        System.out.println(" ");
        
        return "redirect:/";
    }

    /**
     * Get mapping login function.
     * @param model model where the empty login gets added to.
     * @return the index page.
     */
    @GetMapping("/login")
    public String login(Model model){

        model.addAttribute("login", new Login());


        return "index";
    }


    /**
     * Adds the empty user to the model, which then gets made to a user.
     * @param model which gets the placeholder user added to.
     * @return "create" to continue the create process
     */
    @GetMapping("/create")
    public String create(Model model){

        model.addAttribute("user", new User());

        return "create";
    }


    /**
     * Adds the newly created user to the repository.
     * @param user the newly created user
     * @return a redirect to the index page.
     */
    @PostMapping("/create")
    public String create(@ModelAttribute User user){

        users.create(user);

        return "redirect:/";
    }


    /**
     * Adds all the products to the model.
     * @param model The model
     * @param users the active user
     * @return "products"
     */
    @GetMapping("/products")
    public String products(Model model, User users) {

        users = activeUser;
        model.addAttribute("users", users);
        model.addAttribute("products", products.readAll());

        return "products";
    }

    /**
     * display all orders on orders.html
     * @param model the model to add all orders.
     * @param users the active users
     * @return orders
     */
    @GetMapping("/orders")
    public String orders(Model model, User users) {

        users = activeUser;
        model.addAttribute("users", users);
        model.addAttribute("orders", orders.displayAll());
        model.addAttribute("search", new Order());

        return "orders";
    }


    /**
     * adds all the users to the model, to show them on users.html
     * @param model the model
     * @param userss the active user
     * @return
     */
    @GetMapping("/users")
    public String users(Model model, User userss) {

        userss = activeUser;
        model.addAttribute("userss", userss);
        System.out.println("Welcome " + activeUser.getFirstname());
        model.addAttribute("users", users.readAll());

        return "users";
    }

    /**
     * check if the string is a number and then searches for the order in the order repository.
     * @param id the id to search for
     * @param model the model the result gets added to.
     * @param users the active user.
     * @return "searchResult"
     */
    @GetMapping("/search")
    public String searchOrdersById(@RequestParam (value= "id", required = true) String id, Model model, User users) {
        if(checkIsStringNumber(id)){
            searchResults.clear();

            searchResults.add(orders.findByOrderNumber(id));

            users = activeUser;
            model.addAttribute("users", users);
            model.addAttribute("search", new Order());
            model.addAttribute("searchResults", searchResults);
        }

        return "searchResult";
    }

    /**
     * Check if the input string is indeed a number, The function just tries to parse the string as a int, and if exception occurs
     * it catches the exception and then return false;
     * @param id
     * @return
     */
    public boolean checkIsStringNumber(String id){
        try{
            int temp = Integer.parseInt(id);
            if(temp > 0){
                return true;
            }
            return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * add the returned search order to the list that get showed.
     * @param order the order returned by the search
     * @param model the model
     * @return a redirect orders
     */
    @PostMapping("/search")
    public String searchOrdersById(@ModelAttribute Order order, Model model) {
        model.addAttribute("ordersList", order);
        return "redirect:/orders";
    }

}