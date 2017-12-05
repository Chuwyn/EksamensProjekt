package oxymat.demo;


public class User {

    private int id;
    private String firstname;
    private String lastname;
    private String mail;
    private String phone;
    private int display = 1;
    private String username;
    private String password;

    public User(){

    }

    public User(int id, String firstname, String lastname, String mail, String phone, String username, String password, int display){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.phone = phone;
        this. username = username;
        this.password = password;
        this.display = display;
    }

    // Getters
    public int getId(){
        return id;
    }

    public String getFirstname(){
        return firstname;
    }

    public void setFirstname(String value){
        this.firstname = value;
    }

    public String getLastname(){
        return lastname;
    }

    public void setLastname(String value){
        this.lastname = value;
    }


    public String getMail(){
        return mail;
    }

    public void setMail(String value){
        this.mail = value;
    }


    public String getPhone(){
        return phone;
    }

    public void setPhone(String value){
        this.phone = value;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String value){
        this.username = value;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String value){
        this.password = value;
    }

    public int getDisplay(){ return display; }

    public void setDisplay(int value){
        this.display = value;
    }

}
