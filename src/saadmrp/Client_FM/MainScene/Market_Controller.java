package saadmrp.Client_FM.MainScene;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import saadmrp.FM_Client;
import saadmrp.Functions.BoughtPlayer;
import saadmrp.Functions.Player;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Market_Controller implements Initializable {

    private FM_Client main;
    @FXML private TableView<Player> marketTable;
    @FXML private TableColumn<Player,String> nameColumn;
    @FXML private TableColumn<Player,String> countryColumn;
    @FXML private TableColumn<Player,Integer> ageColumn;
    @FXML private TableColumn<Player,Double> heightColumn;
    @FXML private TableColumn<Player,String> positionColumn;
    @FXML private TableColumn<Player,String> numberColumn;
    @FXML private TableColumn<Player,Double> salaryColumn;
    @FXML private TableColumn<Player,Double> priceColumn;
    @FXML private JFXButton buyBtn;
    @FXML private Label nameLbl;
    @FXML private Label countryLbl;
    @FXML private Label ageLbl;
    @FXML private Label heightLbl;
    @FXML private Label priceLbl;
    @FXML private Label positionLbl;
    @FXML private Label numberLbl;
    @FXML private Label salaryLbl;
    @FXML private ImageView playerImage;
    private Player player;
    private String name="";

    private ObservableList<Player> marketList = FXCollections.observableArrayList();

    public void setMain(FM_Client main) {
        this.main = main;
        initiateTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showTable();
        Timer timer = new Timer();
        TimerTask myTask = new TimerTask() {
            @Override
            public void run() {
               marketList.clear();
               initiateTable();
               showTable();
            }
        };
        timer.schedule(myTask, 2000, 2000);
        marketTable.setOnMousePressed(e ->{
            if (e.isPrimaryButtonDown() ){
                player = marketTable.getSelectionModel().getSelectedItem();
                if(player!=null) {
                    Image image = new Image("saadmrp/Resources/Footballer's Image/" + player.getName() + ".jpg");
                    playerImage.setImage(image);

                    playerImage.setVisible(true);
                    nameLbl.setText(player.getName());
                    countryLbl.setText(player.getCountry());
                    ageLbl.setText(player.getAge() + "");
                    heightLbl.setText(player.getHeight() + "");
                    numberLbl.setText(player.getNumber() + "");
                    salaryLbl.setText(player.getSalary() + "");
                    positionLbl.setText(player.getPosition());
                    priceLbl.setText(player.getPrice()+"");
                }
            }
        });
    }

    public void buyPressed(ActionEvent e) throws IOException {
        if (!nameLbl.getText().equals("-----")) {
            if (isClear()) {
                main.getUserClub().removePlayer(nameLbl.getText());
                buyPlayer(nameLbl.getText(), player);

                playerImage.setVisible(false);
                nameLbl.setText("-----");
                countryLbl.setText("-----");
                ageLbl.setText("-----");
                heightLbl.setText("-----");
                numberLbl.setText("-----");
                salaryLbl.setText("-----");
                positionLbl.setText("-----");
                priceLbl.setText("-----");
                marketList.clear();
                initiateTable();
                showTable();
            }
        }
    }

    public void showTable(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("Country"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
        heightColumn.setCellValueFactory(new PropertyValueFactory<>("Height"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("Position"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("Number"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        marketTable.setItems(marketList);
    }

    public void initiateTable(){
            marketList.addAll(main.getMarketPlayers().getPlayers());
    }

    public void buyPlayer(String s,Player p) throws IOException {
        main.getMarketPlayers().removePlayer(s);
        p.setClub(main.getUserName());
        main.getUserClub().addPlayer(p);
        BoughtPlayer bp = new BoughtPlayer(p);
        main.getNetworkUtil().write(bp);
        nameLbl.setText("Name");
    }

    public Boolean isClear() {
        Boolean clear=false;
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Buy Player");
        alert.setHeaderText("Are you sure you want to buy this player?");
        alert.setContentText("");

        ButtonType Cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getDialogPane().getButtonTypes().add(Cancel);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get()==ButtonType.OK){
            clear=true;
        }
        return clear;
    }

}
