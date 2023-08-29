public class Token {
    private int budget;
    private int activeBet;

    public Token(int budget) {
        this.budget = budget;
    }

    public void resetBet() {
        budget += activeBet;
        activeBet = 0;
    }


    public void loseBet() {
        activeBet = 0;
    }

    public void wonBet() {
        budget += activeBet * 2;
        activeBet = 0;
    }

    public void wonBlackJack() {
        budget += activeBet + (activeBet * 3) / 2;
        activeBet = 0;
    }

    public int getBudget() {
        return budget;
    }

    public int getActiveBet() {
        return activeBet;
    }

    public void setActiveBet(int activeBet) {
        budget -= activeBet;
        this.activeBet += activeBet;
        if (budget < 0) {
            throw new IllegalAccessError("Error: budget is negative in class 'Token'!");
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
