package com.example.khalif.a514app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.khalif.a514app.Models.MotherModel;
import com.example.khalif.a514app.R;

import java.util.ArrayList;

/**
 * Created by Khalif on 7/13/2017.
 */


public class DraftsAdapter extends BaseAdapter {

    Context context;
    ArrayList<MotherModel> motherModels;
    boolean _show_button = true;
    public  DraftsAdapter(Context context, ArrayList<MotherModel> motherModels){
        this.context = context;
        this.motherModels = motherModels;
    }

    @Override
    public int getCount() {
        return null == motherModels ? 0 : motherModels.size();
    }

    @Override
    public Object getItem(int position) {
        return motherModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(this.getCount() == 0){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View emptyView = inflater.inflate(R.layout.mother_list_item, null);
            convertView = emptyView.findViewById(R.id.empty);
        }
        else{
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.mother_list_item, null);
            }

            TextView txtMessageId  = (TextView) convertView.findViewById(R.id.txtMessageId);
            TextView txtMessageDetails  = (TextView) convertView.findViewById(R.id.txtMessageDetails);

            MotherModel s = motherModels.get(position);

            txtMessageId.setText("Name : " + s.getClient_name());
            txtMessageDetails.setText("\n Age  : " + s.getClient_age()
                    + "\n Phone  : " + s.getClient_phone()
                    + "\n Patient Name:  " + s.getClient_latitude()
                    + "\n Village :  " + s.getClient_longitude()
            );

        }

        return convertView;
    }

}
