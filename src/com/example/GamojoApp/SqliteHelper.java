package com.example.GamojoApp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SqliteHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "GAMOJO";
    private final String TABLE_POLLS = "tablePolls";
    private final String KEY_ID = "rowId";
    private final String KEY_STATEMENT = "statement";
    private final String NUM_YES = "numYes";
    private final String NUM_NO = "numNo";
    private List<Discussions> allDiscuss;
    private final String TABLE_DISCUSS = "tableDiscuss";
    private final String TABLE_BETS = "tableBets";
    private final String CREATE_TABLE_POLLS = "CREATE TABLE " + TABLE_POLLS + "(" + KEY_ID +
            "INTEGER " +
            "PRIMARY KEY, " + KEY_STATEMENT + " TEXT, " + NUM_YES + " INT, " + NUM_NO + " INT)";
    private final String BET_STATEMENT = "betStatement ";
    private final String NUM_POS = "numPos";
    private final String NUM_NEG = "numNeg";
    private final String NUM_BETS = "numBets";
    private final String CREATE_TABLE_BETS = "CREATE TABLE " + TABLE_BETS + "(" + KEY_ID +
            "INTEGER " +
            "PRIMARY KEY, " + BET_STATEMENT + " TEXT,  " + NUM_POS + " int, " +
            NUM_NEG + " int, " + NUM_BETS + " int)";

    private final String COMMENTS = "comments";
    private final String USERID = "userId";
    private final String GAMEID = "gameId";
    private final String CREATE_TABLE_DISCUSS = "CREATE TABLE " + TABLE_DISCUSS + "(" + KEY_ID +
            "INTEGER " + "PRIMARY KEY, " + USERID + " INT, " + GAMEID + " INT, " +
            "" + COMMENTS + " TEXT)";
//    private PollStatement singlePoll;

    public void addPoll(PollStatement statement) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_STATEMENT, statement.getStatement());
        values.put(NUM_NO, statement.getNumNo());
        values.put(NUM_YES, statement.getNumYes());
        db.insert(TABLE_DISCUSS, null, values);
        db.close();
    }

    public SqliteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_DISCUSS);
        db.execSQL(CREATE_TABLE_BETS);
        db.execSQL(CREATE_TABLE_POLLS);
        ContentValues values = new ContentValues();
        values.put(USERID, 1);
        values.put(GAMEID, 1);
        values.put(COMMENTS, "none");
        db.insert(TABLE_DISCUSS, null, values);
        db.close();
    }

