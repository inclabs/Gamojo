package com.example.GamojoApp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddBets extends Activity {
    private EditText betStatement;
    private Button submitBets;
    private EditText totalBets;
    private EditText numPos;
    private EditText numNeg;
    private SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_bets);
        betStatement = (EditText) findViewById(R.id.betStatement);
        numPos = (EditText) findViewById(R.id.numPos);
        numNeg = (EditText) findViewById(R.id.numNeg);
        totalBets = (EditText) findViewById(R.id.totalBets);
        submitBets = (Button) findViewById(R.id.submitBets);

        submitBets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bet bet = new Bet(String.valueOf(betStatement.getText()),
                        Integer.parseInt(String.valueOf(numPos.getText())),
                        Integer.parseInt(String.valueOf(numNeg.getText())),
                        Integer.parseInt(String.valueOf(totalBets)));
                sqliteHelper = new SqliteHelper(getApplicationContext());
                sqliteHelper.addBets(bet);
                sqliteHelper.close();
            }
        });
    }
}
