package com.siddevlops.finisheditemfromrefrigeratoralertapp.noteactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.DashboardActivity;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.R;

import java.util.HashMap;
import java.util.Map;

public class AddNote extends AppCompatActivity {

      FirebaseFirestore fstore;
      FloatingActionButton fb;

      EditText notetitle;
      EditText notecontent;

      ProgressBar mProgressBar;
      FirebaseUser user;


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

                if(note_content.isEmpty()){
                    Toast.makeText(getApplicationContext(),"The Note Content is Empty",Toast.LENGTH_SHORT).show();
                }
                else if(note_title.isEmpty()){
                    Toast.makeText(getApplicationContext(),"The Note Title is Empty",Toast.LENGTH_SHORT).show();
                }
                else if(note_content.isEmpty() && note_title.isEmpty()){
                    Toast.makeText(getApplicationContext(),"The Note Content and Note Title is Empty",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Note added",Toast.LENGTH_SHORT).show();

                    mProgressBar.setVisibility(View.VISIBLE);
                    Intent trasition_to_mainactivity  = new Intent(AddNote.this, DashboardActivity.class);
                    startActivity(trasition_to_mainactivity);
                    finish();

                }

                DocumentReference docref = fstore.collection(
                        "notes").
                        document(user.getUid()).collection("myNotes").document();

                Map<String,Object> note = new HashMap<>();
                note.put("title",note_title);
                note.put("content",note_content);

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
