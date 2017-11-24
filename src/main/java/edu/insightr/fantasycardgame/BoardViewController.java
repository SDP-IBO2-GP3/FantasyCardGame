package edu.insightr.fantasycardgame;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;


public class BoardViewController implements Initializable{

    private BoardController game;
    private Player human;
    private Player aiPlayer;

    @FXML
    private ImageView deck;

    public void initialize(URL arg0, ResourceBundle arg1) {
        game = new BoardController();
        game.initiliazeGame();
        human = new Player(true);
        aiPlayer = new Player(false);
    }

    public void getCardFromDeck() {
        if (game.playHumanTurn(human, aiPlayer)) {
            // Actualisation de l'image view des la main du joueur.

        }
    }
}
