package com.example.kkutu.bitproject;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Payment extends Activity implements View.OnClickListener {
Button Submit,message,email;
EditText Hour,Km;
    int notifyID = 1;
    String CHANNEL_ID = "my_channel_01";// The id of the channel.
    String channel_name = "manav";
    Button button;
    Context ctx;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);
        ImageView sms,mail,logo;
        logo = findViewById(R.id.imageView4);
        logo.setImageResource(R.drawable.bitlogo);
        Submit = findViewById(R.id.button13);
        Hour = findViewById(R.id.editText2);
        Km = findViewById(R.id.editText8);
        final String Hours_worked;
        final String Kilometer;
        Hours_worked = Hour.getText().toString();
        Kilometer =Hour.getText().toString();
        Submit.setOnClickListener(this);

        sms = findViewById(R.id.imageView12);
        mail = findViewById(R.id.imageView13);

        sms.setImageResource(R.drawable.ic_message);
        logo.setImageResource(R.drawable.bitlogo);
        mail.setImageResource(R.drawable.ic_mail);
        message = findViewById(R.id.button3);
        email = findViewById(R.id.button12);
        message.setOnClickListener(this);
        email.setOnClickListener(this);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Hour.getText().toString().equals(null) || Km.getText().toString().equals(null))
                {
                    Toast.makeText(getApplicationContext(),"Pls Enter the Details and then press sms",Toast.LENGTH_LONG).show();
                }
                else {
                    try {
                        SmsManager smsManager = SmsManager.getDefault();
                        //smsManager.sendTextMessage(phoneTextBox.getText().toString(), null, "The fee for the " + course + "is " + fees,
                        //      null, null);
                        smsManager.sendTextMessage("0420662968", null, "Hours Worked :3" + Hours_worked + "Kilo Meter Travelled: " + Kilometer,
                                null, null);
                        Toast.makeText(getApplicationContext(), "SMS Sent!",
                                Toast.LENGTH_LONG).show();


                        //Toast.makeText(getApplicationContext(), "SMS sent", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "SMS failed" + e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Hour.getText().toString().equals(null) || Km.getText().toString().equals(null))
                {
                    Toast.makeText(getApplicationContext(),"Pls Enter the Details and then press sms",Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent;
                    intent = new Intent(Intent.ACTION_SEND);

                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"elsaranya@gmail.com"});
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Hello Bit Service \n Hours Worked:" + Hours_worked + "\nKilometer Travelled:" + Kilometer);

                    intent.putExtra(Intent.EXTRA_BCC, new String[]{"saranya.eswareen@tafensw.edu.au"});
                    intent.putExtra(Intent.EXTRA_TEXT, "  ".toString());

                    intent.setType("message/rfc822");

                    startActivity(Intent.createChooser(intent, "send email"));
                }

            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Hour.getText().toString().equals(null) || Km.getText().toString().equals(null)) {
                    Toast.makeText(getApplicationContext(), "Pls Enter the Details and then press sms", Toast.LENGTH_LONG).show();
                } else {
                    final NotificationManager mNotific =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                    CharSequence name = "NotificationExample";
                    String desc = "This is Notification";
                    int imp = NotificationManager.IMPORTANCE_HIGH;
                    final String ChannelID = "my_channel_01";
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        // Step 2 : channel for notification
                        NotificationChannel mChannel = new NotificationChannel(ChannelID, name,
                                imp);
                        mChannel.setDescription(desc);
                        mChannel.setLightColor(R.color.colorPrimary);
                        mChannel.canShowBadge();
                        mChannel.setShowBadge(true);
                        mNotific.createNotificationChannel(mChannel);
                    }
                    // notification code - unique for each notification ID
                    final int ncode = 101;
                    // step 3 : Create notification and put on channel
                    String Body = "Your Payment Approved ";
                    // API NOTIFICATION
                    // NOTIFICATION method builder
                    // creating a notification and biniding it to channel
                    Notification n = new Notification.Builder(getApplicationContext(), ChannelID)
                            .setContentTitle(getPackageName())
                            .setContentText(Body)
                            .setBadgeIconType(R.mipmap.ic_launcher)
                            //       .setBadgeIconType(R.mipmap.ic_launcher)
                            .setSmallIcon(R.mipmap.ic_launcher_round)

                            .setAutoCancel(true)
                            .build();
                    Intent notificationIntent = new Intent(Intent.ACTION_VIEW);
                    notificationIntent.setData(Uri.parse("http://www.google.com"));
                    PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, 0);
                    // you can change this intent as per your design

// notify on service manager , the code of notification and your notification which you have build
                    mNotific.notify(ncode, n);

                }
            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}
