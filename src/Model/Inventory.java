package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
//create ObservableList<part> and create partList
    private static final ObservableList<Part> partList = FXCollections.observableArrayList();
    private static final ObservableList<Part> filterPartList = FXCollections.observableArrayList();

    public static void addPart(Part newPart) {

        //when passed part object, adds to observable list
        partList.add(newPart);

    }

    public static ObservableList<Part> getAllParts() {
        return partList;
    }

    public static ObservableList<Part> getAllFilteredParts() {
        return filterPartList;
    }


    private static final ObservableList<Product> productList = FXCollections.observableArrayList();
    private static final ObservableList<Product> filterProductList = FXCollections.observableArrayList();
    public static void addProduct(Product newProduct){

        ////when passed product object, adds to observable list
        productList.add(newProduct);
    }
    public static ObservableList<Product> getAllProducts() {
        return productList;
    }

    public static ObservableList<Product> getAllFilteredProducts() {
        return filterProductList;
    }


}