package com.example.kkutu.bitproject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.example.kkutu.bitproject.Client;
import com.example.kkutu.bitproject.GetDataClass;
import com.example.kkutu.bitproject.Login;


import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class MainActivity extends Activity {
    static  String user = " ",pass = " ", client_account_type = "CLIENT",contractor_account_type = "CONTRACTOR";
    static EditText userName, password;
    Button login;
    public static  String loginType = "Invalid";
    public static String account_Ref= null;
    static TextView textView;
    Animation blink,fade_in,rotate;
    ImageView imageView;
    MediaPlayer button_sound;
    GetJobRequestData getJobRequestData;
    static int NO = 1;
    Intent intentClient = null,intentlocation = null;
    GetDataClass getDataClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        userName = findViewById(R.id.editText3);
        password = findViewById(R.id.editText5);

        user = userName.getText().toString();
        pass = password.getText().toString();
        login = findViewById(R.id.button2);
        textView = findViewById(R.id.textView2);
        imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.bitlogo);
        blink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        fade_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

        getDataClass = new GetDataClass();
        getDataClass.execute();

        login.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.CUPCAKE)
            @Override
            public void onClick(View v) {


                getData(getDataClass.myUserList);
                System.out.println(" Redirect to " +loginType);
                if(loginType.equals("CLIENT")) {

                  //  Toast.makeText(getApplicationContext(), loginType + account_Ref, Toast.LENGTH_LONG).show();
                    button_sound = MediaPlayer.create(MainActivity.this,
                            R.raw.but_click);
                    // create a media player from raw directory and your source / mp3 name and put it on MediaPlayer
                    // reference
                    button_sound.start();
                    imageView.startAnimation(fade_in);
                    intentClient = new Intent(getApplicationContext(), Client.class);
                   // intentClient.putExtra(int account_Ref);
                    System.out.println(account_Ref);
                    intentClient.putExtra("key1",account_Ref);

                    startActivity(intentClient);
                            }
                else if (loginType.equals("CONTRACTOR")) {


                    button_sound = MediaPlayer.create(MainActivity.this,
                            R.raw.but_click);
                    // create a media player from raw directory and your source / mp3 name and put it on MediaPlayer
                    // reference
                    button_sound.start();
                    imageView.startAnimation(fade_in);
                  //  Toast.makeText(getApplicationContext(), loginType, Toast.LENGTH_SHORT).show();
                    intentClient = new Intent(getApplicationContext(), Contractor.class);
                    intentClient.putExtra("key1",account_Ref);
                    startActivity(intentClient);


                }else{
                    Toast.makeText(getApplicationContext(), "Invalid user name/password !", Toast.LENGTH_LONG).show();
                }


            }
        });

    }
    public  static void getData(ArrayList<Login> loginArrayList) {
        user = userName.getText().toString();
        pass = password.getText().toString();

        System.out.println(" array size  " + loginArrayList.size());
        String s = " ";
        for (int i = 0; i < loginArrayList.size(); i++) {

                   //System.out.println(user + "  " +pass + " " + client);
                   System.out.println(loginArrayList.get(i).getUserName() +  " " +
                                      loginArrayList.get(i).getPassword() +  " "+
                                      loginArrayList.get(i).getAccount_Type() + " " +
                           loginArrayList.get(i).getAccount_Ref()
                   );
            s = s + loginArrayList.get(i).getUserName() + " " + loginArrayList.get(i).getPassword() + " "+ loginArrayList.get(i).getAccount_Type()+ " "+ "\n";
            if(user.equals(loginArrayList.get(i).getUserName()) && pass.equals(loginArrayList.get(i).getPassword()) &&  client_account_type.equals(loginArrayList.get(i).getAccount_Type()))
            {
                    System.out.println("Client");
               // textView.setText("Client");
                loginType ="CLIENT";
                account_Ref = loginArrayList.get(i).getAccount_Ref();
               // intentClient =new Intent(list,Client.class);
              //return "Y";
               break;


            }
            else if(user.equals(loginArrayList.get(i).getUserName()) && pass.equals(loginArrayList.get(i).getPassword()) &&  contractor_account_type.equals(loginArrayList.get(i).getAccount_Type()))
            {
                //textView.setText("Contractor");
                System.out.println("Contractor");
                loginType ="CONTRACTOR";
                account_Ref = loginArrayList.get(i).getAccount_Ref();
                break;

                //  return "Y";
            }
            else
            {
               // textView.setText("Not a valid username and password");

                //return "N";
            }

        }

       // textView.setText(s);
    }


}
