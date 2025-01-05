package org.example.footnote;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;


public class Controller {

    public TableView<Game> HomeTable =  new TableView<>();
    public ComboBox<String> PitchBoxGK = new ComboBox<>();
    public ComboBox<String> PitchBoxLB= new ComboBox<>();
    public ComboBox<String> PitchBoxLCB= new ComboBox<>();
    public ComboBox<String> PitchBoxLW= new ComboBox<>();
    public ComboBox<String> PitchBoxLCM= new ComboBox<>();
    public ComboBox<String> PitchBoxLST= new ComboBox<>();
    public ComboBox<String> PitchBoxRCB= new ComboBox<>();
    public ComboBox<String> PitchBoxRB= new ComboBox<>();
    public ComboBox<String> PitchBoxRCM= new ComboBox<>();
    public ComboBox<String> PitchBoxRST= new ComboBox<>();
    public ComboBox<String> PitchBoxRW= new ComboBox<>();
    public ComboBox<String> PitchBoxLDM= new ComboBox<>();
    public ComboBox<String> PitchBoxCAM= new ComboBox<>();
    public ComboBox<String> PitchBoxRDM= new ComboBox<>();
    public ComboBox<String> PitchBoxST= new ComboBox<>();
    public ComboBox<String> PitchBoxLWB= new ComboBox<>();
    public ComboBox<String> PitchBoxCB= new ComboBox<>();
    public ComboBox<String> PitchBoxRWB= new ComboBox<>();
    public ComboBox<String> PitchBoxDM= new ComboBox<>();
    public ComboBox<String>  FormationPicker = new ComboBox<>();
    public ImageView PlayerIMG;
    public ComboBox<String> Teambox= new ComboBox<>();
    public ComboBox<String> Teambox2= new ComboBox<>();
    public ComboBox<String> LeagueBox= new ComboBox<>();
    public TableColumn<Player,String> NoteAwayTeamColumn = new TableColumn<>();
    public TableView<Player> NoteAwayTeamTable = new TableView<>();
    public TableColumn<Player,String> NoteTeamColumn =new TableColumn<>();
    public TableView<Player> NoteTeamTable = new TableView<>();
    public TextArea Notebox = new TextArea();
    public TableColumn<Stats, String> GameColumn = new TableColumn<Stats, String>();
    public TableColumn<Game, String> StatDateColumn= new TableColumn<Game, String>();
    public TableColumn<Stats, String> GoalsColumn= new TableColumn<Stats, String>();
    public TableColumn<Stats, String> AssistsColumn= new TableColumn<Stats, String>();
    public TableColumn<Stats, String> RedColumn= new TableColumn<Stats, String>();
    public TableColumn<Stats, String> YellowColumn= new TableColumn<Stats, String>();
    public TableColumn<Notes, String> GameIDColumn= new TableColumn<Notes, String>();
    public TableColumn<Notes, String> GameDateColumn= new TableColumn<Notes, String>();
    public TableView<Notes> PlayerNoteTable = new TableView<>();
    public TableView<Stats> StatTable = new TableView<>();
    public TextArea PlayerTxtBox = new TextArea();
    public Label PlayerNameLabel = new Label();
    public Label DataLabel= new Label();
    public TableView<Notes> MainNoteTable = new TableView();
    private Stage stage;
    private Scene scene;
    private Parent root;
    public TableColumn<Game,String> AwayTeamColumn = new TableColumn<>();
    public TableColumn<Game,String> ScoreColumn= new TableColumn<>();
    public TableColumn<Game,String> DateColumn= new TableColumn<>();
    public TableColumn<Game,String> HomeTeamColumn= new TableColumn<>();


public void loadHomePage(ActionEvent event) throws IOException {
    root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();


}
    public void loadMainPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
    public void loadDataPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DataPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
    public void UpdateGames() throws ParseException {
        DataLabel.setText( DataRetriever.updateGames()+" Games Updated");
    }

    public void AddGames() throws ParseException {
        for (String item: new String[]{"47","87","55","54","53"}) {
            DataRetriever.loadGamesFromLeague(item);
        }
    }
    public void loadTeamPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TeamPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
public void loadHomePageElement(){

Teambox.getItems().clear();
Teambox2.getItems().clear();
    if (LeagueBox.getValue()==null) {
        for (Team item : Model.fetchAllTeams()) {
            Teambox.getItems().add(item.getName());
            Teambox2.getItems().add(item.getName());
        }
    }else{
        System.out.println(Model.fetchLeagueIDByName(LeagueBox.getValue()));
        for (Team item : Model.fetchTeamsByLeagues(Model.fetchLeagueIDByName(LeagueBox.getValue()))) {
            Teambox.getItems().add(item.getName());
            Teambox2.getItems().add(item.getName());
        }
    }
}
public void loadPlayerPageTables(){
    PlayerNameLabel.setText(Application.activePlayer.getName());
    GameColumn.setCellValueFactory(new PropertyValueFactory<Stats,String>("Appearances"));
    AssistsColumn.setCellValueFactory(new PropertyValueFactory<Stats,String>("Assists"));
    GoalsColumn.setCellValueFactory(new PropertyValueFactory<Stats,String>("Goals"));
    RedColumn.setCellValueFactory(new PropertyValueFactory<Stats,String>("RedCard"));
    YellowColumn.setCellValueFactory(new PropertyValueFactory<Stats,String>("YellowCard"));

    StatTable.getItems().addAll(Model.fetchPlayerStat(Application.activePlayer.getID()));
    GameIDColumn.setCellValueFactory(new PropertyValueFactory<Notes,String>("GameID"));
    GameDateColumn.setCellValueFactory(new PropertyValueFactory<Notes,String>("Date"));

    PlayerNoteTable.getItems().addAll(Model.fetchNoteReturnNotes(Application.activePlayer.getID()));



}

