import java.util.ArrayList;
import java.util.List;

public class CardDealer {
    private ArrayList<Cards> cardsOnTheTable = new ArrayList<>();

    private void dealCards(Player p, List<Cards> cardDeck){
        p.cardHand.add(drawCard(cardDeck));
        p.cardHand.add(drawCard(cardDeck));
    }

    public Cards drawCard(List<Cards> cardDeck){
        Cards drawnCard = cardDeck.get(0);
        cardDeck.remove(0);
        return drawnCard;
    }

    private void showOneCard(){
        System.out.println(cardsOnTheTable.get(0));
    }

    private void showAllCards(){
        System.out.println(cardsOnTheTable);
    }

    private void restockDealerHand(List<Cards> cardDeck){
        cardsOnTheTable.add(drawCard(cardDeck));
        cardsOnTheTable.add(drawCard(cardDeck));
    }

    private void clearTable(){
        cardsOnTheTable.clear();
    }
}
