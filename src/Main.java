import java.util.List;

public class Main {
    public static void main(String[] args) {
startGame();
        }

    private static void startGame() {

        CardDeck c= new CardDeck();
        List<Cards> list= c.getCardDeck();
        //System.out.println(list);
        c.shuffleCardDeck(list);
        //System.out.println(list);
    }
}