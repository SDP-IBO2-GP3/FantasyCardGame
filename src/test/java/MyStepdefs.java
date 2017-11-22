import com.sun.xml.internal.bind.v2.TODO;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.insightr.fantasycardgame.BoardController;
import edu.insightr.fantasycardgame.Card;
import edu.insightr.fantasycardgame.Deck;
import edu.insightr.fantasycardgame.Player;
import junit.framework.Assert;

public class MyStepdefs {

    private BoardController board;
    private Deck deck;

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

    @And("^Kingdoms are empty$")
    public void kingdomsAreEmpty() throws Throwable {
        org.junit.Assert.assertEquals(0, board.getPlayer1().getSizeOfKingdom());
        org.junit.Assert.assertEquals(0, board.getPlayer2().getSizeOfKingdom());
    }

    /**
     * Scenario: Shuffle the deck
     */

    @Given("^Deck is initialized$")
    public void deckIsInitialized() throws Throwable {
        deck = new Deck();
    }

    @When("^Mix the deck$")
    public void mixTheDeck() throws Throwable {
        deck.fillDeck();
    }

    @Then("^the deck is mixed$")
    public void theDeckIsMixed() throws Throwable {
        int numberCard = deck.getSize();
        int sumSame = 0;

        Card card = deck.getACard();

        for(int i=1; i<numberCard; i++){
            if(deck.getACard().toString().equals(card.toString())){
                sumSame++;
            }
        }
        org.junit.Assert.assertTrue(sumSame < numberCard);
    }

    /**
     * Scenario: Empty the deck
     */

    @Given("^Deck is full$")
    public void deckIsFull() throws Throwable {
        deck = new Deck();
        //deck.getACard();
    }

    @When("^Player plays all cards$")
    public void playerPlaysAllCards() throws Throwable {
        int numberCard = deck.getSize();
        for(int i=0;i<numberCard;i++){
            deck.getACard();
        }
    }

    @Then("^the deck is empty$")
    public void theDeckIsEmpty() throws Throwable {
        org.junit.Assert.assertEquals(0,deck.getSize());
    }
}