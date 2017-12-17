package edu.insightr.fantasycardgame;


import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;


public class BoardViewController implements Initializable{

    //region attributes
    private Game game;
    private Player human;
    private Player aiPlayer;
    private static final int LENGTHXALL = 560;
    private static final int LENGTHHEIGHT = 123;
    private static final int LENGTHWIDTH = 76;
    private boolean firstDraw = true;
    private boolean firstAnimation = true;

    //endregion

    //region FXML

    @FXML
    private ImageView displayCardBigger;

    @FXML
    private ImageView deck;

    @FXML
    private ImageView carte1;

    @FXML
    private AnchorPane PlayerHand;

    @FXML
    private AnchorPane OpponentHand;

    @FXML
    private ImageView KorriganPlace;

    @FXML
    private Text NbKorrigan;


    @FXML
    private ImageView GobelinPlace;

    @FXML
    private Text NbGoblin;

    @FXML
    private ImageView ElfPlace;

    @FXML
    private Text NbElf;

    @FXML
    private  ImageView GnomePlace;

    @FXML
    private Text NbGnome;

    @FXML
    private  ImageView DryadPlace;

    @FXML
    private Text NbDryad;

    @FXML
    private ImageView TrollPlace;

    @FXML
    private Text NbTroll;

    @FXML
    private Text ScorePlayer;

    @FXML
    private ImageView KorriganOpponent;

    @FXML
    private  Text NbKorriganOpponent;

    @FXML
    private ImageView ElfOpponent;

    @FXML
    private Text NbElfOpponent;

    @FXML
    private ImageView TrollOpponent;

    @FXML
    private Text NbTrollOpponent;

    @FXML
    private ImageView GobelinOpponent;

    @FXML
    private Text NbGobelinOpponent;

    @FXML
    private ImageView DryadOpponent;

    @FXML
    private Text NbDryadOpponent;

    @FXML
    private ImageView GnomeOpponent;

    @FXML
    private Text NbGnomeOpponent;

    @FXML
    private  Text ScoreOpponnent;

    //endregion


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.game = new Game();
        this.human = game.getPlayer1();
        this.aiPlayer = game.getPlayer2();
        game.initiliazeGame();

        displayPlayerCards();
        displayIACards();

