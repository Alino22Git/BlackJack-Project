import java.util.Objects;
import java.util.Scanner;

public class Game {
    public static void startGame() {
        String exitCondition;
        Player p1 = new Player("Alen");
        CardDealer dealer = new CardDealer();
        exitCondition = continueGame();
        while (exitCondition.equals("start")) {
            prepareRound(dealer, p1);
            if (!hasSomeoneBJ(dealer, p1)) {
                if (startRound(dealer, p1)) {
                    getResults(dealer, p1);
                }
            }
            clearCards(dealer, p1);
            cleanTerminal();
            exitCondition = continueGame();
        }
        System.out.println("Thank you for playing my game!");
    }

    private static String continueGame() {
        Scanner scan = new Scanner(System.in);
        String input = " ";
        System.out.print("Do you want to start a game?\nWrite either 'exit' or 'start' to continue:");
        while (!input.equals("start") && !input.equals("exit")) {
            input = scan.nextLine();
            if (!input.equals("start") && !input.equals("exit")) {
                System.out.print("Wrong Input! Try again:");
            }
        }
        return input;
    }

    private static void prepareRound(CardDealer dealer, Player p) {
        dealer.setCardDeckOfDealer(new CardDeck().getCardDeck());
        dealer.restockDealerHand();
        dealer.dealCards(p, 2);
        p.showAllCards();
        //System.out.println("The Dealer has dealt all cards.\n");
    }

    private static boolean hasSomeoneBJ(CardDealer dealer, Player p) {
        if (dealer.is21() || p.is21()) {
            if (dealer.getCardsValue() == p.getCardsValue()) {
                System.out.println(dealer.getCards());
                System.out.println("Both, Dealer and " + p.getName() + " have a 'BlackJack'.\nIt is a Draw.");
            } else if (dealer.getCardsValue() > p.getCardsValue()) {
                dealer.showAllCards();
                System.out.println("The Dealer wins with a 'BlackJack'.");
            } else {
                System.out.println("The Player wins with a 'BlackJack'");
            }
            System.out.println();
            return true;
        }
        return false;
    }

    private static boolean startRound(CardDealer dealer, Player p1) {
        char status = 'h';
        while (status == 'h') {
            status = playerOptions(dealer, p1);
            if (status == 'b') {
                System.out.println("Player: " + p1.getName() + " got 'Busted'!\n");
                return false;
            }
        }
        return true;
    }

    private static char playerOptions(CardDealer dealer, Player p) throws IllegalArgumentException {
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
        p.showAllCards();
        return p.isBusted();
    }

    private static void getResults(CardDealer dealer, Player p) {
        dealer.drawUntil17();
        if (dealer.isBusted()) {
            System.out.println("Dealer overdraw!(" + dealer.getCardsValue() + ")\n");
            return;
        }
        winCondition(dealer, p);

    }

    private static void winCondition(CardDealer dealer, Player p) {
        if (dealer.getCardsValue() < p.getCardsValue()) {
            System.out.println("Player: " + p.getName() + " wins the round!(" + p.getCardsValue() + ")\n");
        } else if (dealer.getCardsValue() > p.getCardsValue()) {
            System.out.println("Dealer wins the round!(" + dealer.getCardsValue() + ")\n");
        } else {
            System.out.println("It is a draw between the Dealer and the Player!\n");
        }
    }

    private static void clearCards(CardDealer dealer, Player p) {
        dealer.clearTable();
        p.clearHand();
    }

    private static void cleanTerminal() {

    }


}
