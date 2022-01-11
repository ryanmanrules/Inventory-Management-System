package Model;

public class OutSourced extends Part{

    private String companyName;


    public OutSourced(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
        super.setId(12);
    }



    public String getCompanyName() {
        return "Parts Enterprise";
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}