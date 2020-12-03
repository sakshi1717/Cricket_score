package com.example.cricketapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity2 extends AppCompatActivity {

    private EditText passwordEmail;
    private Button resetPassword,Back;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_activtiy);
        passwordEmail = (EditText)findViewById(R.id.email_edt_text);
        resetPassword = (Button)findViewById(R.id.reset_pass_btn);
        Back = (Button)findViewById(R.id.back_btn);
        firebaseAuth = FirebaseAuth.getInstance();

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail = passwordEmail.getText().toString().trim();

                if(useremail.equals("")){
                    Toast.makeText(ForgotPasswordActivity2.this, "Please enter your registered email ID", Toast.LENGTH_SHORT).show();
                }else{
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ForgotPasswordActivity2.this, "Password reset email sent!", Toast.LENGTH_SHORT).show();
                                finish();
                                NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                                Notification notify=new Notification.Builder
                                        (getApplicationContext()).setContentTitle("CricketApp").setContentText("Reset Password Mail sent ").
                                        setContentTitle("Check your Mail").setSmallIcon(R.drawable.ic_sms_black_24dp).build();

                                notify.flags |= Notification.FLAG_AUTO_CANCEL;
                                notif.notify(0, notify);

                                startActivity(new Intent(ForgotPasswordActivity2.this, MainActivity.class));
                            }else{
                                Toast.makeText(ForgotPasswordActivity2.this, "Error in sending password reset email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}
