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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class showQ extends AppCompatActivity {

    private  static final String url="http://192.168.64.2/Login/show.php";
    ListView lv;
    Button b1;
    RadioGroup radio_g;
    RadioButton r1;
    RadioButton r2;

    private static String username[];
    private static String password[];
    private static String password2[];


    ArrayList<String>ans1=new ArrayList<>();
    ArrayList<String>ans2=new ArrayList<>();

    ArrayList<String>filter=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_q);



        b1 = findViewById(R.id.b1);




        lv=(ListView)findViewById(R.id.lv);
        Fdata(lv);






        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = lv.getItemAtPosition(position).toString();

                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        });









    }


    public void Fdata(View view){


        radio_g= findViewById(R.id.answersgrp);
        r1=(RadioButton)findViewById(R.id.radioButton);
        r2=(RadioButton)findViewById(R.id.radioButton2);






       class dbManager extends AsyncTask<String,Void,String>{

           protected void onPostExecute(String data) {

               try {
                   JSONArray ja=new JSONArray(data);
                   JSONObject jo=null;
                   username = new String[ja.length()];
                   password = new String[ja.length()];
                   password2=new String[ja.length()];



                   for (int i=0;i<ja.length();i++){

                       jo=ja.getJSONObject(i);

                       username[i] = jo.getString("username");
                       password[i] = jo.getString("password");
                       password2[i] = jo.getString("password2");



                   }

                   myadapter adptr = new myadapter(getApplicationContext(), username, password,password2);
                   lv.setAdapter(adptr);
                   Log.i(String.valueOf(lv), String.valueOf(lv));

                   for (int i=0;i<username.length;i++){

                       ans1.add(password[i]);
                       ans2.add(password2[i]);

//                       r1.setText(password[0]);
//                       r2.setText(password2[1]);


                   }


//                   r1.setText(ans1.get(1));
//                   r2.setText(ans2.get(1));





//                   for (int i = 0; i < username.length; i++) {
//
//
////                       ans1.add(password[i]);
////                       ans2.add(password2[i]);
//
//

//
//
//
//
//
//
//
//
//                   }




//                       r1.setText("ssss");

//                       Log.i("s", String.valueOf(r1));







               } catch (Exception e) {

                   Log.i(String.valueOf(e), String.valueOf(e));

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
        String ans3[];


        myadapter(Context c, String Q[], String ans[],String ans3[])
        {
            super(c,R.layout.qus,R.id.tv1,Q);
            context=c;
            this.Q=Q;
            this.ans=ans;
            this.ans3=ans3;

        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater inflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View qus=inflater.inflate(R.layout.qus,parent,false);


            TextView tv1=qus.findViewById(R.id.tv1);

            RadioButton ans1=qus.findViewById(R.id.radioButton);

            RadioButton ans2=qus.findViewById(R.id.radioButton2);

            RadioGroup a=qus.findViewById(R.id.answersgrp);





            tv1.setText(Q[position]);
            ans1.setText(ans[position]);

            ans2.setText(ans3[position]);


            b1.setOnClickListener(new  View.OnClickListener(){


                @Override
                public void onClick(View v) {






//                           if(radio_g.getCheckedRadioButtonId()==-1)
//                           {
//                               Toast.makeText(getApplicationContext(), "الرجاء اختيار اجابة", Toast.LENGTH_SHORT).show();
//                               return;
//                           }

                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            for (int i = 0; i < username.length; i++) {

                                String[] field = new String[2];
                                field[0] = "ans";
                                field[1] = "ans2";



                                if(a.getCheckedRadioButtonId()==-1)
                                {
                                    Toast.makeText(getApplicationContext(), "الرجاء اختيار اجابة", Toast.LENGTH_SHORT).show();
                                    return;
                                }
//                                RadioButton uans = findViewById(a.getCheckedRadioButtonId());
//                                String ansText = uans.getText().toString();



                                String[] data = new String[2];

                                data[0]= String.valueOf(ans1.getText());

                                data[1]= String.valueOf(ans2.getText());

                           Log.i("String.valueOf(ans1)", String.valueOf(ans1.getText()));




//                                ans1.add(password[i]);
//                                ans2.add(password2[i]);



//
//                                Log.i("String.valueOf(ans2)", String.valueOf(ans2));
//
//                                Log.i("String.valueOf(ans2)", ans1.get(i));

//
//                                       if(radio_g.getCheckedRadioButtonId()==-1)
//                                       {
//                                           Toast.makeText(getApplicationContext(), "الرجاء اختيار اجابة", Toast.LENGTH_SHORT).show();
//                                           return;
//                                       }










//                                data[0]= ans1.get(i);
//                                data[1]= ans1.get(i);



                                PutData putData = new PutData("http://192.168.64.2/Login/ans.php", "POST", field, data);

                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        String result = putData.getResult();
                                        char str = result.charAt(5);
                                        Log.i("PutData", String.valueOf(str));


                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                                    }
                                }








                            }

                            startActivity(new Intent(showQ.this, MainActivity.class));


                            finish();

                        }
                    });

                }


            });


            return qus;


        }


    }



}