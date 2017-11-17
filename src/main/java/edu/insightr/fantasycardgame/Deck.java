package edu.insightr.fantasycardgame;

import java.util.Collections;
import java.util.Stack;


/**
 * <b>A deck present at the center of our board</b><br><br>
 * <p>
 * Attributes :
 * <ul>
 * <li>listCard - The list of cards in the deck</li>
 * <li>numberOfCards - Maximum number of cards in the deck</li>
 * </ul>
 */
public class Deck {
    /**
     * The list of card present in the deck.
     */
    private Stack<Card> listCard;

    /**
     * The highest number of cards our deck can contain.
     */
    private int numberOfCards;

    /**
     * Default constructor for our deck.
     */
    public Deck() {
        this.numberOfCards = 60;
        this.listCard = new Stack<Card>();
    }

    /**
     * Getter for the size of the deck.
     *
     * @return The deck's size
     */
    public int getSize() {
        return listCard.size();
    }

    /**
     * This method fills the deck with cards. In other terms, it initializes the deck with cards.<br><br>
     * <ul>
     * <li>The deck should contain no more than numberOfCards cards.</li>
     * <li>It should also contain only cards respecting our races.</li>
     * <li>Also, each race should be represented at least once.</li>
     * </ul>
     */
    public void fillDeck() {
        for (int i = 0; i < numberOfCards/6; i++) {
            for (Card.Race race : Card.Race.values()
                    ) {
                this.listCard.push(new Card(race));
            }
        }
    }
    public void shuffleDeck(){
        Collections.shuffle(this.listCard);
    }
    /**
     *
     * @return
     */
    public String toString() {
            return "voici les cartes \n" + listCard;
        }

    /**
     * Gets the top card in the deck and discards it from it.
     *
     * @return An instance of card
     */
    public Card getACard() {
        return this.listCard.pop();
    }
}
