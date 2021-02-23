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


public class Q extends AppCompatActivity {

    Button b1, b2;
    EditText ed1, ed2;
    TextView tx1, tx2;
    int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q);

        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);



        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });

        b1.setOnClickListener(new  View.OnClickListener(){


            @Override
            public void onClick(View v) {
                final String name,password;

                name=String.valueOf(ed1.getText());
                password=String.valueOf(ed2.getText());

                if ((!name.equals("")) && (!password.equals(""))){
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";
                            //Creating array for data
                            String[] data = new String[2];
                            data[0] =name;
                            data[1] = password;


                            PutData putData = new PutData("http://192.168.64.2/Login/physics.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();


                                    char str=result.charAt(7);
                                    Log.i("PutData", String.valueOf(str));



                                    if ("n".equalsIgnoreCase(String.valueOf(str))){

                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();

                                        startActivity(new Intent(Q.this, MainActivity.class));

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
                    Toast.makeText(getApplicationContext(),"errgrror",Toast.LENGTH_SHORT).show();
                }




            }


        });

    }
}