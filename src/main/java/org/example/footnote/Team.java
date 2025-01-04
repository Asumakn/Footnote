package org.example.footnote;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private int Points;
    private String ID;
    private String Name;
    private List<Player> roster;
    private List<Player> eleven;

    public Team(String ID, String name) {
        this.ID = ID;
        Name = name;
        roster = Model.fetchPlayersByTeam(ID);
        eleven= new ArrayList<>();
    }

    public Team() {

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPoints() {
        return Points;
    }

    public void setPoints(int points) {
        Points = points;
    }

    public List<Player> getRoster() {
        return roster;
    }

    public void setRoster(List<Player> roster) {
        this.roster = roster;
    }

    public List<Player> getEleven() {
        return eleven;
    }

    public void setEleven(List<Player> eleven) {
        this.eleven = eleven;
    }
}
