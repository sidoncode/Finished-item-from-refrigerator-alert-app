package com.siddevlops.finisheditemfromrefrigeratoralertapp.userauth;

import com.google.android.gms.tasks.Task;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.userauth.Splash;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.DashboardActivity;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.R;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private ImageView logo;
    //private Button btnTwitter;
    private Button btnSignIn;
    private TextView forgotPass,signUp;
    private ImageView btnTwitter;
    private AutoCompleteTextView password;
    private ImageView ivSignIn;
    private TextView tvSignIn;

    FirebaseAuth fact;

    public AutoCompleteTextView atvEmailLog;


    public String val_email;



    public String getVal_email() {
        return val_email;
    }

    public void setVal_email(String val_email) {
        this.val_email = val_email;
    }




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getActionBar().setTitle("Create New Account");

        fact = FirebaseAuth.getInstance();


        logo = findViewById(R.id.ivLogLogo);
        ivSignIn = findViewById(R.id.ivSignIn);
        btnTwitter = findViewById(R.id.ivFacebook);
        atvEmailLog = findViewById(R.id.atvEmailLog);
        password = findViewById(R.id.atvPasswordLog);
        forgotPass = findViewById(R.id.tvForgotPass);
        signUp = findViewById(R.id.tvSignIn);
        btnSignIn = findViewById(R.id.btnSignIn);

        tvSignIn = findViewById(R.id.tvSignIn);


        Task<AuthResult> user = fact.signInAnonymously();



        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 val_email = atvEmailLog.getText().toString();
                String val_password = password.getText().toString();

                Toast.makeText(getApplicationContext(),"email" +
                        val_email + "password" +
                        val_password,Toast.LENGTH_SHORT).show();


                AuthCredential credential = EmailAuthProvider.getCredential(val_email,val_password);

                fact.getCurrentUser().linkWithCredential(credential).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(getApplicationContext(),
                                "Notes are Synced",Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(getApplicationContext(), DashboardActivity.class));

                        FirebaseUser usr = fact.getCurrentUser();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Try Again" +e.getMessage(),
                                Toast.LENGTH_SHORT).show();
                        //Log.i("error login",e.printStackTrace(););
                    }
                });

            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Registration.class));
            }
        });
    }

    @Override
    public  boolean onOptionsItemSelected(@NonNull MenuItem item){
        startActivity(new Intent(this, ListActivity.class));
        finish();
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
