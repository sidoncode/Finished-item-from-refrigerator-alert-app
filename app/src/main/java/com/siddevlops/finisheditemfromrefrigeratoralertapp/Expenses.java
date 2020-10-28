package com.siddevlops.finisheditemfromrefrigeratoralertapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class Expenses extends AppCompatActivity {

    private EditText edttextone;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_expenses);

        edttextone  =(EditText) findViewById(R.id.edttextone);


        edttextone.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){

                    String var = edttextone.getText().toString();

                    if(var.trim().isEmpty()){
                        Toast.makeText(getApplicationContext(),"Enter User Name",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"All Set to Go",Toast.LENGTH_LONG).show();

                        Intent i = new Intent(Expenses.this,FoodExpenses.class);
                        i.putExtra("var",var);
                        startActivity(i);

                    }

                    return true;

                }
                return false;
            }
        });


    }
}
