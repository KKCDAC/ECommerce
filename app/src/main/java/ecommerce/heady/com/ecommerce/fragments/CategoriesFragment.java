package ecommerce.heady.com.ecommerce.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ecommerce.heady.com.ecommerce.adapter.CustomListAdapter;
import ecommerce.heady.com.ecommerce.DatabaseHandler;
import ecommerce.heady.com.ecommerce.HomeActivity;
import ecommerce.heady.com.ecommerce.ProductDisplay;
import ecommerce.heady.com.ecommerce.R;
import ecommerce.heady.com.ecommerce.classes.CategoriesFull;
import ecommerce.heady.com.ecommerce.classes.PojoStorage;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CategoriesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CategoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriesFragment extends Fragment {

    static Context context;
    static CategoriesFull title;

    CustomListAdapter listAdapter;
    ExpandableListView expListView;
    List<CategoriesFull> listDataHeader;
    HashMap<String, List<CategoriesFull>> listDataChild=new HashMap<>();

    DatabaseHandler db;

    private OnFragmentInteractionListener mListener;

    public CategoriesFragment() {
        // Required empty public constructor
    }


    public static CategoriesFragment newInstance(Context c,CategoriesFull cf) {
        CategoriesFragment fragment = new CategoriesFragment();
        context=c;
        title=cf;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_categories, container, false);
        ((HomeActivity) getActivity()).setActionBarTitle(title.getName());
        expListView=v.findViewById(R.id.expandableListView);

        db=new DatabaseHandler(context);

        listDataHeader=db.getChildCategories(title.getCategories_id());
        Log.e("size",""+listDataHeader.size());
        for(int i=0;i<listDataHeader.size();i++){
            List<CategoriesFull> list=new ArrayList<>();
            list=db.getChildCategories(listDataHeader.get(i).getCategories_id());
            listDataChild.put(listDataHeader.get(i).getName(),list);
        }

        listAdapter = new CustomListAdapter(context, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                PojoStorage.categories=listDataChild.get(listDataHeader.get(i).toString()).get(i1);
                PojoStorage.type=0;
                Intent intent=new Intent(context, ProductDisplay.class);
                startActivity(intent);

                Toast.makeText(context, " "+listDataHeader.get(i).toString()+" "+listDataChild.get(listDataHeader.get(i).toString()).get(i1).toString(), Toast.LENGTH_SHORT).show();
                
                return false;
            }
        });



        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

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
