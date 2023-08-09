public class Token {
    private int budget;
    private int activeBet;

    public Token(int budget) {
        this.budget = budget;
    }

    public int getBudget() {
        return budget;
    }

    public void changeBudget(int tokens) {
        budget+=tokens;
        if(budget<0){
            budget=0;
        }
    }
    public int getActiveBet() {
        return activeBet;
    }

    public void setActiveBet(int activeBet) {
        this.activeBet = activeBet;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
