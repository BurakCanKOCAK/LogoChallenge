package com.bkocak.logochallenge.model;

import java.util.Date;

/**
 * Created by BurakCan on 30/07/2016.
 */
public class DataX {
    private String userId;
    private int statusCode;
    private Date timeEntrance;
    private int gameMode;
    private int gameStatus;

    public DataX() {
    }

    public DataX(String userId, int statusCode, Date timeEntrance, int gameMode, int gameStatus) {
        this.userId = userId;
        this.statusCode = statusCode;
        this.timeEntrance = timeEntrance;
        this.gameMode = gameMode;
        this.gameStatus = gameStatus;
    }

    public String getUserId() {
        return userId;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Date getTimeEntrance() {
        return timeEntrance;
    }

    public int getGameMode() {
        return gameMode;
    }

    public int getGameStatus() {
        return gameStatus;
    }
}
