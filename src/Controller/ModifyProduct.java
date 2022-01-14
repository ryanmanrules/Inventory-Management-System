package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifyProduct {

    Stage stage;
    Parent scene;

    @FXML
    private Button ModifyProductAddButton;

    @FXML
    private Button ModifyProductCancelButton;

    @FXML
    private TextField ModifyProductID;

    @FXML
    private TextField ModifyProductInv;

    @FXML
    private TableColumn<?, ?> ModifyProductInventoryLevelAdd;

    @FXML
    private TableColumn<?, ?> ModifyProductInventoryLevelFind;

    @FXML
    private TextField ModifyProductMax;

    @FXML
    private TextField ModifyProductMin;

    @FXML
    private TextField ModifyProductName;

    @FXML
    private TableColumn<?, ?> ModifyProductPartIDAdd;

    @FXML
    private TableColumn<?, ?> ModifyProductPartIDFind;

    @FXML
    private TableColumn<?, ?> ModifyProductPartNameAdd;

    @FXML
    private TableColumn<?, ?> ModifyProductPartNameFind;

    @FXML
    private TextField ModifyProductPrice;

    @FXML
    private TableColumn<?, ?> ModifyProductPriceCostAdd;

    @FXML
    private TableColumn<?, ?> ModifyProductPriceCostFind;

    @FXML
    private Button ModifyProductRemovePartButton;

    @FXML
    private Button ModifyProductSaveButton;

    @FXML
    private TextField ModifyProductSearchBar;

    @FXML
    private TableView<?> ModifyProductTableFind;

    @FXML
    private TableView<?> TableAddParts;

    @FXML
    void OnActionBackToMenu(ActionEvent event) throws IOException {

        stage = (Stage)(((Button)event.getSource()).getScene().getWindow());
        scene = FXMLLoader.load(getClass().getResource("/Views/FirstScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

}
