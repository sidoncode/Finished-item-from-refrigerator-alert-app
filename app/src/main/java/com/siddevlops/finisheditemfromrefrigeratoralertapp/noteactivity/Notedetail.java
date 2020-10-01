package com.siddevlops.finisheditemfromrefrigeratoralertapp.noteactivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.siddevlops.finisheditemfromrefrigeratoralertapp.R;

import org.w3c.dom.Text;

public class Notedetail extends AppCompatActivity {

    private TextView noteDetailsContent;

    Intent data;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notedetail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Intent data = getIntent();



        TextView content = findViewById(R.id.noteDetailsContent);
        final TextView title = findViewById(R.id.noteDetailsTitle);
        TextView expdate= (TextView) findViewById(R.id.expdate);

        title.setText(data.getStringExtra("title"));
        content.setText(data.getStringExtra("content"));
        expdate.setText(data.getStringExtra("exp"));




        //content.setBackgroundColor(data.getIntExtra("code",0)); // default random value ->  0 //

        content.setBackgroundColor(getResources().getColor(data.getIntExtra("code",0)));



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show();

                Intent i = new Intent(view.getContext(),EditNote.class);

                i.putExtra("title",data.getStringExtra("title"));
                i.putExtra("content",data.getStringExtra("content"));
                i.putExtra("noteId",data.getStringExtra("noteId"));
                i.putExtra("exp",data.getStringExtra("exp"));
                startActivity(i);
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}