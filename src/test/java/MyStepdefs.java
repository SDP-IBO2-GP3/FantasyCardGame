import com.sun.xml.internal.bind.v2.TODO;
import cucumber.api.PendingException;
import cucumber.api.java.cs.A;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import edu.insightr.fantasycardgame.BoardController;
import edu.insightr.fantasycardgame.Deck;
import edu.insightr.fantasycardgame.Player;
import junit.framework.Assert;
import org.junit.runner.RunWith;

import java.util.regex.Matcher;

public class MyStepdefs {

    /**
     * Scenario: Initialize Playground
     */

    private BoardController board;

    @Given("^Playground is initialized$")
    public void playgroundIsInitialized() throws Throwable {
        // Write code here that turns the phrase above into concrete action
        board = new BoardController();
        board.getDeck().fillDeck();
        //throw new PendingException();
    }


    @Then("^Player1 has 5 cards in his hand$")
    public void playerHasCardsInHisHand() throws Throwable {
        // org.junit.Assert.assertEquals("dd",arg0);

        org.junit.Assert.assertEquals(60,board.getDeck().getSize());
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
    }

}