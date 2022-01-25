package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/** Ryan Lynch
 * Main controller class
 */

/**Improvement ideas
 * >> Text fields aligned on any side with all 4 fields so you can specify more values, dedicating that search to just ID,price,stock,name
 * >> Expanding upon that, instead of fields have a seperate window dedicated to part/product search which can be customized more and have a bigger list
 */

public class FirstScreen implements Initializable {

    public static Object getmodifiedPart;
    Stage stage;
    Parent scene;

    private static Product AddProduct;
    private static Part ModifyPart;
    private static Product ModifyProduct;

/** ID exit button */
    @FXML
    private Button MainMenuExitButton;
/** ID add part button */
    @FXML
    private Button MainMenuPartsAddButton;
/** ID delete part button */
    @FXML
    private Button MainMenuPartsDeleteButton;
/** ID column part inventory */
    @FXML
    private TableColumn<Part, Integer> MainMenuPartsInventoryLevel;
/** ID modify part button */
    @FXML
    private Button MainMenuPartsModifyButton;
/** ID column part ID */
    @FXML
    private TableColumn<Part, Integer> MainMenuPartsPartID;
/** ID column part name */
    @FXML
    private TableColumn<Part, String> MainMenuPartsPartName;
/** ID column part price */
    @FXML
    private TableColumn<Part, Double> MainMenuPartsPriceCost;
/** ID add product button */
    @FXML
    private Button MainMenuProductsAddButton;
/** ID delete product button */
    @FXML
    private Button MainMenuProductsDeleteButton;
/** ID column product inventory */
    @FXML
    private TableColumn<Product, Integer> MainMenuProductsInventoryLevel;
/** ID product modify button */
    @FXML
    private Button MainMenuProductsModifyButton;
/** ID column products ID */
    @FXML
    private TableColumn<Product, Integer> MainMenuProductsPartID;
/** ID column products name */
    @FXML
    private TableColumn<Product, String> MainMenuProductsPartName;
/** ID column products price */
    @FXML
    private TableColumn<Product, Double> MainMenuProductsPriceCost;
/** ID parts search bar */
    @FXML
    private TextField MainMenuSearchBarParts;
/** ID products search bar */
    @FXML
    private TextField MainMenuSearchBarProducts;
/** ID parts tableview */
    @FXML
    private TableView<Part> MainMenuTableParts;
/** products tableview */
    @FXML
    private TableView<Product> MainMenuTableProducts;
/** selected product item in table view */
    public static Product getProductToModify() {
        return ModifyProduct;
    }

    /**when table row is selected, will ask with a confirmation to delete item, ok will cancel, cancel will change nothing. */
    @FXML
    void OnActionDeletePart(ActionEvent event) {

        Part selectPart = MainMenuTableParts.getSelectionModel().getSelectedItem();
        if(selectPart == null)
        {
            displayAlert(3);
                    }
        else
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("The selected part will be deleted, ok?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                Inventory.deletePart(selectPart);
            }

        }

    }
/**delete selected product unless it has an associated part with it */
    @FXML
    void OnActionDeleteProduct(ActionEvent event) {
        Product selectProduct = MainMenuTableProducts.getSelectionModel().getSelectedItem();
        if(selectProduct == null)
        {
            displayAlert(4);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("The selected product will be deleted, ok?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                ObservableList<Part> associatedParts = selectProduct.getAllAssociatedParts();
                if(associatedParts.size() >= 1)
                {
                    displayAlert(5);
                }
                else
                {
                    Inventory.deleteProduct(selectProduct);
                }
            }

        }
    }
/**exits application from exit button */
    @FXML
    void OnActionExit(ActionEvent event) {
        System.exit(0);
    }
