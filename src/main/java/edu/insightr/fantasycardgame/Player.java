package edu.insightr.fantasycardgame;

import java.util.ArrayList;
import java.util.Random;

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

    Random random = new Random();
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

    public void addACard(Card card) {
        this.listCardsInHand.add(card);
    }

    public void addACardKingdom(Card card){
        this.listCardsKingdom.add(card);
        this.score += 1;
    }

    //Getters
    public ArrayList<Card> getListCardsKingdom() {
        return this.listCardsKingdom;
    }

    public ArrayList<Card> getListCardsInHand() {
        return this.listCardsInHand;
    }

    public int getScore(){return this.score;}

    public Card getRandomKingdomCard(boolean delete) {
        int randomNumber = (int) (Math.random() * (this.listCardsKingdom.size()));

        Card returnValue = listCardsKingdom.get(randomNumber);

        if(delete){
            this.listCardsKingdom.remove(randomNumber);
            this.score -= 1;
        }

        return returnValue;
    }


    public Card getRandomHandCard(boolean delete) {
        int randomNumber = (int) (Math.random() * (this.listCardsInHand.size()));

        Card returnValue = listCardsInHand.get(randomNumber);

        if(delete)
            this.listCardsInHand.remove(randomNumber);

        return returnValue;
    }
    //Setters
    public void setListCardsKingdom(ArrayList<Card> newKingdom) {
        this.listCardsKingdom = newKingdom;
    }

    public void setListCardsInHand(ArrayList<Card> newListCardsInHand) {
        this.listCardsInHand = newListCardsInHand;
    }

    public void setScore(int newScore) { this.score = newScore; }

    public int getSizeOfKingdom() {return listCardsKingdom.size(); }

    @Override
    public String toString() {
        return "The player has the following cards in hand : " + listCardsInHand;
    }

    public int getSizeOfList() {
        return listCardsInHand.size();
    }
}
