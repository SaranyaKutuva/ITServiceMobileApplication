package com.example.kkutu.bitproject;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import static com.example.kkutu.bitproject.MainActivity.*;

public class GetDataClass extends AsyncTask<Void,Void,Void> {
    String datainstring = " ";
    String formatString = " ";
    public static String loginType ="Invalid";

    ArrayList<Login> myUserList = new ArrayList<>();

    GetDataClass(){
        super();
    }
    Login user;
    // reference to Entity class

    JSONArray array1;
    @Override
    protected Void doInBackground(Void... voids) {
        InputStream inputStream = null;
        String strJSON = "";

        try {
           // URL url = new URL("https://api.myjson.com/bins/wr1wk");
            URL url = new URL("https://api.myjson.com/bins/1gymwd");
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

            JSONObject object = new JSONObject(strJSON);
            JSONArray objJSONArray = object.getJSONArray("bit_user_logon");
           // JSONArray objJSONAraay2 = object.getJSONArray("bit_skill");

//            for (int i = 0; i < objJSONAraay2.length(); i++) {
//
//                JSONObject objJSONObject = objJSONAraay2.getJSONObject(i);
//                formatString = formatString +  objJSONObject.getInt("Skill_Id") +
//                        objJSONObject.getString("Skill_Type");
//
//                skill = new Skills();
//                skill.setSkill_Id(objJSONObject.getInt("Skill_Id"));
//                skill.setSkill_Name(objJSONObject.getString("Skill_Type"));
//                mystudent.add(skill);
//            }

            for (int j = 0; j < objJSONArray.length(); j++) {

                JSONObject objJSONObject1 = objJSONArray.getJSONObject(j);
                formatString = formatString + objJSONObject1.getString("UserName") +
                        objJSONObject1.getString("Password") +
                        objJSONObject1.getString("account_Type") + objJSONObject1.getString("account_Ref");

                user = new Login();
                user.setUserName(objJSONObject1.getString("UserName"));
                user.setPassword(objJSONObject1.getString("Password"));
                user.setAccount_Type(objJSONObject1.getString("account_Type"));
                user.setAccount_Ref(objJSONObject1.getString("account_Ref"));
                // mystudent.add(skill);
                myUserList.add(user);


            }

        } catch (Exception e) {
            Log.d("Emergency","Error syncdata to SQLite :" + e.toString() );
            System.out.println(e.toString());
        }

        return  null;
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
