package com.example.GamojoApp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;


import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class SingleDiscussion extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_discuss);
        long id = getIntent().getExtras().getLong("id");
        Discussions discussion = new SqliteHelper(getApplicationContext())
                .getSingleDiscussion(id);
        EditText gameId = (EditText) findViewById(R.id.gameId);
        gameId.setText(discussion.getGameId());
        EditText convId= (EditText) findViewById(R.id.convId);
        convId.setText(discussion.getConvId());
        EditText comments = (EditText) findViewById(R.id.commentsText);
        comments.setText(discussion.getComments());
    }
}
