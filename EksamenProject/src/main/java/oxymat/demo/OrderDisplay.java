package oxymat.demo;


import java.sql.Date;


public class OrderDisplay {
    private int id;
    private String user;
    private String customer;
    private String modelname;
    private Date placementDate;
    private Date deliveryDate;
    private String status;

    public OrderDisplay(){

    }

    public OrderDisplay(int id, String user, String customer, String modelname, Date placementDate, Date deliveryDate, String status){
        this.id = id;
        this.user = user;
        this.customer = customer;
        this.modelname = modelname;
        this.placementDate = placementDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    public int getId(){
        return id;
    }

    public String getUser(){
        return user;
    }

    public void setUser(String value){
        this.user = value;
    }

    public String getCustomer(){
        return customer;
    }

    public void setCustomer(String value){
        this.customer = value;
    }

    public String getModelname(){
        return modelname;
    }

    public void setModelname(String value){
        this.modelname = value;
    }

    public Date getPlacementDate(){
        return placementDate;
    }

    public void setPlacementDate(Date value){
        this.placementDate = value;
    }

    public Date getDeliveryDate(){
        return deliveryDate;
    }

    public void setDeliveryDate(Date value){
        this.deliveryDate = value;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String value){
        this.status = value;
    }
}
