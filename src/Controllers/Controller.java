package Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//This is commited
//import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //home projects calendar customers email exit
    @FXML
    private Pane pnlProjects = null;

    @FXML
    private VBox pnItems = null;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnProjects;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnItems;

    @FXML
    private Button btnCalendar;

    @FXML
    private Button btnWorkers;

    @FXML
    private Button btnExit;

    @FXML
    private Pane pnlCustomers;

    @FXML
    private Pane pnlHome;

    @FXML
    private Pane paneItems;

    @FXML
    private Pane pnlWorkers;

    @FXML
    private Pane pnlExit;

    @FXML
    private Pane pnlCalendar;

    @FXML
    private StackPane Stackpane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        pnlHome.setStyle("-fx-background-color : #50b065"); //green

        pnlCalendar.setStyle("-fx-background-color : #f1d744"); //yellow

        pnlExit.setStyle("-fx-background-color : #ff00eb"); //purple
        pnlWorkers.setStyle("-fx-background-color : #ff00eb"); //white




        Pane prj = new Pane();
        try {
            prj = FXMLLoader.load(getClass().getResource("../fxml/Projects.fxml"));
            prj.setId("project");

        } catch (IOException e) {
            e.printStackTrace();
        }

        Stackpane.getChildren().add(prj);

        Pane cust = new Pane();
        try {
            cust = FXMLLoader.load(getClass().getResource("../fxml/Customers.fxml"));
            cust.setId("customers");

        } catch (IOException e) {
            e.printStackTrace();
        }

        Stackpane.getChildren().add(cust);

        Pane Calendar_sp = new Pane();
        try {
            Calendar_sp = FXMLLoader.load(getClass().getResource("../fxml/fullCalendar.fxml"));
            Calendar_sp.setId("calendar");

        } catch (IOException e) {
            e.printStackTrace();
        }

        Stackpane.getChildren().add(Calendar_sp);

        Pane Items_sp = new Pane();
                try {
                    Items_sp = FXMLLoader.load(getClass().getResource("../fxml/Items.fxml"));
                    Items_sp.setId("items");

                } catch (IOException e) {
                    e.printStackTrace();
                }

                Stackpane.getChildren().add(Items_sp);

        pnlHome.toFront();

        Pane Workers_sp = new Pane();
        try {
            Items_sp = FXMLLoader.load(getClass().getResource("../fxml/Workers.fxml"));
            Items_sp.setId("workers");

        } catch (IOException e) {
            e.printStackTrace();
        }

        Stackpane.getChildren().add(Items_sp);

        pnlHome.toFront();

    }



    @FXML
    private void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) btnExit.getScene().getWindow();
        // do what you have to do
        stage.close();
    }


    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnCustomers) {
            Stackpane.lookup("#customers").setStyle("-fx-background-color : #1620A1");
            Stackpane.lookup("#customers").toFront();
        }
        if (actionEvent.getSource() == btnHome) {
            pnlHome.toFront();
        }
        if (actionEvent.getSource() == btnCalendar) {

            Stackpane.lookup("#calendar").setStyle("-fx-background-color : #ffec6b");
            Stackpane.lookup("#calendar").toFront();
        }
        if(actionEvent.getSource()==btnProjects)
        {

            Stackpane.lookup("#project").setStyle("-fx-background-color : #1620A1");
            Stackpane.lookup("#project").toFront();
        }

        if(actionEvent.getSource()==btnItems)
                {
                    Stackpane.lookup("#items").setStyle("-fx-background-color : #ca7f68");
                    Stackpane.lookup("#items").toFront();
        }

        if(actionEvent.getSource()== btnWorkers)
        {
            Stackpane.lookup("#workers").setStyle("-fx-background-color : #ca7f68");
            Stackpane.lookup("#workers").toFront();
        }

        if(actionEvent.getSource()==btnExit)
        {
            closeButtonAction();
        }



    }
}