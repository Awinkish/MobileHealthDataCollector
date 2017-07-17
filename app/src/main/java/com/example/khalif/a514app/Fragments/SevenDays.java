package com.example.khalif.a514app.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.khalif.a514app.BabyDssActivity;
import com.example.khalif.a514app.Constants.Constant;
import com.example.khalif.a514app.Databases.SevenDaysDb;
import com.example.khalif.a514app.Models.BabyModel;
import com.example.khalif.a514app.Models.DraftModel;
import com.example.khalif.a514app.Models.SevenQModel;
import com.example.khalif.a514app.Models.TwentyEightQModel;
import com.example.khalif.a514app.R;
import com.example.khalif.a514app.Utils.ExpandablePanel;
import com.example.khalif.a514app.Utils.I_fragmentlistener;


public class SevenDays extends Fragment implements ExpandablePanel.OnExpandListener {

    public SevenDays() {

    }
    SevenQModel sevenQModel;
    ExpandablePanel panelSectionOne,panelBreastfeedingHistory;
    Spinner not_breathing,yellow_skin,feet_blue,not_sucking,tiredness,always_sleepy,fast_breathing,
            chest_drawing,loose_weight,yellow_soles,start_feeding,fed_birth,child_fed;

    private I_fragmentlistener<BabyModel,SevenQModel,TwentyEightQModel,DraftModel> i_fragmentlistener;
    private SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_seven_days,container,false);

        sevenQModel = new SevenQModel();
        panelSectionOne = (ExpandablePanel)view.findViewById(R.id.panelSectionOne);
        panelBreastfeedingHistory = (ExpandablePanel)view.findViewById(R.id.panelBreastfeedingHistory);

        not_breathing = (Spinner)view.findViewById(R.id.not_breathing);
        yellow_skin = (Spinner)view.findViewById(R.id.yellow_skin);
        feet_blue = (Spinner)view.findViewById(R.id.feet_blue);
        not_sucking = (Spinner)view.findViewById(R.id.not_sucking);
        tiredness = (Spinner)view.findViewById(R.id.tiredness);
        always_sleepy = (Spinner)view.findViewById(R.id.always_sleepy);
        fast_breathing = (Spinner)view.findViewById(R.id.fast_breathing);
        chest_drawing = (Spinner)view.findViewById(R.id.chest_drawing);
        loose_weight = (Spinner)view.findViewById(R.id.loose_weight);
        yellow_soles = (Spinner)view.findViewById(R.id.yellow_soles);
        start_feeding = (Spinner)view.findViewById(R.id.start_feeding);
        fed_birth = (Spinner)view.findViewById(R.id.fed_birth);
        child_fed = (Spinner)view.findViewById(R.id.child_fed);

        panelSectionOne.setOnExpandListener(this);
        panelBreastfeedingHistory.setOnExpandListener(this);

        //Create an instance of SevenDaysDb
        SevenDaysDb sevenDaysDb = SevenDaysDb.getInstance(getActivity());
        sevenDaysDb.getWritableDatabase();

        sharedPreferences = getActivity().getSharedPreferences("seven days",Context.MODE_PRIVATE);
        String search = sharedPreferences.getString(Constant.DRAFT_KEY,null);

        if(sevenDaysDb.getRowCount()>0 && i_fragmentlistener.getBol()){
            sevenQModel = sevenDaysDb.getSpecificDetails(search);

            not_breathing.setSelection((int)sevenQModel.getNot_breathing());
            yellow_skin.setSelection((int)sevenQModel.getYellow_skin());
            feet_blue.setSelection((int)sevenQModel.getFeet_blue());
            not_sucking.setSelection((int)sevenQModel.getNot_sucking());
            tiredness.setSelection((int)sevenQModel.getTiredness());
            always_sleepy.setSelection((int)sevenQModel.getAlways_sleepy());
            fast_breathing.setSelection((int)sevenQModel.getFast_breathing());
            chest_drawing.setSelection((int)sevenQModel.getChest_drawing());
            loose_weight.setSelection((int)sevenQModel.getLoose_weight());
            yellow_soles.setSelection((int)sevenQModel.getYellow_soles());
            start_feeding.setSelection((int)sevenQModel.getStart_feeding());
            fed_birth.setSelection((int)sevenQModel.getFed_birth());
            child_fed.setSelection((int)sevenQModel.getChild_fed());
        }


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            i_fragmentlistener = (I_fragmentlistener<BabyModel,SevenQModel,TwentyEightQModel,DraftModel>)getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString() + " must implement onSomeEventListener");
        }
    }

    @Override
    public void onExpand(View handle, View content) {
        TextView header = (TextView) handle;
        header.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_expand_more_white_18dp,0);

    }

    @Override
    public void onCollapse(View handle, View content) {
        TextView header = (TextView) handle;
        header.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_expand_less_white_18dp,0);
    }

    public void setClientDetails(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(BabyDssActivity.UID, Context.MODE_PRIVATE);
        String rand = sharedPreferences.getString(BabyDssActivity.RAND_ID,null);

        sevenQModel.setNot_breathing(not_breathing.getSelectedItemId());
        sevenQModel.setYellow_skin(yellow_skin.getSelectedItemId());
        sevenQModel.setFeet_blue(feet_blue.getSelectedItemId());
        sevenQModel.setNot_sucking(not_sucking.getSelectedItemId());
        sevenQModel.setTiredness(tiredness.getSelectedItemId());
        sevenQModel.setAlways_sleepy(always_sleepy.getSelectedItemId());
        sevenQModel.setFast_breathing(fast_breathing.getSelectedItemId());
        sevenQModel.setChest_drawing(chest_drawing.getSelectedItemId());
        sevenQModel.setLoose_weight(loose_weight.getSelectedItemId());
        sevenQModel.setYellow_soles(yellow_soles.getSelectedItemId());
        sevenQModel.setStart_feeding(start_feeding.getSelectedItemId());
        sevenQModel.setFed_birth(fed_birth.getSelectedItemId());
        sevenQModel.setChild_fed(child_fed.getSelectedItemId());

        i_fragmentlistener.ansQuestion(sevenQModel);

    }


}
