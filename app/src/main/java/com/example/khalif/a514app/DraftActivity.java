package com.example.khalif.a514app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.khalif.a514app.Adapter.MotherDraftAdapter;
import com.example.khalif.a514app.Databases.MotherDetailsDb;
import com.example.khalif.a514app.Models.DraftModel;
import com.example.khalif.a514app.Models.MotherModel;
import com.example.khalif.a514app.Models.PostpartumQModel;
import com.example.khalif.a514app.Models.PregnantQModel;
import com.example.khalif.a514app.Utils.I_fragmentlistener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DraftActivity extends AppCompatActivity {

    ArrayList<MotherModel> motherModels;
    MotherDraftAdapter draftsAdapter;
    ListView listView;
    Context context;
    MotherModel motherModel;
    JSONObject json;
    JSONArray jsonArray;
    I_fragmentlistener<MotherModel, PostpartumQModel, PregnantQModel, DraftModel> listenenr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draft);

        listView = (ListView) findViewById(R.id.mlist);
        MotherDetailsDb motherDetailsDb = MotherDetailsDb.getInstance(getApplicationContext());

        motherModel = new MotherModel();
        motherModels = new ArrayList<>();

        jsonArray = new JSONArray();

        try {
            jsonArray = motherDetailsDb.getDataJson();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Toast.makeText(getApplicationContext(), jsonArray.toString(), Toast.LENGTH_SHORT).show();

        draftsAdapter = new MotherDraftAdapter(this, motherModels);
        listView.setAdapter(draftsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MotherDssActivity.class);
                startActivity(intent);
                listenenr.getBol();
            }
        });
    }

    public void showDescription(View v) {
        //Toast.makeText(getApplicationContext(), v.getContentDescription().toString(), Toast.LENGTH_SHORT).show();
        new SweetAlertDialog(getApplicationContext(), SweetAlertDialog.NORMAL_TYPE)
                .setTitleText("Details")
                .setContentText(v.getContentDescription().toString())
                .show();
    }
}
