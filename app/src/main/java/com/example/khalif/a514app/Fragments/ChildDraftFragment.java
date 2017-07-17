package com.example.khalif.a514app.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.khalif.a514app.Adapter.ChildDraftAdapter;
import com.example.khalif.a514app.Adapter.MotherDraftAdapter;
import com.example.khalif.a514app.Constants.Constant;
import com.example.khalif.a514app.Databases.BabyDetailsDb;
import com.example.khalif.a514app.Databases.MotherDetailsDb;
import com.example.khalif.a514app.Models.BabyModel;
import com.example.khalif.a514app.Models.DraftModel;
import com.example.khalif.a514app.Models.MotherModel;
import com.example.khalif.a514app.Models.PostpartumQModel;
import com.example.khalif.a514app.Models.PregnantQModel;
import com.example.khalif.a514app.R;
import com.example.khalif.a514app.Utils.I_fragmentlistener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Khalif on 7/13/2017.
 */
public class ChildDraftFragment extends Fragment {

    ArrayList<BabyModel> babyList;
    ChildDraftAdapter draftsAdapter;
    ListView listView;
    BabyModel babyModel;
    JSONObject json;
    JSONArray jsonArray;
    I_fragmentlistener<MotherModel, PostpartumQModel, PregnantQModel, DraftModel> complete_listener;
    MotherDssDetails dss;
    Gson gson;
    String search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_draft, container, false);

        listView = (ListView) rootView.findViewById(R.id.mlist);
        BabyDetailsDb babyDetailsDb = BabyDetailsDb.getInstance(getActivity());

        gson = new Gson();
        babyModel = new BabyModel();
        babyList = new ArrayList<>();

        jsonArray = new JSONArray();

        try {
            jsonArray = babyDetailsDb.getDataJson();

            babyList = BabyModel.makeArrayList(jsonArray);

            Toast.makeText(getActivity(), babyList.toString(), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        draftsAdapter = new ChildDraftAdapter(getActivity(), babyList);
        listView.setAdapter(draftsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                search = babyList.get(position).getClient_rand();

                Toast.makeText(getActivity(), search, Toast.LENGTH_SHORT).show();
                BabyDetailsDb clientDb = BabyDetailsDb.getInstance(getActivity());
                clientDb.getWritableDatabase();

                if (clientDb.getRowCount() > 0) {
                    babyModel = clientDb.getSpecificData(search);

                    if (babyModel != null) {
                        complete_listener.isDraft(true);
                    }
                }
                change_fragment();

            }
        });


        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            complete_listener = (I_fragmentlistener<MotherModel, PostpartumQModel, PregnantQModel, DraftModel>) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement onSomeEventListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void doTask(String rand) {

    }

    public void change_fragment() {
        Fragment fragchange = null;

        fragchange = new BabyDssDetails();

        // Replace the current fragment
        if (fragchange != null) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_view, fragchange).commit();
        }
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("baby_details", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(Constant.DRAFT_BABY, gson.toJson(babyModel, BabyModel.class));
        editor.putString(Constant.DRAFT_KEY, search);
        editor.apply();

    }
}
