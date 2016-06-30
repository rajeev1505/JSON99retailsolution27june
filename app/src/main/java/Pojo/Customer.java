package Pojo;

/**
 * Created by w7 on 1/26/2016.
 */
public class Customer {


    String customermobileno;
    String customername;
    String customeremail;
    String creditcust;



    public Customer(String customermobileno, String customername,String customeremail) {
        this.customermobileno = customermobileno;
        this.customername = customername;
        this.customeremail=customeremail;

    }

    public Customer() {

    }

    public String getCustomermobileno() {
        return customermobileno;
    }

    public void setCustomermobileno(String customermobileno) {
        this.customermobileno = customermobileno;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }


    public String getCustomeremail() {
        return customeremail;
    }

    public void setCustomeremail(String customeremail) {
        this.customeremail = customeremail;
    }
    @Override
    public String toString() {
        return customername;

    }

}
