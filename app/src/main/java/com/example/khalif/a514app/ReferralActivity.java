package com.example.khalif.a514app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.khalif.a514app.Constants.Constant;
import com.example.khalif.a514app.Databases.BabyDetailsDb;
import com.example.khalif.a514app.Databases.MotherDetailsDb;
import com.example.khalif.a514app.Fragments.BabyDssDetails;
import com.example.khalif.a514app.Models.BabyModel;
import com.example.khalif.a514app.Models.MotherModel;
import com.google.gson.Gson;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.UUID;

public class ReferralActivity extends AppCompatActivity {

    MaterialEditText txtformSerial,txtPatientID,txtPatientName,txtPatientAge,txtReferralFacility,txtTreatment,txtComments,specific;
    Spinner spnSublocation,spnVillage,category,spnSublocation2,spnLinkFacility,spnReferralFacility;
    RadioGroup rgGender,rgAge;
    CheckBox toStartANC, followUpANC, delivery, postpartumFmPlanning, postpartumService,
            growthMonitoring, growthMonitoringOther,immunization,familyPlaning,growthMonitor,immunize,general_services;
    Button btnRefer;

    BabyDetailsDb babyDetailsDb;


    private int key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referral);

        SharedPreferences sharedPreferences = getSharedPreferences(Constant.ACTIVITY,Context.MODE_PRIVATE);
        key = sharedPreferences.getInt(Constant.KEY_ACTIVITY, 0);

        btnRefer = (Button)findViewById(R.id.btnRefer);

        specific = (MaterialEditText)findViewById(R.id.specific);

        txtformSerial=(MaterialEditText)findViewById(R.id.txtformSerial);
        txtPatientID = (MaterialEditText)findViewById(R.id.txtPatientID);
        txtPatientName = (MaterialEditText)findViewById(R.id.txtPatientName);
        txtPatientAge = (MaterialEditText)findViewById(R.id.txtPatientAge);
        txtReferralFacility = (MaterialEditText)findViewById(R.id.txtReferralFacility);
        txtTreatment = (MaterialEditText)findViewById(R.id.txtTreatment);
        txtComments = (MaterialEditText)findViewById(R.id.txtComments);

        spnSublocation = (Spinner)findViewById(R.id.spnSublocation);
        spnVillage = (Spinner)findViewById(R.id.spnVillage);
        category = (Spinner)findViewById(R.id.category);
        spnSublocation2 = (Spinner)findViewById(R.id.spnSublocation2);
        spnLinkFacility = (Spinner)findViewById(R.id.spnLinkFacility);
        spnReferralFacility = (Spinner)findViewById(R.id.spnReferralFacility);
        rgGender = (RadioGroup)findViewById(R.id.rgGender);
        rgAge = (RadioGroup)findViewById(R.id.rgAge);
        toStartANC = (CheckBox)findViewById(R.id.toStartANC);
        followUpANC = (CheckBox)findViewById(R.id.followUpANC);
        delivery = (CheckBox)findViewById(R.id.delivery);
        postpartumFmPlanning = (CheckBox)findViewById(R.id.postpartumFmPlanning);
        postpartumService = (CheckBox)findViewById(R.id.postpartumService);
        growthMonitoring = (CheckBox)findViewById(R.id.growthMonitoring);
        growthMonitoringOther = (CheckBox)findViewById(R.id.growthMonitoringOther);
        immunization = (CheckBox)findViewById(R.id.immunization);
        familyPlaning = (CheckBox)findViewById(R.id.familyPlaning);
        growthMonitor = (CheckBox)findViewById(R.id.growthMonitor);
        immunize = (CheckBox)findViewById(R.id.immunize);
        general_services = (CheckBox)findViewById(R.id.general_services);


        switch (key){
            case  1:
                babyDetailsDb = BabyDetailsDb.getInstance(getApplicationContext());
                BabyModel babyModel = babyDetailsDb.getData();
                txtformSerial.setText(babyModel.getClient_rand());
                txtPatientName.setText(babyModel.getClient_bName());
                txtPatientAge.setText(babyModel.getClient_age());
                rgGender.check(babyModel.getClient_gender());

                break;
            case 2:
                babyDetailsDb = BabyDetailsDb.getInstance(getApplicationContext());
                BabyModel babyModel2 = babyDetailsDb.getData();
                txtformSerial.setText(babyModel2.getClient_rand());
                txtPatientName.setText(babyModel2.getClient_bName());
                txtPatientAge.setText(babyModel2.getClient_age());
                rgGender.check(babyModel2.getClient_gender());
                break;
        }

    }

}
