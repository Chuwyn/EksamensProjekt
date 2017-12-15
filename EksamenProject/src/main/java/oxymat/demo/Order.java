package oxymat.demo;


import java.sql.Date;

/**
 * The order class. Directly connected with the entities in the orders table.
 * @Author ALS
 */
public class Order{
    private int id;
    private int userId;
    private int customerId;
    private int modelId;
    private Date placementDate;
    private Date deliveryDate;
    private int status;

    public Order(){

    }

    public Order(int id, int userId, int customerId, int modelId, Date placementDate, Date deliveryDate, int status){
        this.id = id;
        this.userId = userId;
        this.customerId = customerId;
        this.modelId = modelId;
        this.placementDate = placementDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    public int getId(){
        return id;
    }

    public int getUserId(){
        return userId;
    }

    public void setUserId(int value){
        this.userId = value;
    }

    public int getCustomerId(){
        return customerId;
    }

    public void setCustomerId(int value){
        this.customerId = value;
    }

    public int getModelId(){
        return modelId;
    }

    public void setModelId(int value){
        this.modelId = value;
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

    public int getStatus(){
        return status;
    }

    public void setStatus(int value){
        this.status = value;
    }
}
