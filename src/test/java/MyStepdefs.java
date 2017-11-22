import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.insightr.fantasycardgame.BoardController;
import edu.insightr.fantasycardgame.Card;
import edu.insightr.fantasycardgame.Deck;
import edu.insightr.fantasycardgame.Player;

public class MyStepdefs {

    private BoardController board;
    private Deck deck;
    private Player player1, player2;

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