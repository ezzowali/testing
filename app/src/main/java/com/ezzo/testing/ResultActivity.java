package com.ezzo.testing;




import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView tv, tv2, tv3;
    Button btnRestart;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        int phyCorrect=showQ2.correctAnswer;
        int phyMarks=showQ2.marks;
        int phyWrong= showQ2.wrongAnswer;

        int chimCorrect=show_Chim.correctAnswer;
        int chimMarks=show_Chim.marks;
        int chimWrong= show_Chim.wrongAnswer;



        tv = (TextView) findViewById(R.id.tvres);
        tv2 = (TextView) findViewById(R.id.tvres2);
        tv3 = (TextView) findViewById(R.id.tvres3);
        btnRestart = (Button) findViewById(R.id.btnRestart);


        StringBuffer sb = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        StringBuffer sb3 = new StringBuffer();


        if(phyCorrect!=0||phyMarks!=0||phyWrong!=0){

            sb.append("الإجابات الصحيحة : " + showQ2.correctAnswer+ "\n");

            sb2.append("الإجابات الخاطئة : " + showQ2.wrongAnswer+ "\n");

            sb3.append("الدرجة النهائية : " + showQ2.marks + "\n");
        }

        if(chimCorrect!=0||chimMarks!=0||chimWrong!=0){

            sb.append("الإجابات الصحيحة : " + show_Chim.correctAnswer+ "\n");

            sb2.append("الإجابات الخاطئة : " + show_Chim.wrongAnswer+ "\n");

            sb3.append("الدرجة النهائية : " + show_Chim.marks + "\n");
        }



        tv.setText(sb);
        tv2.setText(sb2);
        tv3.setText(sb3);



        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQ2.correctAnswer=0;
                showQ2.marks=0;
                showQ2.wrongAnswer=0;

                show_Chim.correctAnswer=0;
                show_Chim.marks=0;
                show_Chim.wrongAnswer=0;

                Intent in = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(in);

                finish();
            }
        });
    }
}