/*
    protected void fillPollsTable(List<PollStatement> pollsArr) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (PollStatement statement : pollsArr) {
            ContentValues values = new ContentValues();
            values.put(KEY_STATEMENT, statement.getStatement());
            values.put(NUM_NO, statement.getNumNo());
            values.put(NUM_YES, statement.getNumYes());
            assert db != null;
            db.insert(TABLE_POLLS, null, values);
        }
        assert db != null;
        db.close();
    }

    protected void fillBetsTable(List<Bet> betsArr) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (Bet bet : betsArr) {
            ContentValues values = new ContentValues();
            values.put(BET_STATEMENT, bet.getStatement());
            values.put(NUM_NO, bet.getNumNo());
            values.put(NUM_YES, bet.getNumYes());
            values.put(NUM_BETS, bet.getNumBets());
            assert db != null;
            db.insert(TABLE_BETS, null, values);
        }
        assert db != null;
        db.close();
    }

    protected void fillDiscussTable(List<Discussions> discussArr) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (Discussions discussion : discussArr) {
            ContentValues values = new ContentValues();
            values.put(USERID, discussion.getUserId());
            values.put(NUM_NO, discussion.getGameId());
            values.put(NUM_YES, discussion.getConvId());
            assert db != null;
            db.insert(TABLE_POLLS, null, values);
        }
        assert db != null;
        db.close();
    }

*/


    public int updatePolls(PollStatement pollStatement) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_STATEMENT, pollStatement.getStatement());
        values.put(NUM_YES, pollStatement.getNumYes());
        values.put(NUM_NO, pollStatement.getNumNo());

        assert db != null;
        return db.update(TABLE_POLLS, values, KEY_ID + " = ?", new String[]{String.valueOf
                (pollStatement.getId())});
    }

    public void deletePolls(PollStatement statement) {
        SQLiteDatabase db = this.getWritableDatabase();
        assert db != null;
        db.delete(TABLE_POLLS, KEY_ID + " = ?", new String[]{String.valueOf(statement.getId())});
        db.close();
    }

    public int getPollsCount() {
        String countQuery = "SELECT * FROM " + TABLE_POLLS;
        SQLiteDatabase db = this.getReadableDatabase();
        assert db != null;
        Cursor cursor = db.rawQuery(countQuery, null);
        int pollsCount = cursor.getCount();
        cursor.close();
        return pollsCount;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<Discussions> getAllDiscuss() {
        List<Discussions> discussList = new ArrayList<Discussions>();
        String selectAllQuery = "SELECT * FROM " + TABLE_DISCUSS;
        SQLiteDatabase db = this.getWritableDatabase();
        assert db != null;
        Cursor cursor = db.rawQuery(selectAllQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Discussions discussion = new Discussions(Integer.parseInt(cursor
                        .getString(0)), Integer.parseInt(cursor.getString(1)),
                        Integer.parseInt(cursor.getString(2)), cursor.getString(3));
                discussList.add(discussion);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return discussList;
    }

    public List<Bet> getAllBets() {
        List<Bet> betsList = new ArrayList<Bet>();
        String selectAllQuery = "SELECT * FROM " + TABLE_BETS;
        SQLiteDatabase db = this.getWritableDatabase();
        assert db != null;
        Cursor cursor = db.rawQuery(selectAllQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Bet bet = new Bet(Integer.parseInt(cursor
                        .getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)),
                        Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString
                        (4)));
                betsList.add(bet);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return betsList;
    }

    public List<PollStatement> getAllPolls() {
        List<PollStatement> pollsList = new ArrayList<PollStatement>();
        String selectAllQuery = "SELECT * FROM " + TABLE_POLLS;
        SQLiteDatabase db = this.getWritableDatabase();
        assert db != null;
        Cursor cursor = db.rawQuery(selectAllQuery, null);
        if (cursor.moveToFirst()) {
            do {
                PollStatement pollStatement = new PollStatement(Integer.parseInt(cursor
                        .getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)),
                        Integer.parseInt(cursor.getString(3)));
                pollsList.add(pollStatement);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return pollsList;
    }

    /*
        void addPolls(String statement) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_STATEMENT, statement);
            db.insert(TABLE_POLLS, null, values);
            db.close();
        }
    */

    public void addDiscussion(Discussions discussion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERID, discussion.getUserId());
        values.put(GAMEID, discussion.getGameId());
        values.put(COMMENTS, discussion.getComments());
        db.insert(TABLE_DISCUSS, null, values);
        db.close();
    }

    public void addBets(Bet bet) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BET_STATEMENT, bet.getStatement());
        values.put(NUM_POS, bet.getNumYes());
        values.put(NUM_NEG, bet.getNumNo());
        values.put(NUM_BETS, bet.getNumBets());
        db.insert(TABLE_BETS, null, values);
        db.close();
    }

    PollStatement getSinglePoll(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        assert db != null;
        Cursor cursor = db.query(TABLE_POLLS, new String[]{KEY_ID, KEY_STATEMENT, NUM_YES,
                NUM_NO}, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        assert cursor != null;
        PollStatement pollStatement = new PollStatement(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)),
                Integer.parseInt(cursor.getString(3)));

        return pollStatement;
    }

    public Discussions getSingleDiscussion(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        assert db != null;
        Cursor cursor = db.query(TABLE_DISCUSS, new String[]{KEY_ID, USERID, GAMEID, COMMENTS},
                KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        assert cursor != null;
        Discussions discussions = new Discussions(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)),
                (cursor.getString(3)));

        return discussions;
    }

    public Bet getSingleBet(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        assert db != null;
        Cursor cursor = db.query(TABLE_BETS, new String[]{KEY_ID, BET_STATEMENT, NUM_POS,
                NUM_NEG, NUM_BETS}, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null,
                null, null);
        if (cursor != null)
            cursor.moveToFirst();
        assert cursor != null;
        Bet bet = new Bet(cursor.getString(0),
                Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)),
                Integer.parseInt(cursor.getString(3)));

        return bet;
    }

/*
    public PollStatement getSinglePoll(long id) {
        return singlePoll;
    }
*/


/*    public void addDiscussion(Discussions discussion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(, bet.getStatement());
        values.put(NUM_POS, bet.getNumYes());
        values.put(NUM_NEG, bet.getNumNo());
        values.put(NUM_BETS, bet.getNumBets());
        db.insert(TABLE_BETS, null, values);
        db.close();
    }

    public void addPoll(PollStatement statement) {
    }*/
}