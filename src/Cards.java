public class Cards {
    String cardNumber;
    String cardSign;

    public Cards(String number, String sign) {
        this.cardNumber = number;
        this.cardSign = sign;
    }


    public static String getCardNumber(Integer i) {
        i = i % 13;
        if (i == 0) return "King";
        if (i == 1) return "Ace";
        if (i < 11) return i.toString();
        if (i == 11) return "Jack";
        return "Queen";
    }

    public static String getCardSign(int i) {
        if (i < 14) return "Hearts";
        if (i < 27) return "Clubs";
        if (i > 40) return "Diamonds";

        return "Spades";
    }

    @Override
    public String toString() {
        // return "["+cardNumber+" "+cardSign+"]";
        return cardNumber + " " + cardSign;
    }
}
