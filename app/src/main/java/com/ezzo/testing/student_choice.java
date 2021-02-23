package com.ezzo.testing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class student_choice extends AppCompatActivity {

    Button b1, b2, b3,b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_choice);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3=findViewById(R.id.b3);
//        b4=findViewById(R.id.b4);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(student_choice.this, showQ2.class));


            }


        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(student_choice.this, show_Chim.class));


            }


        });

    }

}
