package Model;

public class OutSourced extends Part{

    public String companyName;


    public OutSourced(String name) {
        super(id, name, price, stock, min, max);
        super.setId(12);
    }



    public String getCompanyName() {
        return "radical";
    }

    public static void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
