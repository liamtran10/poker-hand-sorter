public class Card implements Comparable<Card>{

    public final static int T = 10;
    public final static int J = 11;
    public final static int Q = 12;
    public final static int K = 13;
    public final static int A = 14;

    private int rank;
    private String suit; 

    public Card (int rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    // setter and getter methods for card properties
    public int getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    // compareTo method to sort cards in ascending order
    @Override
    public int compareTo(Card compareCards) {
        int compareRanks = (((Card)compareCards).getRank());
        return this.rank-compareRanks;
    }

    // return card as string
    public String createCard() {
        return getRank() + getSuit();
    }

}
