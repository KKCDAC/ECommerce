package ecommerce.heady.com.ecommerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import ecommerce.heady.com.ecommerce.classes.PojoStorage;

public class ProductDetails extends AppCompatActivity {

    TextView details,price,vat,total,other_details;

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        setTitle(""+PojoStorage.product.getName());

        details=findViewById(R.id.textView_Details);
        price=findViewById(R.id.textView_price);
        vat=findViewById(R.id.textView_vat);
        total=findViewById(R.id.textView_total);
        other_details=findViewById(R.id.textView_otherDetails);
        img=findViewById(R.id.imageView_product_pic);

        details.setText(PojoStorage.product.getName()+" "+PojoStorage.product.getColor());
        price.setText("Price ₹"+PojoStorage.product.getPrice());
        vat.setText(""+PojoStorage.product.getTax_name()+" --->  "+PojoStorage.product.getTax_value()+"  = ₹"+(PojoStorage.product.getPrice()*((PojoStorage.product.getTax_value()/100))));
        total.setText("Total ---> ₹"+(PojoStorage.product.getPrice()*(1f+(PojoStorage.product.getTax_value()/100))));
        other_details.setText("Viewed -->"+PojoStorage.product.getView_count()+"\n"
        +"Ordered -->"+PojoStorage.product.getOrder_count()+"\n"
                +"Shared -->"+PojoStorage.product.getShare());
        PojoStorage.c=ProductDetails.this;
        img.setBackgroundColor(PojoStorage.getColorInt(PojoStorage.product.getColor()));


    }
}
