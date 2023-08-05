import java.util.Objects;
import java.util.Scanner;

public class Game {
    public static void startGame() {
        String exitCondition="start";
        Player p1 = new Player("Alen");
        CardDealer dealer = new CardDealer();
        while(exitCondition.equals("start")) {
            exitCondition=continueGame();
            prepareRound(dealer, p1);
            startRound(dealer, p1);
            showResults(dealer, p1);
            cleanTerminal();
        }
        System.out.println("Thank you for playing my game!");
    }

    private static String continueGame() {
        Scanner scan = new Scanner(System.in);
        String input = "input";
        System.out.print("Do you want to start a game?\nWrite either 'exit' or 'start' to continue:");
        while (!input.equals("start") && !input.equals("exit")) {
            input = scan.nextLine();
            if (!input.equals("start") && !input.equals("exit")) {
                System.out.print("Wrong Input! Try again:");
            }
        }
        return input;
    }

    private static void cleanTerminal() {

    }

    private static void prepareRound(CardDealer dealer, Player p1) {
        dealer.setCardDeckOfDealer(new CardDeck().getCardDeck());
        dealer.restockDealerHand();
        dealer.dealCards(p1, 2);
        if (dealer.is21()) {
            System.out.println("Dealer: " + dealer.getCards());
            System.out.println("Player: " + p1.getCards());
            System.out.println("Dealer has a 'Black Jack'!");
        }
        System.out.println("The Dealer has dealt all cards.\n");
    }

    private static void startRound(CardDealer dealer, Player p1) {
        if (dealer.is21()) {
            return;
        }
        char status = 'h';
        while (status == 'h') {
            status = playerOptions(dealer, p1);
            if (status == '!') {
                System.out.println("Player: " + p1.getCards());
                System.out.println("Player: " + p1.getName() + " has a 'Black Jack'!");

                break;
            }
            if (status == 'b') {
                System.out.println("Player: " + p1.getCards());
                System.out.println("Player: " + p1.getName() + " got 'Busted'!");
                break;
            }
        }
    }

    private static void showResults(CardDealer dealer, Player p) {
        if (dealer.is21() || p.is21()) {
            return;
        }
        dealer.drawUntil17();
        winCondition(dealer, p);
    }

    private static void winCondition(CardDealer dealer, Player p) {
        if(p.isBusted()) {
            return;
        } else if(dealer.isBusted()){
            System.out.print("Dealer busted with ");
            dealer.showAllCards();
            return;
        }
        if (dealer.getCardsValue() < p.getCardsValue()) {
            System.out.println("Player: " + p.getName() + " wins the round!");
        } else if (dealer.getCardsValue() > p.getCardsValue()) {
            System.out.println("Dealer wins the round!");
        } else {
            System.out.println("It is a draw between the Dealer and the Player!");
        }
        clearCards(dealer, p);
    }

    private static void clearCards(CardDealer dealer, Player p) {
        dealer.clearTable();
        p.clearHand();
    }

    private static char playerOptions(CardDealer dealer, Player p) throws IllegalArgumentException {
        System.out.println("Player: " + p.getCards());
        if (p.is21()) {
            return '!';
        }
        System.out.println("Do you want to hit (h) or to stay (s)");
        Scanner scan = new Scanner(System.in);
        char input = scan.next().trim().charAt(0);
        if (Objects.equals(input, 'h')) {
            if (playerHit(dealer, p)) {
                return 'b';
            }
            return 'h';
        } else if (Objects.equals(input, 's')) return 's';
        else {
            throw new IllegalArgumentException("Wrong Input at 'playerOptions()'");
        }
    }

    private static boolean playerHit(CardDealer dealer, Player p) {
        dealer.dealCards(p, 1);
        return p.isBusted();
    }

}
