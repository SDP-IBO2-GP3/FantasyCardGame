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

    /**
     * Scenario: Initialize Playground
     */

    @Given("^Playground is initialized$")
    public void PlaygroundIsInitialized(BoardController boardController) throws Throwable{

        // Write code allowing to load the playground
    }

    @Then("^Player1 has (\\d+) cards in his hand$")
    public void playerHasCardsInHisHand(Player playerOne) throws Throwable {
        // Write code here that turns the phrase above into concrete actions


        // Assert.assertEquals(0,function lenght card)
        // initialize card
        // Assert.assertEquals(0,function lenght card)

        throw new PendingException();
    }

    @And("^Player1 has (\\d+) cards in his kingdom$")
    public void playerHasCardsInHisKingdom(Player playerOne) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        // Assert.assertEquals(0,function lenght kingdom)
        // initialize kingdom
        // Assert.assertEquals(0,function lenght kingdom)

        throw new PendingException();
    }


    /**
     * Scenario: Initialize Deck
     */

    @Given("^Deck is initialized$")
    public void DeckIsInitialized(Deck deck) throws Throwable{
        // Write code allowing to load the playground
    }

    @Then("^Deck has (\\d+) cards$")
    public void deckHasCards(Deck deck) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        // Assert.assertEquals(number deck parameter,function lenght deck)

        throw new PendingException();
    }

    @And("^Different races$")
    public void differentRaces(Deck deck) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // A deck has a constaint contraint, a deck can't have the same cards


        // int same = 0
        // for (int i=1;i<deck.lenght;i++){
        //    if(deck.getCard(i) == deck.getCard(0)){
        //        same ++;
        //    }
        // }
        // Assert.assertTrue(same < deck.length);

        throw new PendingException();
    }


    /**
     * Scenario: Play a Korrigan
     */


    @Given("^Players1 plays a Korrigan$")
    public void PlayersOnePlaysAKorrigan() throws Throwable{
        // Write code allowing to load the playground

        //

    }

    @Then("^Player1 has (\\d+) more cards in his hand$")
    public void PlayerOnehasMoreCardsInHisHand(Player playerOne) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // Applicate the playerOne

        // n = playerOne lenght card
        // applicate on the cards the power on his cards
        // Assert.assertEquals(n+2, playerOne card lenght)

        throw new PendingException();
    }

    @And("^Player2 has (\\d+) cards less in his hand$")
    public void PlayerTwoHasCardsLessInHisHand(Player playerTwo) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        // n = playerTwo lenght card
        // applicate on the cards the power on playerOne card
        // Assert.assertEquals(n-2, playerTwo card lenght)

        throw new PendingException();
    }



    /**
     * Scenario: Play a Goblin
     */


    @Given("^Given Player1 plays a Goblin$")
    public void GivenPlayerOnePlaysAGoblinn() throws Throwable{
        // Write code allowing to load the playground
    }

    @Then("^Player1 switches his hand with Player2$")
    public void PlayerOneSwitchesHisHandWithPlayerTwo(Player playerOne, Player playerTwo) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        // Switch and verify each card

        // Copy the playerOne deck on a temp tab
        // Copy the playerTwo deck on a seconde tab

        // int same_deck = 0
        // for (int i=0;i<deck02.length;i++){
        //      if(tmptab[i] == deck02){
        //          same_deck ++;
        //      }
        // }


        // int same_deck_02 = 0
        // for (int i=0;i<deck01.length;i++){
        //      if(tmp_tab_second[i] == deck01){
        //          same_deck_02 ++;
        //      }
        // }

        // Assert.assertThat(same_deck,deck02)
        //  Assert.assertThat(same_deck02,deck01)


        throw new PendingException();
    }




    /**
     * Scenario:Play a Dryad
     */


    @Given("^Player1 plays a Dryad$")
    public void PlayerOnePlaysaDryad() throws Throwable{
        // Write code allowing to load the playground
    }

    @Then("^Player2 has one less card in his kingdom$")
    public void PlayerHasOneLessCardInHisKingdom(Player playerTwo) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        // n =  the length kindom of playerTwo
        // applicate the power of Dryand
        // assertThat(n-1,playerTwo.kinhgom.length)

        throw new PendingException();
    }

    @And("^Player1 has one more card in his kingdom$")
    public void PlayerOneHasOneMoreCardInHisKingdom(Player playerOne) throws Throwable {
        // Write code here that turns the phrase above into concrete actions


        // n =  the length kindom of playerOne
        // applicate the power of Dryand
        // assertThat(n+1,playerTwo.kinhgom.length)

        throw new PendingException();
    }




    /**
     * Scenario: Play a Troll
     */


    @Given("^Given Player1 plays a Troll$")
    public void PlayerOnePlaysATroll() throws Throwable{
        // Write code allowing to load the playground


    }

    @Then("^Then Player1 and Player2 switch their kingdoms$")
    public void PlayerOneAndPlayerTwoSwitchTheirKingdoms(Player playerOne,Player playerTwo) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        // Switch and verify each card

        // Copy the kingdomeOne deck on a temp tab
        // Copy the kingdomeTwo deck on a seconde tab

        // int same_deck = 0
        // for (int i=0;i<deck02.length;i++){
        //      if(tmptab[i] == deck02){
        //          same_deck ++;
        //      }
        // }


        // int same_deck_02 = 0
        // for (int i=0;i<kingdomeOne.length;i++){
        //      if(tmp_tab_second[i] == kingdomeOne){
        //          same_deck_02 ++;
        //      }
        // }

        // Assert.assertThat(same_deck,kingdomeTwo)
        //  Assert.assertThat(same_deck02,kingdomeOne)




        throw new PendingException();
    }
}
