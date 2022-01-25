package Model;

/**Ryan Lynch

//constructor class for inhouse parts
 */
public class InHouse extends Part{


    private int machineId;

/**RUNTIME ERROR, I started with building this and Outsourced, I made it overly complicated trying to call more then I needed from Part (using the assistance from intellij too much)
set constructors with super*/
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId=++machineId;
    }


/**return the ID*/
    public int getMachineId() {

        return machineId;
    }
/**Set the machineId*/
    public void setMachineId(int machineId) {

        this.machineId = machineId;
    }
}