package com.example.android.udacityquizapp2;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.android.udacityquizapp2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final int TOTAL_NO_OF_QUESTIONS = 7;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setTitle(getString(R.string.title));

        MainActivity mainActivity = this;
        binding.setMainActivity(mainActivity);

    }

    public void onSubmitClick(){

        boolean areAllAttempted = checkQuestionsAttempted();
        if(!areAllAttempted) {

            Toast.makeText(this, getString(R.string.toast), Toast.LENGTH_SHORT).show();

        } else {

            int correctAnswers = checkAnswers();
            String result = getString(R.string.result, correctAnswers, TOTAL_NO_OF_QUESTIONS);
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        }
   }


    private boolean checkQuestionsAttempted(){


        if((binding.inQuestion1.rgQ1.getCheckedRadioButtonId() == -1)||
                (binding.inQuestion2.rgQ2.getCheckedRadioButtonId() == -1)  ||
                (binding.inQuestion3.rgQ3.getCheckedRadioButtonId() == -1)||

                ((!binding.inQuestion4.cbQ4TowardAnswer.isChecked())&&
                        (!binding.inQuestion4.cbQ4There.isChecked()) &&
                        (!binding.inQuestion4.cbQ4AboveAnswer.isChecked()) &&
                        (!binding.inQuestion4.cbQ4When.isChecked()) )||
                ((!binding.inQuestion5.cbQ5ThenAnswer.isChecked()) &&
                        (!binding.inQuestion5.cbQ5Quiet.isChecked()) &&
                        (!binding.inQuestion5.cbQ5GentlyAnswer.isChecked())  &&
                        (!binding.inQuestion5.cbQ5QuiteAnswer.isChecked()) ) ||
                (binding.inQuestion6.etQ6Answer.getText().toString().isEmpty()) ||
                (binding.inQuestion7.etQ7Answer.getText().toString().isEmpty()) ) {

            return false;
        } else {

            return true;
        }
    }


    private int checkAnswers(){

        int score = 0;
        if(binding.inQuestion1.rgQ1.getCheckedRadioButtonId() == R.id.rb_q1_that_answer){
            score++;
        }
        if(binding.inQuestion2.rgQ2.getCheckedRadioButtonId() == R.id.rb_q2_why_answer) {
            score++;
        }
        if(binding.inQuestion3.rgQ3.getCheckedRadioButtonId() == R.id.rb_q3_option4_answer){
            score++;
        }

        if(binding.inQuestion4.cbQ4TowardAnswer.isChecked() && binding.inQuestion4.cbQ4AboveAnswer.isChecked()
                && !binding.inQuestion4.cbQ4There.isChecked() && !binding.inQuestion4.cbQ4When.isChecked()) {
            score++;
        }

        if(binding.inQuestion5.cbQ5GentlyAnswer.isChecked() && !binding.inQuestion5.cbQ5Quiet.isChecked()
                && binding.inQuestion5.cbQ5QuiteAnswer.isChecked() && binding.inQuestion5.cbQ5ThenAnswer.isChecked()) {
            score++;
        }

        if(getString(R.string.q6_answer).equalsIgnoreCase(binding.inQuestion6.etQ6Answer.getText().toString())) {
            score++;
        }

        if(getString(R.string.q7_answer).equalsIgnoreCase(binding.inQuestion7.etQ7Answer.getText().toString())) {
            score++;
        }

        return score;
    }

}


