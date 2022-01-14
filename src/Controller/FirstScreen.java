package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FirstScreen implements Initializable {

    Stage stage;
    Parent scene;


    @FXML
    private Button MainMenuExitButton;

    @FXML
    private Button MainMenuPartsAddButton;

    @FXML
    private Button MainMenuPartsDeleteButton;

    @FXML
    private TableColumn<Part, Integer> MainMenuPartsInventoryLevel;

    @FXML
    private Button MainMenuPartsModifyButton;

    @FXML
    private TableColumn<Part, Integer> MainMenuPartsPartID;

    @FXML
    private TableColumn<Part, String> MainMenuPartsPartName;

    @FXML
    private TableColumn<Part, Double> MainMenuPartsPriceCost;

    @FXML
    private Button MainMenuProductsAddButton;

    @FXML
    private Button MainMenuProductsDeleteButton;

    @FXML
    private TableColumn<Product, Integer> MainMenuProductsInventoryLevel;

    @FXML
    private Button MainMenuProductsModifyButton;

    @FXML
    private TableColumn<Product, Integer> MainMenuProductsPartID;

    @FXML
    private TableColumn<Product, String> MainMenuProductsPartName;

    @FXML
    private TableColumn<Product, Double> MainMenuProductsPriceCost;

    @FXML
    private TextField MainMenuSearchBarParts;

    @FXML
    private TextField MainMenuSearchBarProducts;

    @FXML
    private TableView<Part> MainMenuTableParts;

    @FXML
    private TableView<Product> MainMenuTableProducts;

    @FXML
    void OnActionDeletePart(ActionEvent event) {

    }

    @FXML
    void OnActionDeleteProduct(ActionEvent event) {

    }

    @FXML
    void OnActionExit(ActionEvent event) {

        System.exit(0);

    }

    @FXML
    void OnActionOpenAddParts(ActionEvent event) throws IOException {

        stage = (Stage)(((Button)event.getSource()).getScene().getWindow());
        scene = FXMLLoader.load(getClass().getResource("/Views/AddPartInHouse.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void OnActionOpenAddProduct(ActionEvent event) throws IOException {

        stage = (Stage)(((Button)event.getSource()).getScene().getWindow());
        scene = FXMLLoader.load(getClass().getResource("/Views/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void OnActionOpenModifyParts(ActionEvent event) throws IOException {

        stage = (Stage)(((Button)event.getSource()).getScene().getWindow());
        scene = FXMLLoader.load(getClass().getResource("/Views/ModifyPartInHouse.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void OnActionOpenModifyProduct(ActionEvent event) throws IOException {

        stage = (Stage)(((Button)event.getSource()).getScene().getWindow());
        scene = FXMLLoader.load(getClass().getResource("/Views/ModifyProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        MainMenuTableParts.setItems(Inventory.getAllParts());
        MainMenuPartsInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        MainMenuPartsPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        MainMenuPartsPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        MainMenuPartsPriceCost.setCellValueFactory(new PropertyValueFactory<>("price"));

        MainMenuTableProducts.setItems(Inventory.getAllProducts());
        MainMenuProductsInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        MainMenuProductsPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        MainMenuProductsPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        MainMenuProductsPriceCost.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

}
