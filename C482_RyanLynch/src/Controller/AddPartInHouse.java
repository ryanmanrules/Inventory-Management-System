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
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;

/**Ryan Lynch*/

public class AddPartInHouse implements Initializable {
    /**create variables to call to open window later*/
    Stage stage;
    Parent scene;
    private TextField addedPart;
    /**IDs for various fields in the window
    //ID for ID*/
    @FXML
    private TextField AddPartInHouseID;
    /**ID for Name*/
    @FXML
    private TextField AddPartInHouseName;
    /**ID for Cancel*/
    @FXML
    private Button AddPartInhouseCancel;
    /**ID for Inv*/
    @FXML
    private TextField AddPartInhouseInv;
    /**ID for MachineID*/
    @FXML
    private TextField AddPartInhouseMachineID;
    /**ID for Max*/
    @FXML
    private TextField AddPartInhouseMax;
    /**ID for Outsourced radio button*/
    @FXML
    private RadioButton AddPartInhouseMenuOutSourcedRadio;
    /**ID for Min*/
    @FXML
    private TextField AddPartInhouseMin;
    /**ID for Price/Cost*/
    @FXML
    private TextField AddPartInhousePriceCost;
    /**ID for Save button*/
    @FXML
    private Button AddPartInhouseSave;
    /**ID for Inhouse Radio*/
    @FXML
    private RadioButton AddPartInhousemenuInhouseRadio;
    /**ID for machineID/company name label*/
    @FXML
    private Label LabelChangeMachineID;

    /**set the cancel button to load back to first screen when clicked and gives an alert any existing data will not be saved*/
    @FXML
    void OnActionPartBackToMenu(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will cancel and remove any current data on this screen, please confirm.");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {

            stage = (Stage) (((Button) event.getSource()).getScene().getWindow());
            scene = FXMLLoader.load(getClass().getResource("/Views/FirstScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**set save button to save data from field with various rules, and alerts if false*/
    @FXML
    void OnActionSaveButton(ActionEvent event) throws IOException {
       /** addedPart = AddPartInhouseMachineID;*/
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Views/FirstScreen.fxml"));
            loader.load();
            FirstScreen firstScreenController = loader.getController();

            /**id will just return a random number up to 20 the rest will be user parsed*/
            int id = 0;
            String Name = AddPartInHouseName.getText();
            int Inv = Integer.parseInt(AddPartInhouseInv.getText());
            int max = Integer.parseInt(AddPartInhouseMax.getText());
            int min = Integer.parseInt(AddPartInhouseMin.getText());
            /**runtime error had integer.parseint instead of double.parsedouble*/
            double PriceCost = Double.parseDouble(AddPartInhousePriceCost.getText());
            int machineID;
            String companyName;
            boolean partAdded = false;

/**if statement to catch all alerts for data input*/
            /**make sure name is not empty*/
            if (Name.isEmpty()) {
                displayAlert(5);
            } else{
                /**input values for min max inv*/
                if (setMin(min, max) && setInventory(min, max, Inv)) {
                    /**input machineID and make sure machineID is an int (while also casting InHouse for the new object)*/
                if (AddPartInhousemenuInhouseRadio.isSelected()) {
                    try {
                        machineID = Integer.parseInt(AddPartInhouseMachineID.getText());
                        InHouse InHousePart = new InHouse(id, Name, PriceCost, Inv, min, max, machineID);
                        InHousePart.setId(Inventory.uniquePartId());
                        Inventory.addPart(InHousePart);
                        partAdded = true;
                    } catch (Exception e) {
                        displayAlert(2);
                    }
                }
            /**input company name and make sure it is a string (while also casting outsourced for the new object)*/
                if (AddPartInhouseMenuOutSourcedRadio.isSelected()) {
                    companyName = AddPartInhouseMachineID.getText();
                    OutSourced OutSourcedPart = new OutSourced(id, Name, PriceCost, Inv, min, max, companyName);
                    OutSourcedPart.setId(Inventory.uniquePartId());
                    Inventory.addPart(OutSourcedPart);
                    partAdded = true;
                }
                if (partAdded) {
                    stage = (Stage) (((Button) event.getSource()).getScene().getWindow());
                    scene = FXMLLoader.load(getClass().getResource("/Views/FirstScreen.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }

            }
        }
    } catch(Exception e)
    {
        displayAlert(1);
    }
}
/**boolean that sets values for min so it's not below 0 or => max's value*/
    private boolean setMin (int min, int max)
    {
        boolean good = true;
        if(min <= 0 || min >= max)
        {
            good = false;
            displayAlert(3);
        }
        return good;
    }
/**boolean statement that only accepts values from inventory that are below min, or inventory that is above max*/
    private boolean setInventory(int min, int max, int Inv)
    {
        boolean good = true;
        if(Inv < min || Inv > max)
        {
            good = false;
            displayAlert(4);
        }
        return good;
    }
/**changes Company Name label to MachineID*/
    @FXML
    void OnActionChangeLabelToMachineID(ActionEvent event) {

            LabelChangeMachineID.setText("MachineId");


    }
    /**Changes machineID label to Company Name*/
        @FXML
        void OnActionChangeLabelCompanyName(ActionEvent event) {

                LabelChangeMachineID.setText("Company Name");


        }
/**switch alerts for all the errors that can appear*/
        private void displayAlert(int alerts){
        Alert alert = new Alert(Alert.AlertType.ERROR);

            switch (alerts) {
                case 1 -> {
                    alert.setTitle("Whoops!");
                    alert.setContentText("There are some blank or incorrect values!");
                    alert.showAndWait();
                }
                case 2 -> {
                    alert.setTitle("Whoops!");
                    alert.setContentText("Machine ID has to contain a number!");
                    alert.showAndWait();
                }
                case 3 -> {
                    alert.setTitle("Whoops!");
                    alert.setContentText("Min has to be greater then 0 AND less then max!");
                    alert.showAndWait();
                }
                case 4 -> {
                    alert.setTitle("Whoops!");
                    alert.setContentText("The Inventory field must be a number between Min and Max!");
                    alert.showAndWait();
                }
                case 5 -> {
                    alert.setTitle("Whoops!");
                    alert.setContentText("There is no name!");
                    alert.showAndWait();
                }
            }

        }
/**initialize class*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
