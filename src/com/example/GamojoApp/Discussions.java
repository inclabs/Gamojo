package com.example.GamojoApp;

public class Discussions {

    private final int userId;
    private final int gameId;
    private final int convId;
    private final String comments;

    public Discussions(int convId, int gameId, int userId, String comments) {
        this.convId = convId;
//        convid is similar to rowId
        this.gameId = gameId;
        this.userId = userId;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return this.comments;
    }

    public int getConvId() {
        return this.convId;
    }

    public int getGameId() {
        return this.gameId;
    }

    public int getUserId() {
        return this.userId;
    }

    public String getComments() {
        return this.comments;
    }
}
