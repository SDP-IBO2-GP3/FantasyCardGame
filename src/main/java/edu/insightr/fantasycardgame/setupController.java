package edu.insightr.fantasycardgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class setupController implements Initializable {

    @FXML
    private AnchorPane ap;

    @FXML
    private Button startBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startBtn.setOnAction(e -> startGame());
    }

    private void startGame() {

        Stage primaryStage = (Stage) ap.getScene().getWindow();
        try {

            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/boardView.fxml"));
            Scene scene = new Scene(root, 989, 690);




            primaryStage.setScene(scene);
            primaryStage.setTitle("FantasyCard");
            primaryStage.setResizable(false);
            primaryStage.show();
            primaryStage.setX((primaryScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
            primaryStage.setY((primaryScreenBounds.getHeight() - primaryStage.getHeight()) / 2);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
