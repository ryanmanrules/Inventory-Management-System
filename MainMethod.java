import Model.InHouse;
import Model.OutSourced;
import Model.Part;

public class MainMethod extends Part{


    public MainMethod(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);

        InHouse inHouse = new InHouse(12);
        OutSourced outsourced = new OutSourced("radical");

        InHouse.setMachineId(12);
        OutSourced.setCompanyName("radical");

    }

      System.out.println(InHouse.setId());

    }






