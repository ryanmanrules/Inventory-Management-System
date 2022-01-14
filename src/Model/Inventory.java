package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> partList = FXCollections.observableArrayList();

    private static void addPart(Part newPart) {

        partList.add(newPart);

    }

    public static ObservableList<Part> getAllParts() {
        return partList;
    }

    private static ObservableList<Product> productList = FXCollections.observableArrayList();
    private static void addProduct(Product newProduct){

        productList.add(newProduct);
    }
    public static ObservableList<Product> getAllProducts() {
        return productList;
    }


}