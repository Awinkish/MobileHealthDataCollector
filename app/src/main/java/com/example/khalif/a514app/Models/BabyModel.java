package com.example.khalif.a514app.Models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BabyModel {
    private String client_id;
    private String client_bName;
    private String client_phone;
    private String client_weight;
    private String client_mName;
    private String client_latitude;
    private String client_longitude;
    private String client_age;
    private long client_days;
    private String client_rand;
    private Boolean client_gender;

    public String getClient_id() {
        return client_id;
    }

    public String getClient_bName() {
        return client_bName;
    }

    public String getClient_weight() {
        return client_weight;
    }

    public String getClient_mName() {
        return client_mName;
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

    public void setClient_days(long client_days) {
        this.client_days = client_days;
    }

    public long getClient_days() {
        return client_days;
    }

    public Boolean getClient_gender() {
        return client_gender;
    }

    public void setClient_gender(Boolean client_gender) {
        this.client_gender = client_gender;
    }

    public String getClient_age() {
        return client_age;
    }

    public String getClient_rand() {
        return client_rand;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
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

    public void setClient_age(String client_age) {
        this.client_age = client_age;
    }

    public void setClient_rand(String client_rand) {
        this.client_rand = client_rand;
    }

    public void setClient_bName(String client_bName) {
        this.client_bName = client_bName;
    }

    public void setClient_weight(String client_weight) {
        this.client_weight = client_weight;
    }

    public void setClient_mName(String client_mName) {
        this.client_mName = client_mName;
    }

    public static BabyModel makeFromJson(JSONObject jsonObject) {
        BabyModel babyModel = new BabyModel();
        try {
            babyModel.setClient_mName(jsonObject.getString("client_mName"));
            babyModel.setClient_bName(jsonObject.getString("clinet_bName"));
            babyModel.setClient_age(jsonObject.getString("client_age"));
            babyModel.setClient_weight(jsonObject.getString("client_weight"));
            babyModel.setClient_phone(jsonObject.getString("client_phone"));
            babyModel.setClient_rand(jsonObject.getString("client_rand"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return babyModel;
    }

    public static ArrayList<BabyModel> makeArrayList(JSONArray jsonArray) {
        ArrayList<BabyModel> messages = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                messages.add(BabyModel.makeFromJson(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return messages;
    }

    @Override
    public String toString() {
        return "BabyModel{"
                + "client_rand='" + client_rand + '\'' + ", client_id='" + client_id + '\'' +
                ", client_mName='" + client_mName + "" + '\'' +
                ", client_bName='" + client_bName + "" + '\'' +
                ", client_age='" + client_age + '\'' +
                ", client_weight='" + client_weight + '\'' +
                ", client_phone=" + client_phone + '\'' +
                ", client_latitude=" + client_latitude + '\'' +
                ", client_longitude=" + client_longitude + '\'' +

                '}';
    }
}
