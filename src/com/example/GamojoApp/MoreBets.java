package com.example.GamojoApp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MoreBets extends Activity {
    private ListView moreBetsList;
    private String[] moreBetsArray;
    private ArrayAdapter<String> moreBetsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_more_bets);
        moreBetsList = (ListView) findViewById(R.id.showMoreBets);
        moreBetsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                R.id.betsRow, moreBetsArray);
        moreBetsList.setAdapter(moreBetsAdapter);
    }
}
