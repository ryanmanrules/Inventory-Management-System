package Controller;

import Model.InHouse;
import Model.Part;
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

    public void sendModifyPart(InHouse part)
    {
        //setText expects a string, so part.getId is converted from int to String here
        //RUNTIME ERROR, cannot figure out why only getId is getting a "can't reference non-static from static, same with MachineId but that's from InHouse (solved, I was using the class Part not part)
        ModifyPartInhouseID.setText(String.valueOf(part.getId()));
        ModifyPartInhouseName.setText(part.getName());
        ModifyPartInhousePriceCost.setText(String.valueOf(part.getPrice()));
        ModifyPartInhouseInv.setText(String.valueOf(part.getStock()));
        ModifyPartInhouseMax.setText(String.valueOf(part.getMax()));
        ModifyPartInhouseMin.setText(String.valueOf(part.getMin()));
        ModifyPartInhouseMachineID.setText(String.valueOf(part.getMachineId()));
    }

    public void SendModifyPartInHouse(InHouse part)
    {
        ModifyPartInhouseMachineID.setText(String.valueOf(part.getMachineId()));
    }

}
