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
     * A boolean telling if the player has a bonus +3 on his score
     */
    private boolean has_bonus;

    private int numberCardSelectedKingdomAdverve;

    private ArrayList<Card.Race> races_in_kingdom = new ArrayList<>();

    /**
     * Basic constructor for the player. By default, he got a score of 0
     * @param isHuman Attributes given to know if the player is human or not
     */
    public Player(boolean isHuman) {
        this.isHuman = isHuman;
        this.listCardsInHand = new ArrayList<Card>();
        this.listCardsKingdom = new ArrayList<Card>();
        this.score = 0;
        this.numberCardSelectedKingdomAdverve = 0;
        this.has_bonus = false;
    }

    public void addACard(Card card) {
        this.listCardsInHand.add(card);
    }

    public void addACardKingdom(Card card){
        this.listCardsKingdom.add(card);
        this.score += 1;

        if(!this.has_bonus){
            boolean newRace = false;
            if(this.races_in_kingdom.size() == 0){
                this.races_in_kingdom.add(card.getRace());
            }
            else{
                int count = 0;
                for(int j=0; j<this.races_in_kingdom.size(); j++){
                    if(card.getRace() != this.races_in_kingdom.get(j)){
                        count += 1;
                    }
                    if(count == races_in_kingdom.size()){
                        newRace = true;
                    }
                }
                if(newRace){
                    this.races_in_kingdom.add(card.getRace());
                }
                if(this.races_in_kingdom.size() == 6){
                    score += 3;
                    this.has_bonus = true;
                }
            }
        }
    }

    //Getters
    public ArrayList<Card> getListCardsKingdom() {
        return this.listCardsKingdom;
    }

    public ArrayList<Card> getListCardsInHand() {
        return this.listCardsInHand;
    }

    public int getNumberCardSelectedKingdomAdverve() {
        return numberCardSelectedKingdomAdverve;
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

    public boolean isHuman() {
        return isHuman;
    }

    public Card getRandomHandCard(boolean delete) {
        int randomNumber = (int) (Math.random() * (this.listCardsInHand.size()));

        Card returnValue = listCardsInHand.get(randomNumber);

        if(delete)
            this.listCardsInHand.remove(randomNumber);

        return returnValue;
    }

    public Card getHandCard(int cardIndextoDelete) {
        Card returnValue = this.getListCardsInHand().get(cardIndextoDelete);
        this.getListCardsInHand().remove(cardIndextoDelete);
        return returnValue;
    }

    public Card getKingdomCard(int cardIndextoDelete,boolean delete) {
        Card returnValue = this.getListCardsKingdom().get(cardIndextoDelete);
        if(delete)
            this.getListCardsKingdom().remove(cardIndextoDelete);
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

    public void setNumberCardSelectedKingdomAdverve(int numberCardSelectedKingdomAdverve) {
        this.numberCardSelectedKingdomAdverve = numberCardSelectedKingdomAdverve;
    }

    public int getSizeOfKingdom() {return listCardsKingdom.size(); }

    @Override
    public String toString() {
        return "The player has the following cards in hand : " + listCardsInHand;
    }

    public int getSizeOfList() {
        return listCardsInHand.size();
    }

}
