package oxymat.demo;

/**
 * Customer class, same attributes as the customers table
 * @Author ALS
 */
public class Customer {
    private int id;
    private String name;
    private int cvr;
    private String address;
    private String phone;
    private String mail;

    public Customer(int id, String name, int cvr, String address, String phone, String mail ){
    this.id = id;
    this.name = name;
    this.cvr = cvr;
    this.address = address;
    this.phone = phone;
    this.mail = mail;
    }

    public int getId() {return id;}

    public void setId (int value) {this.id = value;}

    public String getName(){
        return name;
    }

    public void setName(String value){
        this.name = value;
    }

    public int getCvr(){
        return cvr;
    }

    public void setCvr(int value){
        this.cvr = value;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String value){
        this.address = value;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String value){
        this.phone = value;
    }

    public String getMail(){
        return mail;
    }

    public void setMail(String value){
        this.mail = value;
    }
}
