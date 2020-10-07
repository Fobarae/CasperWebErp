package Controllers;

import Entities.AboveGod;
import Entities.Customer;
import Entities.Project;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.ReadFromFile;

import javax.swing.*;

public class Projects_Controller  extends AboveGod implements Initializable   {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="pnlCustomers"
    private Pane pnlCustomers; // Value injected by FXMLLoader

    @FXML // fx:id="pnItems"
    private VBox pnItems; // Value injected by FXMLLoader

    @FXML
    private Pane pnlProjects;

    @FXML
    private Label ActiveProjectsPanel;

    @FXML
    private Label TotalRevenuePanel;

    @FXML
    private Label ClosestDeadlinePanel;


    @FXML
    private Label TotalRevenue;


    @FXML
    private Button Add;

    @FXML
    public Button Edit;

    public int counter = 0;

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL location, ResourceBundle resources) {

        ReadFromFile rd=new ReadFromFile();
        int i=0;
        //ArrayList<Customer> list =rd.ReadFile("src\\Csv\\customers.csv");

        projectMap=rd.ReadFileProjects("LifeManager/src/Csv/Projects_test.csv");
        for (Map.Entry<String, Project>entry:projectMap.entrySet()){

            try {

                HBox box;
                box = FXMLLoader.load(getClass().getResource("../fxml/Project_Item.fxml"));

                //give the items some effect

                ((Label)box.getChildren().get(1)).setText(entry.getValue().getName());

                ((Label)box.getChildren().get(2)).setText(entry.getValue().getDueDate().toString());

                ((Label)box.getChildren().get(3)).setText(String.valueOf(entry.getValue().getPrice()));

                ((Label)box.getChildren().get(4)).setText(String.valueOf(entry.getValue().getWorkforce()));

                ((Label)box.getChildren().get(5)).setText(entry.getValue().getId());



                box.setId(String.valueOf(i));

                pnItems.getChildren().add(box);

                setCounter(i);
                i++;

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        ActiveProjectsPanel.setText(String.valueOf(projectMap.size()));

        ClosestDeadlinePanel.setText(String.valueOf(findClosestDeadLine()));
        colourDead();

        TotalRevenuePanel.setText(String.valueOf(totalRevenue()));






    }

    public long findClosestDeadLine(){
        long min=100000000;
        for (Map.Entry<String, Project>entry:projectMap.entrySet()){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate deadline_date = LocalDate.parse(entry.getValue().getDueDate().toString(), dtf);
            LocalDate currentDate = LocalDate.now();
            long timeToDeadLine = ChronoUnit.DAYS.between(currentDate, deadline_date);
            if (timeToDeadLine<min)
                min=timeToDeadLine;
        }
        return min;
    }

    public int totalRevenue(){
        int price=0;
        for (Map.Entry<String, Project>entry:projectMap.entrySet()){
            price+=entry.getValue().getPrice();
        }
        return price;
    }


    public void colourDead(){
        if (findClosestDeadLine()>12){
            ClosestDeadlinePanel.setTextFill(Color.web("#12de00"));
        }
        if (findClosestDeadLine()>6&&findClosestDeadLine()<10){
            ClosestDeadlinePanel.setTextFill(Color.web("#dade00"));
        }
        if (findClosestDeadLine()<6){
            ClosestDeadlinePanel.setTextFill(Color.web("#de0000"));
        }
    }


    public void Add_Project(ActionEvent event) throws IOException {
        if (event.getSource() == Add)
        {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/add_project.fxml"));

            Parent root = loader.load();
            proj_add_Controller ctrl = loader.getController();
            ctrl.SetBox(pnItems,counter);
            System.out.println("Counter before =" + counter);
            setCounter(counter+1);
            System.out.println("Counter after =" + counter);
            //The following both lines are the only addition we need to pass the arguments


            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Layout2 + Controller2");
            stage.show();




        }


    }



}


