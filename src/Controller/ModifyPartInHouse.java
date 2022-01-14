package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifyPartInHouse {

    Stage stage;
    Parent scene;

    @FXML
    private ToggleGroup InHouseOutSourced;

    @FXML
    private RadioButton ModifyPartInHouseMenuInhouseRadio;

    @FXML
    private Button ModifyPartInhouseCancelButton;

    @FXML
    private TextField ModifyPartInhouseID;

    @FXML
    private TextField ModifyPartInhouseInv;

    @FXML
    private TextField ModifyPartInhouseMachineID;

    @FXML
    private TextField ModifyPartInhouseMax;

    @FXML
    private RadioButton ModifyPartInhouseMenuOutsourcedRadio;

    @FXML
    private TextField ModifyPartInhouseMin;

    @FXML
    private TextField ModifyPartInhouseName;

    @FXML
    private TextField ModifyPartInhousePriceCost;

    @FXML
    private Button ModifyPartInhouseSaveButton;

    @FXML
    void ModifyPartBackToMenu(ActionEvent event) throws IOException {

        stage = (Stage)(((Button)event.getSource()).getScene().getWindow());
        scene = FXMLLoader.load(getClass().getResource("/Views/FirstScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

}
