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

public class AddDiscussion extends Activity {
    private EditText userId;
    private Button submitDiscuss;
    private EditText convId;
    private EditText gameId;
    private EditText comments;
    private SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_discussions);
        userId = (EditText) findViewById(R.id.userID);
        convId = (EditText) findViewById(R.id.convID);
        gameId = (EditText) findViewById(R.id.gameID);
        comments = (EditText) findViewById(R.id.comments);
        submitDiscuss = (Button) findViewById(R.id.submitDiscuss);

        submitDiscuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Discussions discussion = new Discussions(Integer.parseInt(String.valueOf(convId
                        .getText())), Integer.parseInt(String.valueOf(gameId.getText())),
                        Integer.parseInt(String.valueOf(userId.getText())),
                        String.valueOf(comments.getText()));

//                we need a global variable discussArr

                sqliteHelper = new SqliteHelper(getApplicationContext());
                sqliteHelper.addDiscussion(discussion);
                sqliteHelper.close();
            }

        });
    }
}
