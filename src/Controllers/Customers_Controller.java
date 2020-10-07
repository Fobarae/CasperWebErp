package Controllers;

import Entities.AboveGod;
import Entities.Customer;
import Methods.Read_Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;


public class Customers_Controller extends AboveGod implements Initializable  {

    @FXML
    private Pane pnlCustomers;

    @FXML
    private Label ActiveCustomersPanel;

    @FXML
    private VBox pnItems;

    @FXML
    private Button Add;

    @FXML
    private Label IncomeLabel;

    @FXML
    private Label HostingLabel;

    public int counter = 0;

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Read_Database reader = new Read_Database();

        try {
            reader.Load_Customers();
            reader.Load_Items();
            reader.Load_Domains();
            reader.Load_Invoices();

            for(Map.Entry<String,Customer> entry : customerMap.entrySet())
            {
                entry.getValue().GetCustomerData();
                System.out.println();
            }



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        int i=0;
        //ArrayList<Customer> list =rd.ReadFile("src\\Csv\\customers.csv");
//        try {
//            rd.ReadInvoiceTypes();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            rd.ReadHostings();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //todo put map in readfile
        //customerMap=rd.ReadFile("src\\Csv\\customers.csv");





        for (Map.Entry<String,Customer>entry:customerMap.entrySet()){

            entry.getValue().calculatePrice();
            try {

                HBox box;
                box = FXMLLoader.load(getClass().getResource("../fxml/Item.fxml"));

                //give the items some effect

                ((Label)box.getChildren().get(1)).setText(entry.getValue().getName());
                //todo Show domains

                //Map
                Map<String, Date> temp=entry.getValue().getDomains();


//                for (int k=0;k<temp.size();k++) {
//                    ((ComboBox) box.getChildren().get(2)).getItems().add(temp.);
//                }

                for(int j = 0;j<entry.getValue().DomainsList.size();j++)
                {
                    ((ComboBox) box.getChildren().get(2)).getItems().add(entry.getValue().DomainsList.get(j).Name);
                }



//                ((Label)box.getChildren().get(2)).setText(entry.getValue().getDomain());

                ((Label)box.getChildren().get(3)).setText( String.valueOf(entry.getValue().getPrice()));

                //A hidden box with a customer id
                ((Label)box.getChildren().get(4)).setText(entry.getValue().getId());





                box.setId(String.valueOf(i));

                pnItems.getChildren().add(box);

                setCounter(i);
                i++;

                //Fill the Labels
                ActiveCustomersPanel.setText(String.valueOf(customerMap.size()));



                IncomeLabel.setText((calcTotalPrice())+"$");

                HostingLabel.setText(String.valueOf(numberHosting()));



            } catch (IOException e) {
                e.printStackTrace();
            }

        }





    }



    public int calcTotalPrice(){
        int total=0;
        for (Map.Entry<String,Customer>entry:customerMap.entrySet()){
            total+=entry.getValue().getPrice();
        }
        return total;
    }

    public int numberHosting(){
        int count=0;

        for (Map.Entry<String,Customer>entry:customerMap.entrySet()){
        count+=entry.getValue().DomainsList.size();
            }

        return count;
    }

    public void Add_Customer(ActionEvent event) throws IOException {
        if (event.getSource() == Add)
        {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/add_customer.fxml"));

            Parent root = loader.load();
            cust_add_Controller ctrl = loader.getController();
            ctrl.SetBox(pnItems);

            System.out.println("Counter before =" + counter);
            setCounter(counter+1);
            System.out.println("Counter after =" + counter);


            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Layout2 + Controller2");
            stage.show();
        }

    }


}
