package com.ezzo.testing;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class showQ2 extends AppCompatActivity {

    private  static final String url="http://192.168.64.2/Login/show_phy.php";
    ListView lv;
    Button b1;
    RadioGroup radio_g;
    RadioButton r1;
    RadioButton r2;
    RadioButton r3;
    RadioButton r4;
    TextView tv1;
    public static int marks=0;
    public static int correctAnswer =0;
    public static int wrongAnswer=0;

    private static String right_answer[];
    private static String username[];
    private static String option1[];
    private static String option2[];
    private static String option3[];
    private static String option4[];


    ArrayList<String>ans1=new ArrayList<>();
    ArrayList<String>ans2=new ArrayList<>();
    ArrayList<String>ans3=new ArrayList<>();
    ArrayList<String>ans4=new ArrayList<>();
    ArrayList<String>filter=new ArrayList<>();


    String USERNAME= MainActivity.name ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_q2);

        String USERNAME= MainActivity.name ;
        String PASSWORD= MainActivity.password;
        b1 = findViewById(R.id.b1);
        tv1=findViewById(R.id.tv1);
        Log.i( "ST",USERNAME );
        Fdata();






    }


    public void Fdata(){


        radio_g= findViewById(R.id.answersgrp);
        r1=(RadioButton)findViewById(R.id.radioButton);
        r2=(RadioButton)findViewById(R.id.radioButton2);
        r3=(RadioButton)findViewById(R.id.radioButton3);
        r4=(RadioButton)findViewById(R.id.radioButton4);


        class dbManager extends AsyncTask<String,Void,String>{
            int flag=0;

            protected void onPostExecute(String data) {

                try {
                    JSONArray ja=new JSONArray(data);
                    JSONObject jo=null;
                    username = new String[ja.length()];
                    option1 = new String[ja.length()];
                    option2=new String[ja.length()];
                    option3=new String[ja.length()];
                    option4=new String[ja.length()];
                    right_answer= new String[ja.length()];


                    for (int i=0;i<ja.length();i++){

                        jo=ja.getJSONObject(i);

                        username[i] = jo.getString("username");
                        option1[i] = jo.getString("option1");
                        option2[i] = jo.getString("option2");
                        option3[i] = jo.getString("option3");
                        option4[i] = jo.getString("option4");
                        right_answer[i]=jo.getString("right_answer");

                    }


                    tv1.setText(username[0]);
                    r1.setText(option1[0]);
                    r2.setText(option2[0]);
                    r3.setText(option3[0]);
                    r4.setText(option4[0]);

                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if(radio_g.getCheckedRadioButtonId()==-1)
                            {
                                Toast.makeText(getApplicationContext(), "الرجاء اختيار اجابة", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            RadioButton uans = findViewById(radio_g.getCheckedRadioButtonId());
                            String ansText = uans.getText().toString();
                            Log.i("s",ansText);
                            Log.i("sertee", String.valueOf(flag));



                            if(ansText.equals( right_answer[flag] )){
                                correctAnswer++;
                            }
                            else {
                                wrongAnswer++;
                                Log.i("s", String.valueOf(wrongAnswer));
                            }








                            flag++;
                            radio_g.clearCheck();
                            if(flag<username.length)
                            {
                                tv1.setText(username[flag]);
                                r1.setText(option1[flag]);
                                r2.setText(option2[flag]);
                                r3.setText(option3[flag]);
                                r4.setText(option4[flag]);


                            }

                            else
                            {
                                marks = correctAnswer;
                                Log.i("tag", String.valueOf(marks));

                                String[] field = new String[2];

                                field[0] = "Phy_marks";
                                field[1] = "username";








                                String[] data = new String[2];

                                data[0]=USERNAME ;
                                Log.i("test",USERNAME);
                                data[1]=String.valueOf(marks);




                                PutData putData = new PutData("http://192.168.64.2/Login/markPhy_update.php", "POST", field, data);
                                Log.i("t5555", String.valueOf(marks));
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        String result = putData.getResult();
                                        char str = result.charAt(6);
                                        Log.i("PutData0", String.valueOf(str));



                                        if ("s".equalsIgnoreCase(String.valueOf(str))){


                                            Intent in = new Intent(getApplicationContext(), ResultActivity.class);
                                            startActivity(in);
                                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                            finish();

                                        }

                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }




                                Intent in = new Intent(getApplicationContext(), ResultActivity.class);
                                startActivity(in);



                            }



//                            radio_g.clearCheck();





                        }

                    });







                } catch (Exception e) {

//                    Log.i(String.valueOf(e), String.valueOf(e));

                    Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();;
                }
            }


            @Override
            protected String doInBackground(String... strings) {
                try {
                    URL url=new URL(strings[0]);
                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuffer data=new StringBuffer();
                    String line;
                    while ((line=br.readLine())!=null){
                        data.append(line+"\n");
                    }
                    br.close();


                    return data.toString();
                } catch (Exception ex) {
                    return ex.getMessage();
                }

            }
        }

        dbManager obj=new dbManager();
        obj.execute(url);


    }



    class myadapter extends ArrayAdapter<String>
    {
        Context context;
        String Q[];
        String ans[];
        String ans2[];
        String ans3[];
        String ans4[];



        myadapter(Context c, String Q[], String ans[],String ans2[],  String ans3[],String ans4[])
        {
            super(c,R.layout.activity_show_q2,R.id.tv1,Q);
            context=c;
            this.Q=Q;
            this.ans=ans;
            this.ans2=ans2;
            this.ans3=ans3;
            this.ans4=ans4;

        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater inflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View qus=inflater.inflate(R.layout.activity_show_q2,parent,false);


            TextView tv1=findViewById(R.id.tv1);
            RadioButton ans1=findViewById(R.id.radioButton);
            RadioButton ans2=findViewById(R.id.radioButton2);
            RadioButton ans3=findViewById( R.id.radioButton3);
            RadioButton ans4=findViewById( R.id.radioButton4);
            RadioGroup a=findViewById(R.id.answersgrp);





            tv1.setText(Q[position]);
            ans1.setText(ans[position]);



            return qus;


        }


    }



}