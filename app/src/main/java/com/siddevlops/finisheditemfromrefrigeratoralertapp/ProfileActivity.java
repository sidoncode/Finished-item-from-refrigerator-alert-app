package com.siddevlops.finisheditemfromrefrigeratoralertapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.userauth.Login;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.userauth.Registration;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.userauth.Splash;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSignout;

    FirebaseAuth mFirebaseAuth;
    FirebaseUser user;


    private TextView txtsignout;

    private Class acone;

    private TextView edtshow;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnSignout = (Button) findViewById(R.id.btnSignout);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mFirebaseAuth = FirebaseAuth.getInstance();
        user = mFirebaseAuth.getCurrentUser();

        txtsignout = (TextView) findViewById(R.id.txtsignout);

        acone = (Class) DashboardActivity.class;

        edtshow = (TextView) findViewById(R.id.edtshow);

        Login obj = new Login();

        String email  = obj.getVal_email();

        edtshow.setText(email);
        txtsignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                CheckUser();

                Toast.makeText(getApplicationContext(), "Logged Out", Toast.LENGTH_SHORT).show();
            }

            private void CheckUser() {

                if (user.isAnonymous()) {
                    // 1 -> Anonymous//
                    // 0 -> no Anonymous //
                    displayable();
                    Log.i("display fx called","display fx called");

                } else {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getApplicationContext(), Splash.class));
                }
            }

            private void displayable(){
                AlertDialog.Builder warning = new AlertDialog.Builder(ProfileActivity.this,R.style.Myalertdialog)
                        .setTitle("Are you sure ?")
                        .setMessage("You are logged in with Temporary Account. Logging out will Delete All the notes.")
                        .setPositiveButton("Sync Note", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseUser usr = mFirebaseAuth.getCurrentUser();

                               // Intent send_user_to_other_val = new Intent(startActivity(ProfileActivity.this,Registration.class));

                                //startActivity(new Intent(ProfileActivity.this, Registration.class));

                                Intent i = new Intent(ProfileActivity.this,Login.class);
                                startActivity(i);

                                i.putExtra("firebaseuser",user);
                                FirebaseUser user = mFirebaseAuth.getCurrentUser();


                                Log.i("onclick positive","on click positive");
                                finish();
                            }
                        }).setNegativeButton("Logout", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // ToDO: delete all the notes created by the Anon user
                                // TODO: delete the anon user

                                user.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        startActivity(new Intent(ProfileActivity.this, Login.class));
                                        finish();
                                        //overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
                                    }
                                });
                            }
                        });
                warning.show();
            }
        });
    }
    @Override
    public void onClick(View v) { }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ProfileActivity.this,DashboardActivity.class));
        super.onBackPressed();
    }
}


