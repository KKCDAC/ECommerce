package ecommerce.heady.com.ecommerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ecommerce.heady.com.ecommerce.ProductDetails;
import ecommerce.heady.com.ecommerce.R;
import ecommerce.heady.com.ecommerce.classes.PojoStorage;
import ecommerce.heady.com.ecommerce.classes.ProductsFull;

/**
 * Created by msdg on 07-01-2018.
 */

public class ProductDisplayAdapter extends RecyclerView.Adapter<ProductDisplayAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<ProductsFull> productList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,price;
        public ImageView thumbnail;
        LinearLayout linearLayout_main;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.textView_name);
            price = (TextView) view.findViewById(R.id.textView_price);
            thumbnail = (ImageView) view.findViewById(R.id.imageView2);
            linearLayout_main=(LinearLayout) view.findViewById(R.id.linear_layout_main);
        }
    }


    public ProductDisplayAdapter(Context mContext, ArrayList<ProductsFull> albumList) {
        this.mContext = mContext;
        this.productList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_display_card2, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ProductsFull prod = productList.get(position);
        holder.name.setText(prod.getName()+" "+prod.getColor());
        holder.price.setText("₹"+prod.getPrice());
        PojoStorage.c=mContext;
        holder.thumbnail.setBackgroundColor(PojoStorage.getColorInt(prod.getColor()));

        holder.linearLayout_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PojoStorage.product=prod;
                Intent intent=new Intent(mContext, ProductDetails.class);
                mContext.startActivity(intent);

            }
        });

       /* // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });*/
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    /*private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }*/

    /**
     * Click listener for popup menu items
     */
    /*class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }*/

    @Override
    public int getItemCount() {
        return productList.size();
    }
}