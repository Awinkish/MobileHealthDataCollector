package com.example.khalif.a514app.Fragments;

/**
 * Created by ArwinKish on 7/11/2017.
 */
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
import com.example.khalif.a514app.Databases.PostpartumDssDb;
import com.example.khalif.a514app.Models.DraftModel;
import com.example.khalif.a514app.Models.MotherModel;
import com.example.khalif.a514app.Models.PostpartumQModel;
import com.example.khalif.a514app.Models.PregnantQModel;
import com.example.khalif.a514app.MotherDssActivity;
import com.example.khalif.a514app.R;
import com.example.khalif.a514app.Utils.ExpandablePanel;
import com.example.khalif.a514app.Utils.I_fragmentlistener;

public class PostpartumDss extends Fragment implements ExpandablePanel.OnExpandListener {

    ExpandablePanel panelSectionFive, panelSectionSix, panelPregnancyHistory, panelPostpartumHistory;
    Spinner a_dX, b_aX, f_cX,f_nX, l_pX, l_cX, h_bX, h_dX, b_vX, d_bX,
            p_uX, p_fX, w_bX, a_lX, p_sX, f_sX, f_sP, m_sP, a_sV;

    PostpartumQModel clientDetails;
    private I_fragmentlistener<MotherModel, PostpartumQModel, PregnantQModel, DraftModel> complete_listener;
    private SharedPreferences sharedPreferences;

    public PostpartumDss(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_postpartum, container, false);

        clientDetails = new PostpartumQModel();

        a_dX = ((Spinner) rootView.findViewById(R.id.abdominal_discharge));
        b_aX = ((Spinner) rootView.findViewById(R.id.bad_abdominal));
        f_cX = ((Spinner) rootView.findViewById(R.id.fever_chills));
        f_nX = ((Spinner) rootView.findViewById(R.id.fever_nochills));
        h_bX = ((Spinner) rootView.findViewById(R.id.high_blood));
        l_pX = ((Spinner) rootView.findViewById(R.id.labor_pains));
        l_cX = ((Spinner) rootView.findViewById(R.id.loss_consciousness));
        h_dX = ((Spinner) rootView.findViewById(R.id.head_dizzy));
        b_vX = ((Spinner) rootView.findViewById(R.id.blurred_visionPm));
        d_bX = ((Spinner) rootView.findViewById(R.id.diff_breathing));
        p_uX = ((Spinner) rootView.findViewById(R.id.pass_urine));
        p_fX = ((Spinner) rootView.findViewById(R.id.palm_feet));
        w_bX = ((Spinner) rootView.findViewById(R.id.water_break));
        a_lX = ((Spinner) rootView.findViewById(R.id.arms_legs));
        p_sX = ((Spinner) rootView.findViewById(R.id.placenta));
        f_sX = ((Spinner) rootView.findViewById(R.id.foul_smell));

        f_sP = ((Spinner) rootView.findViewById(R.id.first_pregnancy));
        m_sP = ((Spinner) rootView.findViewById(R.id.months_pregnant));
        a_sV = ((Spinner) rootView.findViewById(R.id.anc_visit));

        panelSectionFive = (ExpandablePanel) rootView.findViewById(R.id.panelSectionFive);
        panelSectionSix = (ExpandablePanel) rootView.findViewById(R.id.panelSectionSix);
        panelPostpartumHistory = (ExpandablePanel) rootView.findViewById(R.id.panelPostpartumHistory);
        panelPregnancyHistory = (ExpandablePanel) rootView.findViewById(R.id.panelPregnancyHistory);

        //panelSectionFive.setOnExpandListener(this);
        panelSectionFive.setOnExpandListener(this);
        panelSectionSix.setOnExpandListener(this);

        PostpartumDssDb dssDb = PostpartumDssDb.getInstance(getActivity());
        dssDb.getWritableDatabase();

        sharedPreferences = getActivity().getSharedPreferences("mother_details", Context.MODE_PRIVATE);
        String search = sharedPreferences.getString(Constant.DRAFT_KEY, null);

        if (dssDb.getRowCount() > 0 && complete_listener.getBol()) {

            clientDetails = dssDb.getSpecificData(search);

            a_dX.setSelection((int) clientDetails.get_a_dX());
            b_aX.setSelection((int) clientDetails.get_b_aX());
            f_cX.setSelection((int) clientDetails.get_f_cX());
            f_nX.setSelection((int) clientDetails.get_f_nX());
            h_bX.setSelection((int) clientDetails.get_h_bX());
            l_pX.setSelection((int) clientDetails.get_l_pX());
            l_cX.setSelection((int) clientDetails.get_l_cX());
            h_dX.setSelection((int) clientDetails.get_h_dX());
            b_vX.setSelection((int) clientDetails.get_b_vX());
            d_bX.setSelection((int) clientDetails.get_d_bX());
            p_uX.setSelection((int) clientDetails.get_p_uX());
            p_fX.setSelection((int) clientDetails.get_p_fX());
            w_bX.setSelection((int) clientDetails.get_w_bX());
            a_lX.setSelection((int) clientDetails.get_a_lX());
            p_sX.setSelection((int) clientDetails.get_p_sX());
            f_sX.setSelection((int) clientDetails.get_f_sX());

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
        header.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.ic_expand_less_white_18dp,0);
    }

    @Override
    public void onCollapse(View handle, View content) {
        TextView header = (TextView) handle;
        header.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.ic_expand_more_white_18dp,0);
    }

    public void setClientDetails(){

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(MotherDssActivity.
                UID, Context.MODE_PRIVATE);
        String rand = sharedPreferences.getString(MotherDssActivity.RAND_ID, null);

        clientDetails.set_a_dX(a_dX.getSelectedItemId());
        clientDetails.set_b_aX(b_aX.getSelectedItemId());
        clientDetails.set_f_cX(f_cX.getSelectedItemId());
        clientDetails.set_f_nX(f_nX.getSelectedItemId());
        clientDetails.set_l_pX(l_pX.getSelectedItemId());
        clientDetails.set_l_cX(l_cX.getSelectedItemId());
        clientDetails.set_h_bX(h_bX.getSelectedItemId());
        clientDetails.set_h_dX(h_dX.getSelectedItemId());
        clientDetails.set_b_vX(b_vX.getSelectedItemId());
        clientDetails.set_d_bX(d_bX.getSelectedItemId());
        clientDetails.set_p_uX(p_uX.getSelectedItemId());
        clientDetails.set_p_fX(p_fX.getSelectedItemId());
        clientDetails.set_w_bX(w_bX.getSelectedItemId());
        clientDetails.set_a_lX(a_lX.getSelectedItemId());
        clientDetails.set_p_sX(p_sX.getSelectedItemId());
        clientDetails.set_f_sX(f_sX.getSelectedItemId());
        clientDetails.set_f_sP(f_sP.getSelectedItemId());
        clientDetails.set_m_sP(m_sP.getSelectedItemId());
        clientDetails.set_a_sV(a_sV.getSelectedItemId());
        clientDetails.setRand(rand);

        complete_listener.ansQuestion(clientDetails);

    }
}

