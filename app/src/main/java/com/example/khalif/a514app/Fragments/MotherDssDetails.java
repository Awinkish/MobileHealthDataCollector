package com.example.khalif.a514app.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khalif.a514app.Databases.MotherDetailsDb;
import com.example.khalif.a514app.Models.PostpartumQModel;
import com.example.khalif.a514app.Models.MotherModel;
import com.example.khalif.a514app.Models.PregnantQModel;
import com.example.khalif.a514app.R;
import com.example.khalif.a514app.Utils.ExpandablePanel;
import com.example.khalif.a514app.Utils.I_fragmentlistener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.StringTokenizer;
import java.util.UUID;

public class MotherDssDetails extends Fragment implements ExpandablePanel.OnExpandListener {

    ExpandablePanel panelSectionFive, panelSectionSix;

    String rand;
    MotherModel clientModel;
    private MaterialEditText mobile, name, age;
    I_fragmentlistener<MotherModel, PostpartumQModel, PregnantQModel> complete_listener;

    public MotherDssDetails() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mother_details, container, false);

        clientModel = new MotherModel();

        rand = UUID.randomUUID().toString();

        mobile = (MaterialEditText) rootView.findViewById(R.id.txtNumber);
        name = (MaterialEditText) rootView.findViewById(R.id.txtName);
        age = (MaterialEditText) rootView.findViewById(R.id.txtAgeId);

        panelSectionFive = (ExpandablePanel) rootView.findViewById(R.id.panelSectionFive);
        panelSectionSix = (ExpandablePanel) rootView.findViewById(R.id.panelSectionSix);

        MotherDetailsDb clientDb = MotherDetailsDb.getInstance(getActivity());
        clientDb.getWritableDatabase();

        if (clientDb.getRowCount() > 0) {

            clientModel = clientDb.getData();
            Toast.makeText(getActivity(), clientModel.toString(), Toast.LENGTH_SHORT).show();
            clientModel = clientDb.getData();
            name.setText(clientModel.getClient_name());
            mobile.setText(clientModel.getClient_phone());
            age.setText(clientModel.getClient_age());
        }

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            complete_listener = (I_fragmentlistener<MotherModel, PostpartumQModel, PregnantQModel>) getActivity();
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

    public void doTask(String rand){

        clientModel.setClient_name(name.getText().toString());
        clientModel.setClient_mobile(mobile.getText().toString());
        clientModel.setClient_age(age.getText().toString());
        clientModel.setClient_latitude("37.83485");
        clientModel.setClient_longitude("0.783496");
        clientModel.setClient_status("1");
        clientModel.setRand(rand);

        complete_listener.onData(clientModel);
    }

}
