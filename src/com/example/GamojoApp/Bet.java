package com.example.GamojoApp;

public class Bet {
    private final int amountBets;
    private final String betStatement;
    private int rowId;
    private final int numPos;
    private final int numNeg;

    public Bet(int rowId, String betStatement, int numPos, int numNeg, int amountBets) {
        this.rowId = rowId;
        this.betStatement = betStatement;
        this.numPos = numPos;
        this.numNeg = numNeg;
        this.amountBets = amountBets;
    }

    public Bet(String betStatement, int numPos, int numNeg, int amountBets) {
        this.betStatement = betStatement;
        this.numPos = numPos;
        this.numNeg = numNeg;
        this.amountBets = amountBets;
    }

    @Override
    public String toString() {
        return this.betStatement + " odds: " + this.numPos / this.numNeg;
    }

    public String getStatement() {
        return this.betStatement;
    }

    public int getNumNo() {
        return this.numNeg;
    }

    public int getNumYes() {
        return this.numPos;
    }

    public int getNumBets() {
        return this.amountBets;
    }
}
