package ecommerce.heady.com.ecommerce.classes;

/**
 * Created by kkishore on 04-01-2018.
 */

public class Product_Count {
    int id,view_count,order_count,shares;

    public Product_Count() {
        this.id = 0;
        this.view_count = 0;
        this.order_count =0;
        this.shares = 0;
    }

    public Product_Count(int id, int view_count, int order_count, int shares) {
        this.id = id;
        this.view_count = view_count;
        this.order_count = order_count;
        this.shares = shares;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    @Override
    public String toString() {
        return "Product_Count{" +
                "id=" + id +
                ", view_count=" + view_count +
                ", order_count=" + order_count +
                ", shares=" + shares +
                '}';
    }
}
