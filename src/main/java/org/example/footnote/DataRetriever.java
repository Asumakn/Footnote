package org.example.footnote;

import com.almasb.fxgl.net.DownloadCallback;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sun.net.httpserver.Request;
import javafx.scene.chart.PieChart;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;

import javax.swing.text.html.HTML;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Scanner;


public class DataRetriever {
    public static String fetchData(String id){
        String connUrl = "https://www.fotmob.com";
        connUrl = connUrl + id;

    try {
        HttpURLConnection apiConn = getResponse(connUrl);
if(apiConn.getResponseCode()!=200){
        System.out.println("Error could not connect");
        return null;
}
else {
    StringBuilder mp = new StringBuilder();
Scanner lp = new Scanner(apiConn.getInputStream());
while (lp.hasNext()){
    mp.append(lp.nextLine());
}
lp.close();
    return mp.toString();
}
    }catch (IOException e){
    System.out.println(e.getMessage());
    }
return null;
    }

    public  static HttpURLConnection getResponse (String urlString){

        try{
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

        return conn;
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        return null;

    }


public static void addLeagueTeams(Integer LeagueID) throws ParseException {
    JSONParser parser = new JSONParser();
    JSONObject result =(JSONObject) parser.parse(Jsoup.parse(fetchData("/leagues/"+LeagueID.toString())).getElementById("__NEXT_DATA__").data());

    JSONObject o1 =(JSONObject) parser.parse(result.get("props").toString());
    JSONObject o2 =(JSONObject) parser.parse(o1.get("pageProps").toString());
    JSONObject o3 =(JSONObject) parser.parse(o2.get("overview").toString());
    JSONArray o4 =(JSONArray) parser.parse(o3.get("table").toString());
    JSONObject o5 =(JSONObject) parser.parse(o4.get(0).toString());
    JSONObject o6 =(JSONObject) parser.parse(o5.get("data").toString());
    JSONObject o7 =(JSONObject) parser.parse(o6.get("table").toString());
    JSONArray o8 =(JSONArray) parser.parse(o7.get("all").toString());
    for (int i = 0 ; i<o8.size();i++) {
        JSONObject o9 = (JSONObject) parser.parse(o8.get(i).toString());
        Model.insertTeam(Integer.parseInt(o9.get("id").toString()), Integer.parseInt(o6.get("leagueId").toString()), o9.get("name").toString());
        System.out.println(o8.get(0));
        System.out.println(o9.get("name"));
    }
}
public static void addSquad(Integer TeamID ) throws ParseException {

    JSONParser parser = new JSONParser();
    JSONObject result = (JSONObject) parser.parse(Jsoup.parse(fetchData("/teams/"+TeamID.toString())).getElementById("__NEXT_DATA__").data());

    JSONObject o1 = (JSONObject) parser.parse(result.get("props").toString());
    JSONObject o2 = (JSONObject) parser.parse(o1.get("pageProps").toString());

    JSONObject o3 = (JSONObject) parser.parse(o2.get("fallback").toString());
    JSONObject o4 = (JSONObject) parser.parse(o3.get("team-" + TeamID.toString()).toString());
    JSONArray o5 = (JSONArray) parser.parse(o4.get("squad").toString());

    for (int i = 1; i < 5; i++){
        JSONObject o6 = (JSONObject) parser.parse(o5.get(i).toString());
        JSONArray o7 = (JSONArray) parser.parse(o6.get("members").toString());
        for (int j = 0; j<o7.size();j++) {
            JSONObject o8 = (JSONObject) parser.parse(o7.get(j).toString());
            JSONObject o9 = (JSONObject) parser.parse(o8.get("role").toString());

            Model.insertPlayer(Integer.parseInt(o8.get("id").toString()),TeamID,o8.get("name").toString(),o8.get("shirtNumber").toString(),o9.get("fallback").toString());

        }

    }

}

public static Integer getValueStats(int type,JSONArray o5) throws ParseException {
    JSONParser parser = new JSONParser();


    JSONObject o6 = (JSONObject) parser.parse(o5.get(type).toString());

    return Integer.parseInt(o6.get("value").toString());
}
    public static String checkValuesPlacing(int type,JSONArray o5) throws ParseException {
        JSONParser parser = new JSONParser();


        JSONObject o6 = (JSONObject) parser.parse(o5.get(type).toString());

        return o6.get("title").toString();
    }
public static void fetchPlayerStats(String id) throws ParseException {
    JSONParser parser = new JSONParser();
    JSONObject result = (JSONObject) parser.parse(Jsoup.parse(fetchData("/players/"+id)).getElementById("__NEXT_DATA__").data());
    JSONObject o1 = (JSONObject) parser.parse(result.get("props").toString());
    JSONObject o2 = (JSONObject) parser.parse(o1.get("pageProps").toString());
    JSONObject o3 = (JSONObject) parser.parse(o2.get("data").toString());
    JSONObject o4 = (JSONObject) parser.parse(o3.get("mainLeague").toString());
    JSONArray o5 = (JSONArray) parser.parse(o4.get("stats").toString());
    if(checkValuesPlacing(5,o5).toString().equalsIgnoreCase("Rating")) {
        Model.insertPlayerStats(Integer.parseInt(id),getValueStats(3,o5),getValueStats(4,o5),getValueStats(0,o5),getValueStats(1,o5),getValueStats(6,o5),getValueStats(7,o5));
    }else {
        Model.insertPlayerStats(Integer.parseInt(id),getValueStats(3,o5),getValueStats(4,o5),getValueStats(0,o5),getValueStats(1,o5),getValueStats(5,o5),getValueStats(6,o5));

    }
}
public static void loadGamesFromLeague(String id) throws ParseException {
    JSONParser parser = new JSONParser();
    JSONObject result = (JSONObject) parser.parse(Jsoup.parse(fetchData("/leagues/"+id)).getElementById("__NEXT_DATA__").data());

    JSONObject o1 = (JSONObject) parser.parse(result.get("props").toString());
    JSONObject o2 = (JSONObject) parser.parse(o1.get("pageProps").toString());
    JSONObject o3 = (JSONObject) parser.parse(o2.get("matches").toString());
    JSONArray o4 = (JSONArray) parser.parse(o3.get("allMatches").toString());
    for(int i = 0;i<o4.size();i++){
        JSONObject o5 =(JSONObject) parser.parse(o4.get(i).toString());
        JSONObject o51 = (JSONObject) parser.parse(o5.get("home").toString());
        JSONObject o52 = (JSONObject) parser.parse(o5.get("away").toString());
        JSONObject o53 = (JSONObject) parser.parse(o5.get("status").toString());
        if(o53.get("scoreStr")!=null) {
            Model.insertGame(Integer.parseInt(o5.get("id").toString()), o53.get("scoreStr").toString(), o53.get("utcTime").toString(), Integer.parseInt(o51.get("id").toString()), o51.get("name").toString(), Integer.parseInt(o52.get("id").toString()), o52.get("name").toString());
        }else{
            Model.insertGame(Integer.parseInt(o5.get("id").toString()), null, o53.get("utcTime").toString(), Integer.parseInt(o51.get("id").toString()), o51.get("name").toString(), Integer.parseInt(o52.get("id").toString()), o52.get("name").toString());
        }

    }
}
    public static void updateGamesbyLeague(String id,String gameID) throws ParseException {
        if(Model.fetchGamesByID(gameID).getScore()==null) {
            JSONParser parser = new JSONParser();
            JSONObject result = (JSONObject) parser.parse(Jsoup.parse(fetchData("/leagues/" + id)).getElementById("__NEXT_DATA__").data());

            JSONObject o1 = (JSONObject) parser.parse(result.get("props").toString());
            JSONObject o2 = (JSONObject) parser.parse(o1.get("pageProps").toString());
            JSONObject o3 = (JSONObject) parser.parse(o2.get("matches").toString());
            JSONArray o4 = (JSONArray) parser.parse(o3.get("allMatches").toString());
            for (int i = 0; i < o4.size(); i++) {
                JSONObject o5 = (JSONObject) parser.parse(o4.get(i).toString());
                JSONObject o53 = (JSONObject) parser.parse(o5.get("status").toString());
                if (o53.get("scoreStr") != null) {
                    Model.updateGame(o5.get("id").toString(), o53.get("scoreStr").toString());
                }


            }
        }
    }
    public static Integer updateGames() throws ParseException {
     int count =0;
            JSONParser parser = new JSONParser();
            for (String leagueID : new String[]{"47", "87", "55", "54", "53"}) {
                JSONObject result = (JSONObject) parser.parse(Jsoup.parse(fetchData("/leagues/" + leagueID)).getElementById("__NEXT_DATA__").data());

                JSONObject o1 = (JSONObject) parser.parse(result.get("props").toString());
                JSONObject o2 = (JSONObject) parser.parse(o1.get("pageProps").toString());
                JSONObject o3 = (JSONObject) parser.parse(o2.get("matches").toString());
                JSONArray o4 = (JSONArray) parser.parse(o3.get("allMatches").toString());
                for (int i = 0; i < o4.size(); i++) {
                    JSONObject o5 = (JSONObject) parser.parse(o4.get(i).toString());
                    JSONObject o53 = (JSONObject) parser.parse(o5.get("status").toString());
                    if (o53.get("scoreStr") != null) {
                        if(Model.fetchGamesByID(o5.get("id").toString()).getScore()==null) {
                            Model.updateGame(o5.get("id").toString(), o53.get("scoreStr").toString());
                            count++;
                        }
                    }


                }
            }
        return count;
    }

    public static void main(String[] args) throws IOException, ParseException {
 JSONParser parser = new JSONParser();
   JSONObject result = (JSONObject) parser.parse(Jsoup.parse( fetchData("/leagues/47")).getElementById("__NEXT_DATA__").data());
      System.out.println(result);




}
    }

