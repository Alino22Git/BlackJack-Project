import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private int cardsValue;
    private ArrayList<Cards> cardHand = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public void clearHand() {
        cardHand.clear();
    }


    private void sumCardValuesOnHand(){
        if(cardHand.isEmpty()){
            throw new NullPointerException("No cards to sum in 'sumCardValues()'.");
        }
        cardsValue=0;
        for (Cards c: this.cardHand) {
            if (c.cardNumber.equals("King") || c.cardNumber.equals("Ace") || c.cardNumber.equals("Queen") || c.cardNumber.equals("Jack")) {
                cardsValue += 10;
            } else {
                cardsValue += Integer.parseInt(c.cardNumber);
            }
        }
    }
    public ArrayList<Cards> getCardHand() {
        return cardHand;
    }

    public void setCardHand(Cards card) {
        this.cardHand.add(card);
    }

    public int getCardsValue() {
        sumCardValuesOnHand();
        return cardsValue;
    }

    public String getName() {
        return name;
    }
}
