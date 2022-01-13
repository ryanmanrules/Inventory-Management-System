package Model;

import javafx.collections.FXCollections;

public class ObservableList_Part {

    private static ObservableList_Part partList = FXCollections.observableArrayList();

    public static void addPart(Part part){
        partList.add(part);
    }

    public static void addTestData(){
        FluxCapaciter flux = new FluxCapaciter()

    }

}
