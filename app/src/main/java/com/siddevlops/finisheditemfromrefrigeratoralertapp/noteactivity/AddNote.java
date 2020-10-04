package com.siddevlops.finisheditemfromrefrigeratoralertapp.noteactivity;

import com.siddevlops.finisheditemfromrefrigeratoralertapp.noteactivity.EditNote;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.R;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddNote extends AppCompatActivity{

      FirebaseFirestore fstore;
      FloatingActionButton fb;

      EditText notetitle;
      EditText notecontent;

      ProgressBar mProgressBar;
      FirebaseUser user;

      private DatePickerDialog.OnDateSetListener mOnDateSetListener;
      private DatePickerDialog.OnCancelListener mOnCancelListener;
      private DatePickerDialog mDatePicker;
      private EditText edited;

     public String date;


    private  EditText mEditText;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnote);

        fstore = FirebaseFirestore.getInstance();

        user = FirebaseAuth.getInstance().getCurrentUser();

        fb = findViewById(R.id.fab);

        edited = (EditText) findViewById(R.id.expdate);

        notecontent = (EditText) findViewById(R.id.addNoteContent);
        notetitle = (EditText) findViewById(R.id.addNoteTitle);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);



        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getApplicationContext(),"btn floating action button is clicked",Toast.LENGTH_SHORT).show();
                String note_content = notecontent.getText().toString();
                String note_title = notetitle.getText().toString();
                String expdate = edited.getText().toString();





                if(note_content.isEmpty()){
                    Toast.makeText(getApplicationContext(),"The Note Content is Empty",Toast.LENGTH_SHORT).show();
                }
                else if(note_title.isEmpty()){
                    Toast.makeText(getApplicationContext(),"The Note Title is Empty",Toast.LENGTH_SHORT).show();
                }
                else if(note_content.isEmpty() && note_title.isEmpty()){
                    Toast.makeText(getApplicationContext(),"The Note Content and Note Title is Empty",
                            Toast.LENGTH_SHORT).show();
                }else if (expdate.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter The Expiry Date Of the Food Product",
                            Toast.LENGTH_SHORT).show();


                    //                    mProgressBar.setVisibility(View.VISIBLE);
                    /**AlertDialog.Builder builder = new AlertDialog.Builder(
                            AddNote.this,R.style.
                            MaterialAlertDialog_MaterialComponents_Title_Icon
                    );

                    final View customlayout= getLayoutInflater().inflate(R.layout.expdialog,null);


                    builder.setView(customlayout);

                    builder.setTitle("Custom Dialog");

                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(),"positive btn",Toast.LENGTH_LONG).show();

                            mEditText = customlayout.findViewById(R.id.editText);

                             date  = mEditText.getText().toString();
                            Log.i("editshowella",date);

                        }

                    }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(),
                                    "The Food Expiry Date is Required Field",Toast.LENGTH_LONG).show();
                        }
                    });

                    builder.show(); **/



                    /**Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    final int day = cal.get(Calendar.DAY_OF_MONTH);

                     DatePickerDialog datePickerDialog = new DatePickerDialog(AddNote.this,
                            android.R.style.Theme_DeviceDefault_Dialog_MinWidth,mOnDateSetListener, year,month,day);
                    datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    datePickerDialog.show();

                    /**datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            String date = month + "/" + dayOfMonth + "/" + month;

                        }
                    });

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
                    });



                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Positive", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            //Toast.makeText(getApplicationContext(),"Positive button clicked",Toast.LENGTH_LONG).show();

                            //Intent trasition_to_mainactivity  = new Intent(AddNote.this, DashboardActivity.class);
                            //startActivity(trasition_to_mainactivity);
                            //finish();
                        }
                    });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Negative", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(),"Negative clicked",Toast.LENGTH_LONG).show();
                        }
                    });**/



                }
                else if(!note_content.isEmpty() || !note_title.isEmpty() || !expdate.isEmpty()) {


                    Intent i = new Intent(Intent.ACTION_INSERT);

                    //i.setData(CalendarContract.Events.DESCRIPTION,note_content.ge)

                    i.putExtra(CalendarContract.Events.DESCRIPTION,note_content);
                    i.putExtra(CalendarContract.Events.TITLE,note_title);
                    i.putExtra(CalendarContract.Events.ALL_DAY,true);

                    if(i.resolveActivity(getPackageManager()) != null){

                        startActivity(i);

                        Log.i("startactivity","inside the if stmnt");


                    }
                    Log.i("startactivity","outside the if stmnt");




                    DocumentReference docref = fstore.collection("notes").document(user.getUid()).collection("myNotes").document();


                    Map<String, Object> note = new HashMap<>();
                    note.put("title", note_title);
                    note.put("content", note_content);
                    note.put("exp", expdate);

                    docref.set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(), "Note added", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Note not added", Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });
        //edited.setText(date);
    }




}
