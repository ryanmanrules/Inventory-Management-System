package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/** Ryan Lynch
 * Modify products class
 */

public class ModifyProduct implements Initializable {

    Stage stage;
    Parent scene;
    private Product modifiedProduct;
    /**associated part list*/
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
/** ID add part button*/
    @FXML
    private Button ModifyProductAddButton;
/** ID cancel button*/
    @FXML
    private Button ModifyProductCancelButton;
/** ID product ID*/
    @FXML
    private TextField ModifyProductID;
/** ID Inventory*/
    @FXML
    private TextField ModifyProductInv;
/** ID column part stock*/
    @FXML
    private TableColumn<Part, Integer> ModifyProductInventoryLevelAdd;
/** ID column associated part stock*/
    @FXML
    private TableColumn<Part, Integer> ModifyProductInventoryLevelFind;
/** ID max*/
    @FXML
    private TextField ModifyProductMax;
/** ID min*/
    @FXML
    private TextField ModifyProductMin;
/** ID name*/
    @FXML
    private TextField ModifyProductName;
/** ID column part ID*/
    @FXML
    private TableColumn<Part, Integer> ModifyProductPartIDAdd;
/** ID column associated part ID*/
    @FXML
    private TableColumn<Part, Integer> ModifyProductPartIDFind;
/** ID column part name*/
    @FXML
    private TableColumn<Part, String> ModifyProductPartNameAdd;
/** ID column associated part name*/
    @FXML
    private TableColumn<Part, String> ModifyProductPartNameFind;
/** ID price*/
    @FXML
    private TextField ModifyProductPrice;
/** ID column part price*/
    @FXML
    private TableColumn<Part, Double> ModifyProductPriceCostAdd;
/** ID column associated part price*/
    @FXML
    private TableColumn<Part, Double> ModifyProductPriceCostFind;
/** ID remove button*/
    @FXML
    private Button ModifyProductRemovePartButton;
/** ID save button*/
    @FXML
    private Button ModifyProductSaveButton;
/** ID search bar*/
    @FXML
    private TextField ModifyProductSearchBar;
/** ID associated part tableview*/
    @FXML
    private TableView<Part> ModifyProductTableFind;
/** ID part tableview*/
    @FXML
    private TableView<Part> TableAddParts;
    /** add selected part to associated part table */
    @FXML
    void OnActionAddButton(ActionEvent event) {

        Part selectedPart = ModifyProductTableFind.getSelectionModel().getSelectedItem();
        if(selectedPart == null)
        {
            displayAlert(5);
        }
        else
        {
            associatedParts.add(selectedPart);
            TableAddParts.setItems(associatedParts);
        }
    }
    /** search for parts within inventory part list by ID or name, alert if not found */
    @FXML
    void OnActionSearchBar(ActionEvent event)
    {
        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> results = FXCollections.observableArrayList();
        String search = ModifyProductSearchBar.getText();
        for(Part part : allParts)
        {
            if(String.valueOf(part.getId()).contains(search)
                    || (part.getName()).toLowerCase(Locale.ROOT).contains(search))
            {
                results.add(part);
            }
        }
        ModifyProductTableFind.setItems(results);
        if(results.size() == 0)
        {
            displayAlert(1);
        }
    }
    /** remove selected associated part from associated part table, if nothing is selected alert */
    @FXML
    void OnActionRemovePart(ActionEvent event) {

        Part selectedPart = TableAddParts.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            displayAlert(5);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to remove the selected part?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                associatedParts.remove(selectedPart);
                TableAddParts.setItems(associatedParts);
            }
        }
    }
    /**set save button to save data from field with various rules, and alerts if false*/
    @FXML
    void OnActionSaveButton(ActionEvent event) throws IOException {
        try {
            /**id will just return an autogenerated incremented int the rest will be user parsed*/
            int id = modifiedProduct.getId();
            String Name = ModifyProductName.getText();
            int Inv = Integer.parseInt(ModifyProductInv.getText());
            int max = Integer.parseInt(ModifyProductMax.getText());
            int min = Integer.parseInt(ModifyProductMin.getText());
            /**runtime error had integer.parseint instead of double.parsedouble*/
            double PriceCost = Double.parseDouble(ModifyProductPrice.getText());
            /**if statement to catch all alerts for data input
            make sure name is not empty*/
            if (Name.isEmpty()) {
                displayAlert(6);
            } else {
                /**input values for min max inv*/
                if (setMin(min, max) && setInventory(min, max, Inv)) {
                    Product product = new Product(id, Name, PriceCost, Inv, min, max);
                    for (Part part : associatedParts) {
                        product.addAssociatedPart(part);
                    }
                    Inventory.addProduct(product);
                    Inventory.deleteProduct(modifiedProduct);
                    stage = (Stage) (((Button) event.getSource()).getScene().getWindow());
                    scene = FXMLLoader.load(getClass().getResource("/Views/FirstScreen.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
            }
        } catch (Exception e) {
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
    /**switch alerts for all the errors that can appear*/
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
                alert.setContentText("No part found.");
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
                alert.setContentText("No part selected!");
                alert.showAndWait();
                break;
            case 6:
                alert.setTitle("Error");
                alert.setHeaderText("Name Empty");
                alert.setContentText("Name cannot be empty.");
                alert.showAndWait();
                break;
        }
    }
    /**set the cancel button to load back to first screen when clicked and gives an alert any existing data will not be saved*/
    @FXML
    void OnActionCancelButton(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will cancel and remove any current data on this screen, please confirm.");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage) (((Button) event.getSource()).getScene().getWindow());
            scene = FXMLLoader.load(getClass().getResource("/Views/FirstScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }
    /** Initializes tables with inventory's part list, and table view for null associated part data, unless it has
     * associated parts*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modifiedProduct = FirstScreen.getProductToModify();
        associatedParts = modifiedProduct.getAllAssociatedParts();

        ModifyProductPartIDFind.setCellValueFactory(new PropertyValueFactory<>("id"));
        ModifyProductPartNameFind.setCellValueFactory(new PropertyValueFactory<>("name"));
        ModifyProductInventoryLevelFind.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ModifyProductPriceCostFind.setCellValueFactory(new PropertyValueFactory<>("price"));
        ModifyProductTableFind.setItems(Inventory.getAllParts());

        ModifyProductPartIDAdd.setCellValueFactory(new PropertyValueFactory<>("id"));
        ModifyProductPartNameAdd.setCellValueFactory(new PropertyValueFactory<>("name"));
        ModifyProductInventoryLevelAdd.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ModifyProductPriceCostAdd.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableAddParts.setItems(associatedParts);


        ModifyProductID.setText(String.valueOf(modifiedProduct.getId()));
        ModifyProductName.setText(modifiedProduct.getName());
        ModifyProductPrice.setText(String.valueOf(modifiedProduct.getPrice()));
        ModifyProductInv.setText(String.valueOf(modifiedProduct.getStock()));
        ModifyProductMax.setText(String.valueOf(modifiedProduct.getMax()));
        ModifyProductMin.setText(String.valueOf(modifiedProduct.getMin()));
    }
}
