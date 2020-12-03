package com.example.cricketapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;



import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.Context.NOTIFICATION_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;
import static androidx.core.content.ContextCompat.getSystemServiceName;

public class UpdateFragment extends Fragment{
    private static final int NOTIFY_ME_ID=1337;

    private EditText newUsername,newUserage,newUseremail;
    private Button update;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private Object NotificationManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        final View inflate = inflater.inflate(R.layout.fragment_update, container, false);

        newUsername = (EditText)inflate.findViewById(R.id.update_name);
        newUserage = (EditText)inflate.findViewById(R.id.update_age);
        newUseremail = (EditText)inflate.findViewById(R.id.update_email);

        update = (Button)inflate.findViewById(R.id.save);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        final DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                newUsername.setText(userProfile.getUserName());
                newUserage.setText(userProfile.getUserAge());
                newUseremail.setText(userProfile.getUserEmail());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //return;
                Toast.makeText(getActivity(),databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }

            /*@Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UpdateFragment.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }*/
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = newUsername.getText().toString();
                String age = newUserage.getText().toString();
                String email = newUseremail.getText().toString();

                UserProfile userProfile = new UserProfile(age,email,name);
                databaseReference.setValue(userProfile);
                Toast.makeText(getActivity(),"Update Successfull",Toast.LENGTH_SHORT).show();
                 //addNotification();
                //finish();

             //   addNotification();



                //addNotification();


                startActivity(new Intent(getActivity(), MainActivity.class));
            }


        });
        return inflate;
    }



    /*@Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        //setContentView(R.layout.fragment_update);


    }
    @Override
    public void onActivityCreated()*/




}
