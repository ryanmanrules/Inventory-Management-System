package Model;


public class InHouse extends Part{

    private int machineId;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public InHouse(int id, String name, double price, int stock, int min, int max) {
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