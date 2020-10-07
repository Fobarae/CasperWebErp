package Entities;

import java.util.Date;

public class Domain extends AboveGod {

    int id;
    public String Name;
    public String date;
    public Date Start_Date;
    public Date Expiry_Date;
    int Invoice_Id;
    public int type;
    public float price;

    public Domain (String name,String Date,int type,float price)
    {
        this.Name=name;
        this.date=Date;
        this.type=type;
        this.price = price;
    }

    public Domain(int id,String name,Date Start_Date,Date Expiry_Date,int Invoice_ID)
    {
        this.id = id;
        this.Name = name;
        this.Start_Date = Start_Date;
        this.Expiry_Date = Expiry_Date;
        this.Invoice_Id = Invoice_ID;
    }

    public String DisplayDomain()
    {
        String out = "";
        out = Name + " " + Start_Date + " " + Expiry_Date + " " + Invoice_Id;
        return  out;
    }

    public String getName() {
        return Name;
    }

    public int getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String[] DomainOutputString()
    {
        String []Output = new String[4];
            Output[0] = Name;
            Output[1] = date;
            Output[2] = Integer.toString(type);
            Output[3] = Float.toString(price);



        return  Output;
    }


}
