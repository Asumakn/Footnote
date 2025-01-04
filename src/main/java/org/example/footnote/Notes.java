package org.example.footnote;

import java.util.Date;

public class Notes {
    private String GameID;
    private String notes;
    private String Date;

    public String getGameID() {
        return GameID;
    }

    public void setGameID(String gameID) {
        GameID = gameID;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Notes(String gameID, String notes, String date) {
        GameID = gameID;
        this.notes = notes;
        Date = date;
    }
}
