package edu.insightr.fantasycardgame;

import java.util.ArrayList;

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
public class Game {
    /**
     * The deck represents the space where the players are going to draw their cards from
     */
    private Deck deck;

    private Player player1;

    private Player player2;

    private boolean isAITurn;

    public static final int DRAW_CARD_FROM_DECK = 0;
    public static final int CHOOSE_CARD_HAND = 1;
    public static final int IA_PLAY = 2;
    public static final int TAKE_CARD_ADVERSE_HAND = 3;
    public static final int TAKE_CARD_ADVERSE_KINGDOM = 4;
    public static final int APPLY_POWER_ADVERSE_KINGDOM = 5;

    private int currentState = -1;

    /**
     * Default constructor for our board. By default the first player is human.
     */
    public Game() {
        this.deck = new Deck();
        this.player1 = new Player(true);
        this.player2 = new Player(false);
    }


    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    /**
     * Play a human player turn. This method is asking for some action from the player.<br><br>
     * According to the card he plays, we are going to call the applyEffect method in order to apply a card's effect.<br>
     * This method should return true while we have cards in the deck, and while the player is able to play (place a card).
     *
     * @return True if the player could play, false else
     */
    public boolean playHumanTurn()
    {
        player1.addACard(deck.getACard());

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
        if(deck.getSize() > 0){
            player2.addACardKingdom(deck.getACard());
        }else{
            player2.addACardKingdom(player2.getListCardsInHand().get(0));
        }
        currentState = DRAW_CARD_FROM_DECK;
        return true;
    }

    public Card playCard(Player playerP, Player player, int cardIndex) {
        Card cardToPlay = playerP.getHandCard(cardIndex);
        playerP.addACardKingdom(cardToPlay);
        this.applyEffect(playerP, player, cardToPlay);
        return cardToPlay;
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
                //Swap kingdom
                ArrayList<Card> tempKingdom = playerP.getListCardsKingdom();
                playerP.setListCardsKingdom(player.getListCardsKingdom());
                player.setListCardsKingdom(tempKingdom);

                //Swap score
                int tempScore = playerP.getScore();
                playerP.setScore(player.getScore());
                player.setScore(tempScore);
                currentState = IA_PLAY;
                break;

            case Goblin:
                ArrayList<Card> tempHand = playerP.getListCardsInHand();
                playerP.setListCardsInHand(player.getListCardsInHand());
                player.setListCardsInHand(tempHand);
                currentState = IA_PLAY;
                break;

            case Elf:
                if(playerP.isHuman()){
                    currentState = APPLY_POWER_ADVERSE_KINGDOM;
                }else{
                    this.applyEffect(playerP, player, player.getRandomKingdomCard(false));
                }

                break;

            case Dryad:
                if(playerP.isHuman()){
                    currentState = TAKE_CARD_ADVERSE_KINGDOM;
                }else{
                    playerP.addACardKingdom(player.getRandomKingdomCard(true));
                }
                break;

            case Gnome:
                playerP.addACard(deck.getACard());
                playerP.addACard(deck.getACard());
                currentState = IA_PLAY;
                break;

            case Korrigan:
                if(playerP.isHuman()){
                    currentState = TAKE_CARD_ADVERSE_HAND;
                }else{
                    playerP.addACard(player.getRandomHandCard(true));
                    playerP.addACard(player.getRandomHandCard(true));
                }
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
    public void initiliazeGame() {
        this.deck.fillDeck();
        this.deck.shuffleDeck();
        for(int i = 0; i < 6; i++){
            this.player1.addACard(this.deck.getACard());
            this.player2.addACard(this.deck.getACard());
        }
        this.currentState = DRAW_CARD_FROM_DECK;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Deck getDeck() { return deck; }


    public void TakeCardOnAdverseHand(Player playerP,Player player,int id){
        Card card = player.getHandCard(id);
        playerP.addACard(card);
    }

     public void TakeCardOnAdverseKingdom(Player playerP,Player player,Card.Race race){
        for(int i=0;i<player.getListCardsKingdom().size();i++){
            if(player.getListCardsKingdom().get(i).race.equals(race)){
                Card card = player.getKingdomCard(i,true);
                playerP.addACardKingdom(card);
                break;
            }
        }
     }





}
