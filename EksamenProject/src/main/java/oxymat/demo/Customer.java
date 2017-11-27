package oxymat.demo;

public class Customer {
    private String name;
    private int cvr;
    private String address;
    private String phone;
    private String mail;

    public Customer(){

    }

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
