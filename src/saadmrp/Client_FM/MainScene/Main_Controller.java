package saadmrp.Client_FM.MainScene;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import saadmrp.FM_Client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Main_Controller implements Initializable {

    @FXML private JFXButton homeBtn;
    @FXML private JFXButton searchBtn;
    @FXML private JFXButton marketBtn;
    @FXML private JFXButton logOutBtn;
    @FXML private AnchorPane viewPane;
    @FXML private JFXButton statsBtn;
    @FXML private ImageView bg;
    private FM_Client main;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void homePressed(ActionEvent e) throws Exception {
            homeBtn.setStyle("-fx-background-color: #083e59;-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );");
            searchBtn.setStyle("-fx-background-color: #145688;");
            marketBtn.setStyle("-fx-background-color: #145688;");
            statsBtn.setStyle("-fx-background-color: #145688;");
            logOutBtn.setStyle("-fx-background-color: #145688;");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("home.fxml"));
            Parent root = loader.load();
            Home_Controller controller = loader.getController();
            controller.setMain(main);
            viewPane.getChildren().setAll(root);

    }
    public void searchPressed(ActionEvent e) throws IOException {
        searchBtn.setStyle("-fx-background-color: #083e59;-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );");
        homeBtn.setStyle("-fx-background-color: #145688;");
        marketBtn.setStyle("-fx-background-color: #145688;");
        statsBtn.setStyle("-fx-background-color: #145688;");
        logOutBtn.setStyle("-fx-background-color: #145688;");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("search.fxml"));
        Parent root = loader.load();
        Search_Controller controller = loader.getController();
        controller.setMain(main);
        viewPane.getChildren().setAll(root);
    }
    public void marketPressed(ActionEvent e) throws IOException {
        marketBtn.setStyle("-fx-background-color: #083e59;-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );");
        searchBtn.setStyle("-fx-background-color: #145688;");
        homeBtn.setStyle("-fx-background-color: #145688;");
        statsBtn.setStyle("-fx-background-color: #145688;");
        logOutBtn.setStyle("-fx-background-color: #145688;");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("market.fxml"));
        Parent root = loader.load();
        Market_Controller controller = loader.getController();
        controller.setMain(main);
        viewPane.getChildren().setAll(root);
    }
    public void statsPressed(ActionEvent e) throws IOException {
        statsBtn.setStyle("-fx-background-color: #083e59;-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );");
        searchBtn.setStyle("-fx-background-color: #145688;");
        homeBtn.setStyle("-fx-background-color: #145688;");
        marketBtn.setStyle("-fx-background-color: #145688;");
        logOutBtn.setStyle("-fx-background-color: #145688;");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("stats.fxml"));
        Parent root = loader.load();
        Stats_Controller controller = loader.getController();
        controller.setMain(main);
        viewPane.getChildren().setAll(root);
    }
    public void logOutPressed(ActionEvent e) throws Exception {
        logOutBtn.setStyle("-fx-background-color: #083e59;-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );");
        statsBtn.setStyle("-fx-background-color: #145688;");
        searchBtn.setStyle("-fx-background-color: #145688;");
        marketBtn.setStyle("-fx-background-color: #145688;");
        homeBtn.setStyle("-fx-background-color: #145688;");

        //change scene to login

        main.showLoginPage();

    }

    public void setMain(FM_Client main) {
        this.main=main;
        Image image = new Image("saadmrp/Resources/BG/"+main.getUserName()+".jpg");
        bg.setPreserveRatio(false);
        bg.setImage(image);
    }

    public void init(String userName) {
    }
}
