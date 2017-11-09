package oxymat.demo;


public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    private String userName;
    private String password;

    public User(String firstName, String lastName, String email, String phoneNumber, String userName, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this. userName = userName;
        this.password = password;
    }

    // Getters n Setters
    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setEmail(String value){
        email = value;
    }

    public String getEmail(){
        return email;
    }

    public void setPhoneNumber(String value){
        phoneNumber = value;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getUserName(){
        return userName;
    }

    public String getPassword(){
        return password;
    }

    public boolean checkPass(String pass){
        return password.equals(pass);
    }

}
