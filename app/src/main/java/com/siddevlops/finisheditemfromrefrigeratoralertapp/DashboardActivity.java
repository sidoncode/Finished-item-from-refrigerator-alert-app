package com.siddevlops.finisheditemfromrefrigeratoralertapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{

    private CardView Cardview1,Cardview2,Cardview3,Cardview4,Cardview5,Cardview6;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

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
                Intent ListingActivity = new Intent(DashboardActivity.this,ListingActivity.class);
                startActivity(ListingActivity);
                finish();
            }
        });


        Cardview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
