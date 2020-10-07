package Controllers;

import Entities.*;
import Methods.WriteFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class cust_add_Controller extends AboveGod {

    @FXML
    private TextField NameF;

    @FXML
    private TextField DomainF;

    @FXML
    private TextField PriceF;

    @FXML
    private Button AddB;

    @FXML
    private VBox vbc;

    public int counter;

    @FXML
    private TextField NameC;

    @FXML
    private Button Update_Customer;

    @FXML
    private VBox Dom_Box;

    @FXML
    private Button Add_Dom;

    @FXML
    private TextField DomName;

    @FXML
    private CheckBox Small;

    @FXML
    private CheckBox Medium;

    @FXML
    private CheckBox Large;

    @FXML
    private DatePicker Domain_Date;

    @FXML
    private VBox Services_Box;

    @FXML
    private Button AddCustomer;



    public void Add_Services()
    {

        for (Map.Entry<String,Item>entry : ItemsMap.entrySet())
        {
            if (entry.getValue().getRecurring().equals("PER MONTH") || entry.getValue().getRecurring().equals("ONCE") || entry.getValue().getRecurring().equals("PER HOUR")  )
            {
                int i = 1;
                HBox Service = new HBox();
                Service.setId(Integer.toString(i));
                CheckBox item = new CheckBox(entry.getValue().getType());
                item.setId(Integer.toString(i));
                Service.getChildren().add(item);
                Services_Box.getChildren().add(Service);
                i++;
            }

        }
    }


    public void SetBox(VBox box)
    {
        this.vbc = box;
        Add_Services();
    }


    public void Add_Customer() throws IOException {
        String name = NameC.getText();

        String dom = DomName.getText();

        int type = 0;

        float price = 0;

        Map<String,Date> temp = new HashMap<>();

        Date DomaDate= Date.valueOf(Domain_Date.getValue());

        temp.put(dom,DomaDate);

        ArrayList<Domain> Temp = new ArrayList<>();

        Domain New_Domain;

        Invoice Hosting_Invoice = new Invoice();


        Customer New_Customer = new Customer(name,(HashMap) temp);

        Customer NewCust = new Customer();

        if(Small.isSelected())
        {

            Hosting_Invoice.getItems().add(ItemsMap.get(6));



            New_Customer.GetInvoicesList().add(new Invoice(invoiceTypes.get(1).getType(), invoiceTypes.get(1).getPrice(),invoiceTypes.get(1).getRecurring()));
            type = 1;
            price = 60;
        }

        if(Medium.isSelected())
        {
            New_Customer.GetInvoicesList().add(new Invoice(invoiceTypes.get(2).getType(), invoiceTypes.get(2).getPrice(),invoiceTypes.get(2).getRecurring()));
            type = 2;
            price = 80;
        }

        if(Large.isSelected())
        {
            New_Customer.GetInvoicesList().add(new Invoice(invoiceTypes.get(3).getType(), invoiceTypes.get(3).getPrice(),invoiceTypes.get(3).getRecurring()));
            type = 3;
            price = 100;
        }


        Domain CustomerDomain = new Domain(dom,DomaDate.toString(),type,price);


        for (int i = 0;i<Services_Box.getChildren().size();i++)
        {
            if(((CheckBox)((HBox)Services_Box.getChildren().get(i)).getChildren().get(0)).isSelected())
            {
                for(int j = 0;j<invoiceTypes.size();j++)
                {
                    if(invoiceTypes.get(j).getType().equals(((CheckBox)((HBox)Services_Box.getChildren().get(i)).getChildren().get(0)).getText()))
                    {
                        New_Customer.GetInvoicesList().add(invoiceTypes.get(j));

                    }
                }
            }
        }

            New_Customer.calculatePrice();

        try {

            HBox box;
            box = FXMLLoader.load(getClass().getResource("../fxml/Item.fxml"));

            //give the items some effect

            ((Label)box.getChildren().get(1)).setText(name);
            //todo Show domains


            ((ComboBox) box.getChildren().get(2)).getItems().addAll(temp.keySet());



            ((Label)box.getChildren().get(3)).setText( String.valueOf(New_Customer.getPrice()));

            //A hidden box with a customer id
            ((Label)box.getChildren().get(4)).setText(New_Customer.getId());

            vbc.getChildren().add(box);

        } catch (IOException e) {
            e.printStackTrace();
        }

        DomainMap.put(dom,CustomerDomain);
        customerMap.put(New_Customer.getId(),New_Customer);


        WriteFile writer = new WriteFile();

        writer.UpdateCustomerFile();
        writer.UpdateHostingsFile();





        // get a handle to the stage
        Stage stage = (Stage) AddCustomer.getScene().getWindow();
        // do what you have to do
        stage.close();
    }


    public void pressed(ActionEvent event) throws IOException {
        if(event.getSource()==AddCustomer)
        {
            Add_Customer();
        }

    }

}
