package org.example.footnote;

public class Game {
    private String Away;
    private String Home;
    private String Date;
    private String Score;
    private String  ID;

    public Game(String id, String home, String away, String score, String date) {
        ID = id;
        Home = home;
        Away = away;
        Date = date;
        Score = score;
    }

    public Game() {

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    public String getAway() {
        return Away;
    }

    public void setAway(String away) {
        Away = away;
    }

    public String getHome() {
        return Home;
    }

    public void setHome(String home) {
        Home = home;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
