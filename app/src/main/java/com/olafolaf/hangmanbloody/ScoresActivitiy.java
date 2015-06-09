package com.olafolaf.hangmanbloody;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class ScoresActivitiy extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        SharedPreferences preferences = getSharedPreferences("MYPREFERENCES", MODE_PRIVATE);

        String scores = preferences.getString("SCORES", "NO SCORES");

        TextView textViewScores = (TextView) findViewById(R.id.textViewScores);

        textViewScores.setText(scores);

    }
}

