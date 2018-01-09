package ecommerce.heady.com.ecommerce.classes;

import android.content.Context;

import ecommerce.heady.com.ecommerce.R;

/**
 * Created by kkishore on 08-01-2018.
 */

public class PojoStorage {
    public static Context c;
    static public CategoriesFull categories;
    static public int type;
    static public ProductsFull product;

    //getResources().getColor(colorResId);
//prog.setBackgroundColor(getResources().getColor(CommonUtils.getNextRandomColor()));
    public static int getColorInt(String colorname){
        if(colorname.equals("White")) {
            return c.getResources().getColor(R.color.white);
        }
        else if(colorname.equals("Grey")) {
            return c.getResources().getColor(R.color.grey);
        }
        else if(colorname.equals("Blue")) {
            return c.getResources().getColor(R.color.blue);
        }
        else if(colorname.equals("Red")) {
            return c.getResources().getColor(R.color.red);
        }
        else if(colorname.equals("Yellow")) {
            return c.getResources().getColor(R.color.yellow);
        }
        else if(colorname.equals("Green")) {
            return c.getResources().getColor(R.color.green);
        }
        else if(colorname.equals("Black")) {
            return c.getResources().getColor(R.color.black);
        }
        else if(colorname.equals("Brown")) {
            return c.getResources().getColor(R.color.brown);
        }
        else if(colorname.equals("Silver")) {
            return c.getResources().getColor(R.color.silver);
        }
        else if(colorname.equals("Golden")) {
            return c.getResources().getColor(R.color.gold);
        }

            return c.getResources().getColor(R.color.white);


    }
}
