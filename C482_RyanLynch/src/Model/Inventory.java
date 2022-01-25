package Model;

import Controller.AddPartInHouse;
import Controller.AddProduct;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**Ryan Lynch

Inventory for functionality with Part and Product and the entire app*/
public class Inventory {
/**create ObservableList<part> and create partList
variables for IDs*/
    private static int partId = 0;
    private static int productId = 0;
/**list of parts*/
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
/**add part to inventory*/
    public static void addPart(Part newPart) {

        /**when passed part object, adds to observable list*/
        allParts.add(newPart);

    }
/**add part to inventory*/
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
/**generate id
    runtime error: I feel like this is sloppy since the ID is incremental and won't be unique the first couple of added
    /parts if you already have a list*/
    public static int uniquePartId()
    {
       return ++partId;

    }


    /**search for parts by ID*/
    public static Part lookupPart(int partId)
    {
        Part partFound = null;
        for(Part part : allParts)
        {
            if(part.getId() == partId){
                partFound = part;
            }
        }
        return partFound;
    }
    /**search for parts by name*/
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> partsFound = FXCollections.observableArrayList();
        for(Part part : allParts){
            if(part.getName().equals(partName))
            {
                partsFound.add(part);
            }
        }
        return partsFound;
    }
/**search list for product ID*/
    public static Product lookupProduct(int productId)
    {
        Product productFound = null;
        for(Product product : allProducts)
        {
            if(product.getId() == productId){
                productFound = product;
            }
        }
        return productFound;
    }
/**search list for name*/
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> productsFound = FXCollections.observableArrayList();
        for(Product product : allProducts){
            if(product.getName().equals(productName))
            {
                productsFound.add(product);
            }
        }
        return productsFound;
    }
/**add product to inventory*/
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static final ObservableList<Product> filterProductList = FXCollections.observableArrayList();
    public static void addProduct(Product newProduct){

        /**when passed product object, adds to observable list*/
        allProducts.add(newProduct);
    }
    /**add product to inventory*/
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
/**generate product ID*/
    public static int uniqueProductId() {
           return ++productId;
        }
/**delete selected part*/
    public static boolean deletePart(Part selectedPart) {
    if(allParts.contains(selectedPart)){
        allParts.remove(selectedPart);
        return true;
    }
    else{
        return false;
    }
    }
/**delete selected product*/
    public static boolean deleteProduct(Product selectedProduct) {
        if(allProducts.contains(selectedProduct)){
            allProducts.remove(selectedProduct);
            return true;
        }
        else{
            return false;
        }
    }
/**replace part when selected in inventory*/
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }
/**replace product when selected in inventory*/
    public static void updateProduct(int index, Product selectedProduct){
        allProducts.set(index, selectedProduct);
    }
}