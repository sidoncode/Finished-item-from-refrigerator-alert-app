package com.siddevlops.finisheditemfromrefrigeratoralertapp.userauth;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.Appintro;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.DashboardActivity;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.R;

public class Splash extends AppCompatActivity {

    private TextView txtsplash;

    FirebaseAuth fauth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        fauth = FirebaseAuth.getInstance();


        txtsplash = (TextView) findViewById(R.id.txtspash);

        Handler handler  = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(fauth.getCurrentUser() != null){
                    startActivity(new Intent(getApplicationContext(), Appintro.class));
                    finish();
                }else {
                    fauth.signInAnonymously().addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(getApplicationContext(),"Sign up successfully",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Splash.this,DashboardActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"SignUp/ Logged In Failed",Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(),));//
                            finish();
                        }
                    });
                }
            }
        },1000);


    }

}


