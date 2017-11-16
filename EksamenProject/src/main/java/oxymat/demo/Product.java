package oxymat.demo;

public class Product {
    private int id;
    private String title;
    private String price;

    public Product(){

    }

    public Product(int id, String title, String price){
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String value){
        this.title = value;
    }

    public String getPrice(){
        return price;
    }

    public void setPrice(String value){
        this.price = value;
    }
}
