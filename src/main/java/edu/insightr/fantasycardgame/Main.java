package edu.insightr.fantasycardgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    //private static final Logger LOGGER = Logger.getLogger(Main.class);


    @Override
    public void start(Stage primaryStage) {


        try {
            Parent setupDisplay = FXMLLoader.load(getClass().getResource("/fxml/setupDisplay.fxml"));
            Scene start = new Scene(setupDisplay, 389, 239.0);

            primaryStage.setScene(start);
            primaryStage.setTitle("FantasyCard");
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        launch(args);
    }

}
