package Model;

/**Ryan Lynch
constructor class for Outsourced parts*/
public class OutSourced extends Part{

    private String companyName;

/**set constructors with super*/
    public OutSourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
       this.companyName=companyName;
    }


/**return companyName*/
    public String getCompanyName() {

        return companyName;
    }
/**set companyName*/
    public void setCompanyName(String companyName)
    {

        this.companyName = companyName;
    }
}