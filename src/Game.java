import java.util.Objects;
import java.util.Scanner;

public class Game {
    public static void startGame() {
        Token token=new Token(100);
        Player p1 = new Player("Alen");
        CardDealer dealer = new CardDealer();
        String exitCondition;
        do {
            setBet(token);
            prepareRound(dealer, p1);
            if (!hasSomeoneBJ(dealer, p1, token)) {
                if (startRound(dealer, p1, token)) {
                    getResults(dealer, p1, token);
                }
            }
            if (token.getBudget() < 10) {
                break;
            }
            clearCards(dealer, p1);
            //cleanTerminal();
            exitCondition = continueGame();
        } while (exitCondition.equals("continue"));
        if (token.getBudget() <= 0) {
            System.out.println("You lost the game");
        } else {
            System.out.println("You quit the game of a total budget of: " + token.getBudget());
        }
        System.out.println("Thank you for playing my game!");
    }

    private static void setBet(Token t) {
        Scanner scan = new Scanner(System.in);
        int input;
        System.out.println("Your budget: " + t.getBudget());
        do {
            System.out.print("Your bet:");
            input = scan.nextInt();
            if (input > t.getBudget()) {
                System.out.print("Your bet can not be higher than your budget!\n");
            } else if (input < 0) {
                System.out.print("Your bet can not be negative!\n");
            }else if(input<10){
                System.out.print("Your bet must be at least 10 tokens high!\n");
            }
        } while (input < 0 || input > t.getBudget() || input < 10);
        t.setActiveBet(input);
    }

    private static String continueGame() {
        Scanner scan = new Scanner(System.in);
        String input;
        System.out.print("Do you want to start a game?\nWrite either 'exit' or 'continue' to continue the game:");
        do {
            input = scan.nextLine();
            if (!input.equals("continue") && !input.equals("exit")) {
                System.out.print("Wrong Input! Try again:");
            }
        } while (!input.equals("continue") && !input.equals("exit"));
        return input;
    }

    private static void prepareRound(CardDealer dealer, Player p) {
        dealer.setCardDeckOfDealer(new CardDeck().getCardDeck());
        dealer.restockDealerHand();
        dealer.dealCards(p, 2);
        p.showAllCards();
        //System.out.println("The Dealer has dealt all cards.\n");
    }

    private static boolean hasSomeoneBJ(CardDealer dealer, Player p, Token t) {
        if (dealer.is21() || p.is21()) {
            if (dealer.getCardsValue() == p.getCardsValue()) {
                dealer.showAllCards();
                System.out.println("Both, Dealer and " + p.getName() + " have a 'BlackJack'.\nIt is a Draw.");
                t.resetBet();
            } else if (dealer.getCardsValue() > p.getCardsValue()) {
                dealer.showAllCards();
                System.out.println("The Dealer wins with a 'BlackJack'.");
                t.loseBet();
            } else {
                System.out.println("The Player wins with a 'BlackJack'");
                t.wonBlackJack();
            }
            System.out.println();
            return true;
        }
        return false;
    }

    private static boolean startRound(CardDealer dealer, Player p1, Token t) {
        char status = 'h';
        while (status == 'h') {
            status = playerOptions(dealer, p1);
            if (status == 'b') {
                System.out.println("Player: " + p1.getName() + " got 'Busted'!\n");
                t.loseBet();
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

    private static void getResults(CardDealer dealer, Player p, Token t) {
        dealer.drawUntil17();
        if (dealer.isBusted()) {
            System.out.println("Dealer overdraw!(" + dealer.getCardsValue() + ")\n");
            t.wonBet();
            return;
        }
        winCondition(dealer, p, t);

    }

    private static void winCondition(CardDealer dealer, Player p, Token t) {
        if (dealer.getCardsValue() < p.getCardsValue()) {
            System.out.println("Player: " + p.getName() + " wins the round!(" + p.getCardsValue() + ")\n");
            t.wonBet();
        } else if (dealer.getCardsValue() > p.getCardsValue()) {
            System.out.println("Dealer wins the round!(" + dealer.getCardsValue() + ")\n");
            t.loseBet();
        } else {
            System.out.println("It is a draw between the Dealer and the Player!\n");
            t.resetBet();
        }
    }

    private static void clearCards(CardDealer dealer, Player p) {
        dealer.clearTable();
        p.clearHand();
    }

    //private static void cleanTerminal() {}
}
