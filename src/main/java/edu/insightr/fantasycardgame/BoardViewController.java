package edu.insightr.fantasycardgame;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;


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

    private boolean firstDraw = true;


    public void initialize(URL arg0, ResourceBundle arg1) {
        game = new BoardController();
        game.initiliazeGame();
        human = new Player(true);
        aiPlayer = new Player(false);

       //for(int i=0;i<25;i++){
       //     game.getPlayer1().getListCardsInHand().add(new Card(Card.Race.Troll));
       // }

    }

    private void displayCards(){
        int numberCard = game.getPlayer1().getListCardsInHand().size();
        int spaceCard = LENGTHXALL/numberCard;
        PlayerHand.getChildren().clear();
        double[] polynome = interpolationCoord(LENGTHXALL,numberCard*2);
        for(int i=0;i<numberCard;i++){

            // ImageView can only apply Image
            Image imageCurrent = createImage(game.getPlayer1().getListCardsInHand().get(i));
            ImageView imageViewCurrent = createImageView(imageCurrent);
            imageViewCurrent.setX(spaceCard*i); // Position on the X axis
            imageViewCurrent.setY(applyPolynome(polynome,spaceCard*i + LENGTHWIDTH/4)); // Position on the Y axis

            // The first half is oriented toward left direction, secont toward right
            double angle = -calculAngle(polynome,spaceCard*i);
            if(i > numberCard / 2 && angle < 0) {
                angle *= -1;
            }

            imageViewCurrent.setRotate(angle);
            PlayerHand.getChildren().add(imageViewCurrent);
        }
    }

    public void getCardFromDeck() {
        if (game.playHumanTurn(human, aiPlayer)) {
            if(!firstDraw){
                Card card = game.getDeck().getACard();
                game.getPlayer1().getListCardsInHand().add(card);
            }else{
                firstDraw = false;
            }
            displayCards();

        }
    }


    /**
     * In function of L and Yn this function create a polynomial
     * @param L
     * @param yn
     * @return
     */
    private double[] interpolationCoord(int L,int yn){
        double[] result = new double[2];
        double a = yn*1.0 / (-(L*L)/4);
        double b = -a*L;
        result[0] = a;
        result[1] = b;
        return result;
    }

    /**
     * Applicate a polynomial for a x given and return the y value corresponding
     * @param poly
     * @param x
     * @return
     */
    private double applyPolynome(double[] poly,double x){
        return -((x*x)*poly[0]+x*poly[1]);
    }

    /**
     * Allow to calcul angle of each card based on arctan technical
     * @param poly
     * @param x
     * @return
     */
    private double calculAngle(double[] poly,double x){

        double a = x;
        x += LENGTHWIDTH/4;

        double yprime = (2*poly[0]*a+poly[1])*(x-a)+applyPolynome(poly,a);
        double y = applyPolynome(poly,x);

        double opposite = yprime-y;
        double adjatent = x-a;

        double delta = opposite/adjatent;
        return Math.toDegrees(Math.atan(delta));
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
