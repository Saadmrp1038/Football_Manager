package saadmrp.Client_FM.LoginScene;

import com.jfoenix.controls.JFXButton;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import saadmrp.FM_Client;
import saadmrp.Server_FM.util.LoginDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login_Controller implements Initializable {
    @FXML private TextField userNameTF;
    @FXML private PasswordField passwordTF;
    @FXML private JFXButton loginBtn;
    @FXML private Label loginLbl;
    @FXML private VBox footBall;
    private static String UserName;
    private static String Password;
    private FM_Client main;

    public static String getUserName() {
        return UserName;
    }

    public static String getPassword() {
        return Password;
    }

    public static void setUserName(String userName) {
        UserName = userName;
    }

    public static void setPassword(String password) {
        Password = password;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginLbl.setText("");
        setRotateball(footBall,true,360,10);
    }

    public void pressedLogin(ActionEvent event) throws IOException {
        System.out.println(main);
        UserName = userNameTF.getText();
        Password = passwordTF.getText();
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserName(UserName);
        loginDTO.setPassword(Password);
        try {
            main.getNetworkUtil().write(loginDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pressedReset(){
        userNameTF.setText("");
        passwordTF.setText("");
    }
        /*else{
            loginLbl.setTextFill(Color.rgb(210, 39, 30));
            loginLbl.setText("Wrong User Name or Password");
        }*/

    public void setRotateball(VBox c, boolean reverse, int angle, int duration){
        RotateTransition rt = new RotateTransition(Duration.seconds(duration),c);
        rt.setAutoReverse(reverse);
        rt.setByAngle(angle);
        rt.setDelay(Duration.seconds(0));
        rt.setRate(3);
        rt.setCycleCount(15);
        rt.play();
    }

    public void setMain(FM_Client mainF) {
        this.main=mainF;
    }
}
