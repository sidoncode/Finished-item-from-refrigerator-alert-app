package com.siddevlops.finisheditemfromrefrigeratoralertapp;

import android.content.Context;
import android.icu.util.Measure;
import android.os.Bundle;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.userauth.ModelEatingHabit;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EatingHabit extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private DatabaseReference mDatabaseReference;

    //private

    private ArrayList<ModelEatingHabit> messagesarraylist;

    private RecyclerAdapter mRecyclerAdapter;
    private Context mContext;






    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eatinghabit);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        //fire base //

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        //array list//

        messagesarraylist = new ArrayList<>();

        //get data method//

        GetDatafromFirebase();
    }

    private void GetDatafromFirebase() {


        Query query = mDatabaseReference.child("message");

        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ClearAll();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){

                    ModelEatingHabit modelEatingHabit = new ModelEatingHabit();

                    modelEatingHabit.setImage(snapshot1.child("image").getValue().toString());
                    modelEatingHabit.setName(snapshot1.child("name").getValue().toString());

                    messagesarraylist.add(modelEatingHabit);


                }
                mRecyclerAdapter = new RecyclerAdapter(getApplicationContext(),messagesarraylist);
                mRecyclerView.setAdapter(mRecyclerAdapter);
                mRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void ClearAll(){

        if(messagesarraylist != null) {
            messagesarraylist.clear();

            if(mRecyclerAdapter != null){
                mRecyclerAdapter.notifyDataSetChanged();
            }
        }
        messagesarraylist = new ArrayList<>();

        }

}


