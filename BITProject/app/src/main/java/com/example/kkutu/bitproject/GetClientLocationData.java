package com.example.kkutu.bitproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class GetClientLocationData  extends AsyncTask<Void,Void,Void>{
    String datainstring = " ";
    String formatString = " ",clientID= " ";
    Bundle bundle;
    Intent intent;

   // public static String loginType ="Invalid";

  public static  ArrayList<ClientLocation> myLocationList = new ArrayList<>();



    GetClientLocationData(){
        super();
    }
    ClientLocation location;
    int clientId;
    // reference to Entity class


    JSONArray array1;
    @Override
    protected Void doInBackground(Void... voids) {
        InputStream inputStream = null;
        String strJSON = "";

        try {
            // URL url = new URL("https://api.myjson.com/bins/wr1wk");
            URL url = new URL("https://api.myjson.com/bins/1ckxnt");
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection)url.openConnection();

            inputStream = httpsURLConnection.getInputStream();

            BufferedReader objBufferesReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder objStrBuilder = new StringBuilder();
            String strLine = null;

            while ((strLine = objBufferesReader.readLine()) != null) {
                objStrBuilder.append(strLine);
            }

            inputStream.close();
            strJSON = objStrBuilder.toString();

            Log.d("Emergency", "Connected JSON Success !");



        } catch (Exception e) {
            Log.d("Emergency","Error Convert To JSON :" + e.toString() );
        }


        try {

            //Toast.makeText(getApplicationContext(),bundle.getString("key1"),Toast.LENGTH_LONG).show();
       //     clientID = bundle.getString("Key1");
            JSONObject object = new JSONObject(strJSON);
            JSONArray objJSONArray = object.getJSONArray("bit_client_location");


            for (int j = 0; j < objJSONArray.length(); j++) {

                JSONObject objJSONObject1 = objJSONArray.getJSONObject(j);
                if (clientID.equals(String.valueOf(objJSONObject1.getInt("Client_Id")) )){
                    formatString = formatString + objJSONObject1.getInt("Location_Id") +
                            objJSONObject1.getString("Location_Name") +
                            objJSONObject1.getString("Suburb") + objJSONObject1.getString("StreetAddress")
                            + objJSONObject1.getInt("Client_Id");

                    ClientLocation location = new ClientLocation();
                    location.setLocation_Id(objJSONObject1.getInt("Location_Id"));
                    location.setLocation_Name(objJSONObject1.getString("Location_Name"));
                    location.setClient_Id(objJSONObject1.getInt("Client_Id"));
                    //location = objJSONObject1.getString("Location_Name");
                    // clientId = objJSONObject1.getInt("Client_Id");
                    System.out.println("Loc " + objJSONObject1.getString("Location_Name"));
                    myLocationList.add(location);
                }
//


            }

        } catch (Exception e) {
            Log.d("Emergency","Error syncdata to SQLite :" + e.toString() );
            System.out.println(e.toString());
        }


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

