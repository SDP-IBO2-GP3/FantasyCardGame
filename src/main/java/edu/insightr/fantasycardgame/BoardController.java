package edu.insightr.fantasycardgame;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>The Board controller class is going to handle our whole game.</b><br><br>
 *
 * Attributes :
 * <ul>
 *      <li>deck - A deck of card</li>
 *      <li>player1 - The first player</li>
 *      <li>player2 - The second player (IA for the moment)</li>
 *      <li>isAITurn - Denotes if it is the AI turn or not</li>
 * </ul><br><br>
 *
 * This class is designed to be the main class in our game : it has to initialize the elements when starting the game
 * and to handle the player's turn. Therefore, we are going to define a method playATurn() which is going to check
 * whose turn it is, and ask/wait for instructions. <br><br>When given the instructions (and especially when a player plays a
 * card), it is going to apply the card's power in the game (either on the board, on the other player, on the player himself).
 */
public class BoardController {
    /**
     * The deck represents the space where the players are going to draw their cards from
     */
    private Deck deck;

    /**
     * First player
     */
    private Player player1;

    /**
     * Second player
     */
    private Player player2;

    /**
     * Denotes if it is an AI turn or not
     */
    private boolean isAITurn;

    /**
     * Default constructor for our board. By default the first player is human.
     */
    public BoardController() {
        this.deck = new Deck();
        this.player1 = new Player(true);
        this.player2 = new Player(false);
    }

    /**
     * Play a human player turn. This method is asking for some action from the player.<br><br>
     * According to the card he plays, we are going to call the applyEffect method in order to apply a card's effect.<br>
     * This method should return true while we have cards in the deck, and while the player is able to play (place a card).
     *
     * @return True if the player could play, false else
     */
    public boolean playATurn()
    {
        return true;
    }

    /**
     * This method is made to play an AI turn.<br>
     * This method should return true while we have cards in the deck, and while the player is able to play (place a card).
     *
     * @return True if the AI could play, false else
     */
    public boolean playAITurn()
    {
        return true;
    }

    /**
     * This methods executes the game while it is possible to do so. After a player couldn't play, the other ones finish
     * the turn, and this methods exits, giving the winner.
     * @return The instance of the player who has won
     */
    public Player execute()
    {
        return new Player(true);
    }

    /**
     * Apply the effect of the card on the board
     * @param card The card which has been played
     * @param playerP The player who has played the card
     * @param player The other player
     */
    public void applyEffect(Player playerP, Player player, Card card)
    {
        switch(card.getRace()){
            case Troll:
                ArrayList<Card> tempKingdom = playerP.getListCardsKingdom();
                playerP.setListCardsKingdom(player.getListCardsKingdom());
                player.setListCardsKingdom(tempKingdom);
                break;

            case Goblin:
                ArrayList<Card> tempHand = playerP.getListCardsInHand();
                playerP.setListCardsInHand(player.getListCardsInHand());
                player.setListCardsInHand(tempHand);
                break;

            case Elf:

                break;

            case Dryad:

                break;

            case Gnome:
                playerP.addACard(deck.getACard());
                playerP.addACard(deck.getACard());
                break;

            case Korrigan:

                break;

            default:

        }
    }

    /**
     * This method initializes the game.<br><br>
     *
     * Steps :
     * <ul>
     *     <li>Get cards in the deck</li>
     *     <li>Give a player a number of cards (1/10th of the deck)</li>
     *     <li>"Flip a coin" to know if the AI should start or not</li>
     * </ul>
     */
    public void initiliazeGame()
    {
        this.deck.fillDeck();
        this.deck.shuffleDeck();
        for(int i = 0; i < 6; i++){
            this.player1.addACard(this.deck.getACard());
            this.player2.addACard(this.deck.getACard());
        }
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
