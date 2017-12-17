package edu.insightr.fantasycardgame;


import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sun.plugin.javascript.navig4.Anchor;

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
    private  boolean onylDisplay = false;
    //endregion

    //region FXML

    @FXML
    private ImageView displayCardBigger;

    @FXML
    private ImageView deck;

    @FXML
    private AnchorPane PlayerHand;

    @FXML
    private AnchorPane KingdomAI;

    @FXML
    private AnchorPane KingdomPlayer;

    @FXML
    private AnchorPane OpponentHand;

    @FXML
    private Text ScorePlayer;

    @FXML
    private  Text ScoreOpponnent;

    @FXML
    private Label Instruction;

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

        attributeEventKindomIA();

        changeStateGame(0);

    }

    private void attributeEventKindomIA(){
        for(int i=0;i<6;i++){
            KingdomAI.getChildren().get(i).setOnMouseClicked(handleChooseCardKingdomAI);
        }

    }


    private void changeStateGame(int state){
        if(state != -1) {
            game.setCurrentState(state);
        }
        switch (game.getCurrentState()){

            case Game.IA_PLAY:
                game.playAITurn();

                displayIACards();
                onylDisplay = true;
                displayPlayerCards();
                displayKingdom(human);
                displayKingdom(aiPlayer);

                effectSelected(false,OpponentHand);
                effectSelected(false,KingdomAI);
                effectSelected(false,PlayerHand);

            case Game.DRAW_CARD_FROM_DECK:
                effectSelected(true,deck);
                Instruction.setText("Pick a card from the deck");
                break;

            case Game.CHOOSE_CARD_HAND:
                effectSelected(false,deck);
                effectSelected(true,PlayerHand);
                Instruction.setText("Choose a card from  \n your hand");
                break;

            case Game.TAKE_CARD_ADVERSE_HAND:
                effectSelected(false,PlayerHand);
                effectSelected(true,OpponentHand);
                Instruction.setText("Take two cards  \n in the opponent's hand");
                break;

            case Game.APPLY_POWER_ADVERSE_KINGDOM:
                effectSelected(false,PlayerHand);
                effectSelected(true,KingdomAI);
                break;

            case Game.TAKE_CARD_ADVERSE_KINGDOM:
                effectSelected(false,PlayerHand);
                effectSelected(true,KingdomAI);
                Instruction.setText("Take a card \n in the opponent's kingdom");
                break;
        }

        ScorePlayer.setText(Integer.toString(human.getScore()));
        ScoreOpponnent.setText(Integer.toString(aiPlayer.getScore()));

    }


    private void effectSelected(boolean select,Node node){
        if(select){
            DropShadow dropShadow = new DropShadow();
            dropShadow.setRadius(5.0);
            dropShadow.setOffsetX(10.0);
            dropShadow.setOffsetY(10.0);
            dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
            node.setEffect(dropShadow);
        }else{
            node.setEffect(null);
        }
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
            if(firstAnimation || (i == numberCard - 1 && onylDisplay == false) )
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
        onylDisplay = false;
    }

    public void SwitchFromAnim(AnchorPane dad,ImageView tmp,ImageView imageViewCurrent) {
        dad.getChildren().remove(tmp);
        PlayerHand.getChildren().add(imageViewCurrent);
    }
    public void SwitchFromAnim(AnchorPane dad,ImageView tmp,int id) {
        dad.getChildren().remove(tmp);
        onylDisplay = true;
        displayPlayerCards();
        displayKingdom(human);
        ScorePlayer.setText(Integer.toString(human.getScore()));
        ScoreOpponnent.setText(Integer.toString(aiPlayer.getScore()));

        changeStateGame(-1);
    }
    /**
     * When the player over past a card, the card is displayed in big on the center of the game
     */
    private EventHandler<? super MouseEvent> handleDisplayCardBigger = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if(game.getCurrentState() == Game.CHOOSE_CARD_HAND){
                ImageView imageViewCurrent = (ImageView) event.getSource();
                displayCardBigger.setVisible(true);
                displayCardBigger.setImage(imageViewCurrent.getImage());
            }
        }
    };

    /**
     * When the player leaves the card currently over past the displayed card it's removed
     */
    private EventHandler<? super MouseEvent> handleEmptyCardBigger = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            displayCardBigger.setVisible(false);
            displayCardBigger.setImage(null);
        }
    };

    /**
     * When the player chooses a card in her desk, this card is deleted and added on the kingdom
     */
    private EventHandler<? super MouseEvent> handleAddCardKing = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if(game.getCurrentState() == Game.CHOOSE_CARD_HAND){
                // get information about the current card
                int id = Integer.parseInt(((ImageView)event.getSource()).getId()); // get the card id
                String cardName = human.getListCardsInHand().get(id).getRace().name();

                ImageView animation = createImageView(((ImageView)event.getSource()).getImage(),110,176);
                AnchorPane dad = (AnchorPane)PlayerHand.getParent();

                game.playCard(human,aiPlayer,id);



                TranslateTransition anim = new TranslateTransition();
                anim.setDuration(Duration.millis(750));

                animation.setX(500);
                animation.setY(663);

                System.out.print(id);
                System.out.print(cardName);

                if(cardName == "Korrigan")
                {
                    anim.setToX(224 - 500 );
                    anim.setToY(332 - 663);
                }
                else if(cardName == "Gnome")
                {
                    anim.setToX(224 - 500 + 480);
                    anim.setToY(332 - 663);
                }
                else if(cardName == "Goblin")
                {
                    anim.setToX(224 - 500 + 360);
                    anim.setToY(332 - 663);
                }
                else if(cardName == "Elf")
                {
                    anim.setToX(224 - 500 + 240);
                    anim.setToY(332 - 663);
                }
                else if(cardName == "Dryad")
                {
                    anim.setToX(224 - 500 + 120);
                    anim.setToY(332 - 663);
                }
                else if(cardName == "Troll")
                {
                    anim.setToX(224 - 500 + 600);
                    anim.setToY(332 - 663);
                }

                anim.setNode(animation);
                anim.setOnFinished(e -> SwitchFromAnim(dad,animation,id));


                dad.getChildren().add(animation);
                anim.play();
                // refresh score player


            }
        }
    };

    private EventHandler<? super MouseEvent> handleChooseCardHandAI = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            // get information about the current card
            if(game.getCurrentState() == Game.TAKE_CARD_ADVERSE_HAND) {
                int id = Integer.parseInt(((ImageView) event.getSource()).getId()); // get the card id
                game.TakeCardOnAdverseHand(human, aiPlayer, id);
                displayIACards();
                onylDisplay = true;
                displayPlayerCards();

                int numberCard = human.getNumberCardSelectedKingdomAdverve();
                numberCard++;
                if(numberCard == 2){
                    numberCard = 0;
                    changeStateGame(Game.IA_PLAY);
                }
                human.setNumberCardSelectedKingdomAdverve(numberCard);
            }
        }
    };

    private EventHandler<? super MouseEvent> handleChooseCardKingdomAI = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if(game.getCurrentState() == Game.TAKE_CARD_ADVERSE_KINGDOM  || game.getCurrentState() == Game.APPLY_POWER_ADVERSE_KINGDOM  ) {
                int index = Integer.parseInt(((ImageView) event.getSource()).getId());
                if(game.getCurrentState() == Game.TAKE_CARD_ADVERSE_KINGDOM){
                    game.TakeCardOnAdverseKingdom(human, aiPlayer, positionToRaceKingdom(index));
                }else{
                    game.applyEffect(human,aiPlayer,new Card(positionToRaceKingdom(index)));
                }

                displayKingdom(human);
                displayKingdom(aiPlayer);

                changeStateGame(Game.IA_PLAY);
            }
        }
    };


    private void displayKingdom(Player player){

        AnchorPane kingdom = KingdomPlayer;
        if(player == aiPlayer){
            kingdom = KingdomAI;
        }

        for (Card.Race race : Card.Race.values()) {
            int freq = Collections.frequency(player.getListCardsKingdom(), new Card(race));
            Node[] informationCard = placeRaceKingdom(kingdom,race);
            Image imageDisplay = new Image(getClass().getResourceAsStream(cardToRessource(new Card(race))));
            if(freq == 0){
                imageDisplay = new Image(getClass().getResourceAsStream("/img/empty.png"));
            }
            ((ImageView)informationCard[0]).setImage(imageDisplay);
            ((Text)informationCard[1]).setText(freq+"");
        }
    }


    private void displayIACards() {
        Image imageIA = new Image(getClass().getResourceAsStream("/img/face_retournee.png"));
        int sizeCardsList = aiPlayer.getSizeOfList();
        OpponentHand.getChildren().clear();
        for (int i = 0; i < sizeCardsList; i++) {
            ImageView imageViewIA = createImageView(imageIA, 72, 100);
            imageViewIA.setOnMouseClicked(handleChooseCardHandAI);
            imageViewIA.setX(450 / sizeCardsList * i);
            imageViewIA.setId("" + i);
            OpponentHand.getChildren().add(imageViewIA);
        }
    }


   private Node[] accessNodeForACardInKingDom(AnchorPane kingdom,int index){
        Node imageView = kingdom.getChildren().get(index);
        Node textView = ((AnchorPane)kingdom.getChildren().get(index+6)).getChildren().get(0);
       return new Node[]{imageView,textView};
   }


    private Card.Race positionToRaceKingdom(int position){
        switch (position){
            case 0:
                return Card.Race.Korrigan;
            case 1:
                return Card.Race.Dryad;
            case 2:
                return Card.Race.Elf;
            case 3:
                return Card.Race.Goblin;
            case 4:
                return Card.Race.Gnome;
            case 5:
                return Card.Race.Troll;
        }

        return null;
    }


    private Node[] placeRaceKingdom(AnchorPane kingdom,Card.Race race){
        switch (race){
            case Korrigan:
                return  accessNodeForACardInKingDom(kingdom,0);
            case Dryad:
                return  accessNodeForACardInKingDom(kingdom,1);
            case Elf:
                return  accessNodeForACardInKingDom(kingdom,2);
            case Goblin:
                return  accessNodeForACardInKingDom(kingdom,3);
            case Gnome:
                return  accessNodeForACardInKingDom(kingdom,4);
            case Troll:
                return  accessNodeForACardInKingDom(kingdom,5);
        }

        return null;
    }


    public void getCardFromDeck() {
        if(game.getCurrentState() == Game.DRAW_CARD_FROM_DECK) {
            //if the deck still has a card
            if (game.getDeck().getSize() > 0) {
                if (game.playHumanTurn()) {
                    displayPlayerCards();
                    changeStateGame(Game.CHOOSE_CARD_HAND);
                }
            }
            //if the deck is empty we hide the imageview of the deck
            else {
                deck.setVisible(false);
            }
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
