package ecommerce.heady.com.ecommerce;

import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import java.util.ArrayList;

import ecommerce.heady.com.ecommerce.adapter.ProductAdapter;
import ecommerce.heady.com.ecommerce.adapter.ProductDisplayAdapter;
import ecommerce.heady.com.ecommerce.classes.PojoStorage;
import ecommerce.heady.com.ecommerce.classes.ProductsFull;

public class ProductDisplay extends AppCompatActivity {

    Button button_sort;
    RecyclerView recyclerView;
    ArrayList<ProductsFull> productsFullArrayList;
    ProductDisplayAdapter productAdapter;
    int radio=1;
    BottomSheetDialog dialog;

    DatabaseHandler db;

    RadioButton radio_most_viewed,radio_price_asc,radio_price_dsc,
            radio_most_ordered,radio_most_shared;
    LinearLayout linear_layout_most_viewed,linear_layout_price_asc,
            linear_layout_price_dsc,linear_layout_most_ordered,
            linear_layout_most_shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display);

        db=new DatabaseHandler(ProductDisplay.this);
        if(PojoStorage.type==0){
            setTitle(""+PojoStorage.categories.getName());
        }
        else if(PojoStorage.type==1){
            setTitle("Most Viewed Product");
        }
        else if(PojoStorage.type==2){
            setTitle("Most Ordered Product");
        }
        else if(PojoStorage.type==3){
            setTitle("Most Shared Product");
        }

        recyclerView=findViewById(R.id.recycler_products);
        button_sort=findViewById(R.id.button_sort);
        if(PojoStorage.type>0){
            button_sort.setVisibility(View.GONE);
        }
        button_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = getLayoutInflater().inflate(R.layout.popup, null);
                dialog = new BottomSheetDialog(ProductDisplay.this);
                dialog.setContentView(v);
                radio_most_viewed = v.findViewById(R.id.radio_most_viewed);
                radio_price_asc = v.findViewById(R.id.radio_price_asc);
                radio_price_dsc = v.findViewById(R.id.radio_price_dsc);
                radio_most_ordered = v.findViewById(R.id.radio_most_ordered);
                radio_most_shared = v.findViewById(R.id.radio_most_shared);

                linear_layout_most_viewed =  v.findViewById(R.id.linear_layout_most_viewed);
                linear_layout_price_asc =  v.findViewById(R.id.linear_layout_price_asc);
                linear_layout_price_dsc =  v.findViewById(R.id.linear_layout_price_dsc);
                linear_layout_most_ordered =  v.findViewById(R.id.linear_layout_most_ordered);
                linear_layout_most_shared =  v.findViewById(R.id.linear_layout_most_shared);

                switch (radio){
                    case 1:
                        radio_most_viewed.setChecked(true);
                        break;
                    case 2:
                        radio_price_asc.setChecked(true);
                        break;
                    case 3:
                        radio_price_dsc.setChecked(true);
                        break;
                    case 4:
                        radio_most_ordered.setChecked(true);
                        break;
                    case 5:
                        radio_most_shared.setChecked(true);
                        break;

                }

                linear_layout_most_viewed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sortProducts(1);
                    }
                });

                linear_layout_price_asc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sortProducts(2);
                    }
                });
                linear_layout_price_dsc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sortProducts(3);
                    }
                });

                linear_layout_most_ordered.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sortProducts(4);
                    }
                });

                linear_layout_most_shared.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sortProducts(5);
                    }
                });

                radio_most_viewed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b){
                            sortProducts(1);
                        }
                    }
                });

                radio_price_asc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b){
                            sortProducts(2);
                        }
                    }
                });

                radio_price_dsc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b){
                            sortProducts(3);
                        }
                    }
                });

                radio_most_ordered.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b){
                            sortProducts(4);
                        }
                    }
                });

                radio_most_shared.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b){
                            sortProducts(5);
                        }
                    }
                });




                dialog.show();
            }
        });
        productsFullArrayList=db.getProductList_Display(PojoStorage.type,PojoStorage.categories);
        productAdapter = new ProductDisplayAdapter(ProductDisplay.this,productsFullArrayList);


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(ProductDisplay.this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(productAdapter);
    }

    void sortProducts(int type_of_sort){

        if(type_of_sort==1){
        radio_most_viewed.setChecked(true);
        radio_price_asc.setChecked(false);
        radio_price_dsc.setChecked(false);
        radio_most_ordered.setChecked(false);
        radio_most_shared.setChecked(false);
        radio=type_of_sort;
        }
        else if(type_of_sort==2){
            radio_most_viewed.setChecked(false);
            radio_price_asc.setChecked(true);
            radio_price_dsc.setChecked(false);
            radio_most_ordered.setChecked(false);
            radio_most_shared.setChecked(false);
            radio=type_of_sort;
        }
        else if(type_of_sort==3){
            radio_most_viewed.setChecked(false);
            radio_price_asc.setChecked(false);
            radio_price_dsc.setChecked(true);
            radio_most_ordered.setChecked(false);
            radio_most_shared.setChecked(false);
            radio=type_of_sort;
        }
        else if(type_of_sort==4){
            radio_most_viewed.setChecked(false);
            radio_price_asc.setChecked(false);
            radio_price_dsc.setChecked(false);
            radio_most_ordered.setChecked(true);
            radio_most_shared.setChecked(false);
            radio=type_of_sort;
        }
        if(type_of_sort==5){
            radio_most_viewed.setChecked(false);
            radio_price_asc.setChecked(false);
            radio_price_dsc.setChecked(false);
            radio_most_ordered.setChecked(false);
            radio_most_shared.setChecked(true);
            radio=type_of_sort;
        }

        productsFullArrayList.clear();
        productsFullArrayList=db.getProductList_sorted(radio,PojoStorage.categories);
        productAdapter = new ProductDisplayAdapter(ProductDisplay.this,productsFullArrayList);


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(ProductDisplay.this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(productAdapter);


        dialog.dismiss();
    }
}
