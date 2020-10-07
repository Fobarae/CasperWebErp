package Controllers;

import Entities.AboveGod;
import Entities.Customer;
import Methods.WriteFile;

import com.opencsv.exceptions.CsvException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


public class Edit_Controller extends AboveGod {


    @FXML
    private Button Edit;

    @FXML
    private Button EditC;

    @FXML
    private HBox Hbc;

    @FXML
    private HBox itemC;

    @FXML
    private Button Del;

    @FXML
    private Button DelC;

    @FXML
    private Button Add_Dom;



    //todo Na kleinei kai na apo8ikeyei to arxeio
    public void Edit_Customer(ActionEvent event) throws IOException  {
        if (event.getSource() == EditC) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/Edit_Customer.fxml"));

            Parent root = loader.load();
            Editor ctrl = loader.getController();
            ctrl.SetBox2(itemC);


            //The following both lines are the only addition we need to pass the arguments


            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Editor");
            stage.show();
            System.out.println("KEKEKEKEK");


            // do what you have to do

        }
    }

    //todo Na kleinei kai na apo8ikeyei to arxeio
    public void Edit_Project(ActionEvent event) throws IOException {
        if (event.getSource() == Edit) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/Edit_Project.fxml"));

            Parent root = loader.load();
            Editor ctrl = loader.getController();
            ctrl.SetBox1(Hbc);


            //The following both lines are the only addition we need to pass the arguments


            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Editor");
            stage.show();
            System.out.println("KEKEKEKEK");

            // get a handle to the stage
            // do what you have to do


        }
    }


    //todo delete has bugs
    public void Delete_Customer(ActionEvent event) throws IOException, CsvException {
        if (event.getSource() == DelC) {
            VBox ri = (VBox) itemC.getParent();
            String temp = itemC.getId();


            //Delete the Customer Entry
            WriteFile wr = new WriteFile();
//
//            Label labelcontained=((Label)itemC.getChildren().get(1));
//            System.out.println("Name looking for  : "+ labelcontained);

            //Delete operation

            String id= (((Label) itemC.getChildren().get(4)).getText());
            System.out.println("Domain  : " +id);

            System.out.println("Id searching for : " + id);
            wr.DeleteCustomer(id);



            //Delete the box
            ri.getChildren().remove(ri.lookup("#" + temp));
        }
    }

    public void Delete_Project(ActionEvent event) throws IOException, CsvException {


        if (event.getSource() == Del) {
            VBox ri = (VBox) Hbc.getParent();
            String temp = Hbc.getId();


            //Delete the Project Entry
            WriteFile wr = new WriteFile();

            //Delete operation

            String id = (((Label) Hbc.getChildren().get(5)).getText());
            System.out.println("Project Id searching for : " + id);
            wr.DeleteProject(id);



            //Delete the box
            ri.getChildren().remove(ri.lookup("#" + temp));
        }
    }

    public void Add_Domain(ActionEvent event) throws IOException
    {
        if(event.getSource()==Add_Dom)
        {



        }
    }

}
