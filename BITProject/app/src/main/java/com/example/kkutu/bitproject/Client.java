package com.example.kkutu.bitproject;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Driver;
import java.sql.SQLException;
import java.sql.DriverManager.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Client extends Activity implements View.OnClickListener {
    Button button, button1, button2;
    Bundle bundle;
    Intent intent;
    Animation blink,fade_in,rotate;
    int notifyID = 1;
    String CHANNEL_ID = "my_channel_01";// The id of the channel.
    String channel_name = "manav";

    Context ctx;
    MediaPlayer button_sound;
    Spinner spinner;
    ListView listView;
    String list;
    ImageView imageView,email,call,web,photo,job;
    TextView textView,status;
    Button website, callBit, emailBit;
    GetClientLocationData getClientLocationData;
    GetJobRequestData getJobRequestData;
    static ArrayList<Jobs> jobsArrayList = new ArrayList<>();
    String clientId ="";
    ArrayList[] listItem;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client);
        website = findViewById(R.id.button4);


        callBit = findViewById(R.id.button8);
        emailBit = findViewById(R.id.button9);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
      //  button1 = findViewById(R.id.button3);
        listView = findViewById(R.id.listview);
        website.setOnClickListener(this);
        callBit.setOnClickListener(this);
        emailBit.setOnClickListener(this);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        imageView = findViewById(R.id.imageView7);
        imageView.setImageResource(R.drawable.bitlogo);
        blink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        fade_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        imageView.startAnimation(blink);
        status = findViewById(R.id.textView14);
    //    status.startAnimation(rotate);
        email = findViewById(R.id.imageView10);
        email.setImageResource(R.drawable.ic_mail);
        call = findViewById(R.id.imageView2);
        call.setImageResource(R.drawable.ic_phone);
        //web = findViewById(R.id.imageView7);
       // web.setImageResource(R.drawable.website);
        photo = findViewById(R.id.imageView8);
        job= findViewById(R.id.imageView9);
        photo.setImageResource(R.drawable.ic_photo_camera);
        job.setImageResource(R.drawable.ic_add);
//        button1.setOnClickListener(this);
        bundle =getIntent().getExtras();
        clientId =bundle.getString ("key1");
       //*************************Load Required data into Arrary or List Array */

        //get client location JSON call
        getClientLocationData = new GetClientLocationData();
        getClientLocationData.clientID =clientId;
        getClientLocationData.execute();


     //   listItem = getResources().getStringArray(R.array.jobs);
     // ArrayAdapter<ClientLocation> adapter = new ArrayAdapter<ClientLocation>(getApplicationContext(),
       //         R.layout.mylistview,R.id.textView6,getClientLocationData.myLocationList);
       // listView.setAdapter(adapter);

        getJobRequestData = new GetJobRequestData();
        getJobRequestData.clientID = clientId;
        getJobRequestData.execute();
        //get job request Database call
         getJobRequestDB();
      //  System.out.println("KJK size " + getJobRequestData.jobsArrayList.size() );
         JobRequestAdaptor  adaptor = new JobRequestAdaptor(getApplicationContext(),R.layout.mylistview,jobsArrayList);
         listView.setAdapter(adaptor);

        //JobRequestAdaptor  adaptor = new JobRequestAdaptor(getApplicationContext(),R.layout.mylistview,getJobRequestData.jobsArrayList);
       // listView.setAdapter(adaptor);
     /*!--************************Load Required data into Arrary or List Array --!*/


//      button1.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//              System.out.println("Button Two Clicked");
//
//
//          }
//      });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(),JobRequest.class);
                intent.putExtra("Key1",clientId);
//                Toast.makeText(getApplicationContext(),bundle.getInt("key1"),Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });



        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http:192.168.1.8/bit/Homepage3.html"));
                    startActivity(intent);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Network Error" + e.toString(), Toast.LENGTH_LONG).show();
                }

            }
        });

        callBit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:0287878712"));
                    startActivity(intent);
                } catch (Exception e) {
                    System.out.println("Call Error" + e.toString());
                    Toast.makeText(getApplicationContext(), "Call Error" + e.toString(), Toast.LENGTH_LONG).show();
                }


            }
        });




        emailBit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // imageView.setImageResource(R.drawable.dipsoftdevelopment);
                // button_sound = MediaPlayer.create(MainActivity.this,

                intent = new Intent(Intent.ACTION_SEND);
                // put data
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"elsaranya@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Hello Bit Service ");
                //  emailIntent.putExtra(Intent.EXTRA_CC,new String[] {cc});
                intent.putExtra(Intent.EXTRA_BCC, new String[]{"saranya.eswareen@tafensw.edu.au"});
                // intent.putExtra(Intent.EXTRA_TEXT,email);
                intent.putExtra(Intent.EXTRA_TEXT, "  ".toString());

                // so in above cpode you are sending email with the data filled by user on your app

                intent.setType("message/rfc822");
                // use above message(string ) to send email via default email app registered with gmail

                startActivity(Intent.createChooser(intent, "send email"));
                // two ways to send sms
                // 1_> intent
                // 2-> SmsManager
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);

            }
        });
    }

        @Override
        public void onClick (View v){

        }

    public void getJobRequestDB(){

        System.out.println("Job Array list count : " +  jobsArrayList.size());
            if (jobsArrayList.size() >0 ){
                System.out.println("1 Job Array list count : " +  jobsArrayList.size());
            }else {

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
                            "select * from bit_job_request_v where  Client_ID = ?");
                    st.setString(1, clientId);
                    ResultSet rs = st.executeQuery();
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int numberOfColumns = rsmd.getColumnCount();
                    System.out.println("Number of columns :" + numberOfColumns + "  " + rs.getRow());

                    while (rs.next()) {
                        //   System.out.println("KJK  " + clientId +  "  " + rs.getString(1));
                        Jobs jobs = new Jobs();
                        jobs.setJobRequest_Id(Integer.parseInt(rs.getString(1)));
                        jobs.setJob_Type(rs.getString(2));
                        jobs.setClient_Id(Integer.parseInt(rs.getString(3)));
                        jobs.setRequestDate(rs.getString(4));
                        jobs.setContractorName(rs.getString(5));
                        jobs.setPhoneNo(rs.getString(6));
                        jobs.setStatus(rs.getString(7));

                        System.out.println("KJK  " + jobs.getJob_Type());
                        jobsArrayList.add(jobs);

                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println(e.toString());
                    //   Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();

                }
                /*************************/
            }

    }

    }


