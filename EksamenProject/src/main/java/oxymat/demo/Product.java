package oxymat.demo;

/**
 * Product class. Same attributes as models table.
 * @Author PTJ
 */
public class Product {
    private int id;
    private String title;
    private String price;
    private int display = 1;

    public Product(){

    }

    public Product(int id, String title, String price, int display){
        this.id = id;
        this.title = title;
        this.price = price;
        this.display = display;
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

    public int getDisplay(){ return display; }

    public void setDisplay(int value){
        this.display = value;
    }
}
