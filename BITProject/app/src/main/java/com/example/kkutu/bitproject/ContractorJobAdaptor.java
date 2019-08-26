package com.example.kkutu.bitproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ContractorJobAdaptor extends ArrayAdapter<ContractorJob> {

    private List<ContractorJob> jobList;
    public ContractorJobAdaptor( Context context, int resource,List<ContractorJob> jobList) {
        super(context, resource);
        this.jobList = jobList;
    }
    public ContractorJob getItem(int position) {
        return jobList.get(position);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ContractorJob job = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.mycontractorlistview, parent, false);
        }
        TextView jobType = (TextView)  convertView.findViewById(R.id.textView11);
        TextView reqdt = (TextView) convertView.findViewById(R.id.textView12);
        TextView contName = (TextView) convertView.findViewById(R.id.textView13);
        TextView contPhone = (TextView) convertView.findViewById(R.id.textView14);
        TextView jobStatus = (TextView) convertView.findViewById(R.id.textView15);
        jobType.setText("Job Type: " + job.getJob_Type());
        reqdt.setText("Req Date: " + job.getRequest_Date());
        contName.setText("Client Name: " + job.getClient_Name());
        contPhone.setText("Client Ph: " + job.getClient_Phoneno());
        jobStatus.setText("Client Address: " + job.getClient_Address());


        // locationId.setText("Location Id: " + String.valueOf(location.getLocation_Id()));
        //locationName.setText(location.getLocation_Name());
        //clientId.setText(String.valueOf(location.getClient_Id()));
        return convertView;
    }

    public int getCount() {
        return jobList.size();
    }
}
