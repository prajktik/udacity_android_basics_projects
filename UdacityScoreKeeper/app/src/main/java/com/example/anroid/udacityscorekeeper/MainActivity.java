package com.example.anroid.udacityscorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int TOUCHDOWN = 6;
    private static final int FIELD_GOAL = 3;
    private static final int SAFETY = 2;
    private static final int EXTRA = 1;

    private int mHomeScore;
    private int mGuestScore;
    private TextView tvHomeScore;
    private TextView tvGuestScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("Football", "HOLA... ");
        setContentView(R.layout.activity_main);
        tvHomeScore = (TextView) findViewById(R.id.tv_home_score);
        tvGuestScore = (TextView) findViewById(R.id.tv_guest_score);
    }

    private void updateHomeScore() {

        tvHomeScore.setText(String.valueOf(mHomeScore));
    }


    private void updateGuestScore() {

        tvGuestScore.setText(String.valueOf(mGuestScore));
    }

    public void homeTouchdown(View view) {
        mHomeScore += TOUCHDOWN;
        updateHomeScore();
    }

    public void guestTouchdown(View view) {
        mGuestScore += TOUCHDOWN;
        updateGuestScore();
    }


    public void homeFieldGoal(View view) {
        mHomeScore += FIELD_GOAL;
        updateHomeScore();
    }

    public void guestFieldGoal(View view) {
        mGuestScore += FIELD_GOAL;
        updateGuestScore();
    }
    public void homeSafety(View view) {
        mHomeScore += SAFETY;
        updateHomeScore();
    }

    public void guestSafety(View view) {
        mGuestScore += SAFETY;
        updateGuestScore();
    }
    public void homeExtra(View view) {
        mHomeScore += EXTRA;
        updateHomeScore();
    }

    public void guestExtra(View view) {
        mGuestScore += EXTRA;
        updateGuestScore();
    }

    public void resetScores(View view){
        mHomeScore = 0;
        mGuestScore = 0;
        updateHomeScore();
        updateGuestScore();
    }


}
