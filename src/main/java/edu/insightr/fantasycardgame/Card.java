package edu.insightr.fantasycardgame;
/**
 * <b>A card present in our game</b><br><br>
 *
 * Attributes :
 * <ul>
 *      <li>race - The race of the card</li>
 * </ul>
 */
public class Card {
    /**
     * This enumeration is going to help us handle the different type of cards in our game
     * It also provides us a way to have a control over the different cards' races
     */
    public enum Race {
        Korrigan,
        Gnome,
        Goblin,
        Elf,
        Dryad,
        Troll;

        @Override
        public String toString() {
            switch(this) {
                case Korrigan: return "draw 2 random cards within your opponent hand";
                case Gnome: return "draw 2 cards";
                case Goblin: return "switch your hand with you opponent";
                case Elf: return "copy and use the power of one of the card in front of you";
                case Dryad: return "stole a card in front of your opponent and add it in front of you without activating its power";
                case Troll: return "swap the cards in front of you with the cards in front of your opponent";
                default: throw new IllegalArgumentException();
            }
        }
    }

    /**
     * Represents the race of the card
     */
    private Race race;

    /**
     * Minimal constructor for the card : we only need to provide a race to the card
     * @param race The race taken from our enumeration
     */
    public Card(Race race) {
        this.race = race;
    }

    @Override
    public String toString() {
        return "Carte : " + this.race.name() + ", " + this.race.toString() +  " \n";
    }
}
