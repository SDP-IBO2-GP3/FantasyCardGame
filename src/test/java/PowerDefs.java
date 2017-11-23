import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.insightr.fantasycardgame.BoardController;
import edu.insightr.fantasycardgame.Card;
import edu.insightr.fantasycardgame.Player;

public class PowerDefs {

    private Player player1, player2;
    private BoardController board;

    /**
     * Scenario: Troll Card Power
     */
    @Given("^The Player1 and Player2 cards$")
    public void thePlayerAndPlayerCards() throws Throwable {
        player2 = new Player(true);
        player1 = new Player(false);

        Card korrigan = new Card(Card.Race.Korrigan);
        Card dryad = new Card(Card.Race.Dryad);
        Card gnome = new Card(Card.Race.Gnome);


        player1.addACardKingdom(dryad);
        player2.addACardKingdom(korrigan);
        player2.addACardKingdom(gnome);
    }

    @When("^Player1 is playing the Troll$")
    public void playerIsPlayingTheTroll() throws Throwable {
        Card troll = new Card(Card.Race.Troll);
        //player1.addACardKingdom(player1.getListCardsInHand().get(0));
        player1.addACard(troll);
        board = new BoardController();
        board.initiliazeGame();
        board.applyEffect(player1, player2, troll);
    }

    @Then("^The players swap kingdoms$")
    public void thePlayersSwapKingdoms() throws Throwable {
        org.junit.Assert.assertEquals(2, player1.getListCardsKingdom().size());
        org.junit.Assert.assertEquals(1, player2.getListCardsKingdom().size());
        org.junit.Assert.assertEquals(Card.Race.Korrigan, player1.getListCardsKingdom().get(0).getRace());
    }

    @And("^The players swap scores$")
    public void thePlayersSwapScores() throws Throwable {
        org.junit.Assert.assertEquals(2, player1.getScore());
        org.junit.Assert.assertEquals(1, player2.getScore());
    }
}
