import java.util.ArrayList;
import java.util.List;

public class Player extends CardOwner{
    private final String name;



    public Player(String name) {
        this.name = name;
    }

    public void clearHand() {
        cards.clear();
    }
    public ArrayList<Cards> getCards() {
        return cards;
    }

    public void setCards(Cards card) {
        this.cards.add(card);
    }

    public int getCardsValue() {
        sumCardValues();
        return cardsValue;
    }

    public String getName() {
        return name;
    }

}
