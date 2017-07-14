package com.example.khalif.a514app.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
