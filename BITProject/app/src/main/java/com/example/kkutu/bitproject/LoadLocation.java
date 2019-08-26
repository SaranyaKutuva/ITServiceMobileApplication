package com.example.kkutu.bitproject;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class LoadLocation {
    ClientLocation location;
    String datainstring = " ";
    String formatString = " ";


    ArrayList<ClientLocation> myLocationList = new ArrayList<>();



    // reference to Entity class

    JSONArray array1;

    public void populateArray() {


        InputStream inputStream = null;
        String strJSON = "";

        try {
            // URL url = new URL("https://api.myjson.com/bins/wr1wk");
            URL url = new URL("https://api.myjson.com/bins/1ckxnt");
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();

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
            Log.d("Emergency", "Error Convert To JSON :" + e.toString());
        }


        try {

            JSONObject object = new JSONObject(strJSON);
            JSONArray objJSONArray = object.getJSONArray("bit_client_location");

            for (int j = 0; j < objJSONArray.length(); j++) {

                JSONObject objJSONObject1 = objJSONArray.getJSONObject(j);
                formatString = formatString + objJSONObject1.getInt("Location_Id") +
                        objJSONObject1.getString("Location_Name") +
                        objJSONObject1.getString("Suburb") + objJSONObject1.getString("StreetAddress")
                        + objJSONObject1.getInt("Client_Id");

                location = new ClientLocation();
                location.setLocation_Id(objJSONObject1.getInt("Location_Id"));
                location.setLocation_Name(objJSONObject1.getString("Location_Name"));
                location.setClient_Id(objJSONObject1.getInt("Client_Id"));
                myLocationList.add(location);
//                user.setUserName(objJSONObject1.getString("UserName"));
//                user.setPassword(objJSONObject1.getString("Password"));
//                user.setAccount_Type(objJSONObject1.getString("account_Type"));
//                user.setAccount_Ref(objJSONObject1.getString("account_Ref"));
//                // mystudent.add(skill);
                // myJobList.add(user);


            }

        } catch (Exception e) {
            Log.d("Emergency", "Error syncdata to SQLite :" + e.toString());
            System.out.println(e.toString());
        }

        //JobRequest.getData(myLocationList);

    }


}
