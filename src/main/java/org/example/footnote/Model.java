package org.example.footnote;

import javafx.scene.control.DatePicker;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private static Connection connect() {
        String url = "jdbc:sqlite:FootNoteData.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println(" Connection to Sqlite has been established");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    public static void fetchSquad(String TeamID, List<Player> playerList){
        String sql = "Select * from Players where TeamID = "+TeamID;
        try(
           Connection conn  = connect();
           PreparedStatement Pstmt = conn.prepareStatement(sql);
           ResultSet rs  = Pstmt.executeQuery(sql)
                ) {
            while(rs.next()){
                playerList.add(new Player(rs.getString("Name"),rs.getString("TeamID"),""+rs.getInt("ID"),rs.getString("Number")));
            }

        }catch(SQLException e){
            System.out.println();
        }


    }

    public static Player fetchPlayerByID(String PlayerID){
        String sql = "Select * from Players where ID = "+PlayerID;
        Player player =  null;
        try(
                Connection conn  = connect();
                PreparedStatement Pstmt = conn.prepareStatement(sql);
                ResultSet rs  = Pstmt.executeQuery()
        ) {
            while(rs.next()){
                player= new Player(rs.getString("Name"),rs.getString("TeamID"),""+rs.getInt("ID"),rs.getString("Number"));
                System.out.println("Player "+rs.getString("First_Name")+" "+rs.getString("Last_Name")+" has been Created");
            }
            return player;
        }catch(SQLException e){
            System.out.println();
        }

        return player;


    }
    public static Game fetchGameByID(String GameID){
        String sql = "Select * from Game Where ID =  " + GameID;
        Game game =  null;
        try(
                Connection conn  = connect();
                PreparedStatement Pstmt = conn.prepareStatement(sql);
                ResultSet rs  = Pstmt.executeQuery();

        ) {
            while(rs.next()){
                game= new Game(rs.getString("ID"),rs.getString("HomeID"),rs.getString("AwayID"), rs.getString("Score"),rs.getString("Date") );
                System.out.println("Game "+rs.getString("Date")+" has been Created Score: "+rs.getString("Score"));

            }
            return game;
        }catch(SQLException e){
            System.out.println();
        }

        return game;


    }
    public static List<Game> fetchAllGames(){
        String sql = "Select * from Game ";
        List<Game> games =  new ArrayList<>();
        try(
                Connection conn  = connect();
                PreparedStatement Pstmt = conn.prepareStatement(sql);
                ResultSet rs  = Pstmt.executeQuery();
        ) {
            while(rs.next()){
                games.add(new Game(rs.getString("ID"),rs.getString("Home"),rs.getString("Away"), rs.getString("Score"),rs.getString("Date") ));
            }
            return games;
        }catch(SQLException e){
            System.out.println();
        }
        return games;
    }

    public static List<Game> fetchGamesByHomeTeam(String Name){
        String sql = "Select * from Game where Home =  \'"+Name+"\'";
        List<Game> games =  new ArrayList<>();
        try(
                Connection conn  = connect();
                PreparedStatement Pstmt = conn.prepareStatement(sql);
                ResultSet rs  = Pstmt.executeQuery();
        ) {
            while(rs.next()){
                games.add(new Game(rs.getString("ID"),rs.getString("Home"),rs.getString("Away"), rs.getString("Score"),rs.getString("Date") ));
            }
            return games;
        }catch(SQLException e){
            System.out.println();
        }
        return games;
    }
    public static Game fetchGamesByID(String id){
        String sql = "Select * from Game where ID = "+id;

        try(
                Connection conn  = connect();
                PreparedStatement Pstmt = conn.prepareStatement(sql);
                ResultSet rs  = Pstmt.executeQuery();
        ) {

                return new Game(rs.getString("ID"),rs.getString("Home"),rs.getString("Away"), rs.getString("Score"),rs.getString("Date") );


        }catch(SQLException e){
            System.out.println();
        }
        return null;
    }
    public static List<Game> fetchGamesByAwayTeam(String Name){
        String sql = "Select * from Game where Away = \'"+Name+"\'";
        List<Game> games =  new ArrayList<>();
        try(
                Connection conn  = connect();
                PreparedStatement Pstmt = conn.prepareStatement(sql);
                ResultSet rs  = Pstmt.executeQuery();
        ) {
            while(rs.next()){
                games.add(new Game(rs.getString("ID"),rs.getString("Home"),rs.getString("Away"), rs.getString("Score"),rs.getString("Date") ));
            }
            return games;
        }catch(SQLException e){
            System.out.println();
        }
        return games;
    }

    public static List<Game> fetchGamesByBothTeams(String Name,String Name2){
        String sql = "Select * from Game where Away =  \'"+Name+"\' "+" AND Home = \'"+Name2+"\'";
        System.out.println(sql);
        List<Game> games =  new ArrayList<>();
        try(
                Connection conn  = connect();
                PreparedStatement Pstmt = conn.prepareStatement(sql);
                ResultSet rs  = Pstmt.executeQuery();
        ) {
            while(rs.next()){
                games.add(new Game(rs.getString("ID"),rs.getString("Home"),rs.getString("Away"), rs.getString("Score"),rs.getString("Date") ));
                System.out.println(rs.getString("ID"));
            }
            return games;
        }catch(SQLException e){
            System.out.println();
        }
        return games;
    }
    public static List<League> fetchAllLeagues(){
        String sql = "Select * from Leagues ";
        List<League> leagues =  new ArrayList<>();
        try(
                Connection conn  = connect();
                PreparedStatement Pstmt = conn.prepareStatement(sql);
                ResultSet rs  = Pstmt.executeQuery();
        ) {
            while(rs.next()){
                leagues.add(new League(rs.getString("ID"), rs.getString("Name")));

            }
            return leagues;
        }catch(SQLException e){
            System.out.println();
        }
        return leagues;
    }
    public static String fetchLeagueIDByName(String Name){
        String sql = "Select * from Leagues where Name = \'"+Name+"\'";
        try(
                Connection conn  = connect();
                PreparedStatement Pstmt = conn.prepareStatement(sql);
                ResultSet rs  = Pstmt.executeQuery();
        ) {




            return  rs.getString("ID");
        }catch(SQLException e){
            System.out.println();
        }
        return null;
    }


    public static List<Player> fetchAllPlayers(){
        String sql = "Select * from Players ";
        List<Player> players =  new ArrayList<>();
        try(
                Connection conn  = connect();
                PreparedStatement Pstmt = conn.prepareStatement(sql);
                ResultSet rs  = Pstmt.executeQuery();
        ) {
            while(rs.next()){
                players.add(new Player(rs.getString("Name"),rs.getString("TeamID"),""+rs.getInt("ID"),rs.getString("Number")));
            }
            return players;
        }catch(SQLException e){
            System.out.println();
        }
        return players;
    }
    public static List<Player> fetchPlayersByPosition(String Position){
        String sql = "Select * from Players Where " +Position+" = 1 AND TeamID = "+9746;

        List<Player> players =  new ArrayList<>();
        try(
                Connection conn  = connect();
                PreparedStatement Pstmt = conn.prepareStatement(sql);
                ResultSet rs  = Pstmt.executeQuery();
        ) {
            while(rs.next()){
                players.add(new Player(rs.getString("Name"),rs.getString("TeamID"),""+rs.getInt("ID"),rs.getString("Number")));

            }
            return players;
        }catch(SQLException e){
            System.out.println();
        }
        return players;
    }
    public static List<Player> fetchPlayersByTeam(String TeamID){
        String sql = "Select * from Players where TeamID = "+TeamID;
        List<Player> players =  new ArrayList<>();
        try(
                Connection conn  = connect();
                PreparedStatement Pstmt = conn.prepareStatement(sql);
                ResultSet rs  = Pstmt.executeQuery();
        ) {
            while(rs.next()){
                players.add(new Player(rs.getString("Name"),rs.getString("TeamID"),""+rs.getInt("ID"),rs.getString("Number")));

            }
            return players;
        }catch(SQLException e){
            System.out.println();
        }
        return players;
    }
    public static List<Player> fetchPlayersByTeamExceptKeepers(String TeamID){
        String sql = "Select * from Players where Keeper IS  NULL AND TeamID = "+TeamID;
        List<Player> players =  new ArrayList<>();
        try(
                Connection conn  = connect();
                PreparedStatement Pstmt = conn.prepareStatement(sql);
                ResultSet rs  = Pstmt.executeQuery();
        ) {
            while(rs.next()){
                players.add(new Player(rs.getString("Name"),rs.getString("TeamID"),""+rs.getInt("ID"),rs.getString("Number")));

            }
            return players;
        }catch(SQLException e){
            System.out.println();
        }
        return players;
    }
    public static List<Team> fetchAllTeams(){
        String sql = "Select * from Team ";
        List<Team> teams =  new ArrayList<>();
        try(
                Connection conn  = connect();
                PreparedStatement Pstmt = conn.prepareStatement(sql);
                ResultSet rs  = Pstmt.executeQuery();
        ) {
            while(rs.next()){
                teams.add(new Team(rs.getString("id"),rs.getString("Name")));
            }
            return teams;
        }catch(SQLException e){
            System.out.println();
        }
        return teams;
    }
    public static Team fetchByTeamByID(String id){
        String sql = "Select * from Team where id = "+id;

        try(
                Connection conn  = connect();
                PreparedStatement Pstmt = conn.prepareStatement(sql);
                ResultSet rs  = Pstmt.executeQuery();
        ) {

                return  new Team(rs.getString("id"),rs.getString("Name"));


        }catch(SQLException e){
            System.out.println();
        }
        return null ;
    }
    public static Team fetchByTeamByName(String Name){
        String sql = "Select * from Team where Name = \'"+Name+"\'";

        try(
                Connection conn  = connect();
                PreparedStatement Pstmt = conn.prepareStatement(sql);
                ResultSet rs  = Pstmt.executeQuery();
        ) {

            return  new Team(rs.getString("id"),rs.getString("Name"));


        }catch(SQLException e){
            System.out.println();
        }
        return null ;
    }

    public static List<Team> fetchTeamsByLeagues(String ID){
        String sql = "Select * from Team where LeagueID = "+ID;
        List<Team> teams =  new ArrayList<>();
        try(
                Connection conn  = connect();
                PreparedStatement Pstmt = conn.prepareStatement(sql);
                ResultSet rs  = Pstmt.executeQuery();
        ) {
            while(rs.next()){
                teams.add(new Team(rs.getString("id"),rs.getString("Name")));
            }
            return teams;
        }catch(SQLException e){
            System.out.println();
        }
        return teams;
    }
    public static List<Stats> fetchAllStats(String ID){
        String sql = "Select * from Stats ";
        List<Stats> stats =  new ArrayList<>();
        try(
                Connection conn  = connect();
                PreparedStatement Pstmt = conn.prepareStatement(sql);
                ResultSet rs  = Pstmt.executeQuery();
        ) {
            while(rs.next()){

                stats.add(new Stats(rs.getString("PlayerID"),rs.getInt("Appearances"),rs.getInt("Minutes"),rs.getInt("Goals"),rs.getInt("Assists"),rs.getInt("Yellows"),rs.getInt("Reds")));
            }
            return stats;
        }catch(SQLException e){
            System.out.println();
        }
        return stats;
    }
    public static Stats fetchPlayerStat(String ID){
        String sql = "Select * from Stats where PlayerID = "+ID;

        try(
                Connection conn  = connect();
                PreparedStatement Pstmt = conn.prepareStatement(sql);
                ResultSet rs  = Pstmt.executeQuery();
        ) {
            while(rs.next()){

                return   new Stats(rs.getString("PlayerID"),rs.getInt("Appearances"),rs.getInt("Minutes"),rs.getInt("Goals"),rs.getInt("Assists"),rs.getInt("Yellows"),rs.getInt("Reds"));
            }

        }catch(SQLException e){
            System.out.println();
        }
      return null;
    }

    public static void showPlayer( ){
        String sql = "Select * from Players";

        try(
                Connection conn  = connect();
                PreparedStatement Pstmt = conn.prepareStatement(sql);
                ResultSet rs  = Pstmt.executeQuery()
        ) {
            while(rs.next()){
                System.out.println("Player "+rs.getString("First_Name")+" "+rs.getString("Last_Name")+" has been Created");
            }
        }catch(SQLException e){
            System.out.println();
        }
    }
    public static void insertLeague(int ID) {
        String sql = " insert into Leagues (ID) values (?)";
        try(Connection conn= connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ID);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertTeam(int ID,int LeagueID,String Name) {
        String sql = " insert  into Team  (ID,LeagueID,Name) values (?,?,?)";
        try(Connection conn= connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ID);
            pstmt.setInt(2, LeagueID);
            pstmt.setString(3, Name);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void insertNote(int GameID,String Name,int PlayerID,String NoteText) {
        String sql = " insert  into Note  (GameID,PlayerName,PlayerID,NoteText,Date) values (?,?,?,?,?)";
        try(Connection conn= connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, GameID);
            pstmt.setString(2, Name);
            pstmt.setInt(3, PlayerID);
            pstmt.setString(4, NoteText);
            pstmt.setString(5, LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static String fetchNote(String PlayerID,String GameID){
        String sql = "Select * from Note where PlayerID = "+PlayerID+" AND GameID = " +GameID ;

        try(
                Connection conn  = connect();
                PreparedStatement Pstmt = conn.prepareStatement(sql);
                ResultSet rs  = Pstmt.executeQuery();
        ) {

            return  rs.getString("NoteText");


        }catch(SQLException e){
            System.out.println();
        }
        return null ;
    }
    public static List<Notes> fetchNoteReturnNotes(String PlayerID){
        String sql = "Select * from Note where PlayerID = "+PlayerID;
List<Notes> notes  = new ArrayList<>();
        try(
                Connection conn  = connect();
                PreparedStatement Pstmt = conn.prepareStatement(sql);
                ResultSet rs  = Pstmt.executeQuery();
        ) {
    while (rs.next()) {
      notes.add(  new Notes(rs.getString("GameID"), rs.getString("NoteText"), rs.getString("Date")));
    }
return notes;
        }catch(SQLException e){
            System.out.println();
        }
        return null ;
    }
    public static List<Notes> fetchAllNotes(){
        String sql = "Select * from Note ";
        List<Notes> notes  = new ArrayList<>();
        try(
                Connection conn  = connect();
                PreparedStatement Pstmt = conn.prepareStatement(sql);
                ResultSet rs  = Pstmt.executeQuery();
        ) {
            while (rs.next()) {
                notes.add(  new Notes(rs.getString("GameID"), rs.getString("NoteText"), rs.getString("Date")));
            }
            return notes;
        }catch(SQLException e){
            System.out.println();
        }
        return null ;
    }
    public static void updateNote(String PlayerID,String GameID,String Text){
        String sql = "UPDATE Note SET NoteText = \'"+Text+"\' WHERE PlayerID = "+PlayerID+" AND GameID = " +GameID ;
        System.out.println(sql);
        try(
                Connection conn= connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {

            pstmt.executeUpdate();

        }catch(SQLException e){
            System.out.println();
        }
    }
    public static void updateGame(String ID,String Text){
        String sql = "UPDATE Game SET Score = \'"+Text+"\' WHERE ID = "+ID;
        System.out.println(sql);
        try(
                Connection conn= connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {

            pstmt.executeUpdate();

        }catch(SQLException e){
            System.out.println();
        }
    }
    public static void insertPlayer(int ID,int TeamID,String Name,String Number,String Position) {
        String sql = " insert  into Players  (ID,TeamID,Name,Number,"+Position+") values (?,?,?,?,?)";
        try(Connection conn= connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ID);
            pstmt.setInt(2, TeamID);
            pstmt.setString(3, Name);
            pstmt.setString(4, Number);
            pstmt.setInt(5, 1);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void insertPlayerStats(int PlayerID,int Appearances,int Minutes,int Goals,int Assists,int Yellows,int Reds) {
        String sql = " insert  into Stats  (PlayerID,Appearances,Minutes,Goals,Assists,Yellows,Reds) values (?,?,?,?,?,?,?)";
        try(Connection conn= connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, PlayerID);
            pstmt.setInt(2, Appearances);
            pstmt.setInt(3, Minutes);
            pstmt.setInt(4, Goals);
            pstmt.setInt(5, Assists);
            pstmt.setInt(6, Yellows);
            pstmt.setInt(7, Reds);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static List<String> fetchAllTeamsIDs(){
        String sql = "Select * from Team ";
        List<String> ids =  new ArrayList<>();
        try(
                Connection conn  = connect();
                PreparedStatement Pstmt = conn.prepareStatement(sql);
                ResultSet rs  = Pstmt.executeQuery();
        ) {
            while(rs.next()){
                ids.add(rs.getString("id"));
            }
            return ids;
        }catch(SQLException e){
            System.out.println();
        }
        return ids;
    }
    public static List<String> fetchAllPlayerIDs(){
        String sql = "Select * from Players where Keeper IS NULL";
        List<String> ids =  new ArrayList<>();
        try(
                Connection conn  = connect();
                PreparedStatement Pstmt = conn.prepareStatement(sql);
                ResultSet rs  = Pstmt.executeQuery();
        ) {
            while(rs.next()){
                ids.add(rs.getString("ID"));
            }
            return ids;
        }catch(SQLException e){
            System.out.println();
        }
        return ids;
    }

    public static void insertGame(int ID,String  Score,String Date,int HomeID,String Home,int AwayID,String Away) {
        String sql = " insert  into Game  (ID,Score,Date,HomeID,Home,AwayID,Away) values (?,?,?,?,?,?,?)";
        try(Connection conn= connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ID);
            pstmt.setString(2, Score);
            pstmt.setString(3, Date);
            pstmt.setInt(4, HomeID);
            pstmt.setString(5,Home);
            pstmt.setInt(6, AwayID);
            pstmt.setString(7,Away);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        for (Game item: Model.fetchAllGames()) {
            if(Integer.parseInt(item.getDate().substring(0,4))<=LocalDateTime.now().getYear()&&item.getScore()==null) {



                if(Integer.parseInt(item.getDate().substring(5,7))<=LocalDateTime.now().getMonthValue()) {

                    if(Integer.parseInt(item.getDate().substring(8,10))<=LocalDateTime.now().getDayOfMonth()) {
                        System.out.println(item.getID());




                    }
                }
            }

        }
    }



    }


