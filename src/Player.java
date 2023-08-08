public class Player extends CardOwner{
    private final String name;
    public Player(String name) {
        this.name = name;
    }
    public void clearHand() {cards.clear();}
    public String getName() {
        return name;
    }

    public void showAllCards() {
        System.out.print("Players Cards: ");
        super.showAllCards();
    }
}
