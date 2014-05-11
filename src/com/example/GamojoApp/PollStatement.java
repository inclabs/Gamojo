package com.example.GamojoApp;


public class PollStatement {
    private int rowId;
    private final String statement;
    private final int numYes;
    private final int numNo;

    public PollStatement(int rowId, String statement, int numYes, int numNo) {
        this.rowId = rowId;
        this.numNo = numNo;
        this.numYes = numYes;
        this.statement = statement;
    }

    public PollStatement(String statement, int numYes, int numNo) {
        this.statement = statement;
        this.numYes = numYes;
        this.numNo = numNo;
    }

    public String getStatement() {
        return this.statement;
    }

    public int getNumYes() {
        return this.numYes;
    }

    public int getNumNo() {
        return this.numNo;
    }

    public int getId() {
        return this.rowId;
    }

    @Override
    public String toString() {
        return this.statement + " \n " + this.numYes / this.numNo;
    }
}
