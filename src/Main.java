import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
startGame();
        }

    private static void startGame() {
        Player p1=new Player("Alen");
        CardDealer dealer=new CardDealer();
        prepareRound(dealer,p1);
        if(!playerOptions(dealer,p1)){
            System.out.println("Player: "+p1.getName()+" got 'Busted'!");
        }
    }
    private static void prepareRound(CardDealer dealer,Player p1) {
        dealer.setCardDeckOfDealer(new CardDeck().getCardDeck());
        dealer.restockDealerHand();
        dealer.dealCards(p1,2);
        //System.out.println("Dealer: "+dealer.getCardsOnTheTable());
        System.out.println("The Dealer has dealed all cards.\n");
    }
    private static boolean playerOptions(CardDealer dealer,Player p1) throws IllegalArgumentException {
        System.out.println("Player: "+p1.getCardHand());
        System.out.println("Do you want to hit (h) or to stay (s)");
        Scanner scan = new Scanner(System.in);
        char input = scan.next().trim().charAt(0);
        if(Objects.equals(input, 'h')){
            if(playerHit(dealer,p1)) {
                return false;
            }
                playerOptions(dealer, p1);
        }else if(Objects.equals(input, 's')) return true;
        else{
            throw new IllegalArgumentException("Wrong Input at 'playerOptions()'");
        }
        return true;
    }

    private static boolean playerHit(CardDealer dealer,Player p) {
        dealer.dealCards(p,1);
        return isBusted(p);
    }

    private static boolean isBusted(Player p) {
        return p.getCardsValue() > 21;
    }

}