/**opens addparts window */
    @FXML
    void OnActionOpenAddParts(ActionEvent event) throws IOException {

        stage = (Stage)(((Button)event.getSource()).getScene().getWindow());
        scene = FXMLLoader.load(getClass().getResource("/Views/AddPartInHouse.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
/** opens add product window */
    @FXML
    void OnActionOpenAddProduct(ActionEvent event) throws IOException {

        ModifyProduct = MainMenuTableProducts.getSelectionModel().getSelectedItem();
        stage = (Stage)(((Button)event.getSource()).getScene().getWindow());
        scene = FXMLLoader.load(getClass().getResource("/Views/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
/**opens modify parts window, sets a new controller to be able to call another controller from this one, gets selected item's values as well */
    @FXML
    void OnActionOpenModifyParts(ActionEvent event) throws IOException {
         try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Views/ModifyPartInHouse.fxml"));
            loader.load();
            //create get controller object to be called
            ModifyPartInHouse MPIHController = loader.getController();
            //send part to sendPart method
            MPIHController.sendModifyPart(MainMenuTableParts.getSelectionModel().getSelectedItem());
            //switching scenes
            stage = (Stage) (((Button) event.getSource()).getScene().getWindow());
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
           // stage.showAndWait();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("You must select a part to modify first!");
            alert.showAndWait();
        }
    }


/**opens ModifyProduct controller window */
    @FXML
    void OnActionOpenModifyProduct(ActionEvent event) throws IOException {

         ModifyProduct = MainMenuTableProducts.getSelectionModel().getSelectedItem();
        if (ModifyProduct == null) {
            displayAlert(4);
        } else {
            Parent parent = FXMLLoader.load(getClass().getResource("/Views/ModifyProduct.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }

    }
/**Search bar event, searches Inventory.getAllParts for matching id or Name (which I optimized all search bars a little bit to not be case sensitive).
    //An Improvement I would make upon this would be to have either more textfields somewhere around the window (side top bottom) or a seperate search window that pops out temporarily, and has all the fields
    //you can search through (ID Name Stock Price) I was going to put some time into doing it, but this project has already taken longer then I expected. */
    @FXML
    void OnActionSearchBar(ActionEvent event) {

        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> partsMatch = FXCollections.observableArrayList();
        String searchString = MainMenuSearchBarParts.getText();
        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchString) ||
                    (part.getName()).toLowerCase(Locale.ROOT).contains(searchString))

            {
                partsMatch.add(part);
            }
        }
        MainMenuTableParts.setItems(partsMatch);
        if (partsMatch.size() == 0) {
            displayAlert(1);
        }
    }
 /** search for parts within inventory part list by ID or name, alert if not found */
    @FXML
    void OnActionSearchbarProduct(ActionEvent event) {
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        ObservableList<Product> productsMatch = FXCollections.observableArrayList();
        String searchString = MainMenuSearchBarProducts.getText();
        for (Product product : allProducts) {
            if (String.valueOf(product.getId()).contains(searchString) ||
                    (product.getName()).toLowerCase(Locale.ROOT).contains(searchString))
            {
                productsMatch.add(product);
            }
        }
        MainMenuTableProducts.setItems(productsMatch);
         if (productsMatch.size() == 0) {
            displayAlert(1);
          }
    }
    /**switch alerts for all the errors that can appear*/
    private void displayAlert(int alertType) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        switch (alertType) {
            case 1 -> {
                alert.setHeaderText("Part not found");
                alert.showAndWait();
            }
            case 2 -> {
                alert.setHeaderText("Product not found");
                alert.showAndWait();
            }
            case 3 -> {
                alertError.setHeaderText("Part not selected");
                alertError.showAndWait();
            }
            case 4 -> {
                alertError.setHeaderText("Product not selected");
                alertError.showAndWait();
            }
            case 5 -> {
                alertError.setHeaderText("Parts Associated");
                alertError.setContentText("All parts must be removed from product before deletion.");
                alertError.showAndWait();
            }
        }
    }
    /** Set the observable list with table views and textfields with values for Part upon launch */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        MainMenuTableParts.setItems(Inventory.getAllParts());
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
    }
}
//End of code this is
//Just a bunch of notes I want to keep for future reference

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

/*
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
    //grabbing the values of the labels. It might be easier then this though
    //this will actually be good for the search box
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
    } */

 /*   try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Views/ModifyProduct.fxml"));
            loader.load();
            //create get controller object to be called
            ModifyProduct MProIHController = loader.getController();
            //send part to sendPart method
            MProIHController.sendModifyProduct(MainMenuTableProducts.getSelectionModel().getSelectedItem());
            //switching scenes
            stage = (Stage) (((Button) event.getSource()).getScene().getWindow());
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            // stage.showAndWait();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("You must select a product to modify first!");
            alert.showAndWait();
        }*/