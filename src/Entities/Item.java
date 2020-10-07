package Entities;

public class Item {

    String id;
    String type;
    String recurring;
    String price;

    public Item (String type, String price, String recurring){

        setType(type);
        setRecurring(recurring);
        setPrice(price);

    }

    public Item (String id,String type, String recurring, String price){

        setId(id);
        setType(type);
        setRecurring(recurring);
        setPrice(price);

    }

    public Item() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRecurring(String recurring) {
        this.recurring = recurring;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public String getRecurring() {
        return recurring;
    }

    public String getPrice() {
        return price;
    }

}
