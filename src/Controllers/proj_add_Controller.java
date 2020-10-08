package Controllers;

import Entities.AboveGod;
import Entities.Project;
import Methods.WriteFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;

public class proj_add_Controller  extends AboveGod {

    @FXML
    private Pane pnlOverview;

    @FXML
    private TextField NameF;

    @FXML
    private TextField DateF;

    @FXML
    private TextField PriceF;

    @FXML
    private TextField WorkforceF;

    @FXML
    private Button AddB;

    @FXML
    private VBox vbc;



    public int counter = 0;


    public void SetBox(VBox box,int counter)
    {
        this.vbc = box;
        this.counter = counter + 1;
    }

    public void setCounter(int counter)
    {
        this.counter = counter + 1;
    }

//todo delete newly added items




//    public void Add_Project() throws IOException {
//        String name = NameF.getText();
//        String date = DateF.getText();
//        String price = PriceF.getText();
//        String wf = WorkforceF.getText();
//
//        HBox hbox = new HBox();
//        hbox = FXMLLoader.load(getClass().getResource("../fxml/Project_Item.fxml"));
//
////        Project temp=new Project(name, Date.valueOf(date),Float.parseFloat(price),Integer.parseInt(wf));
//
//        ((Label)hbox.getChildren().get(1)).setText(name);
//
//        ((Label)hbox.getChildren().get(2)).setText(date);
//
//        ((Label)hbox.getChildren().get(3)).setText(price);
//
//        ((Label)hbox.getChildren().get(4)).setText(wf);
//
////        ((Label)hbox.getChildren().get(5)).setText(temp.getId());
//
//
//
//        hbox.setId(String.valueOf(counter));
//
//        System.out.println("Counter before =" + counter);
//        setCounter(counter);
//
//        System.out.println("Counter after =" + counter);
//
//        vbc.getChildren().add(hbox);
//
//        WriteFile wr=new WriteFile();
//
//
//        wr.SaveProjectsAdded(name,date,price,wf);
//        projectMap.put(temp.getId(),temp);
//
//        // get a handle to the stage
//        Stage stage = (Stage) AddB.getScene().getWindow();
//        // do what you have to do
//        stage.close();
//
//
//
//
//    }


//    public void pressed(ActionEvent event) throws IOException
//    {
//        if (event.getSource() == AddB)
//        {
//            System.out.println("KEKEKEKEK");
//            Add_Project();
//        }
//    }







}
