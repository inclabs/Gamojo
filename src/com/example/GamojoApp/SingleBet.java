package com.example.GamojoApp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


public class SingleBet extends Activity {
    private TextView singleBetStatement;
    private TextView singleBetOdds;
    private TextView singleBetAmount;
    private Spinner singleBetSpinner;
    private Button likeBtn;
    private Button dislikeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_bet);
        singleBetStatement = (TextView) findViewById(R.id.singleBetStatement);
        singleBetOdds = (TextView) findViewById(R.id.singleBetOdds);
        singleBetAmount = (TextView) findViewById(R.id.singleBetAmount);
        singleBetSpinner = (Spinner) findViewById(R.id.singleBetSpinner);
        likeBtn = (Button) findViewById(R.id.like);
        dislikeBtn = (Button) findViewById(R.id.dislike);

        long id = getIntent().getExtras().getLong("id");
        Bet bet = new SqliteHelper(getApplicationContext()).getSingleBet(id);
        singleBetAmount.setText(bet.getStatement());
        singleBetOdds.setText(bet.getNumYes()/bet.getNumNo());
//        singleBetAmount.setText(bet.getNumBets());
    }
}