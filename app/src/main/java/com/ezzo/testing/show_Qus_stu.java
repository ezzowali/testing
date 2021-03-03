package com.ezzo.testing;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class show_Qus_stu extends AppCompatActivity {

    int  timer1=show_subjectStudent.timer1;





    private final long START_TIME_IN_MILLIS = timer1 *60000;


    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;


    private  static final String url="http://192.168.64.2/Server/show_sub_Q.php";
    int minutes;
    int seconds ;
    ListView lv;
    Button b1;
    RadioGroup radio_g;
    RadioButton r1;
    RadioButton r2;
    RadioButton r3;
    RadioButton r4;
    TextView tv1,text_view_countdown;
    public static int marks=0;
    public static int correctAnswer =0;
    public static int wrongAnswer=0;

    public static int finich=1;

    private static String right_answer[];
    private static String Question[];
    private static String option1[];
    private static String option2[];
    private static String option3[];
    private static String option4[];
    private static String subject[];




    ArrayList<String> Subject = new ArrayList<String>();

    ArrayList<String> Subject2 = new ArrayList<String>();






    ArrayList<String> Qus = new ArrayList<String>();

    ArrayList<String> op1 = new ArrayList<String>();


    ArrayList<String> op2 = new ArrayList<String>();

    ArrayList<String> op3 = new ArrayList<String>();

    ArrayList<String> op4 = new ArrayList<String>();


    ArrayList<String> righ = new ArrayList<String>();






    String pla= show_subjectStudent.gg ;
    String name=MainActivity.name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_qus_stu);


        b1 = findViewById(R.id.b1);
        tv1=findViewById(R.id.tv1);
        text_view_countdown = findViewById(R.id.text_view_countdown);
        startTimer();


        Fdata();











    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                mTimerRunning = false;

            }
        }.start();
        mTimerRunning = true;

    }



    private void updateCountDownText() {
        minutes = (int) (mTimeLeftInMillis / 1000) / 60;
         seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        text_view_countdown.setText(timeLeftFormatted);

        Log.i("Qus", String.valueOf(Qus.size()));
        Log.i("lylyl", String.valueOf(minutes));
        Log.i("lylyl22", String.valueOf(seconds));
        Log.i("wrongAnswer", String.valueOf(wrongAnswer));
        Log.i("correctAnswer", String.valueOf(correctAnswer));




        if (minutes==0&&seconds==0 &&(wrongAnswer>0||correctAnswer>0)){

            marks = correctAnswer;
            Log.i("tawlwllg", String.valueOf(marks));

            String[] field = new String[3];

            field[0] = "username";
            field[1] = "subjectName";
            field[2]="marks";


            String[] data = new String[3];

            data[0]=name ;

            data[1]=pla;
            data[2]=String.valueOf(marks);


            PutData putData = new PutData("http://192.168.64.2/Server/marks.php", "POST", field, data);
            Log.i("t5555", String.valueOf(marks));
            if (putData.startPut()) {
                if (putData.onComplete()) {
                    String result = putData.getResult();
                    char str = result.charAt(6);
                    Log.i("PutDatddda0", String.valueOf(str));



                    if ("p".equalsIgnoreCase(String.valueOf(str))){


                        Intent in = new Intent(getApplicationContext(), ResultActivity.class);
                        startActivity(in);
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                        finish();

                    }

                    Intent in = new Intent(getApplicationContext(), ResultActivity.class);
                    startActivity(in);
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }


    }


    public void Fdata(){


        radio_g= findViewById(R.id.answersgrp);
        r1=(RadioButton)findViewById(R.id.radioButton);
        r2=(RadioButton)findViewById(R.id.radioButton2);
        r3=(RadioButton)findViewById(R.id.radioButton3);
        r4=(RadioButton)findViewById(R.id.radioButton4);


        class dbManager extends AsyncTask<String,Void,String>{
            int flag=0;
            int S=Subject.size();
            String nameEquility="";
            int secondQus=0;
            int qoo=0;

            protected void onPostExecute(String data) {

                try {
                    JSONArray ja=new JSONArray(data);
                    JSONObject jo=null;
                    subject=new String[ja.length()];
                    Question = new String[ja.length()];
                    option1 = new String[ja.length()];
                    option2=new String[ja.length()];
                    option3=new String[ja.length()];
                    option4=new String[ja.length()];
                    right_answer= new String[ja.length()];


                    for (int i=0;i<ja.length();i++){

                        jo=ja.getJSONObject(i);

                        subject[i]=jo.getString("Subject");
                        Question[i] = jo.getString("Question");
                        option1[i] = jo.getString("option1");
                        option2[i] = jo.getString("option2");
                        option3[i] = jo.getString("option3");
                        option4[i] = jo.getString("option4");
                        right_answer[i]=jo.getString("right_answer");

                        if (subject[i].equals(pla)){

                            nameEquility=pla;

                            Subject.add(subject[i]);

                            Qus.add(Question[i]);
                            op1.add(option1[i]);
                            op2.add(option2[i]);
                            op3.add(option3[i]);
                            op4.add(option4[i]);
                            righ.add(right_answer[i]);

                            S=Subject.size();

                            Log.i("Array", String.valueOf(S));


                            Log.i("Question", String.valueOf(Question[i]));
                            Log.i("Question", String.valueOf(option1[i]));
                            Log.i("Question", String.valueOf(option2[i]));
                            Log.i("Question", String.valueOf(option3[i]));
                            Log.i("Question", String.valueOf(option4[i]));
                            Log.i("Question", String.valueOf(right_answer[i]));





                        }

                    }


                    ArrayList<String > arrayList = new ArrayList<>();
                    arrayList.add(op1.get(0));
                    arrayList.add(op2.get(0));
                    arrayList.add(op3.get(0));
                    arrayList.add(op4.get(0));
                    Collections.shuffle( arrayList );
                    tv1.setText(Qus.get(0));
                    r1.setText(arrayList.get(0));
                    r2.setText(arrayList.get(1));
                    r3.setText(arrayList.get(2));
                    r4.setText(arrayList.get(3));





                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (radio_g.getCheckedRadioButtonId() == -1) {
                                Toast.makeText(getApplicationContext(), "الرجاء اختيار اجابة", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            RadioButton uans = findViewById(radio_g.getCheckedRadioButtonId());
                            String ansText = uans.getText().toString();
                            Log.i("s", ansText);
                            Log.i("end", String.valueOf(nameEquility));


                            if (ansText.equals(righ.get(secondQus))) {
                                correctAnswer++;
                            } else {
                                wrongAnswer++;
                                Log.i("s", String.valueOf(wrongAnswer));
                            }



                            secondQus++;

                            if(secondQus<Qus.size() )  {





                                    Log.i("jkk", String.valueOf(qoo));



                                    Log.i("inside", String.valueOf(nameEquility));

//                                tv1.setText(Qus.get(secondQus));
                                ArrayList<String > arrayList = new ArrayList<>();
                                arrayList.add(op1.get(secondQus));
                                arrayList.add(op2.get(secondQus));
                                arrayList.add(op3.get(secondQus));
                                arrayList.add(op4.get(secondQus));
                                Collections.shuffle( arrayList );
                                tv1.setText(Qus.get(secondQus));
                                r1.setText(arrayList.get(0));
                                r2.setText(arrayList.get(1));
                                r3.setText(arrayList.get(2));
                                r4.setText(arrayList.get(3));


//                                    r1.setText(op1.get(secondQus));
//                                    r2.setText(op2.get(secondQus));
//                                    r3.setText(op3.get(secondQus));
//                                    r4.setText(op4.get(secondQus));


                                    Subject2.add(nameEquility);
                                    qoo=Subject2.size();









                                Log.i("qoo", String.valueOf(qoo));
                                Log.i("s", String.valueOf(S-1));



                            }


                            else {


                                marks = correctAnswer;
                                Log.i("tag", String.valueOf(marks));

                                String[] field = new String[3];

                                field[0] = "username";
                                field[1] = "subjectName";
                                field[2]="marks";


                                String[] data = new String[3];

                                data[0]=name ;

                                data[1]=pla;
                                data[2]=String.valueOf(marks);


                                PutData putData = new PutData("http://192.168.64.2/Server/marks.php", "POST", field, data);
                                Log.i("t5555", String.valueOf(marks));
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        String result = putData.getResult();
                                        char str = result.charAt(6);
                                        Log.i("PutData0", String.valueOf(str));



                                        if ("p".equalsIgnoreCase(String.valueOf(str))){


                                            Intent in = new Intent(getApplicationContext(), ResultActivity.class);
                                            startActivity(in);
                                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                            finish();

                                        }

                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }



                            radio_g.clearCheck();






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






}