        ScorePlayer.setText(Integer.toString(human.getScore()));
        ScoreOpponnent.setText(Integer.toString(aiPlayer.getScore()));


    }

    private void displayPlayerCards() {
        int numberCard = game.getPlayer1().getListCardsInHand().size();
        int spaceCard = LENGTHXALL / (numberCard+1);

        PlayerHand.getChildren().clear();
        double[] polynome = interpolationCoord(LENGTHXALL, numberCard * 2);
        for (int i = 0; i < numberCard; i++) {

            // ImageView can only apply Image
            Image imageCurrent = createImage(game.getPlayer1().getListCardsInHand().get(i));
            ImageView imageViewCurrent = createImageView(imageCurrent, LENGTHWIDTH, LENGTHHEIGHT);
            ImageView animation = createImageView(imageCurrent,LENGTHWIDTH,LENGTHHEIGHT);
            imageViewCurrent.setId("" + i);
            imageViewCurrent.setOnMouseEntered(handleDisplayCardBigger);
            imageViewCurrent.setOnMouseExited(handleEmptyCardBigger);
            imageViewCurrent.setOnMouseClicked(handleAddCardKing);
            imageViewCurrent.setX(spaceCard * i); // Position on the X axis
            imageViewCurrent.setY(applyPolynome(polynome, spaceCard * i + LENGTHWIDTH / 4)); // Position on the Y axis
            animation.setX(22); // Position on the X axis
            animation.setY(224);

            // The first half is oriented toward left direction, secont toward right
            double angle = -calculAngle(polynome, spaceCard * i);
            if (i > numberCard / 2 && angle < 0) {
                angle *= -1;
            }

            imageViewCurrent.setRotate(angle);
            if(firstAnimation || i == numberCard - 1)
            {

                AnchorPane dad = (AnchorPane)PlayerHand.getParent();
                imageViewCurrent.setRotate(angle);
                animation.setRotate(angle);

                TranslateTransition anim = new TranslateTransition();
                anim.setDuration(Duration.millis(i*150 + 500));

                anim.setToX(284 + spaceCard*i);
                anim.setToY(339 + applyPolynome(polynome,spaceCard*i + LENGTHWIDTH/4));

                anim.setOnFinished(e -> SwitchFromAnim(dad,animation,imageViewCurrent));
                anim.setNode(animation);
                anim.play();

                dad.getChildren().add(animation);

                imageViewCurrent.setRotate(angle);
            }
            else
            {
                PlayerHand.getChildren().add(imageViewCurrent);
            }


        }
        firstAnimation = false;
    }

    public void SwitchFromAnim(AnchorPane dad,ImageView tmp,ImageView imageViewCurrent) {
        dad.getChildren().remove(tmp);
        PlayerHand.getChildren().add(imageViewCurrent);
    }
    /**
     * When the player over past a card, the card is displayed in big on the center of the game
     */
    private EventHandler<? super MouseEvent> handleDisplayCardBigger = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            ImageView imageViewCurrent = (ImageView) event.getSource();
            displayCardBigger.setImage(imageViewCurrent.getImage());
        }
    };

    /**
     * When the player leaves the card currently over past the displayed card it's removed
     */
    private EventHandler<? super MouseEvent> handleEmptyCardBigger = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            displayCardBigger.setImage(null);
        }
    };

    /**
     * When the player chooses a card in her desk, this card is deleted and added on the kingdom
     */
    private EventHandler<? super MouseEvent> handleAddCardKing = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            // get information about the current card
            int id = Integer.parseInt(((ImageView)event.getSource()).getId()); // get the card id
            Card cardRemove = game.playCard(human,aiPlayer,id);

            displayPlayerCards();
            displayPlayerKingdom();

            // refresh score player
            ScorePlayer.setText(Integer.toString(human.getScore()));

            game.playAITurn();
            ScoreOpponnent.setText(Integer.toString(aiPlayer.getScore()));
            displayIACards();
            displayIAKingdom();
        }
    };


    private void displayPlayerKingdom(){
        for (Card.Race race : Card.Race.values()) {
            int freq = Collections.frequency(human.getListCardsKingdom(), new Card(race));
            Node[] informationCard = placeRacePlayerKingdom(race);
            Image imageDisplay = new Image(getClass().getResourceAsStream(cardToRessource(new Card(race))));
            if(freq == 0){
                imageDisplay = new Image(getClass().getResourceAsStream("/img/empty.png"));
            }
            ((ImageView)informationCard[0]).setImage(imageDisplay);
            ((Text)informationCard[1]).setText(freq+"");
        }
    }

    private void displayIAKingdom(){
        for (Card.Race race : Card.Race.values()) {
            int freq = Collections.frequency(aiPlayer.getListCardsKingdom(), new Card(race));
            Node[] informationCard = placeRaceAIKingdom(race);
            Image imageDisplay = new Image(getClass().getResourceAsStream(cardToRessource(new Card(race))));
            if(freq == 0){
                imageDisplay = new Image(getClass().getResourceAsStream("/img/empty.png"));
            }
            ((ImageView)informationCard[0]).setImage(imageDisplay);
            ((Text)informationCard[1]).setText(freq+"");
        }
    }

    private void displayIACards(){
        Image imageIA = new Image(getClass().getResourceAsStream("/img/face_retournee.png"));
        int sizeCardsList = aiPlayer.getSizeOfList();
        OpponentHand.getChildren().clear();
        for(int i=0;i<sizeCardsList;i++){
            ImageView imageViewIA = createImageView(imageIA,72,100);
            imageViewIA.setX(450/sizeCardsList * i);
            OpponentHand.getChildren().add(imageViewIA);
        }
    }


   /* private void playCardFromDeck(Player player,Card card){
        // get information for displaying card on the kingdom
        Node[] InformCard = placeRacePlayerKingdom(card.race);
        ImageView imageViewPlace = (ImageView) InformCard[0];
        Text textNb = (Text) InformCard[1];
        imageViewPlace.setImage(((ImageView)event.getSource()).getImage());

        // refresh the current number of kind race put
        int nbCard = Integer.parseInt(textNb.getText())+1;
        textNb.setText(nbCard+"");

        // add the card removed on the kingdom list
        human.addACardKingdom(card);

        // refresh score player
        ScorePlayer.setText(Integer.toString(player.getScore()));
    }*/


    private Node[] placeRacePlayerKingdom(Card.Race race){
        ImageView imageView  = null;
        Text text = null;
        switch (race){
            case Korrigan:
                imageView = KorriganPlace;
                text = NbKorrigan;
                break;
            case Dryad:
                imageView = DryadPlace;
                text = NbDryad;
                break;
            case Elf:
                imageView = ElfPlace;
                text = NbElf;
                break;
            case Goblin:
                imageView = GobelinPlace;
                text = NbGoblin;
                break;
            case Gnome:
                imageView = GnomePlace;
                text = NbGnome;
                break;
            case Troll:
                imageView = TrollPlace;
                text = NbTroll;
                break;
        }

        return new Node[]{imageView,text};
    }

    private Node[] placeRaceAIKingdom(Card.Race race)
    {
        ImageView imageView  = null;
        Text text = null;
        switch(race)
        {
            case Korrigan:
                imageView = KorriganOpponent;
                text = NbKorriganOpponent;
                break;
            case Dryad:
                imageView = DryadOpponent;
                text = NbDryadOpponent;
                break;
            case Elf:
                imageView = ElfOpponent;
                text = NbElfOpponent;
                break;
            case Goblin:
                imageView = GobelinOpponent;
                text = NbGobelinOpponent;
                break;
            case Gnome:
                imageView = GnomeOpponent;
                text = NbGnomeOpponent;
                break;
            case Troll:
                imageView = TrollOpponent;
                text = NbTrollOpponent;
                break;
        }
        return new Node[]{imageView,text};
    }

    public void getCardFromDeck() {
        //if the deck still has a card
        if (game.getDeck().getSize() > 0) {
            if (game.playHumanTurn()) {
                displayPlayerCards();
            }

        }
        //if the deck is empty we hide the imageview of the deck
        else {
            deck.setVisible(false);
        }
    }


    /**
     * In function of L and Yn this function create a polynomial
     *
     * @param L
     * @param yn
     * @return
     */
    private double[] interpolationCoord(int L, int yn) {
        double[] result = new double[2];
        double a = yn * 1.0 / (-(L * L) / 4);
        double b = -a * L;
        result[0] = a;
        result[1] = b;
        return result;
    }

    /**
     * Applicate a polynomial for a x given and return the y value corresponding
     *
     * @param poly
     * @param x
     * @return
     */
    private double applyPolynome(double[] poly, double x) {
        return -((x * x) * poly[0] + x * poly[1]);
    }

    /**
     * Allow to calcul angle of each card based on arctan technical
     *
     * @param poly
     * @param x
     * @return
     */
    private double calculAngle(double[] poly, double x) {

        double a = x;
        x += LENGTHWIDTH / 4;

        double yprime = (2 * poly[0] * a + poly[1]) * (x - a) + applyPolynome(poly, a);
        double y = applyPolynome(poly, x);

        double opposite = yprime - y;
        double adjatent = x - a;

        double delta = opposite / adjatent;
        return Math.toDegrees(Math.atan(delta));
    }

    private String cardToRessource(Card card) {
        String result = "";
        switch (card.race) {
            case Korrigan:
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


    private Image createImage(Card card) {
        String ressource = cardToRessource(card);
        return new Image(getClass().getResourceAsStream(ressource));
    }

    public ImageView createImageView(Image image, int width, int height) {
//   <ImageView fitHeight="123.0" fitWidth="79.0" layoutX="59.0" pickOnBounds="true" preserveRatio="true">
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        return imageView;
        // PlayerHand.getChildren().add(imageView);
    }


}
