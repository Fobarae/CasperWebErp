package Controllers;

import Entities.AboveGod;
import Entities.Domain;
import Entities.Invoice;
import Methods.WriteFile;
import calendar.WriteFileAppointment;
import com.opencsv.exceptions.CsvException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.util.Map;

public class Editor extends AboveGod {

    @FXML
    private TextField NameF;

    @FXML
    private TextField PriceF;

    @FXML
    private TextField DateF;

    @FXML
    private TextField WorkforceF;

    @FXML
    private TextField NameC;

    @FXML
    private TextField DomainC;

    @FXML
    private TextField PriceC;

    @FXML
    private Button EditC;

    @FXML
    private Button EditB;


    @FXML
    private HBox Hbc;

    @FXML
    private VBox Dom_Box;

    @FXML
    private VBox Invoice_Box;

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
    private Button Update_Customer;

    @FXML
    private TextField Title;

    @FXML
    private TextField Time;

    @FXML
    private TextField Attendee;

    @FXML
    private TextArea Notes;

    @FXML
    private Button Change;

    @FXML
    private RadioButton Important;

    @FXML
    private RadioButton Regular;

    @FXML
    private RadioButton Unimportant;

    @FXML
    private TextField date;



    @FXML
    private Button Add_Services;



    private String importance = "";

    private String id;

    public void SetBox1(HBox hb) {
        this.Hbc = hb;
        NameF.setText(((Label) Hbc.getChildren().get(1)).getText());
        PriceF.setText(((Label) Hbc.getChildren().get(2)).getText());
        DateF.setText(((Label) Hbc.getChildren().get(3)).getText());
        WorkforceF.setText(((Label) Hbc.getChildren().get(4)).getText());

    }

    public void SetBox2(HBox hb) throws IOException {
        this.Hbc = hb;
        NameC.setText(((Label) Hbc.getChildren().get(1)).getText());
        System.out.println(NameC.getText());
        id = (((Label) hb.getChildren().get(4)).getText());



        for (Map.Entry<String, Date> entry : customerMap.get(id).Domains.entrySet()) {

            HBox box;
            String dom_name = entry.getKey();
            box = FXMLLoader.load(getClass().getResource("../fxml/Doms.fxml"));
            System.out.println(dom_name);

            ((Label) box.getChildren().get(1)).setText(dom_name);

            ((Label) box.getChildren().get(2)).setText(DomainMap.get(dom_name).getDate());

            if (DomainMap.get(dom_name).getType() == 1) {
                ((CheckBox) box.getChildren().get(3)).setSelected(true);
            }

            if (DomainMap.get(dom_name).getType() == 2) {
                ((CheckBox) box.getChildren().get(4)).setSelected(true);
            }

            if (DomainMap.get(dom_name).getType() == 3) {
                ((CheckBox) box.getChildren().get(5)).setSelected(true);
            }

            Dom_Box.getChildren().add(box);


        }


        for(int i = 0;i<customerMap.get(id).GetInvoicesList().size();i++)
        {
            HBox box;

            box = FXMLLoader.load(getClass().getResource("../fxml/Invoice_Item.fxml"));

            Invoice invoice = customerMap.get(id).GetInvoicesList().get(i);

            ((Label) box.getChildren().get(1)).setText("INVOICE#" + invoice.getId());

            ((Label) box.getChildren().get(2)).setText(invoice.getBill_Date().toString());

            ((Label) box.getChildren().get(3)).setText(Integer.toString(invoice.getPrice()));

            ((Label) box.getChildren().get(4)).setText(invoice.getRecurring());

            Invoice_Box.getChildren().add(box);



        }


    }


    public void SetBox3(HBox hb) {
        this.Hbc = hb;
        Title.setText(((Label) Hbc.getChildren().get(0)).getText());
        Time.setText(((Label) Hbc.getChildren().get(1)).getText());
        Attendee.setText(((Label) Hbc.getChildren().get(5)).getText());
        Notes.setText(((Label) Hbc.getChildren().get(6)).getText());
        date.setText(((Label) Hbc.getChildren().get(7)).getText());

        switch (((Label) Hbc.getChildren().get(2)).getText()) {
            case "Important":
                Important.setSelected(true);
                setImportant();
                break;
            case "Regular":
                Regular.setSelected(true);
                setRegular();
                break;
            case "Unimportant":
                Unimportant.setSelected(true);
                setUnimportant();
                break;
        }
    }

