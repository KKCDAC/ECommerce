package ecommerce.heady.com.ecommerce.classes;

/**
 * Created by msdg on 07-01-2018.
 */

import java.util.Comparator;

public class ProductsFull {
    int category_id,product_id,variant_id,size,price,view_count,share,order_count;
    String name,date_added,tax_name,color;
    float tax_value;

    public ProductsFull() {
        this.name ="";
        this.date_added ="";
        this.tax_name = "";
        this.color = "";
    }

    public ProductsFull(int category_id, int product_id, int variant_id, int size, int price, int view_count, int share, int order_count, String name, String date_added, String tax_name, String color, float tax_value) {
        this.category_id = category_id;
        this.product_id = product_id;
        this.variant_id = variant_id;
        this.size = size;
        this.price = price;
        this.view_count = view_count;
        this.share = share;
        this.order_count = order_count;
        this.name = name;
        this.date_added = date_added;
        this.tax_name = tax_name;
        this.color = color;
        this.tax_value = tax_value;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getVariant_id() {
        return variant_id;
    }

    public void setVariant_id(int variant_id) {
        this.variant_id = variant_id;
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

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
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

    public String getTax_name() {
        return tax_name;
    }

    public void setTax_name(String tax_name) {
        this.tax_name = tax_name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getTax_value() {
        return tax_value;
    }

    public void setTax_value(float tax_value) {
        this.tax_value = tax_value;
    }

    @Override
    public String toString() {
        return "ProductsFull{" +
                "category_id=" + category_id +
                ", product_id=" + product_id +
                ", variant_id=" + variant_id +
                ", size=" + size +
                ", price=" + price +
                ", view_count=" + view_count +
                ", share=" + share +
                ", order_count=" + order_count +
                ", name='" + name + '\'' +
                ", date_added='" + date_added + '\'' +
                ", tax_name='" + tax_name + '\'' +
                ", color='" + color + '\'' +
                ", tax_value=" + tax_value +
                '}';
    }

    // Comparator
    public static class CompareViewCount_Decending implements Comparator<ProductsFull> {
        @Override
        public int compare(ProductsFull arg0, ProductsFull arg1) {
            return arg1.view_count - arg0.view_count;
        }
    }

    public static class CompareViewCount_Ascending implements Comparator<ProductsFull> {
        @Override
        public int compare(ProductsFull arg0, ProductsFull arg1) {
            return arg0.view_count-arg1.view_count  ;
        }
    }


    public static class CompareOrderCount_Decending implements Comparator<ProductsFull> {
        @Override
        public int compare(ProductsFull arg0, ProductsFull arg1) {
            return arg1.order_count - arg0.order_count;
        }
    }

    public static class CompareOrderCount_Ascending implements Comparator<ProductsFull> {
        @Override
        public int compare(ProductsFull arg0, ProductsFull arg1) {
            return arg0.order_count-arg1.order_count  ;
        }
    }


    public static class CompareShareCount_Decending implements Comparator<ProductsFull> {
        @Override
        public int compare(ProductsFull arg0, ProductsFull arg1) {
            return arg1.share - arg0.share;
        }
    }

    public static class CompareShareCount_Ascending implements Comparator<ProductsFull> {
        @Override
        public int compare(ProductsFull arg0, ProductsFull arg1) {
            return arg0.share-arg1.share  ;
        }
    }


    public static class ComparePrice_Decending implements Comparator<ProductsFull> {
        @Override
        public int compare(ProductsFull arg0, ProductsFull arg1) {
            return arg1.price - arg0.price;
        }
    }

    public static class ComparePrice_Ascending implements Comparator<ProductsFull> {
        @Override
        public int compare(ProductsFull arg0, ProductsFull arg1) {
            return arg0.price-arg1.price  ;
        }
    }

}

