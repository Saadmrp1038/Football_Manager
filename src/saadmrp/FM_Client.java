package saadmrp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import saadmrp.Functions.AllPlayers;
import saadmrp.Functions.MarketPlayers;
import saadmrp.Client_FM.LoginScene.Login_Controller;
import saadmrp.Client_FM.LoginScene.ReadThread;
import saadmrp.Client_FM.MainScene.Main_Controller;
import saadmrp.Server_FM.util.NetworkUtil;


import java.io.IOException;

public class FM_Client extends Application {
    private NetworkUtil networkUtil;
    private Stage stage;
    private AllPlayers userClub;
    private String userName;
    private MarketPlayers marketPlayers;

    public MarketPlayers getMarketPlayers() {
        return this.marketPlayers;
    }

    public void setMarketPlayers(MarketPlayers m) {
        this.marketPlayers = m;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserClub(AllPlayers userClub) {
        this.userClub = userClub;
    }

    public AllPlayers getUserClub() {
        return userClub;
    }

    public Stage getStage() {
        return stage;
    }

    public NetworkUtil getNetworkUtil() {
        return this.networkUtil;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setResizable(false);
        connectToServer();
        showLoginPage();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                System.out.println("exit");
                try {
                    networkUtil.write("exit");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                Platform.exit();
                System.exit(0);
            }
        });
    }

    private void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 9999;
        networkUtil = new NetworkUtil(serverAddress, serverPort);
        new ReadThread(this);
    }

    public void showLoginPage() throws Exception {

        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Client_FM/LoginScene/login.fxml"));
        Parent root = loader.load();

        // Loading the controller
        Login_Controller controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 405, 500));
        stage.show();
    }

    public void showMainPage(String userName) throws Exception {
        this.setUserName(userName);
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Client_FM/MainScene/main.fxml"));
        Parent root = loader.load();

        // Loading the controller
        Main_Controller controller = loader.getController();
        controller.init(userName);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("FootBall Manager");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
