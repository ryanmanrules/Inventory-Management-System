package Main;

//import Model.InHouse;
//import Model.OutSourced;
import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMethod extends Application {
    @Override
    // Launch application loading FirstScreen.fxml, this sets the path, title of window, scene with size and the stage overall
    public void start(Stage Stage)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Views/FirstScreen.fxml"));
        Stage.setTitle("First Screen");
        Stage.setScene(new Scene(root, 1008, 548));
        Stage.show();


    }

    public static void main(String[] args)
    {
       //set data/objects so data is not duplicated when called upon launch, placing them in the main method we know it will only launch once not being part of the controllers
        //RUNTIME ERROR I had OutSourced called here but had to change to Product because that's how the observable list is set up, I'm not sure if I will have to adjust this later, I will adjust this message if so.
        //changed back to OutSourced because I'm a dummy
    InHouse ram1 = new InHouse(1, "DDR4 RAM", 80.00, 20, 1, 20, 30);

    InHouse HDD1 = new InHouse(2, "Seagate 1TB", 100.89, 50, 3, 50, 20);

    OutSourced HDD2 = new OutSourced(3, "RocketFish 2TB", 155.00, 15, 4, 30, "Bestbuy");

    OutSourced ram2 = new OutSourced(4, "EVGA DDR5", 300.50, 2, 1, 2, "EVGA");

    InHouse monitor = new InHouse(5, "ACER 42'", 80.99, 20, 2, 5, 10);


    //add parts/products to tableview

        Inventory.addPart(ram1);
        Inventory.addPart(HDD1);
        Inventory.addPart(HDD2);
        Inventory.addPart(ram2);
        Inventory.addPart(monitor);



    launch(args);
    }




}