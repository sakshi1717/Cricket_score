package com.example.cricketapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
//import androidx.multidex.MultiDex;
//import androidx.multidex.MultiDex;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
//import android.support.v7.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerView;

    //private String url = "https://cricapi.com/api/cricket/?apikey=S2nwGwwH2xXI6LW41fuVyUTwy0g1";
    //https://cricapi.com/api/cricket
    private String url = "https://cricapi.com/api/matches/?apikey=S2nwGwwH2xXI6LW41fuVyUTwy0g1";
    private RecyclerView.Adapter mAdapter;
    private List<Model> modelList;
    private DrawerLayout drawer;
    private ImageView home_btn,dash_menu,prof_disp;

    private FirebaseAuth auth;
    private Button logoutBtn;
    /*@Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();

        //Intent intent = new Intent(this,LoginActivity.class);
        //finish();
        if(auth.getCurrentUser() == null)
            startActivity(new Intent(this, LoginActivity2.class));
        else{
            Toast.makeText(this,"Already logged in",Toast.LENGTH_LONG).show();
        }
        setContentView(R.layout.activity_main);
        //loadFragment(new HomeFragment());

        /*BottomNavigationView navigation1 = findViewById(R.id.navigation);
        navigation1.setOnNavigationItemSelectedListener(this);
        */
        /*logoutBtn = findViewById(R.id.logout_btn);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity2.class));
                finish();
            }
        });*/

        dash_menu = findViewById(R.id.dash_menu);
        home_btn = findViewById(R.id.home_btn);
        prof_disp = findViewById(R.id.prof_disp);
        dash_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, dashboard.class));
            }
        });

        prof_disp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });





        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
       // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentAbout()).commit();
        //navigationView.setCheckedItem(R.id.nav_view);


        mRecyclerView = findViewById(R.id.recyclerview);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        modelList = new ArrayList<>();
        
        loadUrlData();
    }

  /*  @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.logout:{
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity2.class));
                finish();
                break;
            }
            case R.id.profileMenu: {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                break;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //Fragment fragment = null;
        switch(item.getItemId()){

            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,  new FragmentAbout()).commit();
                break;

            case R.id.nav_update:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UpdateFragment()).commit();
                break;

            case R.id.nav_fav:
                startActivity(new Intent(MainActivity.this, favorites.class));
                break;



            /*case R.id.navigation_dashboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2, new DashboardFragment()).commit();
                break;*/


        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }



    private void loadUrlData() {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Loading.....");
        pd.show();
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                pd.dismiss();

                try{
                    JSONArray jsonArray = new JSONObject(response).getJSONArray("matches");

                    for (int i = 0; i < jsonArray.length();i++){
                    try {

                        String uniqueId = jsonArray.getJSONObject(i).getString("unique_id");
                        String team1 = jsonArray.getJSONObject(i).getString("team-1");
                        String team2 = jsonArray.getJSONObject(i).getString("team-2");
                        String matchType = jsonArray.getJSONObject(i).getString("type");
                        String matchStatus = jsonArray.getJSONObject(i).getString("matchStarted");
                        if (matchStatus.equals("true")){
                            matchStatus = "Match Started";
                        }
                        else{
                            matchStatus = "Match not started";
                        }
                        String dateTimeGMT = jsonArray.getJSONObject(i).getString("dateTimeGMT");

                        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                        format1.setTimeZone(TimeZone.getTimeZone(dateTimeGMT));
                        Date date = format1.parse(dateTimeGMT);

                        SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        format2.setTimeZone(TimeZone.getTimeZone("GMT"));
                        String dateTime = format2.format(date);

                        Model model = new Model(uniqueId,team1, team2, matchType, matchStatus, dateTime);
                        modelList.add(model);

                    }
                    catch (Exception e){
                        Toast.makeText(MainActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                    }

                    mAdapter = new MyAdapter(modelList,getApplicationContext());
                    mRecyclerView.setAdapter(mAdapter);

                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Error: "+error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
