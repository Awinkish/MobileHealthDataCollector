package com.example.khalif.a514app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.khalif.a514app.Databases.MotherDetailsDb;
import com.example.khalif.a514app.Databases.PostpartumDssDb;
import com.example.khalif.a514app.Databases.PregnantDssDb;
import com.example.khalif.a514app.Fragments.MotherDssDetails;
import com.example.khalif.a514app.Fragments.PostpartumDss;
import com.example.khalif.a514app.Fragments.PregnantDss;
import com.example.khalif.a514app.Models.MotherModel;
import com.example.khalif.a514app.Models.PostpartumQModel;
import com.example.khalif.a514app.Models.PregnantQModel;
import com.example.khalif.a514app.Utils.I_fragmentlistener;

import java.util.UUID;

public class MotherDssActivity extends AppCompatActivity implements I_fragmentlistener<MotherModel, PostpartumQModel, PregnantQModel> {
    Button btn_back, btn_next;
    int i = 0;
    private String status;
    private String rand;

    public static final String UID = "uid";
    public static final String RAND_ID = "rand";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a random key for draft storage and access
        rand = UUID.randomUUID().toString();

        SharedPreferences sharedPreferences = getSharedPreferences(UID, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(RAND_ID);
        editor.apply();

        editor.putString(RAND_ID, rand);
        editor.apply();

        // Add the mother details Fragment
        Fragment frag = new MotherDssDetails();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_view, frag).commit();

        btn_back = (Button) findViewById(R.id.btn_back);
        btn_next = (Button) findViewById(R.id.btn_next);
    }

    public void change_fragment(int id) {
        Log.e("Fragment Position", String.valueOf(id));
        Fragment fragchange = null;
        switch (id) {
            case 0:
                fragchange = new MotherDssDetails();
                break;
            case 1:
                if (status.equals("1")) {
                    fragchange = new PregnantDss();
                } else {
                    fragchange = new PostpartumDss();
                }

                break;
        }

        // Replace the current fragment
        if (fragchange != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_view, fragchange).commit();
        }

    }

    @Override
    public void onData(MotherModel clientModel) {
        status = clientModel.getClient_status();
        MotherDetailsDb db = MotherDetailsDb.getInstance(getApplicationContext());
        db.getWritableDatabase();
        db.resetTables();
        db.save(clientModel);
    }

    @Override
    public void ansQuestion(PostpartumQModel clientDetails) {
        PostpartumDssDb db = PostpartumDssDb.getInstance(getApplicationContext());
        db.getWritableDatabase();
        db.resetTables();

        db.save(clientDetails);
    }

    @Override
    public void ansQuestionPregnant(PregnantQModel pregantQModel) {
        PregnantDssDb db = PregnantDssDb.getInstance(getApplicationContext());
        db.getWritableDatabase();
        db.resetTables();
        db.save(pregantQModel);
    }

    public void next(View view) {

        Toast.makeText(getApplicationContext(), rand, Toast.LENGTH_SHORT).show();
        switch (i) {
            case 0:
                // Get values from
                MotherDssDetails fragment = (MotherDssDetails) getSupportFragmentManager().findFragmentById(R.id.fragment_view);
                fragment.doTask(rand);

                break;
            case 1:
                if (status.equals("1")) {
                    PregnantDss fragment2 = (PregnantDss) getSupportFragmentManager().findFragmentById(R.id.fragment_view);
                    fragment2.setClientDetails(rand);
                } else {
                    PostpartumDss fragment1 = (PostpartumDss) getSupportFragmentManager().findFragmentById(R.id.fragment_view);
                    fragment1.setClientDetails();
                }

                break;
        }
        i++;
        change_fragment(i);
    }

    public void back(View view) {

        if (i == 0) {
            Toast.makeText(getApplicationContext(), "Cannot go back", Toast.LENGTH_SHORT).show();
            return;
        }
        if (i == 2) {
            btn_next.setText("Next");
        }
        i = i - 1;
        change_fragment(i);
    }
}
