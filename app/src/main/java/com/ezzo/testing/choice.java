package com.ezzo.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class choice extends AppCompatActivity {


    Button b1, b2, b3,b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3=findViewById(R.id.b3);
//        b4=findViewById(R.id.b4);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(choice.this, physics.class));


            }


        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(choice.this, chimistry.class));


            }


        });

    }


}