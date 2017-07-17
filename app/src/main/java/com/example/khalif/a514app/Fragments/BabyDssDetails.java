package com.example.khalif.a514app.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.khalif.a514app.Constants.Constant;
import com.example.khalif.a514app.Models.BabyModel;
import com.example.khalif.a514app.Models.DraftModel;
import com.example.khalif.a514app.Models.SevenQModel;
import com.example.khalif.a514app.Models.TwentyEightQModel;
import com.example.khalif.a514app.R;
import com.example.khalif.a514app.Utils.I_fragmentlistener;
import com.google.gson.Gson;
import com.rengwuxian.materialedittext.MaterialEditText;


public class BabyDssDetails extends Fragment {

    SharedPreferences sharedPreferences;
    private MaterialEditText mName,bName, age, weight, mobile;
    private RadioGroup rGender;
    private Spinner days;
    private Spinner spnSubLoc;
    private Spinner community_unit;

    BabyModel babyModel;


    I_fragmentlistener<BabyModel, SevenQModel, TwentyEightQModel, DraftModel> complete_listener;

    public BabyDssDetails() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_baby_details,container,false);
        BabyModel babyModel = new BabyModel();

        mName = (MaterialEditText)rootView.findViewById(R.id.txtMother);
        bName = (MaterialEditText)rootView.findViewById(R.id.txtName);
        age = (MaterialEditText)rootView.findViewById(R.id.txtAgeId);
        weight = (MaterialEditText)rootView.findViewById(R.id.txtBabyWeight);
        mobile = (MaterialEditText)rootView.findViewById(R.id.txtNumber);
        spnSubLoc = (Spinner) rootView.findViewById(R.id.sub_location);
        community_unit = (Spinner) rootView.findViewById(R.id.community_unit);
        days = (Spinner) rootView.findViewById(R.id.spnDays);
        //rGender = (RadioGroup)rootView.findViewById(R.id.rGender);

        sharedPreferences = getActivity().getSharedPreferences("baby_details", Context.MODE_PRIVATE);
        String populate = sharedPreferences.getString(Constant.DRAFT_BABY, null);


        if (populate !=null && complete_listener.getBol()){
            populateFields(populate);
        }
        return rootView;
    }

    public void populateFields(String populate){
        Gson gson = new Gson();

        BabyModel babyModel = gson.fromJson(populate,BabyModel.class);
        mName.setText(babyModel.getClient_mName());
        bName.setText(babyModel.getClient_bName());
        age.setText(babyModel.getClient_age());
        weight.setText(babyModel.getClient_weight());
        mobile.setText(babyModel.getClient_phone());
        //days.setSelection(babyModel.getClient_days(days.getSelectedItemId()));
        rGender.setSelected(true);

    }

    public void doTask(String rand){
        babyModel.setClient_mName(mName.getText().toString());
        babyModel.setClient_bName(bName.getText().toString());
        babyModel.setClient_age(age.getText().toString());
        babyModel.setClient_weight(weight.getText().toString());
        babyModel.setClient_phone(mobile.getText().toString());
        babyModel.setClient_days((int)days.getSelectedItem());
        babyModel.setClient_gender(rGender.isActivated());

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            complete_listener = (I_fragmentlistener<BabyModel,SevenQModel,TwentyEightQModel,DraftModel>) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString() + " must implement onSomeEventListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
