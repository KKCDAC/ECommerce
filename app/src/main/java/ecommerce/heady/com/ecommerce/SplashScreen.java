package ecommerce.heady.com.ecommerce;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ecommerce.heady.com.ecommerce.classes.Categories;
import ecommerce.heady.com.ecommerce.classes.Rankings;

public class SplashScreen extends Activity {

    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        RequestQueue queue = Volley.newRequestQueue(this);
        String result_json_object="";

        String url="https://stark-spire-93433.herokuapp.com/json";
        db=new DatabaseHandler(SplashScreen.this);

        /*final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();*/

        final RequestQueue requestQueue = Volley.newRequestQueue(SplashScreen.this);

        // Initialize a new StringRequest
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Do something with response string
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray_categories= (JSONArray) jsonObject.get("categories");
                            JSONArray jsonArray_ranking=(JSONArray)jsonObject.get("rankings");
                            Log.e("array length",""+jsonArray_categories.length()+" "+jsonArray_ranking.length());
                            db.deleteAll();
                            db.addFirst();
                            for (int i=0;i<jsonArray_categories.length();i++){
                                Gson g = new Gson();
                                Categories p = g.fromJson(jsonArray_categories.get(i).toString(), Categories.class);
                                db.addCategories(p);
                                Log.e("to string ",p.toString());
                            }
                            for (int i=0;i<jsonArray_categories.length();i++){
                                Gson g = new Gson();
                                Categories p = g.fromJson(jsonArray_categories.get(i).toString(), Categories.class);
                                db.addChild(p);

                            }
                            for (int i=0;i<jsonArray_ranking.length();i++){
                                Gson g = new Gson();
                                Rankings p = g.fromJson(jsonArray_ranking.get(i).toString(), Rankings.class);
                                if(i==0){
                                    db.addViewCounts(p);
                                }
                                else if(i==1){
                                    db.addOrderCounts(p);
                                }
                                else if(i==2){
                                    db.addShareCounts(p);
                                }

                            }

                            Intent intent=new Intent(SplashScreen.this,HomeActivity.class);
                            startActivity(intent);
                            finish();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("Result",response);

                        requestQueue.stop();
                        //progressDialog.hide();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when get error
                        requestQueue.stop();
                        //progressDialog.hide();
                    }
                }
        );

        // Add StringRequest to the RequestQueue
        requestQueue.add(stringRequest);

    }
}
