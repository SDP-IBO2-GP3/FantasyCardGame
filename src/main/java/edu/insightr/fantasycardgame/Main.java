package edu.insightr.fantasycardgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

public class Main extends Application{
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    @Override
    public void start(Stage primaryStage) {
        try {

            /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/boardView.fxml"));
            loader.setController(new BoardViewController());
            loader.setClassLoader(getClass().getClassLoader());
            Parent root = loader.load();*/

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/boardView.fxml"));
            Scene scene = new Scene(root,600,400);


            primaryStage.setScene(scene);
            primaryStage.setTitle("FantasyCard");

            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        launch(args);
        System.out.println("Hello Wolrd ! This is our amazing card game  ");
        System.out.println((new Card(Card.Race.Goblin).toString()));
        Deck deck = new Deck();
        deck.fillDeck();
        System.out.println(deck);
        deck.shuffleDeck();
        System.out.println(deck);

        BoardController b = new BoardController();
        b.initiliazeGame();
        System.out.println(b.getPlayer1());
        System.out.println(b.getPlayer2());
        LOGGER.error("Hello world it doesn't work for now .. ");
    }
}
