package com.ezzo.testing;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Handler;
import android.util.Log;
import android.view.View;

import android.widget.Toast;


public class chimistry extends AppCompatActivity {

    Button b1, b2;
    EditText ed1, ed2,ed3,ed4,ed5,ed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physics);

        ed5 = findViewById( R.id.ed5 );
        ed = findViewById( R.id.ed);
        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        ed3 = findViewById(R.id.ed3);
        ed4= findViewById( R.id.ed4);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);



        b2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {



                startActivity(new Intent(chimistry.this, MainActivity.class));

            }


        });

        b1.setOnClickListener(new  View.OnClickListener(){



            @Override
            public void onClick(View v) {
                final String name,option1,option2,option3,option4,right_answer;

                name=String.valueOf(ed1.getText());
                option1=String.valueOf(ed2.getText());
                option2=String.valueOf(ed3.getText());
                option3=String.valueOf(ed5.getText());
                option4=String.valueOf(ed.getText());
                right_answer=String.valueOf(ed4.getText());


                if ((!name.equals("")) && (!option1.equals("")) &&(!option2.equals(""))&& (!right_answer.equals(""))&&(!option3.equals( "" ))&&(!option4.equals("")) ){
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[6];
                            field[0] = "username";
                            field[1] = "option1";
                            field[2] = "option2";
                            field[3]="option3";
                            field[4]="option4";
                            field[5]= "right_answer";


                            //Creating array for data
                            String[] data = new String[6];
                            data[0] =name;
                            data[1] = option1;
                            data[2] = option2;
                            data[3] = option3;
                            data[4] = option4;
                            data[5] = right_answer;





                            PutData putData = new PutData("http://192.168.64.2/Login/chimistry.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();


                                    char str=result.charAt(7);
                                    Log.i("PutData", String.valueOf(str));



                                    if ("n".equalsIgnoreCase(String.valueOf(str))){

                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();

                                        startActivity(new Intent(chimistry.this, chimistry.class));
                                        finish();
                                    }

                                    else{

                                        Toast.makeText((getApplicationContext()),result,Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }
                            //End Write and Read data with URL
                        }
                    });


                }
                else
                {
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                }




            }


        });

    }
}