import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Game {
    /*///---------------------------------------------------------------------------------------------
    //Parameter:    -
    //Return value: -
    //Description:  Controls the game-loop
    /*///---------------------------------------------------------------------------------------------
    public static void startGame() {
        Token token = new Token(100); //starting-budget
        Player p1 = new Player("Alen");
        CardDealer dealer = new CardDealer();
        String exitCondition;

        do {
            setBet(token);
            prepareRound(dealer, p1);
            if (!hasSomeoneBJ(dealer, p1, token)) { //testing for BlackJack (3:2 payout)
                if (startRound(dealer, p1, token)) {
                    getResults(dealer, p1, token);
                }
            }
            if (token.getBudget() < 10) {
                break;
            }
            clearCards(dealer, p1);
            exitCondition = continueGame();
        } while (exitCondition.equals("continue"));
        endScreen(token);
    }
    /*///---------------------------------------------------------------------------------------------
    //Parameter:    Tokens from the player
    //Return value: -
    //Description:  Requests a bet
    /*///---------------------------------------------------------------------------------------------
    private static void setBet(Token t) throws RuntimeException {
        Scanner scan = new Scanner(System.in);
        int input;
        System.out.println("Your budget: " + t.getBudget());
        do {
            System.out.print("Your bet:");
            try {
                input = scan.nextInt();
            }catch (InputMismatchException ex){
                throw new RuntimeException("Wrong input in function 'setBet()'!");
            }
            if (input > t.getBudget()) {
                System.out.print("Your bet can not be higher than your budget!\n");
            } else if (input < 0) {
                System.out.print("Your bet can not be negative!\n");
            } else if (input < 10) {
                System.out.print("Your bet must be at least 10 tokens high!\n");
            }
        } while (input < 0 || input > t.getBudget() || input < 10);
        t.setActiveBet(input);
    }
    /*///---------------------------------------------------------------------------------------------
    //Parameter:    Dealer, Player (to deal cards)
    //Return value: -
    //Description:  Dealer deals cards to player
    /*///---------------------------------------------------------------------------------------------
    private static void prepareRound(CardDealer dealer, Player p) {
        dealer.setCardDeckOfDealer(new CardDeck().getCardDeck());
        dealer.restockDealerHand();
        dealer.dealCards(p, 2);
        p.showAllCards();
        //System.out.println("The Dealer has dealt all cards.\n");
    }
    /*///---------------------------------------------------------------------------------------------
    //Parameter:    -
    //Return value: String (exit/continue) to decide if the game ends or not
    //Description:  Option to continue with game
    /*///---------------------------------------------------------------------------------------------
    private static String continueGame() {
        Scanner scan = new Scanner(System.in);
        String input;
        System.out.print("Do you want to continue the game?\nWrite either 'exit' or 'continue':");
        do {
            input = scan.nextLine();
            if (!input.equals("continue") && !input.equals("exit")) {
                System.out.print("Wrong Input! Try again:");
            }
        } while (!input.equals("continue") && !input.equals("exit"));
        return input;
    }
    /*///---------------------------------------------------------------------------------------------
    //Parameter:    Dealer, Player, Players Tokens (to check their cards and lose or win the bet)
    //Return value: boolean (yes->one of them has BJ, no-> none of them have BJ)
    //Description:  Control if someone has a BlackJack
    /*///---------------------------------------------------------------------------------------------
    private static boolean hasSomeoneBJ(CardDealer dealer, Player p, Token t) {
        if (dealer.is21() || p.is21()) { //if someone has 21 at the beginning of the game it's a BJ
            if (dealer.getCardsValue() == p.getCardsValue()) { //testing a BJ draw
                dealer.showAllCards();
                System.out.println("Both, Dealer and " + p.getName() + " have a 'BlackJack'.\nIt is a Draw.");
                t.resetBet();
            } else if (dealer.getCardsValue() > p.getCardsValue()) {
                dealer.showAllCards();
                System.out.println("The Dealer wins with a 'BlackJack'.");
                t.loseBet();
            } else {
                dealer.showAllCards();
                System.out.println("The Player wins with a 'BlackJack'");
                t.wonBlackJack();
            }
            System.out.println();
            return true;
        }
        return false;
    }
    /*///---------------------------------------------------------------------------------------------
    //Parameter:    Dealer, Player, Token (to redirect it to other functions)
    //Return value: boolean to decide whether to continue with players options or not
    //Description:  controls the actions of the player
    /*///---------------------------------------------------------------------------------------------
    private static boolean startRound(CardDealer dealer, Player p1, Token t) {
        char status = 'x';
        do {
            status = playerOptions(dealer, p1, status, t);

            if (status == 'b') {
                System.out.println("Player: " + p1.getName() + " is 'Busted'!\n");
                t.loseBet();
                return false;
            }
        } while (status == 'h');
        return true;
    }
    /*///---------------------------------------------------------------------------------------------
    //Parameter:    Dealer deals cards (hit or double), Player choose an option, Token
    //Return value: char is the option of the player
    //Description:  Acts on the actions of the player
    /*///---------------------------------------------------------------------------------------------
    private static char playerOptions(CardDealer dealer, Player p, char status, Token t) throws IllegalArgumentException {
        if (status == 'x' && t.getBudget() >= t.getActiveBet()) { //'x' means it's the players first turn (double only allowed in the first turn)
            System.out.println("Do you want to hit(h), stay(s) or double(d):");
        } else {
            System.out.println("Do you want to hit(h) or stay(s):");
        }
        Scanner scan = new Scanner(System.in);
        char input = scan.next().trim().charAt(0);
        if (Objects.equals(input, 'h')) {
            if (playerHit(dealer, p)) {
                return 'b';
            }
            return 'h';
        } else if (Objects.equals(input, 's')) {
            return 's';
        } else if (Objects.equals(input, 'd') && status == 'x' && t.getBudget() >= t.getActiveBet()) {
            t.setActiveBet(t.getActiveBet());
            System.out.println("Player: " + p.getName() + " has 'Doubled'!\n");
            if (playerHit(dealer, p)) {
                return 'b';
            }
            return 'd';
        } else {
            throw new IllegalArgumentException("Wrong Input at 'playerOptions()'");
        }
    }
    /*///---------------------------------------------------------------------------------------------
    //Parameter:    Dealer deals cards, Player (functions to use his cards)
    //Return value: boolean (yes->player is busted, no-> player is not busted)
    //Description:  Control if player has busted after choose the option hit (means he has a card value over 21)
    /*///---------------------------------------------------------------------------------------------
    private static boolean playerHit(CardDealer dealer, Player p) {
        dealer.dealCards(p, 1);
        p.showAllCards();
        return p.isBusted();
    }
    /*///---------------------------------------------------------------------------------------------
    //Parameter:    Dealer draws cards, Player (to check their card value), Token
    //Return value: -
    //Description:  Dealer draws (overdraw->dealer has a card value over 21), get the results (who won), get payed (if won)
    /*///---------------------------------------------------------------------------------------------
    private static void getResults(CardDealer dealer, Player p, Token t) {
        dealer.drawUntil17();
        if (dealer.isBusted()) {
            System.out.println("Dealer overdraw!(" + dealer.getCardsValue() + ")\n");
            t.wonBet();
            return;
        }
        winCondition(dealer, p, t);
    }
    /*///---------------------------------------------------------------------------------------------
    //Parameter:    Dealer, Player (to check their cards), Token to win or lose players tokens
    //Return value: -
    //Description:  Prints who won
    /*///---------------------------------------------------------------------------------------------
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
    /*///---------------------------------------------------------------------------------------------
    //Parameter:    Dealer, Player (to get access to their hands)
    //Return value: -
    //Description:  Dealer and Players hands get removed
    /*///---------------------------------------------------------------------------------------------
    private static void clearCards(CardDealer dealer, Player p) {
        dealer.clearTable();
        p.clearHand();
    }
    //private static void cleanTerminal() {}
    /*///---------------------------------------------------------------------------------------------
    //Parameter:    Token to show the amount of Tokens (if won)
    //Return value: -
    //Description:  End-Screen of the game
    /*///---------------------------------------------------------------------------------------------
    private static void endScreen(Token token) {
        if (token.getBudget() <= 0) {
            System.out.println("You lost the game");
        } else {
            System.out.println("You quit the game of a total budget of: " + token.getBudget());
        }
        System.out.println("Thank you for playing my game!");
    }
}
