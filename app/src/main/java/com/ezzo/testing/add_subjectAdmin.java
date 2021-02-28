
package com.ezzo.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class add_subjectAdmin extends AppCompatActivity {

    Button b, b1, b2;
    EditText ed1, ed2;
    TextView tx1, tx2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject_admin);


        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);



        b = findViewById( R.id.b);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);


        tx1 = findViewById(R.id.textView);
        tx2 = findViewById(R.id.textView2);


        b.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(add_subjectAdmin.this, show_subjectAdmin.class));
                finish();
            }});






        b1.setOnClickListener(new  View.OnClickListener(){


            @Override
            public void onClick(View v) {
                final String subject,timer;

                subject=String.valueOf(ed1.getText());
                timer =String.valueOf(ed2.getText());








                if ((!subject.equals("")) ){


                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "Subject";
                            field[1] = "timer";

                            //Creating array for data
                            String[] data = new String[2];
                            data[0] =subject;
                            data[1] =timer;




                            PutData putData = new PutData("http://192.168.64.2/Login/add_subject.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();


                                    char str=result.charAt(1);
                                    Log.i("PutData", String.valueOf(str));





                                    if ("o".equalsIgnoreCase(String.valueOf(str))){

                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();

                                        startActivity(new Intent(add_subjectAdmin.this, show_subjectAdmin.class));

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

                    ////

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"errgrror",Toast.LENGTH_SHORT).show();
                }




            }


        });


    }
}