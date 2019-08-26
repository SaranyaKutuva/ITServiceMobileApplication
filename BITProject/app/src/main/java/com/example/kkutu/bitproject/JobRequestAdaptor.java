package com.example.kkutu.bitproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class JobRequestAdaptor extends ArrayAdapter<Jobs> {

    private List<Jobs> jobList;

    public JobRequestAdaptor(Context context, int resource, List<Jobs> jobList) {
        super(context, resource);
        this.jobList = jobList;
       // System.out.println(" KJK adaptor " + jobList.get(0).getJob_Type());
    }
@Override
    public Jobs getItem(int position){
    return jobList.get(position);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Jobs job = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.mylistview, parent, false);
        }
        TextView jobType = (TextView)  convertView.findViewById(R.id.textView10);
       TextView reqdt = (TextView) convertView.findViewById(R.id.textView9);
        TextView contName = (TextView) convertView.findViewById(R.id.textView6);
        TextView contPhone = (TextView) convertView.findViewById(R.id.textView7);
        TextView jobStatus = (TextView) convertView.findViewById(R.id.textView8);
        jobType.setText("Job Type: " + job.getJob_Type());
       reqdt.setText("Req Date: " + job.getRequestDate());
        contName.setText("Cont Name: " + job.getContractorName());
        contPhone.setText("Cont Ph: " + job.getPhoneNo());
        jobStatus.setText("Status: " + job.getStatus());


       // locationId.setText("Location Id: " + String.valueOf(location.getLocation_Id()));
        //locationName.setText(location.getLocation_Name());
        //clientId.setText(String.valueOf(location.getClient_Id()));
        return convertView;
    }

    @Override
    public int getCount() {
        return jobList.size();
    }
}