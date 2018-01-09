package ecommerce.heady.com.ecommerce.classes;

import java.util.ArrayList;

/**
 * Created by kkishore on 04-01-2018.
 */

public class Categories {

    int id;
    String name;
    ArrayList<Products> products;
    ArrayList<Integer> child_categories;
    public Categories() {
        this.id = 0;
        this.name = "";
        this.products = new ArrayList<>();
        this.child_categories = new ArrayList<>();
    }

    public Categories(int id, String name, ArrayList<Products> products, ArrayList<Integer> child_categories) {
        this.id = id;
        this.name = name;
        this.products = products;
        this.child_categories = child_categories;
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

    public ArrayList<Products> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Products> products) {
        this.products = products;
    }

    public ArrayList<Integer> getChild_categories() {
        return child_categories;
    }

    public void setChild_categories(ArrayList<Integer> child_categories) {
        this.child_categories = child_categories;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", products=" + products +
                ", child_categories=" + child_categories +
                '}';
    }
}
