package ecommerce.heady.com.ecommerce.classes;

import java.util.ArrayList;

/**
 * Created by kkishore on 04-01-2018.
 */

public class Ecommerce {

    ArrayList<Categories> categories;
    ArrayList<Rankings> rankings;

    public Ecommerce() {
        this.categories = new ArrayList<>();
        this.rankings = new ArrayList<>();
    }

    public Ecommerce(ArrayList<Categories> categories, ArrayList<Rankings> rankings) {
        this.categories = categories;
        this.rankings = rankings;
    }

    public ArrayList<Categories> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Categories> categories) {
        this.categories = categories;
    }

    public ArrayList<Rankings> getRankings() {
        return rankings;
    }

    public void setRankings(ArrayList<Rankings> rankings) {
        this.rankings = rankings;
    }

    @Override
    public String toString() {
        return "Ecommerce{" +
                "categories=" + categories +
                ", rankings=" + rankings +
                '}';
    }
}
