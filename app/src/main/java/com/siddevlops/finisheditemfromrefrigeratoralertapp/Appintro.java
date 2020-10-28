package com.siddevlops.finisheditemfromrefrigeratoralertapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class Appintro extends AppIntro {


    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor sharedprefeditor;

    public void transtion_main_activity(){
        Intent intent = new Intent(this,DashboardActivity.class);
        startActivity(intent);
        finish();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_appintro);

        // getSupportActionBar().hide();

        addSlide(AppIntroFragment.newInstance("Welcome to the app","first slide",R.drawable.img,
                ContextCompat.getColor(getApplicationContext(),R.color.purpel)));

        addSlide(AppIntroFragment.newInstance("Welcome to the app","first slide",R.drawable.slidetwo,
                ContextCompat.getColor(getApplicationContext(),R.color.blue)));


        addSlide(AppIntroFragment.newInstance("Welcome to the app","first slide",R.drawable.circle_rocket,
                ContextCompat.getColor(getApplicationContext(),R.color.white)));


        mSharedPreferences  = getApplicationContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        sharedprefeditor  = mSharedPreferences.edit();

        if(mSharedPreferences !=null){
            boolean checkshared = mSharedPreferences.getBoolean("checkstate",false);

            if(checkshared == true){
                startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
                finish();
            }
        }

    }


    @Override
    public void  onSkipPressed(Fragment currentFragment) {

        super.onSkipPressed(currentFragment);
        // Decide what to do when the user clicks on "Skip"
        transtion_main_activity();
        sharedprefeditor.putBoolean("checkstate",false).commit();
        finish();
    }

    @Override
    public void  onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Decide what to do when the user clicks on "Done"
        transtion_main_activity();
        sharedprefeditor.putBoolean("checkstate",true).commit();
        finish();
    }
}