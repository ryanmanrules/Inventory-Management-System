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

public class AddProduct {

    Stage stage;
    Parent scene;

    @FXML
    private Button AddProductAddButton;

    @FXML
    private Button AddProductCancelButton;

    @FXML
    private TextField AddProductID;

    @FXML
    private TextField AddProductInv;

    @FXML
    private TableColumn<?, ?> AddProductInventoryLevelAdd;

    @FXML
    private TableColumn<?, ?> AddProductInventoryLevelFind;

    @FXML
    private TextField AddProductMax;

    @FXML
    private TextField AddProductName;

    @FXML
    private TableColumn<?, ?> AddProductPartIDAdd;

    @FXML
    private TableColumn<?, ?> AddProductPartIDFind;

    @FXML
    private TableColumn<?, ?> AddProductPartNameAdd;

    @FXML
    private TableColumn<?, ?> AddProductPartNameFind;

    @FXML
    private TextField AddProductPrice;

    @FXML
    private TableColumn<?, ?> AddProductPriceCostAdd;

    @FXML
    private TableColumn<?, ?> AddProductPriceCostFind;

    @FXML
    private Button AddProductRemovePartButton;

    @FXML
    private Button AddProductSaveButton;

    @FXML
    private TextField AddProductSearchBar;

    @FXML
    private TableView<?> AddProductTableAdd;

    @FXML
    private TableView<?> AddProductTableFind;

    @FXML
    private TextField AddProductmin;

    @FXML
    void OnActionBackToMenu(ActionEvent event) throws IOException {

        stage = (Stage)(((Button)event.getSource()).getScene().getWindow());
        scene = FXMLLoader.load(getClass().getResource("/Views/FirstScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

}
