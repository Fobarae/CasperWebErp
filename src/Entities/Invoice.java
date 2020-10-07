package Entities;

import java.util.ArrayList;
import java.util.Date;
import java.sql.*;
public class Invoice {
    int id;
    String type;
    ArrayList<Item> items = new ArrayList<>();
    int price;
    int discount = 1;
    Date Bill_Date;
    String recurring;
    String Notes;


    public  Invoice(int id, Date Bill_Date, int discount , String recurring, ArrayList<Item> items )
    {
        this.id = id;
        this.Bill_Date = Bill_Date;
        this.discount = discount;
        this.recurring = recurring;
        this.items = items;
    }

    public Invoice(String type, int price, String reccuring){
    this.type=type;
    this.price=price;
    this.recurring =reccuring;
    }

    public Invoice (Invoice invoice)
    {
        this.type = invoice.getType();
        this.price = invoice.getPrice();
        this.recurring = invoice.getRecurring();
    }

    public Invoice() {

    }

    public String DisplayInvoiceData()
    {
        String out = "";
        Calc_Invoice_Price();
        String item = "";
        for(int i = 0;i<items.size();i++)
        {
            item = item + items.get(i).getType() + items.get(i).getPrice();
        }
        out = id+ " " + item + " " + price ;

        return  out;
    }


    public void Calc_Invoice_Price()
    {
        for(int i = 0;i<items.size();i++)
        {
            this.price = this.price + Integer.parseInt(items.get(i).getPrice());
        }

        this.price = this.getPrice() * discount;
    }

//    Invoice(String type,int price,int times,String reccuring,String domain){
//        this.type=type;
//        this.price=price;
//        this.times=times;
//        this.reccuring=reccuring;
//    }

    public int getPrice() {
        return price;
    }


    public String getRecurring() {
        return recurring;
    }

    public String getType() {
        return type;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
