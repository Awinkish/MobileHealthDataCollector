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
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.khalif.a514app.Models.BabyModel;
import com.example.khalif.a514app.Models.DraftModel;
import com.example.khalif.a514app.Models.MotherModel;
import com.example.khalif.a514app.Models.PostpartumQModel;
import com.example.khalif.a514app.Models.PregnantQModel;
import com.example.khalif.a514app.R;
import com.example.khalif.a514app.Utils.I_fragmentlistener;
import com.rengwuxian.materialedittext.MaterialEditText;


public class BabyDetails extends Fragment {

    SharedPreferences sharedPreferences;
    private MaterialEditText mName,bName, age, weight, mobile;
    private RadioGroup rGender;
    private Spinner days;
    private Spinner spnSubLoc;
    private Spinner community_unit;


    private OnFragmentInteractionListener mListener;

    I_fragmentlistener<, PostpartumQModel, PregnantQModel, DraftModel> complete_listener;

    public BabyDetails() {
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



        return super.onCreateView(inflater, container, savedInstanceState);
    }

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
