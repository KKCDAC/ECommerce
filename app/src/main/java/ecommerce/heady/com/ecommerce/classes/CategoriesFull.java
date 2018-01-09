package ecommerce.heady.com.ecommerce.classes;

/**
 * Created by msdg on 07-01-2018.
 */

public class CategoriesFull {
    int categories_id,parent_id;
    String name;


    public CategoriesFull() {
        this.name ="";
    }

    public CategoriesFull(int categories_id, int parent_id, String name) {
        this.categories_id = categories_id;
        this.parent_id = parent_id;
        this.name = name;
    }

    public int getCategories_id() {
        return categories_id;
    }

    public void setCategories_id(int categories_id) {
        this.categories_id = categories_id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
