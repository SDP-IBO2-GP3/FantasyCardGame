import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.insightr.fantasycardgame.BoardController;
import edu.insightr.fantasycardgame.Card;
import edu.insightr.fantasycardgame.Player;

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


        player2 = new Player(true);
        player1 = new Player(false);

        board = new BoardController();
        board.initiliazeGame();

        createCardKingdom(player1,10);
        createCardKingdom(player2,5);



    }

    private Player player1, player2;
    private BoardController board;

    private int sizePlayer1BeforeEffect;
    private int sizePlayer2BeforeEffect;
    private int scorePlayer1BeforeEffect;
    private int scorePlayer2BeforeEffect;

    /**
     * Scenario: Troll Card Power
     */
    @Given("^The Player1 and Player2 cards$")
    public void thePlayerAndPlayerCards() throws Throwable {

    }

    @When("^Player1 is playing the Troll$")
    public void playerIsPlayingTheTroll() throws Throwable {
        Card troll = new Card(Card.Race.Troll);
        //player1.addACardKingdom(player1.getListCardsInHand().get(0));
        player1.addACard(troll);
        board = new BoardController();
        board.initiliazeGame();

        sizePlayer1BeforeEffect = player1.getListCardsKingdom().size();
        sizePlayer2BeforeEffect = player2.getListCardsKingdom().size();

        scorePlayer1BeforeEffect = player1.getScore();
        scorePlayer2BeforeEffect = player2.getScore();

        board.applyEffect(player1, player2, troll);
    }

    @Then("^The players swap kingdoms$")
    public void thePlayersSwapKingdoms() throws Throwable {
        org.junit.Assert.assertEquals(sizePlayer2BeforeEffect, player1.getListCardsKingdom().size());
        org.junit.Assert.assertEquals(sizePlayer1BeforeEffect, player2.getListCardsKingdom().size());
        org.junit.Assert.assertEquals(Card.Race.Korrigan, player1.getListCardsKingdom().get(0).getRace());
    }

    @And("^The players swap scores$")
    public void thePlayersSwapScores() throws Throwable {


        org.junit.Assert.assertEquals(scorePlayer2BeforeEffect, player1.getScore());
        org.junit.Assert.assertEquals(scorePlayer1BeforeEffect, player2.getScore());
    }
}
