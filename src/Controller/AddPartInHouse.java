package Controller;

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

    Stage stage;
    Parent scene;

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

    @FXML
    void OnActionPartBackToMenu(ActionEvent event) throws IOException {

        stage = (Stage)(((Button)event.getSource()).getScene().getWindow());
        scene = FXMLLoader.load(getClass().getResource("/Views/FirstScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
