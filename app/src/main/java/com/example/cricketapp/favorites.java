package com.example.cricketapp;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class favorites extends AppCompatActivity {
    //private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = ;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    CheckBox checkBox,checkBox1,checkBox2;
    Button button;
    String result = "Hello.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites);
        addListenerOnButtonClick();
    }

    public void addListenerOnButtonClick() {
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        button = (Button) findViewById(R.id.btn1send);
        button.setOnClickListener(new View.OnClickListener() {

            private int MY_PERMISSIONS_REQUEST_SEND_SMS;

            @Override
            public void onClick(View view) {


                if (checkBox.isChecked()) {
                    String result1 = '\n'+
                            "IPL 2020(13th tournament) is postponed indefinitely due to the pandemic. Scheduled to be held in India";

                    result = result.concat(result1);
                }
                if (checkBox1.isChecked()) {
                    String result2 = '\n' +"The 2020 ICC Men's T20 World Cup is scheduled to be the seventh ICC Men's T20 World Cup tournament,with matches to be held in Australia from 18 October to 15 November 2020.";
                    result = result.concat(result2);
                }
                if (checkBox2.isChecked()) {
                    String result3 = '\n'+"The 2019 Ashes series (officially the Specsavers Ashes Series for sponsorship reasons) was a series of Test cricket matches played between England and Australia for The Ashes in August and September 2019.";
                    result = result.concat(result3);
                }
                String no = "08347378191";
                /*Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                PendingIntent pi= PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
                SmsManager sms= SmsManager.getDefault();
                sms.sendTextMessage(no, null, result, pi,null);
                Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                        Toast.LENGTH_LONG).show();*/

                // phoneNo = txtphoneNo.getText().toString();
                //message = txtMessage.getText().toString();

                //sendSMSMessage();

               //

               /* if (ContextCompat.checkSelfPermission(thisActivity,
                        Manifest.permission.READ_CONTACTS)
                        != PackageManager.PERMISSION_GRANTED) {

                    // Permission is not granted
                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getParent(),
                            Manifest.permission.SEND_SMS)) {
                        // Show an explanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.
                    } else {
                        // No explanation needed; request the permission
                        ActivityCompat.requestPermissions(thisActivity,
                                new String[]{Manifest.permission.READ_CONTACTS},
                                MY_PERMISSIONS_REQUEST_SEND_SMS);

                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.
                    }
                } else {
                    // Permission has already been granted
                }*/


                Log.i("Send SMS", "");
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);

                smsIntent.setData(Uri.parse("smsto:"));
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address"  , new String ("09148793834"));
                smsIntent.putExtra("sms_body"  , result);

                try {
                    startActivity(smsIntent);
                    finish();
                    Log.i("Finished sending SMS...", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(favorites.this,
                            "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


}
