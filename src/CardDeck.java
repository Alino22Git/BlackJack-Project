import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private final List<Cards> cardDeck = new ArrayList<>();

    public CardDeck() {
        generateCardDeck();
        shuffleCardDeck();
    }

    public void generateCardDeck() { //creating a deck of 6*52 Cards (BlackJack is usually played with this amount of cards)
        int x = 0;
        while (x < 6) {
            for (int i = 1; i <= 52; i++) {
                cardDeck.add(new Cards(Cards.getCardNumber(i), Cards.getCardSign(i)));
            }
            x++;
        }
    }

    public void shuffleCardDeck() {
        Collections.shuffle(cardDeck);
    }//shuffles deck

    public List<Cards> getCardDeck() {
        return cardDeck;
    }

    @Override
    public String toString() {
        StringBuilder cards = new StringBuilder();
        for (int i = 0; i < getCardDeck().size(); i++) {
            cards.append(getCardDeck().get(i).toString()).append("\n");
        }
        return cards.toString();
    }
}
