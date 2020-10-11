package com.siddevlops.finisheditemfromrefrigeratoralertapp;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.PermissionChecker;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.auth.User;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.model.Adapter;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private CardView Cardview1,Cardview2,Cardview3,Cardview4,Cardview5,Cardview6;

    // add view;

    private AdView adview1;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        adview1 = (AdView)findViewById(R.id.ad_view1);

        MobileAds.initialize(this,"ca-app-pub-9094130848994954/2051462044");

        AdRequest adRequest = new AdRequest.Builder().build();


        Log.i("ad","add");

        adview1.loadAd(adRequest);




        Cardview1 = findViewById(R.id.cardview1);
        Cardview2 = findViewById(R.id.cardview2);
        Cardview3 = findViewById(R.id.cardview3);
        Cardview4 = findViewById(R.id.cardview4);
        Cardview5 = findViewById(R.id.cardview5);
        Cardview6 = findViewById(R.id.cardview6);

        // set on click listerners
        Cardview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // listing activity -> for adding new notes //

                Intent ListingActivity = new Intent(DashboardActivity.this,ListingActivity.class);
                startActivity(ListingActivity);
                finish();
            }
        });


        Cardview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent FoodExpenses = new Intent(DashboardActivity.this,FoodExpenses.class);
                startActivity(FoodExpenses);
                finish();

            }
        });

        Cardview3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Profile_activity = new Intent(DashboardActivity.this,ProfileActivity.class);
                startActivity(Profile_activity);
                finish();
            }
        });


    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
