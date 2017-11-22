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
        board.initiliazeGame();
    }

    @Then("^Deck is filled$")
    public void deckIsFilled() throws Throwable {
        org.junit.Assert.assertEquals(48, board.getDeck().getSize());
    }

    @And("^Players have 6 cards in their hand$")
    public void playerHasCardsInHisHand() throws Throwable {
        org.junit.Assert.assertEquals(6, board.getPlayer1().getSizeOfList());
        org.junit.Assert.assertEquals(6, board.getPlayer2().getSizeOfList());
    }
}
