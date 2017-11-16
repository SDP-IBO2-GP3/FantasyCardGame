package edu.insightr.fantasycardgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.apache.log4j.Logger;

public class Main extends Application{
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    @Override
    public void start(Stage primaryStage) {
        try {
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

        LOGGER.error("Hello world it doesn't work for now .. ");
    }
}
