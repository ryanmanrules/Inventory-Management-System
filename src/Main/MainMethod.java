package Main;

import Model.InHouse;
import Model.OutSourced;
import Model.Part;


public class MainMethod extends Part{


    public MainMethod(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);

        InHouse inHouse = new InHouse(12);
        OutSourced outsourced = new OutSourced("");

        inHouse.setMachineId(12);
        outsourced.setCompanyName("Parts Enterprise");

    }



}