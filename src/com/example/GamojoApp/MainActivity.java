package com.example.GamojoApp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private ImageView teamPic;
    private EditText firstTeam;
    private EditText firstScore;
    private EditText secondScore;
    private ListView discussList;
    private Button moreDiscuss;
    private ListView betsList;
    private Button moreBets;
    private ListView pollsList;
    private Button morePolls;
    private final String url = "http://static.cricinfo.com/rss/livescores.xml";
    private ArrayList<Match> matchList;
    private List<Discussions> discussArray;
    private ArrayAdapter<Discussions> discussAdapter;
    private ArrayAdapter<PollStatement> pollAdapter;
    private List<PollStatement> pollArray;
    private ArrayAdapter<Bet> betsAdapter;
    private List<Bet> betsArray;
    private List<Discussions> discussArr;
    private List<PollStatement> pollsArr;
    private List<Bet> betsArr;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Add Discussion");
        menu.add("Add Bets");
        menu.add("Add Polls");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                startActivity(new Intent(this, AddDiscussion.class));
            case 1:
                startActivity(new Intent(this, AddBets.class));
            case 2:
                startActivity(new Intent(this, AddPolls.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        teamPic = (ImageView) findViewById(R.id.matchPic);
        firstTeam = (EditText) findViewById(R.id.firstTeam);
        firstScore = (EditText) findViewById(R.id.firstScore);
        secondScore = (EditText) findViewById(R.id.secondScore);
        discussList = (ListView) findViewById(R.id.discussList);
        moreDiscuss = (Button) findViewById(R.id.moreDiscuss);
        betsList = (ListView) findViewById(R.id.betsList);
        moreBets = (Button) findViewById(R.id.moreBets);
        pollsList = (ListView) findViewById(R.id.pollsList);
        morePolls = (Button) findViewById(R.id.morePolls);

//        new fetchMainScreen();

//        fillTables();

        pollArray = new SqliteHelper(this).getAllPolls();

        pollAdapter = new ArrayAdapter<PollStatement>(this,
                android.R.layout.simple_dropdown_item_1line, pollArray);

        pollsList.setAdapter(pollAdapter);

        pollsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this, SinglePoll.class).putExtra
                        ("id", id));
            }
        });

        morePolls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MorePolls.class));
            }
        });

        discussArray = new SqliteHelper(this).getAllDiscuss();
        System.out.println("///////////////" );
        System.out.println(discussArray.get(0));

        discussAdapter = new ArrayAdapter<Discussions>(this,
                android.R.layout.simple_dropdown_item_1line, discussArray);
        discussList.setAdapter(discussAdapter);

        discussList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this, SingleDiscussion.class).putExtra
                        ("id", id));
            }
        });

        moreDiscuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MoreDiscussion.class));
            }
        });

        betsArray = new SqliteHelper(this).getAllBets();

        betsAdapter = new ArrayAdapter<Bet>(this, android.R.layout.simple_dropdown_item_1line,
                betsArray);
        betsList.setAdapter(betsAdapter);

        betsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this, SingleBet.class).putExtra
                        ("id", id));
            }
        });

        moreBets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MoreBets.class));
            }
        });
    }

/*
    private void fillTables() {
        SqliteHelper helper = new SqliteHelper(this);
        helper.fillDiscussTable(discussArr);
        helper.fillBetsTable(betsArr);
        helper.fillPollsTable(pollsArr);
    }
*/

    private class fetchMainScreen extends AsyncTask {
        private HandleXml obj;

        @Override
        protected Object doInBackground(Object[] params) {
            fetchXml();
            return null;
        }

        private void fetchXml() {
            obj = new HandleXml(url);
            obj.fetchXml();
            while (obj.parsingComplete) {
                Match match = new Match(obj.getTitle(), obj.getLink(), obj.getDesc());
                matchList.add(match);
            }
        }
    }

    private class HandleXml {
        private final String urlString;
        private String title = "title";
        private String link = "link";
        private String desc = "description";
        private boolean parsingComplete = true;

        public HandleXml(String url) {
            this.urlString = url;
        }

        public void fetchXml() {
            Thread thread = new Thread(new Runnable() {
                public XmlPullParserFactory xmlFacObj;

                @Override
                public void run() {
                    try {
                        URL url = new URL(urlString);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.connect();
                        InputStream stream = conn.getInputStream();
                        xmlFacObj = XmlPullParserFactory.newInstance();
                        XmlPullParser parser = xmlFacObj.newPullParser();
                        parser.setInput(stream, null);
                        parseXmlAndStoreIt(parser);
                        stream.close();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }

        private void parseXmlAndStoreIt(XmlPullParser parser) throws XmlPullParserException,
                IOException {
            int event;
            String text = null;
            event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = parser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (name.equals("title")) {
                            title = text;
                        }
                        if (name.equals("link")) {
                            link = text;
                        }
                        if (name.equals("description")) {
                            desc = text;
                        }
                        break;
                }
                event = parser.next();
            }
            parsingComplete = false;
        }

        public String getTitle() {
            return title;
        }

        public String getDesc() {
            return desc;
        }

        public String getLink() {
            return link;
        }
    }
}
