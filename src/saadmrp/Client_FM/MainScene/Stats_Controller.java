package saadmrp.Client_FM.MainScene;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import saadmrp.FM_Client;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class Stats_Controller implements Initializable {
   // @FXML private ChoiceBox<String> statOption;
    private FM_Client main;
    @FXML private ImageView clubLogo;
    @FXML private ImageView bg;
    @FXML private Label nameLbl;
    @FXML private Label playerCountLbl;
    @FXML private Label totalSalaryLbl;
    @FXML private Label sloganLbl;

    public Stats_Controller() throws FileNotFoundException {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //statOption.getItems().addAll("Country-wise Player Count");
        //statOption.setOnAction(this::chooseStat);
    }

    //public void chooseStat(ActionEvent e){ String option = statOption.getValue(); }

    public void setMain(FM_Client main) {
        this.main = main;
        Image image = new Image("saadmrp/Resources/icons/"+main.getUserName()+".png");
        clubLogo.setImage(image);
        nameLbl.setText(main.getUserName());
        playerCountLbl.setText(""+main.getUserClub().getPlayerCount());
        totalSalaryLbl.setText(""+main.getUserClub().getTotalSalary());

        image = new Image("saadmrp/Resources/BG/"+main.getUserName()+".jpg");
        bg.setPreserveRatio(false);
        bg.setOpacity(.25);
        bg.setImage(image);

        if(main.getUserName().equals("Manchester City")){
            sloganLbl.setText("Pride in Battle");
        }
        else if(main.getUserName().equals("Manchester United")){
            sloganLbl.setText("Wisdom and Effort");
        }
        else if(main.getUserName().equals("Liverpool")){
            sloganLbl.setText("You'll Never Walk Alone");
        }
        else if(main.getUserName().equals("Arsenal")){
            sloganLbl.setText("Victory Through Harmony");
        }
        else if(main.getUserName().equals("Chelsea")){
            sloganLbl.setText("Without God, it is in vain");
        }
    }
}
