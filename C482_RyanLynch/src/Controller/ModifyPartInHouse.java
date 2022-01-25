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
import java.util.ResourceBundle;

/**
 *  Ryan Lynch


    Modify Part Controller that provides functionality to the modify part window when a item is selected
    I was thinking a good but maybe not entirely needed upgrade for this is being able to double click an item to open the modify window, which i may implement */
public class ModifyPartInHouse{
/**initialize variables */
    Stage stage;
    Parent scene;
    private Part modifiedPart;
/**Toggle button group so button switches back and forth between outsourced and inhouse */
    @FXML
    private ToggleGroup InHouseOutSourced;
/**Radio button Inhouse for OnAction events */
    @FXML
    private RadioButton ModifyPartInHouseMenuInhouseRadio;
/**ID for cancel button but only need OnAction event to cancel out to FirstScreen */
    @FXML
    private Button ModifyPartInhouseCancelButton;
/**ID for textfield for MachineID (disabled for users) */
    @FXML
    private TextField ModifyPartInhouseID;
/**ID for textfield for Inv */
    @FXML
    private TextField ModifyPartInhouseInv;
/**ID for textfield for machineID */
    @FXML
    private TextField ModifyPartInhouseMachineID;
/**ID for textfield for Max */
    @FXML
    private TextField ModifyPartInhouseMax;
/**ID for outsourced radio button */
    @FXML
    private RadioButton ModifyPartInhouseMenuOutsourcedRadio;
/**ID for textfield for min */
    @FXML
    private TextField ModifyPartInhouseMin;
/**ID for textfield for Name */
    @FXML
    private TextField ModifyPartInhouseName;
/**ID for textfield for Price/Cost */
    @FXML
    private TextField ModifyPartInhousePriceCost;
/**ID for save button (only OnAction event was needed for functionality) */
    @FXML
    private Button ModifyPartInhouseSaveButton;
/**ID for label to change between MachineID and InHouse when radio button is switched */
    @FXML
    private Label MachineIDLabel;

/**Rules to properly save textfield values, otherwise throw error, includes min max rules, empty field rules, proper values*/
    @FXML
    void OnActionSaveButton(ActionEvent event) {
        try {
            /**autogen ID*/
            int id = modifiedPart.getId();
            String Name = ModifyPartInhouseName.getText();
            int Inv = Integer.parseInt(ModifyPartInhouseInv.getText());
            int max = Integer.parseInt(ModifyPartInhouseMax.getText());
            int min = Integer.parseInt(ModifyPartInhouseMin.getText());
            /**runtime error had integer.parseint instead of double.parsedouble*/
            double PriceCost = Double.parseDouble(ModifyPartInhousePriceCost.getText());
            int machineID;
            String companyName;
            boolean partAdded = false;
        /**if statement to catch all alerts for data input
            make sure name is not empty*/
            if (Name.isEmpty()) {
                displayAlert(5);
            } else{
                /**input values for min max inv*/
                if (setMin(min, max) && setInventory(min, max, Inv)) {
                    /**input machineID and make sure machineID is an int (while also casting InHouse for the new object)*/
                    if (ModifyPartInHouseMenuInhouseRadio.isSelected()) {
                        try {
                            machineID = Integer.parseInt(ModifyPartInhouseMachineID.getText());
                            InHouse InHousePart = new InHouse(id, Name, PriceCost, Inv, min, max, machineID);
                            InHousePart.setId(Inventory.uniquePartId());
                            Inventory.addPart(InHousePart);
                            partAdded = true;
                        } catch (Exception e) {
                            displayAlert(2);
                        }
                    }
                    /**input company name and make sure it is a string (while also casting outsourced for the new object)*/
                    if (ModifyPartInhouseMenuOutsourcedRadio.isSelected()) {
                        companyName = ModifyPartInhouseMachineID.getText();
                        OutSourced OutSourcedPart = new OutSourced(id, Name, PriceCost, Inv, min, max, companyName);
                        OutSourcedPart.setId(Inventory.uniquePartId());
                        Inventory.addPart(OutSourcedPart);
                        partAdded = true;
                    }
                    if(partAdded)
                    {
                        Inventory.deletePart(modifiedPart);
                        ModifyPartBackToMenu(event);
                    }

                }
            }
        } catch(Exception e)
        {
            displayAlert(1);
        }
    }
/**set min/max rules, copied over from add part*/
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
/**set inventory rules, copied over from part*/
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
/**alert cases, catches each of the user error textfield inputs, also copied over from add part*/
    private void displayAlert(int alerts){
        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (alerts)
        {
            case 1:
                alert.setTitle("Whoops!");
                alert.setContentText("There are some blank or incorrect values!");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Whoops!");
                alert.setContentText("Machine ID has to contain a number!");
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle("Whoops!");
                alert.setContentText("Min has to be greater then 0 AND less then max!");
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("Whoops!");
                alert.setContentText("The Inventory field must be a number between Min and Max!");
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle("Whoops!");
                alert.setContentText("There is no name!");
                alert.showAndWait();
                break;
        }

    }
/**Cancel button clicked, gives warning, goes back to menu*/
    @FXML
    void ModifyPartBackToMenu(ActionEvent event) throws IOException {
        stage = (Stage)(((Button)event.getSource()).getScene().getWindow());
        scene = FXMLLoader.load(getClass().getResource("/Views/FirstScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void OnActionInHouseClick(ActionEvent event) {
    }

    @FXML
    void OnActionOutSourcedClick(ActionEvent event) {
    }

/**method to send data to new window*/
    public void sendModifyPart(Part part)
    {
        /**created object so you can call both InHouse machineID and OutSourced companyName*/
        modifiedPart = part;

        if (modifiedPart instanceof InHouse)
        {
            ModifyPartInHouseMenuInhouseRadio.setSelected(true);
            MachineIDLabel.setText("Machine ID");
            ModifyPartInhouseMachineID.setText(String.valueOf(((InHouse)modifiedPart).getMachineId()));
        }
        if (modifiedPart instanceof OutSourced)
        {
            ModifyPartInhouseMenuOutsourcedRadio.setSelected(true);
            MachineIDLabel.setText("Company Name");
            ModifyPartInhouseMachineID.setText(String.valueOf(((OutSourced)modifiedPart).getCompanyName()));
        }
        /**setText expects a string, so part.getId is converted from int to String here
        RUNTIME ERROR, cannot figure out why only getId is getting a "can't reference non-static from static, same with MachineId but that's from InHouse (solved, I was using the class Part not part) UPDATE:
        that was wrong as well, was using part from this method, instead of a new private part variable "modifiedPart" so I could call InHouse+OutSourced
        convert and send int ID*/
        ModifyPartInhouseID.setText(String.valueOf(modifiedPart.getId()));
        ModifyPartInhouseName.setText(modifiedPart.getName());
        ModifyPartInhousePriceCost.setText(String.valueOf(modifiedPart.getPrice()));
        ModifyPartInhouseInv.setText(String.valueOf(modifiedPart.getStock()));
        ModifyPartInhouseMax.setText(String.valueOf(modifiedPart.getMax()));
        ModifyPartInhouseMin.setText(String.valueOf(modifiedPart.getMin()));

/**if statement to change lable, if modifiedPart is an instance of InHouse radio is set true, the label will
 * be MachineID, else if modifiedPart is an instance of OutSourced radio is set to true, the label will be Company Name*/

        if(modifiedPart instanceof InHouse)
        {
            MachineIDLabel.setText("MachineId");
            ModifyPartInHouseMenuInhouseRadio.setSelected(true);
            ModifyPartInhouseMachineID.setText(String.valueOf(((InHouse)modifiedPart).getMachineId()));
        }
        else if (modifiedPart instanceof OutSourced)
        {
            MachineIDLabel.setText("Company Name");
            ModifyPartInhouseMenuOutsourcedRadio.setSelected(true);
            ModifyPartInhouseMachineID.setText(((OutSourced)modifiedPart).getCompanyName());
        }
    }
}
