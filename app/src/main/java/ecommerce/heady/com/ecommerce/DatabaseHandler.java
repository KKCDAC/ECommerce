package ecommerce.heady.com.ecommerce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Array;

import ecommerce.heady.com.ecommerce.classes.Categories;
import ecommerce.heady.com.ecommerce.classes.CategoriesFull;
import ecommerce.heady.com.ecommerce.classes.ProductsFull;
import ecommerce.heady.com.ecommerce.classes.Rankings;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kkishore on 04-01-2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {


    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "ecommerce";

    // table name
    private static final String TABLE_CATEGORIES = "categories";
    private static final String TABLE_PRODUCTS = "products";

    // Contacts Table Categories names
    private static final String TABLE_CATEGORIES_CATEGORIES_ID = "categories_id";
    private static final String TABLE_CATEGORIES_PARENT_ID = "parent_id";
    private static final String TABLE_CATEGORIES_NAME = "name";

    //Table product
    private static final String TABLE_PRODUCT_ID = "product_id";
    private static final String TABLE_PRODUCT_VARIANT_ID = "variant_id";
    private static final String TABLE_PRODUCT_NAME = "name";
    private static final String TABLE_PRODUCT_DATE_ADDED = "date_added";
    private static final String TABLE_PRODUCT_TAX_NAME = "tax_name";
    private static final String TABLE_PRODUCT_TAX_VALUE = "tax_value";
    private static final String TABLE_PRODUCT_COLOR = "color";
    private static final String TABLE_PRODUCT_SIZE = "size";
    private static final String TABLE_PRODUCT_PRICE = "price";
    private static final String TABLE_PRODUCT_VIEW_COUNT = "view_count";
    private static final String TABLE_PRODUCT_ORDER_COUNT = "order_count";
    private static final String TABLE_PRODUCT_SHARES = "shares";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_CATEGORIES + "("
                + TABLE_CATEGORIES_CATEGORIES_ID + " INTEGER PRIMARY KEY," + TABLE_CATEGORIES_NAME + " TEXT,"
                + TABLE_CATEGORIES_PARENT_ID + " INTEGER" + ")";
        String CREATE_PRODUCT = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + TABLE_CATEGORIES_CATEGORIES_ID + " INTEGER," + TABLE_PRODUCT_ID + " INTEGER,"
                + TABLE_PRODUCT_VARIANT_ID + " INTEGER PRIMARY KEY," + TABLE_PRODUCT_NAME + " TEXT,"
                + TABLE_PRODUCT_DATE_ADDED + " TEXT," + TABLE_PRODUCT_TAX_NAME + " TEXT,"
                + TABLE_PRODUCT_TAX_VALUE + " INTEGER," + TABLE_PRODUCT_COLOR + " TEXT,"
                + TABLE_PRODUCT_SIZE + " INTEGER," + TABLE_PRODUCT_PRICE + " INTEGER,"
                + TABLE_PRODUCT_VIEW_COUNT + " INTEGER," + TABLE_PRODUCT_ORDER_COUNT + " INTEGER,"
                + TABLE_PRODUCT_SHARES + " INTEGER" + ")";

        db.execSQL(CREATE_CATEGORIES_TABLE);
        db.execSQL(CREATE_PRODUCT);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addFirst(){
        SQLiteDatabase db = this.getWritableDatabase();
            ContentValues value1 = new ContentValues();
            value1.put(TABLE_CATEGORIES_CATEGORIES_ID,0); // Contact Name
            value1.put(TABLE_CATEGORIES_NAME,"Products"); // Contact Phone
            value1.put(TABLE_CATEGORIES_PARENT_ID,0);
            db.insert(TABLE_CATEGORIES, null, value1);
        db.close();
    }



    void addCategories(Categories categories){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value1 = new ContentValues();
        value1.put(TABLE_CATEGORIES_CATEGORIES_ID,categories.getId()); // Contact Name
        value1.put(TABLE_CATEGORIES_NAME,categories.getName()); // Contact Phone
        value1.put(TABLE_CATEGORIES_PARENT_ID,0);
        db.insert(TABLE_CATEGORIES, null, value1);
        Log.e("inside condition","Value Added");
        if(categories.getChild_categories().size()==0){
                addproducts(categories);
        }
        db.close();
    }

    void addproducts(Categories categories){
        SQLiteDatabase db = this.getWritableDatabase();
        for(int i=0;i<categories.getProducts().size();i++){
            for(int j=0;j<categories.getProducts().get(i).getVariants().size();j++){
                ContentValues value1 = new ContentValues();
                value1.put(TABLE_CATEGORIES_CATEGORIES_ID,categories.getId());

                value1.put(TABLE_PRODUCT_ID,categories.getProducts().get(i).getId());
                value1.put(TABLE_PRODUCT_NAME,categories.getProducts().get(i).getName());
                value1.put(TABLE_PRODUCT_DATE_ADDED,categories.getProducts().get(i).getDate_added());
                value1.put(TABLE_PRODUCT_TAX_NAME,categories.getProducts().get(i).getTax().getName());
                value1.put(TABLE_PRODUCT_TAX_VALUE,categories.getProducts().get(i).getTax().getValue());

                value1.put(TABLE_PRODUCT_VARIANT_ID,categories.getProducts().get(i).getVariants().get(j).getId());
                value1.put(TABLE_PRODUCT_COLOR,categories.getProducts().get(i).getVariants().get(j).getColor());
                value1.put(TABLE_PRODUCT_SIZE,categories.getProducts().get(i).getVariants().get(j).getSize());
                value1.put(TABLE_PRODUCT_PRICE,categories.getProducts().get(i).getVariants().get(j).getPrice());

                value1.put(TABLE_PRODUCT_VIEW_COUNT,0);
                value1.put(TABLE_PRODUCT_SHARES,0);
                value1.put(TABLE_PRODUCT_ORDER_COUNT,0);



                db.insert(TABLE_PRODUCTS, null, value1);
            }
        }
        db.close();
    }

    void addChild(Categories categories){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.e("inside condition",""+categories.getChild_categories().size());
        if(categories.getChild_categories().size()>0){

            for(int i=0;i<categories.getChild_categories().size();i++){
                ContentValues values = new ContentValues();
                values.put(TABLE_CATEGORIES_PARENT_ID,(Integer)categories.getId());
                // updating row
                db.update(TABLE_CATEGORIES, values, TABLE_CATEGORIES_CATEGORIES_ID + " = "+(Integer)categories.getChild_categories().get(i).intValue(), null);
            }

        }

        db.close();
    }

    void addOrderCounts(Rankings rk){
        SQLiteDatabase db = this.getWritableDatabase();

        if(rk.getProducts().size()>0){

            for(int i=0;i<rk.getProducts().size();i++){
                ContentValues values = new ContentValues();
                values.put(TABLE_PRODUCT_ORDER_COUNT,(Integer)rk.getProducts().get(i).getOrder_count());
                // updating row
                db.update(TABLE_PRODUCTS, values, TABLE_PRODUCT_VARIANT_ID + " = "+(Integer)rk.getProducts().get(i).getId(), null);
            }

        }

        db.close();
    }

    void addViewCounts(Rankings rk){
        SQLiteDatabase db = this.getWritableDatabase();

        if(rk.getProducts().size()>0){

            for(int i=0;i<rk.getProducts().size();i++){
                ContentValues values = new ContentValues();
                values.put(TABLE_PRODUCT_VIEW_COUNT,(Integer)rk.getProducts().get(i).getView_count());
                // updating row
                db.update(TABLE_PRODUCTS, values, TABLE_PRODUCT_VARIANT_ID + " = "+(Integer)rk.getProducts().get(i).getId(), null);
            }

        }

        db.close();
    }

    void addShareCounts(Rankings rk){
        SQLiteDatabase db = this.getWritableDatabase();

        if(rk.getProducts().size()>0){

            for(int i=0;i<rk.getProducts().size();i++){
                ContentValues values = new ContentValues();
                values.put(TABLE_PRODUCT_SHARES,(Integer)rk.getProducts().get(i).getShares());
                // updating row
                db.update(TABLE_PRODUCTS, values, TABLE_PRODUCT_VARIANT_ID + " = "+(Integer)rk.getProducts().get(i).getId(), null);
            }

        }

        db.close();
    }

    public ArrayList<ProductsFull> getAllProducts() {
        ArrayList<ProductsFull> prod_array = new ArrayList();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ProductsFull prod = new ProductsFull();

                prod.setCategory_id(cursor.getInt(0));
                prod.setProduct_id(cursor.getInt(1));
                prod.setVariant_id(cursor.getInt(2));
                prod.setName(cursor.getString(3));
                prod.setDate_added(cursor.getString(4));
                prod.setTax_name(cursor.getString(5));
                prod.setTax_value(cursor.getInt(6));
                prod.setColor(cursor.getString(7));
                prod.setSize(cursor.getInt(8));
                prod.setPrice(cursor.getInt(9));
                prod.setView_count(cursor.getInt(10));
                prod.setOrder_count(cursor.getInt(11));
                prod.setShare(cursor.getInt(12));
                prod_array.add(prod);
            } while (cursor.moveToNext());
        }

        // return contact list
        db.close();
        return prod_array;
    }

    public ArrayList<ProductsFull> getLimitedProducts(int type) {
        ArrayList<ProductsFull> prod_array = new ArrayList();
        // Select All Query
        String selectQuery="";
        if(type==1){
            selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS +" WHERE "
                    +TABLE_PRODUCT_VIEW_COUNT+">0 ORDER BY "+TABLE_PRODUCT_VIEW_COUNT+" DESC LIMIT 5";
        }
        else if(type==2){
            selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS +" WHERE "
                    +TABLE_PRODUCT_ORDER_COUNT+">0 ORDER BY "+TABLE_PRODUCT_ORDER_COUNT+" DESC LIMIT 5";
        }
        else if(type==3){
            selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS +" WHERE "
                    +TABLE_PRODUCT_SHARES+">0 ORDER BY "+TABLE_PRODUCT_SHARES+" DESC LIMIT 5";
        }


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ProductsFull prod = new ProductsFull();

                prod.setCategory_id(cursor.getInt(0));
                prod.setProduct_id(cursor.getInt(1));
                prod.setVariant_id(cursor.getInt(2));
                prod.setName(cursor.getString(3));
                prod.setDate_added(cursor.getString(4));
                prod.setTax_name(cursor.getString(5));
                prod.setTax_value(cursor.getInt(6));
                prod.setColor(cursor.getString(7));
                prod.setSize(cursor.getInt(8));
                prod.setPrice(cursor.getInt(9));
                prod.setView_count(cursor.getInt(10));
                prod.setOrder_count(cursor.getInt(11));
                prod.setShare(cursor.getInt(12));
                prod_array.add(prod);
            } while (cursor.moveToNext());
        }

        // return contact list
        db.close();
        return prod_array;
    }

    public List<CategoriesFull> getChildCategories(int parentid) {
        List<CategoriesFull> cate_array = new ArrayList();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORIES+" WHERE "+TABLE_CATEGORIES_PARENT_ID+"="+parentid;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if(cursor!=null) {
            if (cursor.moveToFirst()) {
                do {
                    CategoriesFull categ = new CategoriesFull();

                    categ.setCategories_id(cursor.getInt(0));
                    categ.setName(cursor.getString(1));
                    categ.setParent_id(cursor.getInt(2));

                /*String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_CATEGORIES + "("
                            + TABLE_CATEGORIES_CATEGORIES_ID + " INTEGER PRIMARY KEY," + TABLE_CATEGORIES_NAME + " TEXT,"
                            + TABLE_CATEGORIES_PARENT_ID + " INTEGER" + ")";*/
                    cate_array.add(categ);
                } while (cursor.moveToNext());
            }
        }

        // return contact list
        db.close();
        return cate_array;
    }


    public ArrayList<ProductsFull> getProductList_Display(int type,CategoriesFull cat) {
        ArrayList<ProductsFull> prod_array = new ArrayList();
        // Select All Query
        String selectQuery="";
        if(type==0){
            selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS +" WHERE "
                    +TABLE_CATEGORIES_CATEGORIES_ID+"="+cat.getCategories_id()+" ORDER BY "+TABLE_PRODUCT_VIEW_COUNT+" DESC";
        }
        if(type==1){
            selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS +" WHERE "
                    +TABLE_PRODUCT_VIEW_COUNT+">0 ORDER BY "+TABLE_PRODUCT_VIEW_COUNT+" DESC";
        }
        else if(type==2){
            selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS +" WHERE "
                    +TABLE_PRODUCT_ORDER_COUNT+">0 ORDER BY "+TABLE_PRODUCT_ORDER_COUNT+" DESC";
        }
        else if(type==3){
            selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS +" WHERE "
                    +TABLE_PRODUCT_SHARES+">0 ORDER BY "+TABLE_PRODUCT_SHARES+" DESC";
        }


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ProductsFull prod = new ProductsFull();

                prod.setCategory_id(cursor.getInt(0));
                prod.setProduct_id(cursor.getInt(1));
                prod.setVariant_id(cursor.getInt(2));
                prod.setName(cursor.getString(3));
                prod.setDate_added(cursor.getString(4));
                prod.setTax_name(cursor.getString(5));
                prod.setTax_value(cursor.getInt(6));
                prod.setColor(cursor.getString(7));
                prod.setSize(cursor.getInt(8));
                prod.setPrice(cursor.getInt(9));
                prod.setView_count(cursor.getInt(10));
                prod.setOrder_count(cursor.getInt(11));
                prod.setShare(cursor.getInt(12));
                prod_array.add(prod);
            } while (cursor.moveToNext());
        }

        // return contact list
        db.close();
        return prod_array;
    }


    public ArrayList<ProductsFull> getProductList_sorted(int type,CategoriesFull cat) {
        ArrayList<ProductsFull> prod_array = new ArrayList();
        // Select All Query
        String selectQuery="";
        if(type==1){
            selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS +" WHERE "
                    +TABLE_CATEGORIES_CATEGORIES_ID+"="+cat.getCategories_id()+" ORDER BY "+TABLE_PRODUCT_VIEW_COUNT+" DESC";
        }
        else if(type==2){
            selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS +" WHERE "
                    +TABLE_CATEGORIES_CATEGORIES_ID+"="+cat.getCategories_id()+" ORDER BY "+TABLE_PRODUCT_PRICE+" ASC";
        }
        else if(type==3){
            selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS +" WHERE "
                    +TABLE_CATEGORIES_CATEGORIES_ID+"="+cat.getCategories_id()+" ORDER BY "+TABLE_PRODUCT_PRICE+" DESC";
        }
        else if(type==4){
            selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS +" WHERE "
                    +TABLE_CATEGORIES_CATEGORIES_ID+"="+cat.getCategories_id()+" ORDER BY "+TABLE_PRODUCT_ORDER_COUNT+" DESC";
        }
        else if(type==5){
            selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS +" WHERE "
                    +TABLE_CATEGORIES_CATEGORIES_ID+"="+cat.getCategories_id()+" ORDER BY "+TABLE_PRODUCT_SHARES+" DESC";
        }


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ProductsFull prod = new ProductsFull();

                prod.setCategory_id(cursor.getInt(0));
                prod.setProduct_id(cursor.getInt(1));
                prod.setVariant_id(cursor.getInt(2));
                prod.setName(cursor.getString(3));
                prod.setDate_added(cursor.getString(4));
                prod.setTax_name(cursor.getString(5));
                prod.setTax_value(cursor.getInt(6));
                prod.setColor(cursor.getString(7));
                prod.setSize(cursor.getInt(8));
                prod.setPrice(cursor.getInt(9));
                prod.setView_count(cursor.getInt(10));
                prod.setOrder_count(cursor.getInt(11));
                prod.setShare(cursor.getInt(12));
                prod_array.add(prod);
            } while (cursor.moveToNext());
        }

        // return contact list
        db.close();
        return prod_array;
    }


    //Delete all content first
    void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_CATEGORIES);
        db.execSQL("delete from "+ TABLE_PRODUCTS);
        db.close();
    }


    /*void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact Name
        values.put(KEY_PH_NO, contact.getPhoneNumber()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return contact;
    }

    // Getting All Contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }

    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }*/
}
