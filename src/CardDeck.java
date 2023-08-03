import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private List<Cards> cardDeck=new ArrayList<>();

    public CardDeck() {
        generateCardDeck();
    }

    public void generateCardDeck() {
        int x=0;
        while(x<4) {
            for (int i = 1; i <= 52; i++) {
                cardDeck.add(new Cards(Cards.getCardNumber(i), Cards.getCardSign(i)));
            }
            x++;
        }
    }

    public void shuffleCardDeck(List<Cards> cardDeck){
        Collections.shuffle(cardDeck);
    }

    public List<Cards> getCardDeck() {
        return cardDeck;
    }

    @Override
    public String toString() {
        StringBuilder cards= new StringBuilder();
        for(int i=0;i<getCardDeck().size();i++){
            cards.append(getCardDeck().get(i).toString()).append("\n");
        }
        return cards.toString();
    }
}
