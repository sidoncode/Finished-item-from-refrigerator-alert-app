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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.DashboardActivity;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.R;

import java.util.HashMap;
import java.util.Map;

public class EditNote extends AppCompatActivity {
    Intent data;
    ProgressBar mProgressBar;


    EditText edttxtnotecontent;
    EditText edttxtnotetitle;


    FirebaseFirestore fbfirestore;

    FloatingActionButton fab;

    FirebaseUser user;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editnote);

        fbfirestore = FirebaseFirestore.getInstance(); // init the firebase connection to the database //

        user = FirebaseAuth.getInstance().getCurrentUser();

        edttxtnotecontent = (EditText) findViewById(R.id.editNoteContent);
        edttxtnotetitle = (EditText) findViewById(R.id.editNoteTitle);

        fab = (FloatingActionButton) findViewById(R.id.saveEditedNote);
        data = getIntent();

        String title = data.getStringExtra("title");
        String content = data.getStringExtra("content");

        edttxtnotetitle.setText(title);
        edttxtnotecontent.setText(content);


        mProgressBar = (ProgressBar) findViewById(R.id.progressBar2);




        // set onclick listeners //
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"btn floating action button is clicked",Toast.LENGTH_SHORT).show();
                String note_content = edttxtnotecontent.getText().toString();
                String note_title = edttxtnotetitle.getText().toString();

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
                    Intent trasition_to_mainactivity  = new Intent(EditNote.this, DashboardActivity.class);
                    startActivity(trasition_to_mainactivity);
                    finish();

                }


                DocumentReference docref =
                        fbfirestore.collection("notes").document(user.getUid()).
                                collection("myNotes")
                                .document(data.getStringExtra("noteId"));

                Map<String,Object> note = new HashMap<>();
                note.put("title",note_title);
                note.put("content",note_content);

                docref.update(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Note added",Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(getApplicationContext(),DashboardActivity.class));

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Note not added",Toast.LENGTH_SHORT).show();
                        mProgressBar.setVisibility(View.VISIBLE);

                    }
                });

            }
        });
    }
}
