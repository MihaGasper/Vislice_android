package com.olafolaf.hangmanbloody;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



public class GameMultiActivitiy extends ActionBarActivity {

    String mWord;

    int mFailCounter=0;

    int mGuessedLetter=0;

    int mPoints=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_multi);

        String wordSent = getIntent().getStringExtra("WORD_IDENTIFIER");

        mWord = wordSent;

        createTextViews(wordSent);


    }

    /**
     * retriving the letter introduced on the EditText
     * @param v (button clicked)
     */
    public void introduceLetter(View v){
        EditText myEditText = (EditText) findViewById(R.id.editTextLetter);

        String letter = myEditText.getText().toString();

        myEditText.setText("");



        if (letter.length()>0){
            checkLetter(letter);
        } else {
            Toast.makeText(this,"Please Introduce letter", Toast.LENGTH_SHORT).show();
        }

    }
    public void checkLetter(String introducedLetter) {
        char charIntroduced = introducedLetter.charAt(0);
        boolean letterGuessed = false;
        for (int i = 0; i < mWord.length(); i++) {
            char charFromTheWord = mWord.charAt(i);
            if (charFromTheWord == charIntroduced) {

                Log.d("MYLOG", "There was to much");
                letterGuessed = true;

                showLettersAtIndex(i, charIntroduced);

                mGuessedLetter++;
            }

        }

        if (letterGuessed == false) {
            letterFailed(Character.toString(charIntroduced));
        }
        if (mGuessedLetter == mWord.length()) {
            mPoints++;
            Toast.makeText(this,"YOU GUESSED, PLAY AGAIN", Toast.LENGTH_LONG).show();

            Intent myIntent =  new Intent(this,MultiPlayerActivitiy.class);

            startActivity(myIntent);

        }
    }
    public void showLettersAtIndex(int position, char letterGuessed){

        LinearLayout layoutLetter = (LinearLayout) findViewById(R.id.layoutLetters);

        TextView textView = (TextView) layoutLetter.getChildAt(position);

        textView.setText(Character.toString(letterGuessed));

    }

    public void createTextViews(String word){

        LinearLayout layoutletters = (LinearLayout) findViewById(R.id.layoutLetters);

        for (int i = 0; i < word.length(); i++) {

            TextView newTextView = (TextView) getLayoutInflater().inflate(R.layout.textview,null);

            layoutletters.addView(newTextView);

        }
    }

    public void clearScreen(){
        TextView textViewFailed = (TextView)findViewById(R.id.textView7);
        textViewFailed.setText("");

        mGuessedLetter=0;
        mFailCounter=0;

        LinearLayout layoutLetters = (LinearLayout) findViewById(R.id.layoutLetters);


        for (int i = 0; i < layoutLetters.getChildCount(); i++) {
            TextView currentTextView = (TextView) layoutLetters.getChildAt(i);
            currentTextView.setText("_");
        }

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.hangman_0);
    }
    public void letterFailed(String letterFailed) {

        TextView textViewFailed = (TextView)findViewById(R.id.textView7);
        String previousFail = textViewFailed.getText().toString();
        textViewFailed.setText(previousFail+letterFailed);

        mFailCounter++;
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        if (mFailCounter == 1) {
            imageView.setImageResource(R.drawable.hangman_1);
        } else if (mFailCounter == 2) {
            imageView.setImageResource(R.drawable.hangman_2);
        } else if (mFailCounter == 3) {
            imageView.setImageResource(R.drawable.hangman_3);
        } else if (mFailCounter == 4) {
            imageView.setImageResource(R.drawable.hangman_4);
        } else if (mFailCounter == 5) {
            imageView.setImageResource(R.drawable.hangman_5);
        } else if (mFailCounter == 6) {
            imageView.setImageResource(R.drawable.hangman_6);
        } else if (mFailCounter == 7) {
            imageView.setImageResource(R.drawable.game_over);
            Intent gameOverIntent = new Intent(this,GameOverActivity.class);

            gameOverIntent.putExtra("POINTS_IDENTIFIER",mPoints);

            startActivity(gameOverIntent);

            finish();

        }
    }
}
