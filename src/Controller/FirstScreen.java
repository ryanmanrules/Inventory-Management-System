package Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.ObservableList;
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



    }

    @FXML
    void OnActionOpenModifyParts(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/ModifyPartInHouse.fxml"));
        loader.load();
        //create get controller object to be called
        ModifyPartInHouse APIHController = loader.getController();
        //send part to sendPart method
        APIHController.sendModifyPart((InHouse) MainMenuTableParts.getSelectionModel().getSelectedItem());
       //**Might need this later
        //APIHController.sendModifyPartInHouse(MainMenuTableParts.getSelectionModel().getSelectedItem());
        //switching scenes
        stage = (Stage)(((Button)event.getSource()).getScene().getWindow());
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.showAndWait();

    }



    @FXML
    void OnActionOpenModifyProduct(ActionEvent event) throws IOException {

        stage = (Stage)(((Button)event.getSource()).getScene().getWindow());
        scene = FXMLLoader.load(getClass().getResource("/Views/ModifyProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }
//enhanced for loop for basic search, will need to adjust
 /*  public boolean search(int id){
        for(Part InHouse : Inventory.getAllParts())
        {
            if(InHouse.getId() == id)
                return true;
        }
        return false;
    }


//simple method for grabbing the associated id and replacing it with a new object (this will be good for modifying I believe, although i feel like you could just set the button to run
    //grabbing the values of the indexes, but doing nothing if they are empty. It might be easier then this though
    public boolean update(int id, InHouse ram)
    {
        int index = -1;

        for(Part updatePart : Inventory.getAllParts())
        {
            index++;
            if(ram.getId() == id)
            {
                Inventory.getAllParts().set(index, ram);
                return true;
            }
        }
        return false;
    }


//delete method for grabbing the associated id and deleting it. (should be able to insert this to run when the delete button is selected on one of the indexes)
    public boolean deleteInHousePart(int id)
    {
        for(Part deleteInHousePart : Inventory.getAllParts())
        {
            if(deleteInHousePart.getId() == id)
                return Inventory.getAllParts().remove(deleteInHousePart);
        }
        return false;
    }

//selecting items from a table, using enhanced for loop when calling Inventory.getAllParts (will also use for getAllProducts)
    public Part selectPart(int id)
    {
        for(Part selectPart : Inventory.getAllParts())
        {
            if(selectPart.getId() == id)
                return selectPart;
        }
        return null;
    }






//enhanced for loop for filtering any value entered from an observable list (in this case getAllParts)
    public ObservableList<Part> filter(String Name)
    {
        //clearing filter to avoid duplication
        if(!(Inventory.getAllFilteredParts().isEmpty()));
        Inventory.getAllFilteredParts().clear();

        //filtering for getAllParts
        for(Part filteredparts : Inventory.getAllParts())
            if(filteredparts.getName().contains(Name))
                Inventory.getAllFilteredParts().add(filteredparts);
        //return regular list if nothing is filtered
        if(Inventory.getAllFilteredParts().isEmpty())
           //returns list of all parts
            return Inventory.getAllParts();
        else //else return the filtered parts
        return Inventory.getAllFilteredParts();

    }
*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
// Set the observable list with table views and textfields with values for Part
        MainMenuTableParts.setItems(Inventory.getAllParts());
      //filter for reference later  MainMenuTableParts.setItems(filter("Tuxedo"));
        MainMenuPartsPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        MainMenuPartsInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        MainMenuPartsPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        MainMenuPartsPriceCost.setCellValueFactory(new PropertyValueFactory<>("price"));
//Set the observable list with table views and textfields with values for Product
        MainMenuTableProducts.setItems(Inventory.getAllProducts());
        MainMenuProductsInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        MainMenuProductsPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        MainMenuProductsPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        MainMenuProductsPriceCost.setCellValueFactory(new PropertyValueFactory<>("price"));




        //search results
     /*   if(search(2))
        System.out.println("Match");
        else
            System.out.println("Match");
*/
/*
        if(update(1, new InHouse(1, "DDR3 RAM", 20.00, 90, 1, 20, 11)))
        System.out.println("update successful");
        else
        System.out.println("update failed");

 */
/*
        if(delete(1))
            System.out.println("deleted");
        else
            System.out.println("no match!");

 */

      //  MainMenuTableParts.getSelectionModel().select(selectPart(5));


    }

}
