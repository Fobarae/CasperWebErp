package Controllers;

import Entities.AboveGod;
import Entities.Worker;
import Methods.Read_Database;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import sample.ReadFromFile;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;



public class Workers_Controller extends AboveGod implements Initializable {

    @FXML
    private Pane pnlWorkers;

    @FXML
    private Label TotalWorkersLabel;

    @FXML
    private Label AvailableWorkersLabel;

    @FXML
    private Label BusyWorkersLabel;

    @FXML
    private VBox pnWorkItems;







    public int counter = 0;

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void initialize(URL location, ResourceBundle resources) {

        ReadFromFile rd=new ReadFromFile();
        int i=0;


        //ArrayList<Customer> list =rd.ReadFile("src\\Csv\\customers.csv");

        //projectMap=rd.ReadFileProjects("LifeManager/src/Csv/Projects_test.csv");
        Read_Database reader=new Read_Database();

        try {
            reader.LoadProjects();
            reader.LoadWorkers();
            reader.LoadWorkerTaks();
            reader.LoadProjectWorkers();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for (Map.Entry<Integer,Worker>entry:workerMap.entrySet()){
            try {

                HBox box;
                box = FXMLLoader.load(getClass().getResource("../fxml/Worker_Item.fxml"));

                //give the items some effect

                ((Label)box.getChildren().get(1)).setText(entry.getValue().getName());

                ((Label)box.getChildren().get(2)).setText(entry.getValue().getEmail());





                box.setId(String.valueOf(i));

                pnWorkItems.getChildren().add(box);

                setCounter(i);
                i++;

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        TotalWorkersLabel.setText(String.valueOf(workerMap.size()));
//
//        ClosestDeadlinePanel.setText(String.valueOf(findClosestDeadLine()));
//        colourDead();
//
//        TotalRevenuePanel.setText(String.valueOf(totalRevenue()));


    }

    //sendEmail(){

    }

}
