package com.example.GamojoApp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MoreDiscussion extends Activity {
    private ListView discussForumList;
    private String[] discussForumArray;
    private ArrayAdapter<String> discussForumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_discussion);
        discussForumList = (ListView) findViewById(R.id.discussForumList);
        discussForumAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                R.id.discussRow, discussForumArray);
        discussForumList.setAdapter(discussForumAdapter);
    }
}