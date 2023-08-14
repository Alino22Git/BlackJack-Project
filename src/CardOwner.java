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
            if (c.cardNumber.equals("King") || c.cardNumber.equals("Queen") || c.cardNumber.equals("Jack")) {
                cardsValue += 10;
            } else if (c.cardNumber.equals("Ace")) {
                cardsValue += 11;
            } else {
                cardsValue += Integer.parseInt(c.cardNumber);
            }
        }
        aceOneOrEleven();
    }

    private void aceOneOrEleven() {
        int aces = (int) this.cards.stream().filter(card -> card.cardNumber.equals("Ace")).count();
        for (int i = 0; i < aces; i++) {
            if (cardsValue > 21) {
                cardsValue -= 10;
            }
        }
    }

    public boolean is21() {
        sumCardValues();
        return cardsValue == 21;
    }

    public boolean isBusted() {
        sumCardValues();
        return cardsValue > 21;
    }

    public int getCardsValue() {
        sumCardValues();
        return cardsValue;
    }

    public void setCards(Cards card) {
        this.cards.add(card);
    }

    public void showAllCards() {
        for (Cards card : cards) {
            System.out.print(card + " ");
        }
        System.out.println(" ");
    }
}
