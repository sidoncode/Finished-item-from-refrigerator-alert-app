package com.siddevlops.finisheditemfromrefrigeratoralertapp.userauth;


import com.siddevlops.finisheditemfromrefrigeratoralertapp.userauth.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.DashboardActivity;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.R;

import java.util.Map;
import java.util.Set;

public class Registration extends AppCompatActivity {
    private AutoCompleteTextView atvUsernameReg;
    private AutoCompleteTextView atvEmailReg;
    private AutoCompleteTextView atvPasswordReg;
    private Button btnSignUp;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirebaseAuth = FirebaseAuth.getInstance();


        setContentView(R.layout.activity_registration);
        atvUsernameReg = (AutoCompleteTextView) findViewById(R.id.atvUsernameReg);
        atvEmailReg =(AutoCompleteTextView) findViewById(R.id.atvEmailReg);
        atvPasswordReg = (AutoCompleteTextView) findViewById(R.id.atvPasswordReg);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_val = atvEmailReg.getText().toString();
                String password_val = atvPasswordReg.getText().toString();

                if(email_val.isEmpty() || password_val.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Email or Password si empty",Toast.LENGTH_LONG).show();
                }
                else {
                    mFirebaseAuth.signInWithEmailAndPassword(email_val,password_val).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(getApplicationContext(),"sucees line no 63",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Registration.this,DashboardActivity.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Failure", Toast.LENGTH_LONG).show();
                            Log.i("xxsss",e.getMessage());
                            //Log.i("error2",e.printStackTrace(););
                        }
                    });
                }
            }
        });

    }
}
