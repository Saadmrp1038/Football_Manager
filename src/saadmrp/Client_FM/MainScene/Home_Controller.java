package saadmrp.Client_FM.MainScene;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import saadmrp.FM_Client;
import saadmrp.Functions.Player;
import saadmrp.Functions.SoldPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Home_Controller implements Initializable {
    private FM_Client main;
    @FXML private AnchorPane homePane;
    @FXML private TableView<Player> homeTable;
    @FXML private TableColumn<Player,String> nameColumn;
    @FXML private TableColumn<Player,String> countryColumn;
    @FXML private TableColumn<Player,Integer> ageColumn;
    @FXML private TableColumn<Player,Double> heightColumn;
    @FXML private TableColumn<Player,String> clubColumn;
    @FXML private TableColumn<Player,String> positionColumn;
    @FXML private TableColumn<Player,String> numberColumn;
    @FXML private TableColumn<Player,Double> salaryColumn;
    @FXML private Label nameLbl;
    @FXML private Label countryLbl;
    @FXML private Label ageLbl;
    @FXML private Label heightLbl;
    @FXML private Label clubLbl;
    @FXML private Label positionLbl;
    @FXML private Label numberLbl;
    @FXML private Label salaryLbl;
    @FXML private JFXButton sellBtn;
    @FXML private TextField priceField;
    @FXML private ImageView playerImage;
    private Player player;
    private String name="";
    private  double d;

    private ObservableList<Player> homeList = FXCollections.observableArrayList();



    public void setMain(FM_Client Fmain) {
        this.main = Fmain;
        initiateTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showTable();
        Timer timer = new Timer();
        TimerTask myTask = new TimerTask() {
            @Override
            public void run() {
                homeList.clear();
                initiateTable();
                showTable();
            }
        };
        timer.schedule(myTask, 2000, 2000);
        homeTable.setOnMousePressed(e ->{
            if (e.isPrimaryButtonDown() ){
                player = homeTable.getSelectionModel().getSelectedItem();
                if(player!=null) {
                    Image image = new Image("saadmrp/Resources/Footballer's Image/" + player.getName() + ".jpg");
                    playerImage.setImage(image);

                    playerImage.setVisible(true);
                    nameLbl.setText(player.getName());
                    clubLbl.setText(player.getClub());
                    countryLbl.setText(player.getCountry());
                    ageLbl.setText(player.getAge() + "");
                    heightLbl.setText(player.getHeight() + "");
                    numberLbl.setText(player.getNumber() + "");
                    salaryLbl.setText(player.getSalary() + "");
                    positionLbl.setText(player.getPosition());
                }
            }
        });
    }

    public void sellPressed(ActionEvent e) throws IOException {
        if(!nameLbl.getText().equals("-----")){
            if(isClear()) {
                if(priceField.getText()==null || priceField.getText().trim().isEmpty()){
                    warn();
                }else {
                    try{
                        d=Double.parseDouble(priceField.getText());
                    }catch (Exception ex){
                        warn();
                        return;
                    }
                    main.getUserClub().removePlayer(nameLbl.getText());
                    sellPlayer(nameLbl.getText(), player);

                    playerImage.setVisible(false);
                    nameLbl.setText("-----");
                    clubLbl.setText("-----");
                    countryLbl.setText("-----");
                    ageLbl.setText("-----");
                    heightLbl.setText("-----");
                    numberLbl.setText("-----");
                    salaryLbl.setText("-----");
                    positionLbl.setText("-----");
                    priceField.setText("");
                    homeList.clear();
                    initiateTable();
                    showTable();
                }
            }
        }
    }


    public void showTable(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("Country"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
        heightColumn.setCellValueFactory(new PropertyValueFactory<>("Height"));
        clubColumn.setCellValueFactory(new PropertyValueFactory<>("Club"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("Position"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("Number"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("Salary"));

        homeTable.setItems(homeList);
    }
    public void initiateTable(){
            homeList.addAll(main.getUserClub().getPlayers());
    }

    public void sellPlayer(String s,Player p) throws IOException {
        main.getUserClub().removePlayer(s);
        if(priceField.getText()==null || priceField.getText().trim().isEmpty()){
        p.setPrice(0.00);
        }
        else{
           d = Double.parseDouble(priceField.getText());
           p.setPrice(d);
        }
        SoldPlayer sp = new SoldPlayer(p);
        main.getNetworkUtil().write(sp);
        nameLbl.setText("Name");
    }

    public Boolean isClear() {
        Boolean clear=false;
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Sell Player");
        alert.setHeaderText("Are you sure you want to sell this player?");
        alert.setContentText("");

        ButtonType Cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getDialogPane().getButtonTypes().add(Cancel);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get()==ButtonType.OK){
            clear=true;
        }
        return clear;
    }

    public void warn(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Input Invalid");
        alert.setHeaderText("Input is not valid");
        alert.setContentText("");
        alert.showAndWait();
    }
}












