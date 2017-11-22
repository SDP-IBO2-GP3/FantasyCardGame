import com.sun.xml.internal.bind.v2.TODO;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import edu.insightr.fantasycardgame.BoardController;
import edu.insightr.fantasycardgame.Deck;
import edu.insightr.fantasycardgame.Player;
import junit.framework.Assert;

public class MyStepdefs {

    private BoardController board;
    /**
     * Scenario: Initialize Playground
     */

    @Given("^Playground is initialized$")
    public void PlaygroundIsInitialized() throws Throwable{
        board = new BoardController();
        board.getDeck().fillDeck();
        // Write code allowing to load the playground
    }

    @Then("^Player1 has 5 cards in his hand$")
    public void playerHasCardsInHisHand() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        org.junit.Assert.assertEquals(60, board.getDeck().getSize());
    }
}
