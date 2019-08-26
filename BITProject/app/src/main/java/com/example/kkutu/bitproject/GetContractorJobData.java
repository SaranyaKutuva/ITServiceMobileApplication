package com.example.kkutu.bitproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import org.json.JSONArray;

import java.sql.*;
import java.util.ArrayList;

public class GetContractorJobData extends AsyncTask<Void,Void,Void> {
    String datainstring = " ";
    String formatString = " ",contractorID= " ";
    Bundle bundle;
    Intent intent;

    static ArrayList<ContractorJob> jobsArrayList = new ArrayList<>();
    GetContractorJobData(){
        super();
    }

    int contractorId;
    // reference to Entity class

   JSONArray array1;



    @Override
    protected Void doInBackground(Void... voids) {


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
                    "select * from bit_contractor_job_v");
            //  st.setString(1,clientId);
            ResultSet rs = st.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();
            System.out.println("Number of columns :" + numberOfColumns + "  " + rs.getRow());

            while (rs.next()) {
                //   System.out.println("KJK  " + clientId +  "  " + rs.getString(1));
                ContractorJob jobs = new ContractorJob();
                jobs.setJob_Id(Integer.parseInt(rs.getString(1)));
                jobs.setJob_Type(rs.getString(2));
                // jobs.setClient_Name(Integer.parseInt(rs.getString(3)));
                jobs.setRequest_Date(rs.getString(4));
                jobs.setClient_Name(rs.getString(5));
                jobs.setClient_Phoneno(rs.getString(6));
                jobs.setClient_Address(rs.getString(7));

                System.out.println("KJK  " + jobs.getJob_Type());
                //     jobsArrayList.add(jobs);

            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.toString());
            //   Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();

        }
        /*************************/
        return null;
    }

    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);
        // MainActivity.getData(myUserList);
        // MainActivity.textView.setText(this.formatString);
        //MainActivity mainActivity = new MainActivity();
        //mainActivity.getData(myUserList);

        //calling a method from main to fill arraylist of objects
    }
}
