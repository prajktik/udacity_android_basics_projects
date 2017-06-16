package com.example.anroid.udacityquizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private static final int TOTAL_NO_OF_QUESTIONS = 7;
    private RadioGroup rg1;
    private RadioGroup rg2;
    private RadioGroup rg3;
    private CheckBox cb_q4_toward_answer;
    private CheckBox cb_q4_there;
    private CheckBox cb_q4_above_answer;
    private CheckBox cb_q4_when;
    private CheckBox cb_q5_then_answer;
    private CheckBox cb_q5_quiet;
    private CheckBox cb_q5_gently_answer;
    private CheckBox cb_q5_quite_answer;
    private EditText et_q6;
    private EditText et_q7;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.title));

        rg1 = (RadioGroup) findViewById(R.id.rg_q1);
        rg2 = (RadioGroup) findViewById(R.id.rg_q2);
        rg3 = (RadioGroup) findViewById(R.id.rg_q3);

        cb_q4_toward_answer = (CheckBox) findViewById(R.id.cb_q4_toward_answer);
        cb_q4_there = (CheckBox) findViewById(R.id.cb_q4_there);
        cb_q4_above_answer = (CheckBox) findViewById(R.id.cb_q4_above_answer);
        cb_q4_when = (CheckBox) findViewById(R.id.cb_q4_when);

        cb_q5_then_answer = (CheckBox) findViewById(R.id.cb_q5_then_answer);
        cb_q5_quiet = (CheckBox) findViewById(R.id.cb_q5_quiet);
        cb_q5_gently_answer = (CheckBox) findViewById(R.id.cb_q5_gently_answer);
        cb_q5_quite_answer = (CheckBox) findViewById(R.id.cb_q5_quite_answer);

        et_q6 = (EditText) findViewById(R.id.et_q6_answer);
        et_q7 = (EditText) findViewById(R.id.et_q7_answer);
    }

    public void onSumbitClick(View view){

        boolean areAllAttempted = checkQuestionsAttempted();
        if(!areAllAttempted) {
            Toast.makeText(this, getString(R.string.toast), Toast.LENGTH_SHORT).show();
            return;
        }

        int correctAnswers = checkAnswers();
        String result = getString(R.string.result,correctAnswers,TOTAL_NO_OF_QUESTIONS);

        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

    }

    private boolean checkQuestionsAttempted(){

        if((rg1.getCheckedRadioButtonId() == -1)||
                (rg2.getCheckedRadioButtonId() == -1)  ||
                (rg3.getCheckedRadioButtonId() == -1)||

                ((!cb_q4_toward_answer.isChecked())&&
                        (!cb_q4_there.isChecked()) &&
                        (!cb_q4_above_answer.isChecked()) &&
                        (!cb_q4_when.isChecked()) )||
                ((!cb_q5_then_answer.isChecked()) &&
                        (!cb_q5_quiet.isChecked()) &&
                        (!cb_q5_gently_answer.isChecked())  &&
                        (!cb_q5_quite_answer.isChecked()) ) ||
                (et_q6.getText().toString().isEmpty()) ||
                (et_q7.getText().toString().isEmpty()) ) {

            return false;
        }

        return true;

    }


    private int checkAnswers(){

        int score = 0;
        if(rg1.getCheckedRadioButtonId() == R.id.rb_q1_that_answer){
            score++;
        }
        if(rg2.getCheckedRadioButtonId() == R.id.rb_q2_why_answer) {
            score++;
        }
        if(rg3.getCheckedRadioButtonId() == R.id.rb_q3_option4_answer){
            score++;
        }

        if(cb_q4_toward_answer.isChecked() && cb_q4_above_answer.isChecked()
                && !cb_q4_there.isChecked() && !cb_q4_when.isChecked()) {
            score++;
        }

        if(cb_q5_gently_answer.isChecked() && !cb_q5_quiet.isChecked()
                && cb_q5_quite_answer.isChecked() && cb_q5_then_answer.isChecked()) {
            score++;
        }

        if(getString(R.string.q6_answer).equalsIgnoreCase(et_q6.getText().toString())) {
            score++;
        }

        if(getString(R.string.q7_answer).equalsIgnoreCase(et_q7.getText().toString())) {
            score++;
        }

        return score;
    }

}


