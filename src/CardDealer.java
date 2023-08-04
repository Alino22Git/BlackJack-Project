import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDealer extends CardOwner{

    private List<Cards> cardsOnTheTable = new ArrayList<>();
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
        //STILL IN WORK
        int i = 0;
        while (i < 1) {
            cardsOnTheTable.add(drawCard());
            i++;
        }
        showAllCards();
    }

    private void showOneCard() {
        System.out.println("Dealers " + cardsOnTheTable.get(0) + " (AND " + cardsOnTheTable.get(1) + " )");
    }

    public void showAllCards() {
        System.out.println(cardsOnTheTable);
    }

    public void restockDealerHand() {
        checkCardDeckNotEmpty(2);
        cardsOnTheTable.add(drawCard());
        cardsOnTheTable.add(drawCard());
        showOneCard();
    }

    private void checkCardDeckNotEmpty(int number) {
        if (cardDeckOfDealer.size() < number) {
            cardDeckOfDealer = new CardDeck().getCardDeck();
        }
    }

    public void clearTable() {
        cardsOnTheTable.clear();
    }




    public void setCardDeckOfDealer(List<Cards> cardDeckOfDealer) {
        this.cardDeckOfDealer = cardDeckOfDealer;
    }

    public int getCardsValue() {
        return cardsValue;
    }

    public List<Cards> getCardsOnTheTable() {
        return cardsOnTheTable;
    }
}
