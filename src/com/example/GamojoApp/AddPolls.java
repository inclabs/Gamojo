package com.example.GamojoApp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPolls extends Activity {
    private EditText pollStatement;
    private Button pollSubmit;
    private EditText numNo;
    private EditText numYes;
    private SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_polls);
        pollStatement = (EditText) findViewById(R.id.pollStatement);
        numYes = (EditText) findViewById(R.id.numYes);
        numNo = (EditText) findViewById(R.id.numNo);
        pollSubmit = (Button) findViewById(R.id.pollSubmit);

        pollSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PollStatement statement = new PollStatement(String.valueOf(pollStatement
                        .getText()), Integer.parseInt(String.valueOf(numYes.getText())),
                        Integer.parseInt(String.valueOf(numNo.getText())));

//                we need a global variable pollsArr
                sqliteHelper = new SqliteHelper(getApplicationContext());
                sqliteHelper.addPoll(statement);
                sqliteHelper.close();
            }
        });
    }
}
