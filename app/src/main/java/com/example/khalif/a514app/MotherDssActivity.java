package com.example.khalif.a514app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.khalif.a514app.Databases.MotherDetailsDb;
import com.example.khalif.a514app.Databases.PostpartumDssDb;
import com.example.khalif.a514app.Databases.PregnantDssDb;
import com.example.khalif.a514app.Fragments.MotherDraftFragment;
import com.example.khalif.a514app.Fragments.MotherDssDetails;
import com.example.khalif.a514app.Fragments.PostpartumDss;
import com.example.khalif.a514app.Fragments.PregnantDss;
import com.example.khalif.a514app.Models.DraftModel;
import com.example.khalif.a514app.Models.MotherModel;
import com.example.khalif.a514app.Models.PostpartumQModel;
import com.example.khalif.a514app.Models.PregnantQModel;
import com.example.khalif.a514app.Utils.I_fragmentlistener;

import java.util.UUID;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MotherDssActivity extends AppCompatActivity implements I_fragmentlistener<MotherModel, PostpartumQModel, PregnantQModel, DraftModel> {
    Button btn_back, btn_next;
    int i = 0;
    private int status;
    private String rand;

    public static final String UID = "uid";
    public static final String RAND_ID = "rand";
    private boolean bol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a random key for draft storage and access
        rand = UUID.randomUUID().toString();

        // Add the mother details Fragment
        Fragment frag = new MotherDssDetails();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_view, frag).commit();

        btn_back = (Button) findViewById(R.id.btn_back);
        btn_next = (Button) findViewById(R.id.btn_next);
    }

    @Override
    public void onData(MotherModel clientModel) {
        status = clientModel.getClient_status();
        rand = clientModel.getClient_rand();
        MotherDetailsDb db = MotherDetailsDb.getInstance(getApplicationContext());
        db.getWritableDatabase();
        if(!bol){
            db.save(clientModel, rand);
        }
    }

    @Override
    public void ansQuestion(PostpartumQModel postpartumQModel) {
        PostpartumDssDb db = PostpartumDssDb.getInstance(getApplicationContext());
        db.getWritableDatabase();

        if(!bol){
            db.save(postpartumQModel, rand);
        }
    }

    @Override
    public void ansQuestionPregnant(PregnantQModel pregnantQModel) {
        PregnantDssDb db = PregnantDssDb.getInstance(getApplicationContext());
        db.getWritableDatabase();
        if(!bol){
            db.save(pregnantQModel, rand);
        }
    }

    @Override
    public void isDraft(boolean t) {
        bol = t;
    }

    @Override
    public boolean getBol() {
        return bol;
    }

    public void next(View view) {

        switch (i) {
            case 0:
                // Get values from
                MotherDssDetails fragment = (MotherDssDetails) getSupportFragmentManager().findFragmentById(R.id.fragment_view);
                fragment.doTask(rand);
                break;
            case 1:
                if (status == 2) {
                    PregnantDss fragment2 = (PregnantDss) getSupportFragmentManager().findFragmentById(R.id.fragment_view);
                    fragment2.setClientDetails(rand);
                } else {
                    PostpartumDss fragment1 = (PostpartumDss) getSupportFragmentManager().findFragmentById(R.id.fragment_view);
                    fragment1.setClientDetails();
                }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_draft:
                change_fragment(10);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void change_fragment(int id) {
        Log.e("Fragment Position", String.valueOf(id));
        Fragment fragchange = null;
        switch (id) {
            case 0:
                fragchange = new MotherDssDetails();
                break;
            case 1:
                if (status == 2) {
                    fragchange = new PregnantDss();
                } else {
                    fragchange = new PostpartumDss();
                }
                break;
            case 2:
                fragchange = new MotherDssDetails();
                isDraft(false);
                i = 0;
                break;
            case 10:
                fragchange = new MotherDraftFragment();
                break;

        }

        // Replace the current fragment
        if (fragchange != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_view, fragchange).commit();
        }

    }

    public void showDescription(View v) {
        //Toast.makeText(getApplicationContext(), v.getContentDescription().toString(), Toast.LENGTH_SHORT).show();
        new SweetAlertDialog(MotherDssActivity.this, SweetAlertDialog.NORMAL_TYPE)
                .setTitleText("Details")
                .setContentText(v.getContentDescription().toString())
                .show();
    }
}
