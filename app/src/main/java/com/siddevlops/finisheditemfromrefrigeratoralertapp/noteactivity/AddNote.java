package com.siddevlops.finisheditemfromrefrigeratoralertapp.noteactivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.role.RoleManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.Appintro;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.DashboardActivity;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.R;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.userauth.Splash;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddNote extends AppCompatActivity {

      FirebaseFirestore fstore;
      FloatingActionButton fb;

      EditText notetitle;
      EditText notecontent;

      ProgressBar mProgressBar;
      FirebaseUser user;

      private DatePickerDialog.OnDateSetListener mOnDateSetListener;
      private DatePickerDialog.OnCancelListener mOnCancelListener;

      private DatePickerDialog mDatePicker;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnote);

        fstore = FirebaseFirestore.getInstance();

        user = FirebaseAuth.getInstance().getCurrentUser();

        fb = findViewById(R.id.fab);

        notecontent = (EditText) findViewById(R.id.addNoteContent);
        notetitle = (EditText) findViewById(R.id.addNoteTitle);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);



        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"btn floating action button is clicked",Toast.LENGTH_SHORT).show();
                String note_content = notecontent.getText().toString();
                String note_title = notetitle.getText().toString();
                String exp_date = null;





                if(note_content.isEmpty()){
                    Toast.makeText(getApplicationContext(),"The Note Content is Empty",Toast.LENGTH_SHORT).show();
                }
                else if(note_title.isEmpty()){
                    Toast.makeText(getApplicationContext(),"The Note Title is Empty",Toast.LENGTH_SHORT).show();
                }
                else if(note_content.isEmpty() && note_title.isEmpty()){
                    Toast.makeText(getApplicationContext(),"The Note Content and Note Title is Empty",Toast.LENGTH_SHORT).show();
                }else{
                    //Toast.makeText(getApplicationContext(),"Note added",Toast.LENGTH_SHORT).show();

                    mProgressBar.setVisibility(View.VISIBLE);

                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);

                    final DatePickerDialog datePickerDialog = new DatePickerDialog(AddNote.this,
                            android.R.style.Theme_Holo_Dialog_MinWidth,mOnDateSetListener, year,month,day);
                    datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    datePickerDialog.show();


                    mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            Log.d("selected date", "date selected");

                        }
                    };

                    mOnCancelListener = new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            Toast.makeText(getApplicationContext(),"canceleed btn pressed",Toast.LENGTH_LONG).show();
                        }
                    };
                    /**datePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            Toast.makeText(getApplicationContext(),"dissmissss",Toast.LENGTH_LONG).show();
                        }
                    });**/
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Positive", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(),"Positive button clicked",Toast.LENGTH_LONG).show();

                            Intent trasition_to_mainactivity  = new Intent(AddNote.this, DashboardActivity.class);
                            startActivity(trasition_to_mainactivity);
                            finish();
                        }
                    });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Negative", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(),"Negative clicked",Toast.LENGTH_LONG).show();
                        }
                    });





                }

                DocumentReference docref = fstore.collection(
                        "notes").
                        document(user.getUid()).collection("myNotes").document();

                exp_date = exp_date.toLowerCase().toString().trim();

                Map<String,Object> note = new HashMap<>();
                note.put("title",note_title);
                note.put("content",note_content);
                note.put("Exp Date",10/10/10);

                docref.set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Note added",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Note not added",Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });


    }

}
