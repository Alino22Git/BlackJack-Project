import java.util.ArrayList;

public class Player {
    private String name = null;
    private ArrayList<Cards> cardHand =new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public void clearHand(){
cardHand.clear();
    }

    public ArrayList<Cards> getCardHand() {
        return cardHand;
    }

    public void setCardHand(Cards card) {
        this.cardHand.add(card);
    }
}
