import java.util.ArrayList;
import java.util.Collections;

public class BaccaratDealer {

    ArrayList<Card> deck;

    // default constructor
    public BaccaratDealer() {
        deck = new ArrayList<>();
        generateDeck();
        shuffleDeck();
    }

    // this function generates the 52 card deck
    public void generateDeck() {
        String[] suites = {"Hearts", "Diamonds", "Clubs", "Spades"};
        // here, 1-ace, 11-jack, 12-queen, 13-king
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        // creating an arrayList of 52 card deck, which are precisely 52 instances
        // of the Card Class
        for (String suite : suites) {
            for (int value : values) {
                Card card = new Card(suite, value);
                deck.add(card);
            }
        }
    }

    // this function deals with 2 cards and returns an
    // arraylist of the 2 cards
    public ArrayList<Card> dealHand() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(drawOne());
        hand.add(drawOne());
        return hand;
    }

    // this function precisely draws 1 card from the standard 52 card deck
    public Card drawOne() {
        if(deckSize() > 0) {
            Card drawOneCard = deck.remove(0);
            return drawOneCard;
        }
        return null;
    }

    // this function shuffles the standard 52 card deck
    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    // this method simply returns the number of cards in the deck
    // at any given time
    public int deckSize() {
        return deck.size();
    }
}