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
            if (c.cardNumber.equals("King") || c.cardNumber.equals("Queen") || c.cardNumber.equals("Jack")) { //the 3 cards have a value of 10
                cardsValue += 10;
            } else if (c.cardNumber.equals("Ace")) { //Ace has a value of 1 OR 11 depends on the value of the player
                cardsValue += 11;
            } else {
                cardsValue += Integer.parseInt(c.cardNumber);//Ex. String "8" into int 8
            }
        }
        aceOneOrEleven();
    }

    private void aceOneOrEleven() {
        int aces = (int) this.cards.stream().filter(card -> card.cardNumber.equals("Ace")).count(); //searches for aces in the hand of the player/dealer
        for (int i = 0; i < aces; i++) {
            if (cardsValue > 21) { //if the hand (dealer/player) is over 21, aces count as 1 (else 11)
                cardsValue -= 10; //11-10=1
            }
        }
    }

    public boolean is21() {//checks if the card value is 21
        sumCardValues();
        return cardsValue == 21;
    }

    public boolean isBusted() {//checks if the card value is over 21
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
