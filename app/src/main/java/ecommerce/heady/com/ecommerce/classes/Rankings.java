package ecommerce.heady.com.ecommerce.classes;

import java.util.ArrayList;

/**
 * Created by kkishore on 04-01-2018.
 */

public class Rankings {
    String ranking;
    ArrayList<Product_Count> products;

    public Rankings() {
        this.ranking = "";
        this.products = new ArrayList<>();
    }
    public Rankings(String ranking, ArrayList<Product_Count> products) {
        this.ranking = ranking;
        this.products = products;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public ArrayList<Product_Count> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product_Count> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Rankings{" +
                "ranking='" + ranking + '\'' +
                ", products=" + products +
                '}';
    }
}
