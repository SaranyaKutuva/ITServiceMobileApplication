package com.example.kkutu.bitproject;


import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.content.Intent;
import android.widget.*;


import java.lang.reflect.Array;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobRequest extends FragmentActivity implements View.OnClickListener {
    Spinner jobType, priority, location;
    Bundle bundle;
    EditText clientId, Comments;
    static EditText DateOfRequest;
    ImageView imageView;
    //Intent intentClient = null;
    Button goBack, send;
    GetClientLocationData getClientLocationData;
    static String clientID = "",locationID= "",commentsID ="";
    String jobid = "";
    Intent intentClient;
    List<String> locationArray = new ArrayList<>() ;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newjobrequest);
        clientId = findViewById(R.id.editText3);
        DateOfRequest = findViewById(R.id.editText4);
        Comments = findViewById(R.id.editText5);
        // goBack = findViewById(R.id.button5);
        send = findViewById(R.id.button4);
        jobType = findViewById(R.id.spinner2);
        imageView = findViewById(R.id.imageView3);
        imageView.setImageResource(R.drawable.bitlogo);
        location = findViewById(R.id.spinner3);
        priority = findViewById(R.id.spinner4);
      //  getJobTypeClass = new GetJobTypeClass();
      // getJobTypeClass.execute();
        bundle = getIntent().getExtras();
        //Toast.makeText(getApplicationContext(),bundle.getString("key1"),Toast.LENGTH_LONG).show();
        clientID = bundle.getString("Key1");
        //locationID = bundle.getString("key2");
       // commentsID = bundle.getString("key3");
        Toast.makeText(getApplicationContext(), clientID, Toast.LENGTH_LONG).show();
        clientId.setText(String.valueOf(clientID));
//        intentClient =
//                new Intent
//                        (getApplicationContext(),
//                                Client.class);
//        intentClient.putExtra("key1",clientId.getText());
//        intentClient.putExtra("key2",jobType.getSelectedItem().toString());
//        intentClient.putExtra("key3",Comments.getText().toString());
//      //  intentClient.putExtra("key4",bundle.getString("key4").toString());
//        startActivity(intentClient);

        send.setOnClickListener(this);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*System.out.println(location.getSelectedItem());
                System.out.println(priority.getSelectedItem());
                System.out.println(jobType.getSelectedItem());
                System.out.println(Comments.getText().toString());
                System.out.println(clientId.getText().toString());
                System.out.println(DateOfRequest.getText().toString());*/
                submitNewJobDB();


            }
        });

         //below call is required to convert the location list to   List<String>
         convertToLocationListStringArray(getClientLocationData.myLocationList);
         ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.mylocationspinner ,R.id.textView,locationArray);
         location.setAdapter(arrayAdapter);



       /*
        jobType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 jobid = jobType.getSelectedItem().toString();
             //  System.out.println(jobid);
               Toast.makeText(getApplicationContext(),"Hi" +jobid,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
   //       getData(getJobTypeClass.myLocationList);
        final String[] jobST = getResources().getStringArray(R.array.jobs);
          ArrayAdapter arrayAdapter3 = new ArrayAdapter(getApplicationContext(),R.layout.myjobspinner,R.id.textView5,jobST );
         // arrayAdapter3.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
           jobType.setAdapter(arrayAdapter3);

        final String[] list = getResources().getStringArray(R.array.priority);
        //  final String[] Classes = getResources().getStringArray(R.array.Classes);
        // data for your spinner
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(getApplicationContext(), R.layout.myspinnerlayout,
                R.id.textView3, list);

        priority.setAdapter(arrayAdapter1);
//        priority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String prior = list[position].toString();
//            }
//
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


        DateOfRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRequestDatePickerDialog(v);
            }
        });


    }

    public void showRequestDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onClick(View v) {

    }


    public  void convertToLocationListStringArray(ArrayList<ClientLocation> LocationArrayList) {


        System.out.println(" array size  " + LocationArrayList.size());
        String s = " ";
        int j =0;
        // final String Location =getResources().getStringArray(R.array.)
        for (int i = 0; i < LocationArrayList.size(); i++) {
              if(clientID.equals(String.valueOf(LocationArrayList.get(i).getClient_Id()))) {
                  //System.out.println(LocationArrayList.get(i).getLocation_Name());
                      locationArray.add(LocationArrayList.get(i).getLocation_Name());
            }


        }
    }
    public void submitNewJobDB(){


        /**************************/
        Integer maxJobRequestId=0;
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
                    "SELECT max(jobRequest_Id)+1 max_jobRequest_Id FROM bit_job_request_v ");
            //st.setString(1, clientId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                maxJobRequestId =  rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.toString());
            //   Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();

        }
        System.out.println(location.getSelectedItem());
        System.out.println(priority.getSelectedItem());
        System.out.println(jobType.getSelectedItem());
        System.out.println(Comments.getText().toString());
        System.out.println(clientId.getText().toString());
        System.out.println(DateOfRequest.getText().toString());

        Jobs jobs = new Jobs();
        jobs.setJobRequest_Id(maxJobRequestId);
        jobs.setJob_Type(jobType.getSelectedItem().toString());
        jobs.setClient_Id(Integer.parseInt(clientID));
        jobs.setRequestDate(DateOfRequest.getText().toString());
        jobs.setContractorName("");
        jobs.setPhoneNo("");
        jobs.setStatus("Requsted");

        System.out.println("KJK  " + jobs.getJob_Type());
        Client.jobsArrayList.add(jobs);
        intentClient = new Intent(getApplicationContext(), Client.class);
        // intentClient.putExtra(int account_Ref);
        System.out.println(clientID);
        intentClient.putExtra("key1",clientID);
        startActivity(intentClient);
        /**************************/

        /*final String url = "jdbc:mysql://10.0.2.2:3306/bit";
        final String user = "root";
        final String pass = "";
        String result= null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        try{ // Loading the MySQL Connector/J driver
            Class.forName("com.mysql.jdbc.Driver");
            //Class.forName("MySQLJDBCDriver41");
            //Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Successfully connected Driver: ");
        }catch(Exception e){
            System.out.println("Error while loading the Driver: " + e.getMessage());
        }
        try {


            PreparedStatement st;
            Connection con = DriverManager.getConnection(url, user, pass);
            // Toast.makeText(getApplicationContext(),"Database connection success",Toast.LENGTH_LONG).show();

            result = "Database Connection Successful\n";
            System.out.println("Database Connection Successful ");
            //Statement st = con.createStatement();
            st = con.prepareStatement("{ call BitInsertJobRequest(?,?,?,?,?,?)}");

            st.setString(1,"2019-10-10");
            st.setString(2,priority.getSelectedItem().toString());
            st.setString(3,jobType.getSelectedItem().toString());
            st.setString(4,Comments.getText().toString());
            st.setString(5,location.getSelectedItem().toString());
            st.setString(6,clientID);

             st.execute();



        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.toString());
            //   Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();

        }*/
        /*************************/
    }
}