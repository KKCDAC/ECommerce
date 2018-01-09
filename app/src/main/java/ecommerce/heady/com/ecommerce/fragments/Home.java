package ecommerce.heady.com.ecommerce.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ecommerce.heady.com.ecommerce.DatabaseHandler;
import ecommerce.heady.com.ecommerce.HomeActivity;
import ecommerce.heady.com.ecommerce.adapter.ProductAdapter;
import ecommerce.heady.com.ecommerce.ProductDisplay;
import ecommerce.heady.com.ecommerce.R;
import ecommerce.heady.com.ecommerce.classes.PojoStorage;
import ecommerce.heady.com.ecommerce.classes.ProductsFull;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Home.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ArrayList<ProductsFull> ordered_arrray=new ArrayList<>();
    ArrayList<ProductsFull> shared_arrray=new ArrayList<>();
    ArrayList<ProductsFull> viewed_arrray=new ArrayList<>();

    TextView textView_mostViewed,textView_mostOrdered,textView_mostShared;

    DatabaseHandler db;


    static Context context;

    private RecyclerView recyclerView_most_viewed,recyclerView_most_ordered,recyclerView_shared;
    private ProductAdapter productAdapter_viwed,productAdapter_ordered,productAdapter_shared;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(Context c,String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        context=c;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);
        ((HomeActivity) getActivity()).setActionBarTitle("Home");
        db=new DatabaseHandler(context);
        viewed_arrray=db.getLimitedProducts(1);
        ordered_arrray=db.getLimitedProducts(2);
        shared_arrray=db.getLimitedProducts(3);

        recyclerView_most_ordered = (RecyclerView)v.findViewById(R.id.recycler_most_ordered);
        recyclerView_most_viewed = (RecyclerView)v.findViewById(R.id.recycler_most_viewed);
        recyclerView_shared = (RecyclerView)v.findViewById(R.id.recycler_most_shared);

        textView_mostViewed=v.findViewById(R.id.textView_mostViewed);
        textView_mostOrdered=v.findViewById(R.id.textView_mostOrdered);
        textView_mostShared=v.findViewById(R.id.textView_mostShared);

        textView_mostViewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PojoStorage.categories=null;
                PojoStorage.type=1;
                Intent intent=new Intent(context, ProductDisplay.class);
                startActivity(intent);
            }
        });

        textView_mostOrdered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PojoStorage.categories=null;
                PojoStorage.type=2;
                Intent intent=new Intent(context, ProductDisplay.class);
                startActivity(intent);
            }
        });

        textView_mostShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PojoStorage.categories=null;
                PojoStorage.type=3;
                Intent intent=new Intent(context, ProductDisplay.class);
                startActivity(intent);
            }
        });

        productAdapter_ordered = new ProductAdapter(context,ordered_arrray);
        productAdapter_viwed = new ProductAdapter(context,viewed_arrray);
        productAdapter_shared = new ProductAdapter(context,shared_arrray);

       /* RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());*/


        LinearLayoutManager horizontalLayoutManagaer_ordered = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager horizontalLayoutManagaer_shared = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager horizontalLayoutManagaer_viewed = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_most_ordered.setLayoutManager(horizontalLayoutManagaer_ordered);
        recyclerView_most_viewed.setLayoutManager(horizontalLayoutManagaer_shared);
        recyclerView_shared.setLayoutManager(horizontalLayoutManagaer_viewed);
        recyclerView_most_ordered.setAdapter(productAdapter_ordered);
        recyclerView_most_viewed.setAdapter(productAdapter_viwed);
        recyclerView_shared.setAdapter(productAdapter_shared);




        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
