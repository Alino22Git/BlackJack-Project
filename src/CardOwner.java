import java.util.ArrayList;

public abstract class CardOwner {
    protected int cardsValue;
    protected ArrayList<Cards> cards = new ArrayList<>();

    protected void sumCardValues() {
        if (cards.isEmpty()) {
            throw new NullPointerException("No cards to sum in 'sumCardValues()'.");
        }
        cardsValue = 0;
        for (Cards c : this.cards) {
            if (c.cardNumber.equals("King") || c.cardNumber.equals("Ace") || c.cardNumber.equals("Queen") || c.cardNumber.equals("Jack")) {
                cardsValue += 10;
            } else {
                cardsValue += Integer.parseInt(c.cardNumber);
            }
        }
    }
    public boolean is21(){
        sumCardValues();
        return cardsValue==21;
    }
    public boolean isBusted() {
        sumCardValues();
        return cardsValue > 21;
    }

    public int getCardsValue() {
        sumCardValues();
        return cardsValue;
    }

    public ArrayList<Cards> getCards() {
        return cards;
    }

    public void setCards(Cards card) {
        this.cards.add(card);
    }
}
