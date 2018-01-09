package ecommerce.heady.com.ecommerce.classes;

import java.util.ArrayList;

/**
 * Created by kkishore on 04-01-2018.
 */

public class Products {

    int id;
    String name;
    String date_added;
    ArrayList<Variants> variants;
    Tax tax;

    public Products() {
        this.id = 0;
        this.name = "";
        this.date_added = "";
        this.variants = new ArrayList<>();
        this.tax = null;
    }

    public Products(int id, String name, String date_added, ArrayList<Variants> variants, Tax tax) {
        this.id = id;
        this.name = name;
        this.date_added = date_added;
        this.variants = variants;
        this.tax = tax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    public ArrayList<Variants> getVariants() {
        return variants;
    }

    public void setVariants(ArrayList<Variants> variants) {
        this.variants = variants;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date_added='" + date_added + '\'' +
                ", variants=" + variants +
                ", tax=" + tax +
                '}';
    }
}
