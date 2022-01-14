package Main;

//import Model.InHouse;
//import Model.OutSourced;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMethod extends Application {
    @Override
    public void start(Stage Stage)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Views/FirstScreen.fxml"));
        Stage.setTitle("First Screen");
        Stage.setScene(new Scene(root, 1008, 548));
        Stage.show();


    }

    public static void main(String[] args){
        launch(args);
    }

   // public MainMethod(int id, String name, double price, int stock, int min, int max) {


       // InHouse inHouse = new InHouse(12, "Flux capacitor", 12.00, 10, 5, 20, 5);
       // OutSourced outsourced = new OutSourced(12, "Flux capacitor",12,10,5,20,"Parts Enterprise");


   // }



}