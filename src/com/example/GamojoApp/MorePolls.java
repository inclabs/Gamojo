package com.example.GamojoApp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MorePolls extends Activity {
    private ListView morePollsList;
    private List<PollStatement> morePollsArray;
    private ArrayAdapter<PollStatement> morePollsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_more_polls);
        morePollsList = (ListView) findViewById(R.id.showMorePolls);
        morePollsAdapter = new ArrayAdapter<PollStatement>(this,
                android.R.layout.simple_dropdown_item_1line,
                R.id.betsRow, morePollsArray);
        morePollsList.setAdapter(morePollsAdapter);
    }
}