package com.olafolaf.hangmanbloody;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MultiPlayerActivitiy extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player_activitiy);
    }

    public void startMultiGame(View v){

        EditText editText = (EditText) findViewById(R.id.editTextWord);

        String wordToGuess = editText.getText().toString();

        editText.setText("");

        Intent myIntent =  new Intent(this,GameMultiActivitiy.class);

        myIntent.putExtra("WORD_IDENTIFIER", wordToGuess);

        startActivity(myIntent);


    }

}
