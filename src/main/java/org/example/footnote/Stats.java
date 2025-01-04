package org.example.footnote;

public class Stats {
    private String PlayerID;
    private int Goals;
    private int Assists;
    private int YellowCard;
    private int RedCard;
    private int Appearances;
    private int Minutes;
    private int Rating;

    public Stats(String playerID, int appearances, int minutes, int goals, int assists, int yellowCard, int redCard) {
        PlayerID = playerID;
        Appearances = appearances;
        Minutes = minutes;
        Goals = goals;
        Assists = assists;
        YellowCard = yellowCard;
        RedCard = redCard;
    }

    public String getPlayerID() {
        return PlayerID;
    }

    public void setPlayerID(String playerID) {
        PlayerID = playerID;
    }

    public int getGoals() {
        return Goals;
    }

    public void setGoals(int goals) {
        Goals = goals;
    }

    public int getAssists() {
        return Assists;
    }

    public void setAssists(int assists) {
        Assists = assists;
    }

    public int getYellowCard() {
        return YellowCard;
    }

    public void setYellowCard(int yellowCard) {
        YellowCard = yellowCard;
    }

    public int getRedCard() {
        return RedCard;
    }

    public void setRedCard(int redCard) {
        RedCard = redCard;
    }

    public int getAppearances() {
        return Appearances;
    }

    public void setAppearances(int appearances) {
        Appearances = appearances;
    }
}
