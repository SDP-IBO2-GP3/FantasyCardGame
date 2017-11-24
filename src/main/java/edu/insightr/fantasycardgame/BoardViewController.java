package edu.insightr.fantasycardgame;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import static edu.insightr.fantasycardgame.Card.Race.Korrigan;


public class BoardViewController implements Initializable{

    private BoardController game;
    private Player human;
    private Player aiPlayer;

    @FXML
    private ImageView deck;

    @FXML
    private ImageView carte1;

    @FXML private AnchorPane PlayerHand;

    private static final int LENGTHXALL = 560;
    private static final int LENGTHHEIGHT = 123;
    private static final int LENGTHWIDTH = 76;


    public void initialize(URL arg0, ResourceBundle arg1) {
        game = new BoardController();
        game.initiliazeGame();
        human = new Player(true);
        aiPlayer = new Player(false);

        /*for(int i=0;i<10;i++){
            game.getPlayer1().getListCardsInHand().add(new Card(Card.Race.Troll));
        }*/

    }

    public void getCardFromDeck() {
        if (game.playHumanTurn(human, aiPlayer)) {
            int numberCard = game.getPlayer1().getListCardsInHand().size();
            int spaceCard = LENGTHXALL/numberCard;
            for(int i=0;i<numberCard;i++){
                Image imageCurrent = createImage(game.getPlayer1().getListCardsInHand().get(i));
                ImageView imageViewCurrent = createImageView(imageCurrent);
                imageViewCurrent.setX(spaceCard*i);
                PlayerHand.getChildren().add(imageViewCurrent);
            }
            // Actualisation de l'image view des la main du joueur.

        }
    }

    private String cardToRessource(Card card){
        String result = "";
        switch (card.race){
            case Korrigan :
                result = "/img/KORRIGAN.png";
                break;
            case Dryad:
                result = "/img/DRYAD.png";
                break;
            case Elf:
                result = "/img/ELF.png";
                break;
            case Goblin:
                result = "/img/GOBELIN.png";
                break;
            case Gnome:
                result = "/img/GNOME.png";
                break;
            case Troll:
                result = "/img/TROLL.png";
                break;

        }

        return result;
    }


    private Image createImage(Card card){
        String ressource = cardToRessource(card);
        System.out.print("Ressource : "+ressource);
        return new Image(getClass().getResourceAsStream(ressource));
    }

    public ImageView createImageView(Image image){
//   <ImageView fitHeight="123.0" fitWidth="79.0" layoutX="59.0" pickOnBounds="true" preserveRatio="true">
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(LENGTHHEIGHT);
        imageView.setFitWidth(LENGTHWIDTH);
        return imageView;
       // PlayerHand.getChildren().add(imageView);
    }
}