    public void LoadMainTable(){
        PlayerNameLabel.setText(Application.activePlayer.getName());
        GameColumn.setCellValueFactory(new PropertyValueFactory<Stats,String>("Appearances"));
        AssistsColumn.setCellValueFactory(new PropertyValueFactory<Stats,String>("Assists"));
        GoalsColumn.setCellValueFactory(new PropertyValueFactory<Stats,String>("Goals"));
        RedColumn.setCellValueFactory(new PropertyValueFactory<Stats,String>("RedCard"));
        YellowColumn.setCellValueFactory(new PropertyValueFactory<Stats,String>("YellowCard"));

        StatTable.getItems().addAll(Model.fetchPlayerStat(Application.activePlayer.getID()));
        GameIDColumn.setCellValueFactory(new PropertyValueFactory<Notes,String>("GameID"));
        GameDateColumn.setCellValueFactory(new PropertyValueFactory<Notes,String>("Date"));

        MainNoteTable.getItems().addAll(Model.fetchAllNotes());



    }
public void showNotes(){
    int selection = PlayerNoteTable.getSelectionModel().getFocusedIndex();
PlayerTxtBox.setText(PlayerNoteTable.getItems().get(selection).getNotes());



}
    public void showNotesMain(){
        int selection = MainNoteTable.getSelectionModel().getFocusedIndex();
        PlayerTxtBox.setText(MainNoteTable.getItems().get(selection).getNotes());



    }

public void LoadHomeTable(){

    HomeTable.getItems().clear();

AwayTeamColumn.setCellValueFactory(new PropertyValueFactory<Game,String>("Away"));
HomeTeamColumn.setCellValueFactory(new PropertyValueFactory<Game,String>("Home"));
ScoreColumn.setCellValueFactory(new PropertyValueFactory<Game,String>("Score"));
DateColumn.setCellValueFactory(new PropertyValueFactory<Game,String>("Date"));

if (Teambox.getValue()==null&&Teambox2.getValue()==null){
    ObservableList<Game> gameList = HomeTable.getItems();
    gameList.addAll(Model.fetchAllGames());
    HomeTable.setItems(gameList);
    System.out.println("Games have been Loaded");

}else if (Teambox.getValue()!=null&&Teambox2.getValue()==null){
    ObservableList<Game> gameList = HomeTable.getItems();
    gameList.addAll(Model.fetchGamesByHomeTeam(Teambox.getValue().toString()));
    HomeTable.setItems(gameList);
    System.out.println("Games have been Loaded");

} else if (Teambox.getValue()==null&& Teambox2.getValue()!=(null)) {
    ObservableList<Game> gameList = HomeTable.getItems();
    gameList.addAll(Model.fetchGamesByAwayTeam(Teambox2.getValue().toString()));
    HomeTable.setItems(gameList);
    System.out.println("Games have been Loaded");


} else if (Teambox.getValue()!=null&& Teambox2.getValue()!=null) {
    ObservableList<Game> gameList = HomeTable.getItems();
    gameList.addAll(Model.fetchGamesByBothTeams(Teambox2.getValue(),Teambox.getValue().toString()));
    HomeTable.setItems(gameList);
    System.out.println("Games have been Loaded");
}


}
public void initialize(){
    LoadMainTable();
    loadPlayerPageTables();
    loadNoteTable();
    for (League item:Model.fetchAllLeagues()){
        LeagueBox.getItems().add(item.getName());
    }
    loadHomePageElement();
    LoadHomeTable();
    LoadPlayers();

}
public void ChooseFormation(ActionEvent event) throws IOException {

    root = FXMLLoader.load(getClass().getResource(FormationPicker.getSelectionModel().getSelectedItem()+".fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();


}

public void SaveNote(){
    Integer selection =   NoteTeamTable.getSelectionModel().getFocusedIndex();
    Player notePlayer = NoteTeamTable.getItems().get(selection);
    if(Model.fetchNote(notePlayer.getID(),Application.activeGame.getID())==null) {
        Model.insertNote(Integer.parseInt(Application.activeGame.getID()), notePlayer.getName(), Integer.parseInt(notePlayer.getID()), Notebox.getText());
    }else{
        Model.updateNote(notePlayer.getID(),Application.activeGame.getID(), Notebox.getText());
    }

    }
    public void PlayerSaveNote(){


            Model.updateNote(Application.activePlayer.getID(),Application.activeGame.getID(), PlayerTxtBox.getText());



    }
    public void PlayerSaveNoteMain(){
 int selection = MainNoteTable.getSelectionModel().getFocusedIndex();

        Model.updateNote(Application.activePlayer.getID(),MainNoteTable.getItems().get(selection).getGameID(), PlayerTxtBox.getText());



    }
    public void loadPlayerPage(ActionEvent event) throws IOException {
int selection = NoteTeamTable.getSelectionModel().getFocusedIndex();
Application.activePlayer = NoteTeamTable.getItems().get(selection);
        System.out.println(Application.activePlayer.getName());
        System.out.println(Application.activeTeam);
        root = FXMLLoader.load(getClass().getResource("PlayerPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }




    public void LoadNote(){
        Integer selection =   NoteTeamTable.getSelectionModel().getFocusedIndex();
        Player notePlayer = NoteTeamTable.getItems().get(selection);
        Notebox.setText(Model.fetchNote(notePlayer.getID(),Application.activeGame.getID()));


    }


public void loadNotesPage(ActionEvent event) throws IOException {



    root = FXMLLoader.load(getClass().getResource("NotePage.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();

}
    public void loadNotesPageHome(ActionEvent event) throws IOException {
        int selection = HomeTable.getSelectionModel().getFocusedIndex();
        Application.activeGame = HomeTable.getItems().get(selection);
        Application.activeTeam = Model.fetchByTeamByName(HomeTable.getItems().get(selection).getHome());
        root = FXMLLoader.load(getClass().getResource("NotePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void loadNotesPageAway(ActionEvent event) throws IOException {
        int selection = HomeTable.getSelectionModel().getFocusedIndex();
        Application.activeGame = HomeTable.getItems().get(selection);
        Application.activeTeam = Model.fetchByTeamByName(HomeTable.getItems().get(selection).getAway());
        root = FXMLLoader.load(getClass().getResource("NotePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

public void loadNoteTable(){

    NoteTeamTable.getItems().clear();

    NoteTeamColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("Name"));


    NoteTeamTable.getItems().addAll(Model.fetchPlayersByTeam(Application.activeTeam.getID()));
}
public void LoadPlayers(){

    for (Player item : Model.fetchPlayersByPosition("Keeper")) {
        PitchBoxGK.getItems().add(item.getName());
    }
    for (Player item : Model.fetchPlayersByPosition("Defender")) {
        PitchBoxLB.getItems().add(item.getName());
    }
    for (Player item : Model.fetchPlayersByPosition("Defender")) {
        PitchBoxLWB.getItems().add(item.getName());
    }
    for (Player item : Model.fetchPlayersByPosition("Defender")) {
        PitchBoxRB.getItems().add(item.getName());
    }
    for (Player item : Model.fetchPlayersByPosition("Defender")) {
        PitchBoxRWB.getItems().add(item.getName());
    }
    for (Player item : Model.fetchPlayersByPosition("Defender")) {
        PitchBoxLCB.getItems().add(item.getName());
    }
    for (Player item : Model.fetchPlayersByPosition("Defender")) {
        PitchBoxCB.getItems().add(item.getName());
    }
    for (Player item : Model.fetchPlayersByPosition("Defender")) {
        PitchBoxRCB.getItems().add(item.getName());
    }
    for (Player item : Model.fetchPlayersByPosition("Midfielder")) {
        PitchBoxDM.getItems().add(item.getName());
    }
    for (Player item : Model.fetchPlayersByPosition("Midfielder")) {
        PitchBoxRDM.getItems().add(item.getName());
    }
    for (Player item : Model.fetchPlayersByPosition("Midfielder")) {

        PitchBoxLDM.getItems().add(item.getName());
    }
    for (Player item : Model.fetchPlayersByPosition("Midfielder")) {
        PitchBoxLCM.getItems().add(item.getName());
    }
    for (Player item : Model.fetchPlayersByPosition("Midfielder")) {
        PitchBoxRCM.getItems().add(item.getName());
    }
    for (Player item : Model.fetchPlayersByPosition("Midfielder")) {
        PitchBoxCAM.getItems().add(item.getName());
    }
    for (Player item : Model.fetchPlayersByPosition("Attacker")) {
        PitchBoxRW.getItems().add(item.getName());
    }
    for (Player item : Model.fetchPlayersByPosition("Attacker")) {
        PitchBoxLW.getItems().add(item.getName());
    }
    for (Player item : Model.fetchPlayersByPosition("Attacker")) {
        PitchBoxRST.getItems().add(item.getName());
    }
    for (Player item : Model.fetchPlayersByPosition("Attacker")) {
        PitchBoxST.getItems().add(item.getName());
    }
    for (Player item : Model.fetchPlayersByPosition("Attacker")) {
        PitchBoxLST.getItems().add(item.getName());
    }
    FormationPicker.getItems().addAll("4-4-2", "4-3-3", "5-2-3", "4-2-4", "4-2-3-1");


}



}

