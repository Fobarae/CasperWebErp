package Methods;

import Entities.*;

import java.sql.*;
import java.util.ArrayList;

public class Read_Database extends AboveGod {

    public void Load_Customers() throws ClassNotFoundException, SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");
//here sonoo is database name, root is username and password
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from CUSTOMERS");
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String Country = rs.getString(3);
                String City = rs.getString(4);
                String Address = rs.getString(5);
                String Zip = rs.getString(6);
                String Phone = rs.getString(7);
                String Email = rs.getString(8);
                String AFM = rs.getString(9);
                Customer customer = new Customer(id, name, Country, City, Address, Zip, Phone, Email, AFM);
                customerMap.put(id, customer);

                System.out.println(id + " " + name + " " + Country + " " + City + " " + Address + " " + Zip + " " + Phone + " " + Email + " " + AFM);
                //customerMap.put(id,customer);


            }
            con.close();

        } catch (Exception e){ System.out.println(e);}
    }

    public void Load_Items() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");
//here sonoo is database name, root is username and password
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from ITEMS");
        while (rs.next()) {
            String id = rs.getString(1);
            String name = rs.getString(2);
            String Reccuring = rs.getString(3);
            int price = Math.round(rs.getFloat(4));

            Item item = new Item(id,name,Reccuring,Integer.toString(price));
            ItemsMap.put(id,item);



            System.out.println(id+ " " +name+" " +Reccuring+" " +price+" " );



        }
        con.close();
    }


    public void Load_Domains() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");
//here sonoo is database name, root is username and password
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from DOMAINS");
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String Customer_Id = rs.getString(3);
            int Invoice_ID = rs.getInt(4);
            Date Start_Date = rs.getDate(5);
            Date Expiry_Date = rs.getDate(6);
            Domain domain = new Domain(id,name,Start_Date,Expiry_Date,Invoice_ID);
            customerMap.get(Customer_Id).DomainsList.add(domain);



            System.out.println(id+ " " +name+" " +Start_Date+" " +Expiry_Date+" ");



        }
        con.close();
    }

    public void Load_Invoices() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from INVOICE");
        while (rs.next()) {
            int id = rs.getInt(1);
            int Customer_ID = rs.getInt(2);
            Date Bill_Date = rs.getDate(3);
            Float discount = rs.getFloat(4);
            String Reccuring = rs.getString(5);

            ArrayList<Item> items = new ArrayList<>();

            Statement stmt2 = con.createStatement();
            ResultSet rs2 = stmt2.executeQuery("select  itm.ITEM_ID\n" +
                    "from INVOICE inv\n" +
                    "inner join invoice_items_link link ON inv.INVOICE_ID = link.INVOICE_ID\n" +
                    "inner join items itm ON link.ITEM_ID = itm.ITEM_ID\n" +
                    "where (inv.INVOICE_ID = "+ id +  ")");

            while(rs2.next())
            {
                String item_id = Integer.toString(rs2.getInt(1));
                items.add(ItemsMap.get(item_id));
            }

            Invoice invoice = new Invoice(id,Bill_Date,Math.round(discount),Reccuring,items);

            customerMap.get(Integer.toString(Customer_ID)).GetInvoicesList().add(invoice);










        }
        con.close();


    }
}
