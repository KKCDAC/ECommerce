package ecommerce.heady.com.ecommerce.classes;

/**
 * Created by kkishore on 04-01-2018.
 */

public class Variants {
    int id;
    String color;
    int size;
    int price;

    public Variants() {
        this.id = 0;
        this.color = "";
        this.size = 0;
        this.price = 0;
    }

    public Variants(int id, String color, int size, int price) {
        this.id = id;
        this.color = color;
        this.size = size;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Variants{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", size=" + size +
                ", price=" + price +
                '}';
    }
}
