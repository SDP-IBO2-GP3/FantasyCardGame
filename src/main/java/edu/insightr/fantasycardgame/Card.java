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
    private enum Race {
        // TODO Add races
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
}
