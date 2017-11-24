import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.insightr.fantasycardgame.BoardController;
import edu.insightr.fantasycardgame.Card;
import edu.insightr.fantasycardgame.Player;
import org.junit.Assert;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class PowerDefs {


    public void createCardKingdom(Player player,int sizeRandom){

        Random rand = new Random();

        // Assure that all kind race is distributed
        for(Card.Race race : Card.Race.values()){
            player.addACardKingdom(new Card(race));
        }

        // Add randomly cards on the deck
        // sizeRandom allows to change the size if each deck
        for(int i=0;i<sizeRandom;i++){
            int value = rand.nextInt(6);
            Card cardAdd = new Card(Card.Race.values()[value]);
            player.addACardKingdom(cardAdd);
        }



    }


    @Before
    public void setUp(){

        board = new BoardController();
        board.initiliazeGame();

        createCardKingdom(board.getPlayer1(),10);
        createCardKingdom(board.getPlayer2(),5);



    }

    private BoardController board;

    private int sizePlayer1BeforeEffect;
    private int sizePlayer2BeforeEffect;
    private int scorePlayer1BeforeEffect;
    private int scorePlayer2BeforeEffect;
    private ArrayList<Card> handPlayer1BeforeEffect;
    private ArrayList<Card> handPlayer2BeforeEffect;

    /**
     * Scenario: Troll Card Power
     */

    @When("^Player1 is playing the Troll$")
    public void playerIsPlayingTheTroll() throws Throwable {
        Card troll = new Card(Card.Race.Troll);
        board.getPlayer1().addACardKingdom(troll);

        sizePlayer1BeforeEffect = board.getPlayer1().getListCardsKingdom().size();
        sizePlayer2BeforeEffect = board.getPlayer2().getListCardsKingdom().size();

        scorePlayer1BeforeEffect = board.getPlayer1().getScore();
        scorePlayer2BeforeEffect = board.getPlayer2().getScore();

        board.applyEffect(board.getPlayer1(), board.getPlayer2(), troll);
    }

    @Then("^The players swap kingdoms$")
    public void thePlayersSwapKingdoms() throws Throwable {
        org.junit.Assert.assertEquals(sizePlayer2BeforeEffect, board.getPlayer1().getListCardsKingdom().size());
        org.junit.Assert.assertEquals(sizePlayer1BeforeEffect, board.getPlayer2().getListCardsKingdom().size());
        org.junit.Assert.assertEquals(Card.Race.Korrigan, board.getPlayer1().getListCardsKingdom().get(0).getRace());
    }

    @And("^The players swap scores$")
    public void thePlayersSwapScores() throws Throwable {
        org.junit.Assert.assertEquals(scorePlayer2BeforeEffect, board.getPlayer1().getScore());
        org.junit.Assert.assertEquals(scorePlayer1BeforeEffect, board.getPlayer2().getScore());
    }

    /**
     * Scenario: Goblin Card Power
     */

    @When("^Player1 is playing a Goblin$")
    public void playerIsPlayingAGoblin() throws Throwable {
        Card goblin = new Card(Card.Race.Goblin);
        board.getPlayer1().addACardKingdom(goblin);

        handPlayer1BeforeEffect = board.getPlayer1().getListCardsInHand();
        handPlayer2BeforeEffect = board.getPlayer2().getListCardsInHand();

        board.applyEffect(board.getPlayer1(), board.getPlayer2(), goblin);
    }

    @Then("^The players swap hands$")
    public void thePlayersSwapHands() throws Throwable {
        Assert.assertEquals(handPlayer1BeforeEffect, board.getPlayer2().getListCardsInHand());
        Assert.assertEquals(handPlayer2BeforeEffect, board.getPlayer1().getListCardsInHand());
    }

    /**
     * Scenario: Korrigan Card Power
     */

    @When("^Player1 is playing a Korrigan$")
    public void playerIsPlayingAKorrigan() throws Throwable {
        Card korrigan = new Card(Card.Race.Korrigan);
        board.getPlayer1().addACardKingdom(korrigan);

        handPlayer1BeforeEffect = board.getPlayer1().getListCardsInHand();
        handPlayer2BeforeEffect = board.getPlayer2().getListCardsInHand();
        System.out.println(handPlayer1BeforeEffect.size());
        System.out.println(handPlayer2BeforeEffect.size());
        board.applyEffect(board.getPlayer1(), board.getPlayer2(), korrigan);
    }

    @Then("^Player2 has two cards less in his hand$")
    public void playerHasTwoCardsLessInHisHand() throws Throwable {
        Assert.assertEquals(handPlayer2BeforeEffect.size()-2, board.getPlayer2().getListCardsInHand().size());
    }

    @And("^Player1 has two more cards in his hand$")
    public void playerHasTwoMoreCardsInHisHand() throws Throwable {
        Assert.assertEquals(handPlayer1BeforeEffect.size()+2, board.getPlayer1().getListCardsInHand().size());
    }
}