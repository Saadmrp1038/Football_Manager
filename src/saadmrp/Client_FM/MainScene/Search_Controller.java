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
import javafx.scene.layout.Pane;
import saadmrp.FM_Client;
import saadmrp.Functions.PinC;
import saadmrp.Functions.Player;

import java.net.URL;
import java.util.*;

public class Search_Controller implements Initializable {
    @FXML private ChoiceBox<String> searchOption;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private TextField textField1;
    @FXML private TextField textField2;
    @FXML private JFXButton searchBtn;
    @FXML private TableView<Player> searchTable;
    @FXML private TableColumn<Player,String> nameColumn;
    @FXML private TableColumn<Player,String> countryColumn;
    @FXML private TableColumn<Player,Integer> ageColumn;
    @FXML private TableColumn<Player,Double> heightColumn;
    @FXML private TableColumn<Player,String> clubColumn;
    @FXML private TableColumn<Player,String> positionColumn;
    @FXML private TableColumn<Player,String> numberColumn;
    @FXML private TableColumn<Player,Double> salaryColumn;
    @FXML private TableView<PinC> countTable;
    @FXML  TableColumn<PinC,String> allCountryColumn;
    @FXML private TableColumn<PinC,Integer> countColumn;
    @FXML private Label nameLbl;
    @FXML private Label countryLbl;
    @FXML private Label ageLbl;
    @FXML private Label heightLbl;
    @FXML private Label clubLbl;
    @FXML private Label positionLbl;
    @FXML private Label numberLbl;
    @FXML private Label salaryLbl;
    @FXML private ImageView playerImage;
    @FXML private Pane playerDetailsPane;
    private FM_Client main;
    private String s1,s2;
    private double lo,hi;
    private Player player;
    private ObservableList<Player> homeList = FXCollections.observableArrayList();
    private ObservableList<PinC>countList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchOption.getItems().addAll("Player Name", "Country", "Salary Range", "Position","Max Age","Max Height","Max Salary",
                "Country-wise Player Count");
        searchOption.setOnAction(this::choose);
        label1.setVisible(false);
        label2.setVisible(false);
        textField1.setVisible(false);
        textField2.setVisible(false);
        nameColumn.setVisible(true);
        playerDetailsPane.setVisible(true);
        countTable.setVisible(false);

        searchTable.setOnMousePressed(e ->{
            if (e.isPrimaryButtonDown() ){
                player = searchTable.getSelectionModel().getSelectedItem();
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

    public void choose(ActionEvent e) {
        String option = searchOption.getValue();

        if(option.equals("Player Name")){
            label1.setVisible(true);
            label2.setVisible(false);
            textField1.setVisible(true);
            textField2.setVisible(false);
            label1.setText("Name");

        }
        else if(option.equals("Country")){
            label1.setVisible(true);
            label2.setVisible(false);
            textField1.setVisible(true);
            textField2.setVisible(false);
            label1.setText("Country");
        }
        else if(option.equals("Salary Range")){
            label1.setVisible(true);
            label2.setVisible(true);
            textField1.setVisible(true);
            textField2.setVisible(true);
            label1.setText("Lower Limit");
            label2.setText("Higher Limit");
        }
        else if(option.equals("Position")){
            label1.setVisible(true);
            label2.setVisible(false);
            textField1.setVisible(true);
            textField2.setVisible(false);
            label1.setText("Position");
        }
        else{
            label1.setVisible(false);
            label2.setVisible(false);
            textField1.setVisible(false);
            textField2.setVisible(false);
        }
    }

    public void searchPressed(ActionEvent e){
        String option = searchOption.getValue();
        if(option==null) return;
        homeList.clear();
        if(option.equals("Player Name")){
            if(textField1.getText()==null || textField1.getText().trim().isEmpty()){
                warn();
                return;
            }
            nameColumn.setVisible(true);
            playerDetailsPane.setVisible(true);
            countTable.setVisible(false);
            s1=textField1.getText();
            homeList.addAll(main.getUserClub().searchName(s1));
            showTable();
        }
        else if(option.equals("Country")){
            if(textField1.getText()==null || textField1.getText().trim().isEmpty()){
                warn();
                return;
            }
            nameColumn.setVisible(true);
            playerDetailsPane.setVisible(true);
            countTable.setVisible(false);
            s1=textField1.getText();
            homeList.clear();

            homeList.addAll(main.getUserClub().searchCountry(s1));
            showTable();
        }
        else if(option.equals("Salary Range")){
            nameColumn.setVisible(true);
            playerDetailsPane.setVisible(true);
            countTable.setVisible(false);
            s1=textField1.getText();
            s2=textField2.getText();

            try{
                lo= Double.parseDouble(s1);
                hi= Double.parseDouble(s2);
                homeList.clear();

                homeList.addAll(main.getUserClub().searchSalary(lo,hi));
                showTable();
            }catch (Exception ex){
                warn();
            }
        }
        else if(option.equals("Position")){
            if(textField1.getText()==null || textField1.getText().trim().isEmpty()){
                warn();
                return;
            }
            nameColumn.setVisible(true);
            playerDetailsPane.setVisible(true);
            countTable.setVisible(false);
            s1=textField1.getText();
            homeList.clear();

            homeList.addAll(main.getUserClub().searchPosition(s1));
            showTable();
        }
        else if(option.equals("Max Age")){
            nameColumn.setVisible(true);
            playerDetailsPane.setVisible(true);
            countTable.setVisible(false);
            homeList.clear();

            homeList.addAll(main.getUserClub().searchMaxAge());
            showTable();
        }
        else if(option.equals("Max Height")){
            nameColumn.setVisible(true);
            playerDetailsPane.setVisible(true);
            countTable.setVisible(false);
            homeList.clear();
            homeList.addAll(main.getUserClub().searchMaxHeight());
            showTable();
        }
        else if(option.equals("Max Salary")){
            nameColumn.setVisible(true);
            playerDetailsPane.setVisible(true);
            countTable.setVisible(false);
            homeList.clear();
            homeList.addAll(main.getUserClub().searchMaxSalary());
            showTable();
        }
        else if(option.equals("Country-wise Player Count")){
            System.out.println("Yup");
            homeList.clear();
            nameColumn.setVisible(false);
            playerDetailsPane.setVisible(false);
            countTable.setVisible(true);
            ArrayList<PinC> ar = new ArrayList<>();
            for (Map.Entry me : main.getUserClub().getCountries().entrySet()) {
               PinC pc = new PinC((String)me.getKey(),(Integer)me.getValue());
               ar.add(pc);
            }
            countList.addAll(ar);
            allCountryColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
            countTable.setItems(countList);
        }

    }


    public void setMain(FM_Client main) {
        this.main=main;
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

        searchTable.setItems(homeList);
    }

    public void warn(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Input Invalid");
        alert.setHeaderText("Input is not valid");
        alert.setContentText("");
        alert.showAndWait();
    }
}
