package oxymat.demo;

public class Model {
    private int id;
    private String name;
    private int price;

    public Model(int id, String name, int price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {return  id;}

    public String getName(){
        return name;
    }

    public void setName(String value){
        this.name = value;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int value){
        this.price = value;
    }
}
