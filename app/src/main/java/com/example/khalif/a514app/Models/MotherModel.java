package com.example.khalif.a514app.Models;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import com.example.khalif.a514app.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Khalif on 7/11/2017.
 */
public class MotherModel {
    public static final String KEY_CLIENT_NAME = "client_name";
    public static final String KEY_CLIENT_ID = "client_id";
    public static final String KEY_CLIENT_PHONE = "client_phone";
    public static final String KEY_CLIENT_AGE = "client_age";
    public static final String KEY_CLIENT_LATITUDE = "client_lat";
    public static final String KEY_CLIENT_LONGITUDE = "client_lng";
    private String client_id;
    private String client_name;
    private String client_phone;
    private String client_latitude;
    private String client_longitude;
    private String client_age;
    private int client_status;
    private String client_rand;

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public void setClient_phone(String client_phone) {
        this.client_phone = client_phone;
    }

    public void setClient_latitude(String client_latitude) {
        this.client_latitude = client_latitude;
    }

    public void setClient_longitude(String client_longitude) {
        this.client_longitude = client_longitude;
    }

    public void setClient_status(int client_status) {
        this.client_status = client_status;
    }

    public void setClient_mobile(String client_phone) {
        this.client_phone = client_phone;
    }

    public void setClient_age(String client_age) {
        this.client_age = client_age;
    }

    public String getClient_id() {
        return client_id;
    }

    public String getClient_age() {
        return client_age;
    }

    public String getClient_name() {
        return client_name;
    }

    public String getClient_phone() {
        return client_phone;
    }

    public String getClient_latitude() {
        return client_latitude;
    }

    public String getClient_longitude() {
        return client_longitude;
    }

    public int getClient_status() {
        return client_status;
    }

    public String getClient_rand() {
        return client_rand;
    }

    public void setClient_rand(String client_rand) {
        this.client_rand = client_rand;
    }

    public static MotherModel makeFromJSON(JSONObject json) {
        MotherModel motherModel = new MotherModel();
        try {
            motherModel.setClient_name(json.getString("client_name"));
            motherModel.setClient_age(json.getString("client_age"));
            motherModel.setClient_phone(json.getString("client_phone"));
            motherModel.setClient_rand(json.getString("client_rand"));

        } catch (JSONException e) {
            //if(doLog) Log.e(TAG, CTAG  + e.toString());
        }
        return motherModel;
    }

    public static ArrayList<MotherModel> makeArraylist(JSONArray jsonArray) {
        ArrayList<MotherModel> messages = new ArrayList<MotherModel>();

        try {
            for (int i = 0; i < jsonArray.length(); i++){
                messages.add(MotherModel.makeFromJSON(jsonArray.getJSONObject(i)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return messages;
    }

//    public ValidateModel isSet(Context context){
//
//        if (a_dX.getSelectedItemId() == 0) {
//            showInvalidMessage(R.string.abdominal_discharge, context);
//            return;
//        }
//        if (b_aX.getSelectedItemId() == 0) {
//            showInvalidMessage(R.string.bad_abdominal, context);
//            return;
//        }
//        if (f_nX.getSelectedItemId() == 0) {
//            showInvalidMessage(R.string.fever_chills, context);
//            return;
//        }
//        if (f_cX.getSelectedItemId() == 0) {
//            showInvalidMessage(R.string.fever_nochills, context);
//            return;
//        }
//        if (h_bX.getSelectedItemId() == 0) {
//            showInvalidMessage(R.string.high_blood, context);
//            return;
//        }
//        if (l_pX.getSelectedItemId() == 0) {
//            showInvalidMessage(R.string.labour_pain, context);
//            return;
//        }
//        if (l_cX.getSelectedItemId() == 0) {
//            showInvalidMessage(R.string.loss_consciousness, context);
//            return;
//        }
//        if (h_dX.getSelectedItemId() == 0) {
//            showInvalidMessage(R.string.head_dizzy, context);
//            return;
//        }
//        if (b_vX.getSelectedItemId() == 0) {
//            showInvalidMessage(R.string.Blurred_vision, context);
//            return;
//        }
//        if (d_bX.getSelectedItemId() == 0) {
//            showInvalidMessage(R.string.diff_breathing, context);
//            return;
//        }
//        if (p_uX.getSelectedItemId() == 0) {
//            showInvalidMessage(R.string.pass_urine, context);
//            return;
//        }
//        if (p_fX.getSelectedItemId() == 0) {
//            showInvalidMessage(R.string.palm_feet, context);
//            return;
//        }
//        if (w_bX.getSelectedItemId() == 0) {
//            showInvalidMessage(R.string.water_break, context);
//            return;
//        }
//        if (a_lX.getSelectedItemId() == 0) {
//            showInvalidMessage(R.string.arm_legs, context);
//            return;
//        }
//        if (p_sX.getSelectedItemId() == 0) {
//            showInvalidMessage(R.string.placenta, context);
//            return;
//        }
//        if (f_sX.getSelectedItemId() == 0) {
//            showInvalidMessage(R.string.foul_smelling, context);
//            return;
//        }
//        if (t_bS.getSelectedItemPosition() == 0) {
//            showInvalidMessage(R.string.time_birth, context);
//            return;
//        }
//        if (f_bS.getSelectedItemPosition() == 0) {
//            showInvalidMessage(R.string.first_baby, context);
//            return;
//        }
//        if (p_aS.getSelectedItemPosition() == 0) {
//            showInvalidMessage(R.string.pnc_attended, context);
//            return;
//        }
//    }
//
//    public void showInvalidMessage(int message, Context context) {
//        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
//                .setContentText("Select If " + context.getResources().getString(message)).show();
//    }

    @Override
    public String toString() {
        return "MotherModel{" +
                "client_rand='" + client_rand + '\'' +
                ", client_id='" + client_id + '\'' +
                ", client_name='" + client_name + "" + '\'' +
                ", client_phone=" + client_phone + '\'' +
                ", client_latitude=" + client_latitude + '\'' +
                ", client_longitude=" + client_longitude + '\'' +
                ", client_phone='" + client_phone + '\'' +
                ", client_status='" + client_status + '\'' +
                ", client_age='" + client_age + '\'' +
                '}';
    }

}
