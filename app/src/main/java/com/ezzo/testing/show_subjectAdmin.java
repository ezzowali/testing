package com.ezzo.testing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class show_subjectAdmin extends AppCompatActivity {
    private static final String apiurl = "http://192.168.64.2/Login/Subject.php";
    ListView lv;

    Button b_subj,b_add;


    private static String subject[];
    private static String timer[];


    public static String gg;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_subject_admin);

        lv = (ListView) findViewById(R.id.lv);

        b_add=findViewById(R.id.b_add);

        fetch_data_into_array(lv);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = lv.getItemAtPosition(position).toString();

                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection

        switch (item.getItemId()) {
            case R.id.add:
                startActivity(new Intent(show_subjectAdmin.this, add_subjectAdmin.class));
                return true;
            case R.id.back:
                startActivity(new Intent(show_subjectAdmin.this, MainActivity.class));

                return true;
            default:
                return super.onOptionsItemSelected(item);





        }
    }

    public void fetch_data_into_array(View view) {

        class dbManager extends AsyncTask<String, Void, String> {
            protected void onPostExecute(String data) {
                try {
                    JSONArray ja = new JSONArray(data);
                    JSONObject jo = null;

                    subject = new String[ja.length()];
                    timer =new String[ja.length()];


                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);
                        subject[i] = jo.getString("Subject");
                        timer[i] = jo.getString("timer");





                    }

                    myadapter adptr = new myadapter(getApplicationContext(), subject,timer);
                    lv.setAdapter(adptr);

                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(String... strings) {
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuffer data = new StringBuffer();
                    String line;

                    while ((line = br.readLine()) != null) {
                        data.append(line + "\n");
                    }
                    br.close();

                    return data.toString();

                } catch (Exception ex) {
                    return ex.getMessage();
                }

            }

        }
        dbManager obj = new dbManager();
        obj.execute(apiurl);

    }

    class myadapter extends ArrayAdapter<String> {
        Context context;
        String subject[];
        String timer[];


        myadapter(Context c, String subject[],String timer[]) {
            super(c, R.layout.subject_list, R.id.tv1, subject);
            context = c;
            this.subject = subject;
            this.timer=timer;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             View subject_list = inflater.inflate(R.layout.subject_list, parent, false);


            TextView tv1 = subject_list.findViewById(R.id.tv1);
            TextView tv2 = subject_list.findViewById(R.id.tv2);
            Button b_add=subject_list.findViewById(R.id.b_add);




            tv1.setText(subject[position]);
            tv2.setText(timer[position]);

            b_add.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    startActivity(new Intent(show_subjectAdmin.this, write_Qus.class));

Log.i("vcvcv", subject[position]);

gg=subject[position];

                }


            });










            return subject_list;

        }
    }

}