    @FXML
    void Add_Domain(ActionEvent event) throws IOException//Add domain to the customer
    {
        if (event.getSource() == Add_Dom) {
            HBox box;
            int type = 0;
            float price = 0;
            String dom_name = DomName.getText();
            box = FXMLLoader.load(getClass().getResource("../fxml/Doms.fxml"));
            Date DomaDate = Date.valueOf(Domain_Date.getValue());
            System.out.println(dom_name);


            ((Label) box.getChildren().get(1)).setText(dom_name);

            ((Label) box.getChildren().get(2)).setText(String.valueOf(DomaDate));


            if (Small.isSelected()) {
                // Invoice temp = new Invoice("Hosting_small",30,);
                ((CheckBox) box.getChildren().get(3)).setSelected(true);
                customerMap.get(id).GetInvoicesList().add(new Invoice(invoiceTypes.get(1).getType(), invoiceTypes.get(1).getPrice(), invoiceTypes.get(1).getRecurring()));
                type = 1;
                price = 60;


            }

            if (Medium.isSelected()) {
                ((CheckBox) box.getChildren().get(4)).setSelected(true);
                customerMap.get(id).GetInvoicesList().add(new Invoice(invoiceTypes.get(2).getType(), invoiceTypes.get(2).getPrice(), invoiceTypes.get(2).getRecurring()));
                type = 2;
                price = 80;
            }

            if (Large.isSelected()) {
                ((CheckBox) box.getChildren().get(5)).setSelected(true);
                customerMap.get(id).GetInvoicesList().add(new Invoice(invoiceTypes.get(3).getType(), invoiceTypes.get(3).getPrice(), invoiceTypes.get(3).getRecurring()));
                type = 3;
                price = 100;
            }


            customerMap.get(id).Domains.put(dom_name, DomaDate);
            DomainMap.put(dom_name, new Domain(dom_name, DomaDate.toString(), type, price));

            ((ComboBox) Hbc.getChildren().get(2)).getItems().add(dom_name);
            Dom_Box.getChildren().add(box);



        }
    }




    @FXML
    void pressed(ActionEvent event) throws IOException, CsvException {



            if (event.getSource() == EditB) {
                ((Label) Hbc.getChildren().get(1)).setText(NameF.getText());

                ((Label) Hbc.getChildren().get(2)).setText(DateF.getText());

                ((Label) Hbc.getChildren().get(3)).setText(PriceF.getText());

                ((Label) Hbc.getChildren().get(4)).setText(WorkforceF.getText());
                // get a handle to the stage


                Stage stage = (Stage) EditB.getScene().getWindow();

                // do what you have to do
                stage.close();
            }

            if (event.getSource() == EditC) {

                ((Label) Hbc.getChildren().get(1)).setText(NameC.getText());

                ((Label) Hbc.getChildren().get(2)).setText(DomainC.getText());

                ((Label) Hbc.getChildren().get(3)).setText(PriceC.getText());

                // get a handle to the stage
                Stage stage = (Stage) EditC.getScene().getWindow();

                // do what you have to do
                stage.close();

            }

            if (event.getSource() == Update_Customer) {
                WriteFile writer = new WriteFile();
                customerMap.get(id).calculatePrice();
                writer.UpdateCustomerFile();
                writer.UpdateHostingsFile();

                ((Label) Hbc.getChildren().get(3)).setText(Float.toString(customerMap.get(id).getPrice()));


            }


            if (event.getSource() == Change) {

                System.out.println("skata");

                Stage stage = (Stage) Change.getScene().getWindow();


                WriteFileAppointment wr = new WriteFileAppointment();

                String title = ((Label) Hbc.getChildren().get(0)).getText();

                wr.DeleteAppointment(title);

                title = Title.getText();
                String time = Time.getText();
                String attendee = Attendee.getText();
                String notes = Notes.getText();
                String date2 = date.getText();

                wr.SaveAppointmentAdded(title, time, date2, attendee, notes, importance);

                // do what you have to do
                stage.close();

            }



            if (event.getSource() == Add_Services)
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/AddServices.fxml"));

                Parent root = loader.load();

                Add_Service_Controller ctrl = loader.getController();
                ctrl.SetBox(Invoice_Box,id);



                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Editor");
                stage.show();
                System.out.println("KEKEKEKEK");
            }

        }

        public void setImportant () {
            System.out.println("--->" + Important.getId());

            if (importance.equals(Important.getId())) {
                importance = "";
            } else {
                importance = Important.getId();
            }

        }

        public void setRegular () {
            System.out.println("--->" + Regular.getId());

            if (importance.equals(Regular.getId())) {
                importance = "";
            } else {
                importance = Regular.getId();
            }
        }

        public void setUnimportant () {
            System.out.println("--->" + Unimportant.getId());

            if (importance.equals(Unimportant.getId())) {
                importance = "";
            } else {
                importance = Unimportant.getId();
            }
        }




    }

