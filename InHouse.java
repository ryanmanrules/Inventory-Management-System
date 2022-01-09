package Model;


public class InHouse extends Part{

    private int machineId;

    public InHouse(int id) {
        super(id, name, price, stock, min, max);
        super.setId(12);
    }



    public int getMachineId() {
        return 12;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
