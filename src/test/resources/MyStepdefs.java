import com.sun.xml.internal.bind.v2.TODO;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class MyStepdefs {

    /**
     * Scenario: Initialize Playground
     */

    @Give("^Playground is initialized$")
    public void PlaygroundIsInitialized(BoardController boardController) throws Throwable{
        // Write code allowing to load the playground
    }

    @Then("^Player1 has (\\d+) cards in his hand$")
    public void playerHasCardsInHisHand(int arg0, int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        throw new PendingException();
    }

    @And("^Player1 has (\\d+) cards in his kingdom$")
    public void playerHasCardsInHisKingdom(int arg0, int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        throw new PendingException();
    }


    /**
     * Scenario: Initialize Deck
     */

    @Give("^Deck is initialized$")
    public void DeckIsInitialized(Deck deck) throws Throwable{
        // Write code allowing to load the playground
    }

    @Then("^Deck has (\\d+) cards$")
    public void deckHasCards(int arg0, int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        throw new PendingException();
    }

    @And("^Different races$")
    public void differentRaces(int arg0, int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        throw new PendingException();
    }


    /**
     * Scenario: Play a Korrigan
     */


    @Give("^Players1 plays a Korrigan$")
    public void PlayersOnePlaysAKorrigan() throws Throwable{
        // Write code allowing to load the playground
    }

    @Then("^Player1 has (\\d+) more cards in his hand$")
    public void PlayerOnehasMoreCardsInHisHand(int arg0, int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        throw new PendingException();
    }

    @And("^Player2 has (\\d+) cards less in his hand$")
    public void PlayerTwoHasCardsLessInHisHand(int arg0, int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        throw new PendingException();
    }



    /**
     * Scenario: Play a Goblin
     */


    @Give("^Given Player1 plays a Goblin$")
    public void GivenPlayerOnePlaysAGoblinn() throws Throwable{
        // Write code allowing to load the playground
    }

    @Then("^Player1 switches his hand with Player2$")
    public void PlayerOneSwitchesHisHandWithPlayerTwo(int arg0, int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        throw new PendingException();
    }




    /**
     * Scenario:Play a Dryad
     */


    @Give("^Player1 plays a Dryad$")
    public void PlayerOnePlaysaDryad() throws Throwable{
        // Write code allowing to load the playground
    }

    @Then("^Player2 has one less card in his kingdom$")
    public void PlayerHasOneLessCardInHisKingdom(int arg0, int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        throw new PendingException();
    }

    @And("^Player1 has one more card in his kingdom$")
    public void PlayerOneHasOneMoreCardInHisKingdom(int arg0, int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        throw new PendingException();
    }




    /**
     * Scenario: Play a Troll
     */


    @Give("^Given Player1 plays a Troll$")
    public void PlayerOnePlaysATroll() throws Throwable{
        // Write code allowing to load the playground
    }

    @Then("^Then Player1 and Player2 switch their kingdoms$")
    public void PlayerOneAndPlayerTwoSwitchTheirKingdoms(int arg0, int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        throw new PendingException();
    }




}
