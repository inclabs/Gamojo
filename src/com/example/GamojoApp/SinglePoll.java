package com.example.GamojoApp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class SinglePoll extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_poll);
        long id = getIntent().getExtras().getLong("id");
        PollStatement statement = new SqliteHelper(getApplicationContext())
                .getSinglePoll(id);
        EditText pollStatement = (EditText) findViewById(R.id.statement);
        pollStatement.setText(statement.getStatement());
        EditText odds = (EditText) findViewById(R.id.odds);
        odds.setText(statement.getNumYes() / statement.getNumNo());
    }
}
