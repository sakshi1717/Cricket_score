package com.example.cricketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class dashboard extends AppCompatActivity {
    private ImageView ODIRankings,dash_menu,home_btn,prof_disp,favts,quiz_btn,stand_btn,map_park,video_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_menu_activity);

        ODIRankings = findViewById(R.id.ICCRanking);
        favts = findViewById(R.id.favts);
        quiz_btn = findViewById(R.id.trivia);
        stand_btn = findViewById(R.id.calendar);
        map_park = findViewById(R.id.park_maps);
        video_btn = findViewById(R.id.video);



        dash_menu = findViewById(R.id.dash_menu);
        home_btn = findViewById(R.id.home_btn);
        prof_disp = findViewById(R.id.prof_disp);

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dashboard.this, MainActivity.class));
            }
        });

        prof_disp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dashboard.this, ProfileActivity.class));
            }
        });



        ODIRankings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dashboard.this, odirankings.class));
            }
        });
        favts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dashboard.this, favorites.class));
            }
        });

        quiz_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dashboard.this, Quiz.class));
            }
        });

        stand_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dashboard.this, standings.class));
            }
        });

        map_park.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dashboard.this, MapsActivity.class));
            }
        });

        video_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dashboard.this, VideoPlayer.class));
            }
        });


    }
}
