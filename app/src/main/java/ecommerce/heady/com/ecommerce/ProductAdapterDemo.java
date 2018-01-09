package ecommerce.heady.com.ecommerce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ecommerce.heady.com.ecommerce.classes.ProductsFull;

/**
 * Created by msdg on 07-01-2018.
 */

public class ProductAdapterDemo extends BaseAdapter implements AdapterView.OnItemClickListener{
    /*********** Declare Used Variables *********/
    private Context context;
    private ArrayList<ProductsFull> data;
    private static LayoutInflater inflater=null;
    //public Resources res;
    ProductsFull tempValues=null;
    int i=0;

    /*************  CustomAdapter Constructor *****************/
    public ProductAdapterDemo(Context context, ArrayList<ProductsFull> d) {

        /********** Take passed values **********/
        this.context= context;
        data=d;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /******** What is the size of Passed Arraylist Size ************/
    public int getCount() {

        if(data.size()<=0)
            return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{

        public TextView text_name;
        public TextView text_price;
        public ImageView image;

    }

    /****** Depends upon data size called for each row , Create each ListView row *****/
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if(convertView==null){

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.product_view, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.text_name = (TextView) vi.findViewById(R.id.textView_name);
            holder.text_price=(TextView)vi.findViewById(R.id.textView_price);
            holder.image=(ImageView)vi.findViewById(R.id.imageView2);

            /************  Set holder with LayoutInflater ************/
            vi.setTag( holder );
        }
        else
            holder=(ViewHolder)vi.getTag();

        if(data.size()<=0)
        {
            holder.text_name.setText("No Data");

        }
        else
        {
            /***** Get each Model object from Arraylist ********/
            tempValues=null;
            tempValues = ( ProductsFull ) data.get( position );

            /************  Set Model values in Holder elements ***********/

            holder.text_name.setText( tempValues.getName()+ " "+tempValues.getColor() );
            holder.text_price.setText( "₹"+tempValues.getPrice() );
            /*holder.image.setImageResource(
                    res.getIdentifier(
                            "com.androidexample.customlistview:drawable/"+tempValues.getImage()
                            ,null,null));*/

            /******** Set Item Click Listner for LayoutInflater for each row *******/

            vi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Do something", Toast.LENGTH_SHORT).show();
                }
            });
        }
        return vi;
    }
}