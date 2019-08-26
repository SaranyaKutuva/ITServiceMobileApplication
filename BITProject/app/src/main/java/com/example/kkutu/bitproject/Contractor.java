package com.example.kkutu.bitproject;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.telephony.SmsManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

import java.sql.*;
import java.util.ArrayList;

public class Contractor extends Activity implements View.OnClickListener{

Button smsClient,smsBit,emailBit,payment,location,accept,reject,completed;
Bundle bundle;
ListView listView;
EditText message;
String contractorId;
    Animation blink,fade_in,rotate;
    ImageView imageView;
    MediaPlayer button_sound;
ImageView logo,sms,map;
Intent intent;
GetContractorJobData getContractorJobData;
    ArrayList[] listItem;
    static ArrayList<ContractorJob> jobsArrayList = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contractor);
        smsClient = findViewById(R.id.button4);
        smsBit = findViewById(R.id.button8);
        message = findViewById(R.id.editText9);
        logo = findViewById(R.id.imageView5);
        fade_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        logo.startAnimation(fade_in);
        logo.setImageResource(R.drawable.bitlogo);
        sms = findViewById(R.id.imageView15);
        sms.setImageResource(R.drawable.ic_message);
        map = findViewById(R.id.imageView11);
        map.setImageResource(R.drawable.ic_location);
        emailBit = findViewById(R.id.button9);
        payment = findViewById(R.id.button);
        location = findViewById(R.id.button5);
        location.setOnClickListener(this);
        accept = findViewById(R.id.button6);
        reject = findViewById(R.id.button7);
        completed = findViewById(R.id.button11);
        completed.setEnabled(false);
        accept.setEnabled(false);
        reject.setEnabled(false);

        accept.setOnClickListener(this);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ContractorJob con = (ContractorJob) listView.getSelectedItem();
                 //if (con.getJob_Id() >0 ) {


                     reject.setEnabled(false);
                     accept.setEnabled(false);
                     completed.setEnabled(true);
                     Toast.makeText(getApplicationContext(), "Job Accepted", Toast.LENGTH_LONG).show();
               //  }else{
                 //    Toast.makeText(getApplicationContext(), "Please select a job !", Toast.LENGTH_LONG).show();
                 //}
            }
        });
        reject.setOnClickListener(this);
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept.setEnabled(false);
                reject.setEnabled(false);
                completed.setEnabled(false);
                Toast.makeText(getApplicationContext(), "Job Rejected", Toast.LENGTH_LONG).show();
            }
        });
        completed.setOnClickListener(this);
        completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPayment = new Intent(getApplicationContext(), Payment.class);
                startActivity(intentPayment);
            }
        });
        listView = findViewById(R.id.listViewContractor);
        smsClient.setOnClickListener(this);
        // smsBit.setOnClickListener(this);
        //   emailBit.setOnClickListener(this);
//        payment.setOnClickListener(this);
        //   location.setOnClickListener(this);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locateClient();
            }

            private void locateClient() {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //insert client address details below
                            Uri gmmIntentUri = Uri.parse("geo:0,0?q=3 Mount St, Hornsby, 2075");
// you can read above from your database
                            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                            mapIntent.setPackage("com.google.android.apps.maps");
                            startActivity(mapIntent);
                        }
                    }, 1000);
                }

        });




        bundle =getIntent().getExtras();
        contractorId =bundle.getString ("key1");
        //getContractorJobData = new GetContractorJobData();
       // getJobRequestData.clientID = clientId;
        //get job request Database call
        //getContractorJobData.execute();
        getContractorJobDB();
        ContractorJobAdaptor adaptor = new ContractorJobAdaptor(getApplicationContext(),R.layout.mycontractorlistview,jobsArrayList);

        listView.setAdapter(adaptor);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                accept.setEnabled(true);
                reject.setEnabled(true);
            }
        });
        smsClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    SmsManager smsManager = SmsManager.getDefault();
                   //smsManager.sendTextMessage(phoneTextBox.getText().toString(), null, "The fee for the " + course + "is " + fees,
                      //      null, null);
                    smsManager.sendTextMessage("0420662968", null, "I will attend around  " + message.getText().toString() ,
                            null, null);
                    Toast.makeText(getApplicationContext(), "SMS Sent!",
                            Toast.LENGTH_LONG).show();


                    //Toast.makeText(getApplicationContext(), "SMS sent", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "SMS failed" + e.toString(), Toast.LENGTH_LONG).show();
                }

            }
        });


    }
    @Override
    public void onClick(View v) {

    }

    public void getContractorJobDB() {


        /**************************/

        final String url = "jdbc:mysql://10.0.2.2:3306/bit";
        final String user = "root";
        final String pass = "";
        String result = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        try { // Loading the MySQL Connector/J driver
            Class.forName("com.mysql.jdbc.Driver");
            //Class.forName("MySQLJDBCDriver41");
            //Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Successfully connected Driver: ");
        } catch (Exception e) {
            System.out.println("Error while loading the Driver: " + e.getMessage());
        }
        try {


            PreparedStatement st;
            Connection con = DriverManager.getConnection(url, user, pass);
            // Toast.makeText(getApplicationContext(),"Database connection success",Toast.LENGTH_LONG).show();

            result = "Database Connection Successful\n";
            System.out.println("Database Connection Successful ");
            //Statement st = con.createStatement();
            st = con.prepareStatement(
                    "select * from bit_contractor_job_v where Contractor_Id =?");
            st.setString(1,contractorId);
            ResultSet rs = st.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();
            System.out.println("Number of columns :" + numberOfColumns + "  " + rs.getRow());

            while (rs.next()) {
                  System.out.println("Contractor  "  +  "  " + rs.getString(1));
                ContractorJob jobs = new ContractorJob();
                jobs.setJob_Id(Integer.parseInt(rs.getString(1)));
                jobs.setJob_Type(rs.getString(2));
               // jobs.setClient_Name(Integer.parseInt(rs.getString(3)));
                jobs.setRequest_Date(rs.getString(3));
                jobs.setClient_Name(rs.getString(6));
                jobs.setClient_Phoneno(rs.getString(4));
                jobs.setClient_Address(rs.getString(5));
                System.out.println("KJK  " + jobs.getJob_Type());
                   jobsArrayList.add(jobs);

            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.toString());
            //   Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();

        }
    }    /*************************/
}
