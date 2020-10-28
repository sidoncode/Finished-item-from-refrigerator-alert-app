package com.siddevlops.finisheditemfromrefrigeratoralertapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.callback.OnPieSelectListener;
import com.razerdp.widget.animatedpieview.data.IPieInfo;
import com.razerdp.widget.animatedpieview.data.PieOption;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;





public class FoodExpenses extends AppCompatActivity {

    private EditText edt;
    private EditText edttwo;

    private Button btnsave;

    Double val1;
    Double val2;

    private EditText editTextNumberSigned;



    double val_f;
    Double val;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodexpenses);

        edt = (EditText) findViewById(R.id.edtfirst);
        edttwo = (EditText) findViewById(R.id.edtsecond);

        btnsave = (Button) findViewById(R.id.btnsave);

        editTextNumberSigned = findViewById(R.id.editTextNumberSigned);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val1 = Double.parseDouble(edt.getText().toString());
                val2 = Double.parseDouble(edttwo.getText().toString());

                edt.setText(String.valueOf(val1));
                edttwo.setText(String.valueOf(val2));

            }
        });


        edt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( (event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode== KeyEvent.KEYCODE_ENTER) ){
                    //Toast.makeText(FoodExpenses.this,"",Toast.LENGTH_LONG).show();
                    edt.setText(edt.getText().toString());

                }
                return false;
            }
        });



        Log.i("number in edttext","val" + val);


        AnimatedPieView animatedPieView = findViewById(R.id.piechart);

        AnimatedPieViewConfig config = new AnimatedPieViewConfig();


        int a = 1000;

        int b = 200;


        int c = Integer.parseInt(editTextNumberSigned.getText().toString());




        config.addData(new SimplePieInfo(200, Color.parseColor("#4c84ff"),"A"));
        config.addData(new SimplePieInfo(250, Color.parseColor("#18a3fe"),"B"));
        config.addData(new SimplePieInfo(200, Color.parseColor("#656565"),"C"));
        config.duration(1000);
        config.drawText(true);
        config.strokeMode(false);
        config.textSize(22);

        config.selectListener(new OnPieSelectListener<IPieInfo>() {
            @Override
            public void onSelectPie(@NonNull IPieInfo pieInfo, boolean isFloatUp) {
                Toast.makeText(FoodExpenses.this,pieInfo.getDesc() + " " + pieInfo.getValue(),Toast.LENGTH_LONG).show();
            }
        });

        animatedPieView.applyConfig(config);

        animatedPieView.start();

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(FoodExpenses.this,DashboardActivity.class));
        finish();
        super.onBackPressed();
    }
}
