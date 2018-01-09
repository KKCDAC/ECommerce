package ecommerce.heady.com.ecommerce.classes;

/**
 * Created by kkishore on 04-01-2018.
 */

public class Tax {
    String name;
    float value;

    public Tax() {
        this.name = "";
        this.value = 0;
    }

    public Tax(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Tax{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
