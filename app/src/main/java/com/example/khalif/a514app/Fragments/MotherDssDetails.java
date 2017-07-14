package com.example.khalif.a514app.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.khalif.a514app.Constants.Constant;
import com.example.khalif.a514app.Models.DraftModel;
import com.example.khalif.a514app.Models.MotherModel;
import com.example.khalif.a514app.Models.PostpartumQModel;
import com.example.khalif.a514app.Models.PregnantQModel;
import com.example.khalif.a514app.R;
import com.example.khalif.a514app.Utils.ExpandablePanel;
import com.example.khalif.a514app.Utils.I_fragmentlistener;
import com.google.gson.Gson;
import com.rengwuxian.materialedittext.MaterialEditText;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MotherDssDetails extends Fragment implements ExpandablePanel.OnExpandListener {

    ExpandablePanel panelSectionFive, panelSectionSix;

    MotherModel clientModel;
    private MaterialEditText mobile, name, age;
    I_fragmentlistener<MotherModel, PostpartumQModel, PregnantQModel, DraftModel> complete_listener;
    SharedPreferences sharedPreferences;
    private Spinner patientStatus;
    private Spinner subLocation;
    private Spinner communityUnit;

    public MotherDssDetails() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mother_details, container, false);

        clientModel = new MotherModel();

        mobile = (MaterialEditText) rootView.findViewById(R.id.txtNumber);
        name = (MaterialEditText) rootView.findViewById(R.id.txtName);
        age = (MaterialEditText) rootView.findViewById(R.id.txtAgeId);
        patientStatus = (Spinner) rootView.findViewById(R.id.category);
        subLocation = (Spinner) rootView.findViewById(R.id.sub_location);
        communityUnit = (Spinner) rootView.findViewById(R.id.community_unit);

        panelSectionFive = (ExpandablePanel) rootView.findViewById(R.id.panelSectionFive);
        panelSectionSix = (ExpandablePanel) rootView.findViewById(R.id.panelSectionSix);

        sharedPreferences = getActivity().getSharedPreferences("mother_details", Context.MODE_PRIVATE);
        String populate = sharedPreferences.getString(Constant.DRAFT_MOTHER, null);

        if(populate != null && complete_listener.getBol()){
            populateFields(populate);
        }

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

    @Override
    public void onExpand(View handle, View content) {
        TextView header = (TextView) handle;
        header.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_less_white_18dp, 0);
    }

    @Override
    public void onCollapse(View handle, View content) {
        TextView header = (TextView) handle;
        header.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more_white_18dp, 0);
    }

    public void populateFields(String populate) {

        Gson gson = new Gson();

        MotherModel motherModel = gson.fromJson(populate, MotherModel.class);
        name.setText(motherModel.getClient_name());
        mobile.setText(motherModel.getClient_phone());
        age.setText(motherModel.getClient_age());
        patientStatus.setSelection(motherModel.getClient_status());

        //sharedPreferences
    }

    public void doTask(String rand) {

        clientModel.setClient_name(name.getText().toString());
        clientModel.setClient_mobile(mobile.getText().toString());
        clientModel.setClient_age(age.getText().toString());
        clientModel.setClient_latitude("37.83485");
        clientModel.setClient_longitude("0.783496");
        clientModel.setClient_status((int) patientStatus.getSelectedItemId());
        clientModel.setClient_rand(rand);

        complete_listener.onData(clientModel);
    }

    public void showDescription(View v) {
        //Toast.makeText(getApplicationContext(), v.getContentDescription().toString(), Toast.LENGTH_SHORT).show();
        new SweetAlertDialog(getActivity(), SweetAlertDialog.NORMAL_TYPE)
                .setTitleText("Details")
                .setContentText(v.getContentDescription().toString())
                .show();
    }
}
