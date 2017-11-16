package edu.insightr.fantasycardgame;

import java.util.ArrayList;

/**
 * <b>A class representing each of our players</b><br><br>
 *
 * Attributes :
 * <ul>
 *      <li>listCardsInHand - The list of cards in the player's hand</li>
 *      <li>listCardsKingdom - The list of cards in the player's kingdom</li>
 *      <li>isHuman - A boolean True if the player is a human, False</li>
 *      <li>score - Score of an individual player</li>
 * </ul>
 */
public class Player {
    /**
     * List of the cards in the hand of the player
     */
    private ArrayList<Card> listCardsInHand;

    /**
     * List of the cards in the kingdom of the player
     */
    private ArrayList<Card> listCardsKingdom;

    /**
     * A boolean telling us if the player is human or not
     */
    private boolean isHuman;

    /**
     * Score of the player
     */
    private int score;

    /**
     * Basic constructor for the player. By default, he got a score of 0
     * @param isHuman Attributes given to know if the player is human or not
     */
    public Player(boolean isHuman) {
        this.isHuman = isHuman;
        this.listCardsInHand = new ArrayList<Card>();
        this.listCardsKingdom = new ArrayList<Card>();
        this.score = 0;
    }

    public void addACard(Card card)
    {
        this.listCardsInHand.add(card);
    }
}
