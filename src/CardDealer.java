import java.util.ArrayList;
import java.util.List;

public class CardDealer extends CardOwner {

    private List<Cards> cardDeckOfDealer = new ArrayList<>();

    public void dealCards(Player p, int number) {
        checkCardDeckNotEmpty(number);
        for (int i = 0; i < number; i++) {
            p.setCards(drawCard());
        }
    }

    private Cards drawCard() {
        checkCardDeckNotEmpty(1);
        Cards drawnCard = cardDeckOfDealer.get(0);
        cardDeckOfDealer.remove(0);
        return drawnCard;
    }

    public void drawUntil17() {
        showAllCards();
        while (!is17()) {
            cards.add(drawCard());
            showAllCards();
        }
    }

    private void showOneCard() {
        //System.out.println("Dealers Card: " + cards.get(0) + " (AND " + cards.get(1) + ")");
        System.out.println("Dealers Card: " + cards.get(0));
    }

    public void showAllCards() {
        System.out.print("Dealers Cards: ");
        super.showAllCards();
    }

    public void restockDealerHand() {
        checkCardDeckNotEmpty(2);
        cards.add(drawCard());
        cards.add(drawCard());
        showOneCard();
    }

    private void checkCardDeckNotEmpty(int number) {
        if (cardDeckOfDealer.size() < number) {
            cardDeckOfDealer = new CardDeck().getCardDeck();
        }
    }

    public void clearTable() {
        cards.clear();
    }

    public void setCardDeckOfDealer(List<Cards> cardDeckOfDealer) {
        this.cardDeckOfDealer = cardDeckOfDealer;
    }

    public boolean is17() {
        sumCardValues();
        return cardsValue >= 17;
    }


}
