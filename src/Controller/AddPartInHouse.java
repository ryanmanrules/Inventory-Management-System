package Controller;

import Model.InHouse;
import Model.Inventory;
import Model.OutSourced;
import Model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPartInHouse implements Initializable {
//create variables to call to open window later
    Stage stage;
    Parent scene;
//IDs for various fields in the window
    @FXML
    private TextField AddPartInHouseID;

    @FXML
    private TextField AddPartInHouseName;

    @FXML
    private Button AddPartInhouseCancel;

    @FXML
    private TextField AddPartInhouseInv;

    @FXML
    private TextField AddPartInhouseMachineID;

    @FXML
    private TextField AddPartInhouseMax;

    @FXML
    private RadioButton AddPartInhouseMenuOutSourcedRadio;

    @FXML
    private TextField AddPartInhouseMin;

    @FXML
    private TextField AddPartInhousePriceCost;

    @FXML
    private Button AddPartInhouseSave;


    @FXML
    private RadioButton AddPartInhousemenuInhouseRadio;
//set the cancel button to load back to first screen when clicked
    @FXML
    void OnActionPartBackToMenu(ActionEvent event) throws IOException {

        stage = (Stage)(((Button)event.getSource()).getScene().getWindow());
        scene = FXMLLoader.load(getClass().getResource("/Views/FirstScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
//set save button to save data from field
    @FXML
    void OnActionSaveButton(ActionEvent event) throws IOException {

        int id = Integer.parseInt(AddPartInHouseID.getText());
        String Name = AddPartInHouseName.getText();
        int Inv = Integer.parseInt(AddPartInhouseInv.getText());
        int max = Integer.parseInt(AddPartInhouseMax.getText());
        int min = Integer.parseInt(AddPartInhouseMin.getText());
        double PriceCost = Integer.parseInt(AddPartInhousePriceCost.getText());
        int machineID = Integer.parseInt(AddPartInhouseMachineID.getText());
        String companyName = AddPartInhouseMachineID.getText();

//adds data to observable list
        //RUNTIME ERROR, thought I had to add data types to the identifiers(ID, name...)
        Inventory.addPart(new InHouse(id, Name, PriceCost, Inv, max, min, machineID));
        Inventory.addPart(new OutSourced(id, Name, PriceCost, Inv, max, min, companyName));
//closes window back to FirstScreen
        stage = (Stage)(((Button)event.getSource()).getScene().getWindow());
        scene = FXMLLoader.load(getClass().getResource("/Views/FirstScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void OnActionChangeLabelToMachineID(ActionEvent event) {


    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
