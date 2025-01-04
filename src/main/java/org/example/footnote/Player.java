package org.example.footnote;

import java.util.List;

public class Player {
    private String Name;
    private String TeamID;
    private String ID;
    private String Number;

    private List<Position> positions;
    private List<Notes> playerNotes;




    public Player(String Name, String teamID, String ID, String number) {
        this.Name = Name;
        TeamID = teamID;
        this.ID = ID;
        Number = number;
    }

    public Player() {

    }

    public String getID() {
        return ID;
    }


    public String getTeamID() {
        return TeamID;
    }

    public void setTeamID(String teamID) {
        TeamID = teamID;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }


    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public List<Notes> getPlayerNotes() {
        return playerNotes;
    }

    public void setPlayerNotes(List<Notes> playerNotes) {
        this.playerNotes = playerNotes;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
