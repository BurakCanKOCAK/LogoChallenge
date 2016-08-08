package com.bkocak.logochallenge.model;

import java.util.Date;

/**
 * Created by bkocak on 08/08/2016.
 */
//http://stackoverflow.com/questions/17123384/how-to-generate-class-diagram-uml-on-android-studio
public class Room {
    private int roomSize;
    private String roomType;
    private Date roomStartDate;
    private String roomStatus;

    public Room(int roomSize, String roomType, Date roomStartDate, String roomStatus) {
        this.roomSize = roomSize;
        this.roomType = roomType;
        this.roomStartDate = roomStartDate;
        this.roomStatus = roomStatus;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public String getRoomType() {
        return roomType;
    }

    public Date getRoomStartDate() {
        return roomStartDate;
    }

    public String getRoomStatus() {
        return roomStatus;
    }
}
