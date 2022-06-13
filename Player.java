import java.util.*;
import java.util.ArrayList;

public class Player {

    private Game game;

    public Player(Game game) {
        this.game = game;
    }

	// T, J, Q, K, A in the same suit.
	public boolean isRoyalFlush(ArrayList<Card> playerHand) {
		// must be a flush, a straight, and begin with a 10
		ArrayList<Card> storeCard = new ArrayList<Card>();
		boolean rflush = false;
		boolean flush = false;
		int count = 0;
		for (int i = 0; i < playerHand.size()-1; i++) {
			if (playerHand.get(i).getSuit().equals((playerHand.get(i+1).getSuit()))){
				count++;				
			}
		}
		if (count == 4)
			flush = true;
		boolean straight = false;
		int count2 = 0;
		for (int i = 0; i < playerHand.size()-1; i++) {
			if ((playerHand.get(i+1).getRank() == (playerHand.get(i).getRank() + 1))
					|| ((playerHand.get(0).getRank() == 2) && (playerHand.get(1).getRank() == 3)
							&& (playerHand.get(2).getRank() == 4)
							&& (playerHand.get(3).getRank() == 5) && (playerHand.get(0).getRank() == 10)))
				count2++;
				storeCard.add(new Card(playerHand.get(i).getRank(), playerHand.get(i).getSuit()));
		}
		if (count2 == 4) {
			straight = true;
		}
		if ((flush == true) && (straight == true) && playerHand.get(0).getRank() == 10) {
			playerHand.set(0, storeCard.get(0));
			rflush = true;
		}
		return rflush;
	}

	// All 5 cards in consecutive value order with the same suit.
	public boolean isStraightFlush(ArrayList<Card> playerHand) {
		// must be a flush and a straight
		ArrayList<Card> storeCard = new ArrayList<Card>();
		boolean sflush = false;
		boolean flush = false;
		int count = 0;
		for (int i = 0; i < playerHand.size()-1; i++) {
			if (playerHand.get(i).getSuit().equals((playerHand.get(i+1).getSuit()))){
				count++;
			}
		}
		if (count == 4)
			flush = true;
		boolean straight = false;
		int count2 = 0;
		for (int i = 0; i < playerHand.size()-1; i++) {
			if ((playerHand.get(i+1).getRank() == (playerHand.get(i).getRank() + 1))
					|| ((playerHand.get(0).getRank() == 2) && (playerHand.get(1).getRank() == 3)
							&& (playerHand.get(2).getRank() == 4)
							&& (playerHand.get(3).getRank() == 5) && (playerHand.get(4).getRank() == 12)))
				count2++;
				storeCard.add(new Card(playerHand.get(i).getRank(), playerHand.get(i).getSuit()));
		}
		if (count2 == 4) {
			straight = true;
		}
		if ((flush == true) && (straight == true)) {
			playerHand.set(0, storeCard.get(0));
			sflush = true;
		}
		return sflush;
	}

	// Four cards of the same value.
	public boolean isFourOfAKind(ArrayList<Card> playerHand) {
		// must have four cards of the same rank
		boolean four = false;
		int count = 0;
		for (int i = 0; i < playerHand.size(); i++) {
			count = 0;
			for (int j = 0; j < playerHand.size(); j++) {
				if ((playerHand.get(i).getRank() == (playerHand.get(j).getRank())) && (i != j))
					count++;
			}
			if (count == 3) {
				playerHand.set(0, playerHand.get(i));
				four = true;
			}
		}
		return four;
	}

	// Three of a kind and a pair.
	public boolean isFullHouse(ArrayList<Card> playerHand) {
		// must contain both a pair and a triple
		boolean fullhouse = false;
		boolean onep = false;
		int count = 0;
		for (int i = 0; i < playerHand.size(); i++) {
			count = 0;
			for (int j = 0; j < playerHand.size(); j++) {
				if ((playerHand.get(i).getRank() == (playerHand.get(j).getRank())) && (i != j))
					count++;
			}
			if (count == 1)
				onep = true;
		}
		boolean triple = false;
		int count2 = 0;
		for (int i = 0; i < playerHand.size(); i++) {
			count2 = 0;
			for (int j = 0; j < playerHand.size(); j++) {
				if ((playerHand.get(i).getRank() == (playerHand.get(j).getRank())) && (i != j))
					count2++;
			}
			if (count2 == 2)
				triple = true;
		}
		if (onep == true && triple == true)
			fullhouse = true;
		return fullhouse;
	}

	// All five cards having the same suit
	public boolean isFlush(ArrayList<Card> playerHand) {
		// all cards must be of same suit
		ArrayList<Card> storeCard = new ArrayList<Card>();
		boolean flush = false;
		int count = 0;
		for (int i = 0; i < 4; i++) {
			if (playerHand.get(i).getSuit().equals((playerHand.get(i+1).getSuit())))
				count++;
				storeCard.add(new Card(playerHand.get(i).getRank(), playerHand.get(i).getSuit()));
		}
		if (count == 4)  {
			playerHand.set(0, storeCard.get(0));
			flush = true;
		}
		return flush;
	}

	// All 5 cards in consecutive value order
	public boolean isStraight(ArrayList<Card> playerHand) {
		// ranks of cards must be exactly consecutive
		// also accounts for straight where Ace is low
		ArrayList<Card> storeCard = new ArrayList<Card>();
		boolean straight = false;
		int count = 0;
		for (int i = 0; i < playerHand.size()-1; i++) {
			if ((playerHand.get(i+1).getRank() == (playerHand.get(i).getRank() + 1))
					|| ((playerHand.get(0).getRank() == 2) && (playerHand.get(1).getRank() == 3)
							&& (playerHand.get(2).getRank() == 4)
							&& (playerHand.get(3).getRank() == 5) && (playerHand.get(4).getRank() == 12)))
				count++;
				storeCard.add(new Card(playerHand.get(i).getRank(), playerHand.get(i).getSuit()));
		}
		if (count == 4) {
			playerHand.set(0, storeCard.get(0));
			straight = true;
		}
		return straight;
	}

	// Three cards of the same value.
    public boolean isThreeOfAKind(ArrayList<Card> playerHand) {
		// three of the cards must be of the same rank
		boolean triple = false;
		int count = 0;
		for (int i = 0; i < playerHand.size(); i++) {
			count = 0;
			for (int j = 0; j < playerHand.size(); j++) {
				if ((playerHand.get(i).getRank() == (playerHand.get(j).getRank())) && (i != j))
					count++;
			}
			if (count == 2) {
				playerHand.set(0, playerHand.get(i));
				triple = true;
			}
		}
		return triple;
	}

	// Two different pairs.
	public boolean isTwoPair(ArrayList<Card> playerHand) {
		// must have two pairs of cards of the same rank
		ArrayList<Card> storeCard = new ArrayList<Card>();
		boolean twop = false;
		int count = 0;
		for (int i = 0; i < playerHand.size()-1; i++) {
			if (playerHand.get(i).getRank() == (playerHand.get(i+1).getRank()))
				count++;
				storeCard.add(new Card(playerHand.get(i).getRank(), playerHand.get(i).getSuit()));
		}

		if (count == 2) {
			playerHand.set(0, storeCard.get(3));
			twop = true;
		}
		return twop;
	}

	// Two cards of the same value.
    public boolean isOnePair(ArrayList<Card> playerHand) {
		// must have two cards of the same rank
        boolean onep = false;
        int count = 0;
        for (int i=0; i<playerHand.size(); i++) {
            count = 0;
            for (int j=0; j<playerHand.size(); j++) {
                if ((playerHand.get(i).getRank() == (playerHand.get(j).getRank())) && (i != j))
					count++;
            }
            if (count == 1) {
				playerHand.set(0, playerHand.get(i));
                onep = true;
            }
        }
        return onep;
    }

	// Highest value card.
    public boolean isHighestCard(ArrayList<Card> playerHand) {
		boolean max = false;
		Collections.sort(playerHand);
		playerHand.set(0, playerHand.get(4));
		max = true;
		return max;
    }

	// Read card ranks as strings and return as integers.
	public int readRank(String cardRank) {
		switch (cardRank) {
			case "3": return 3;
			case "4": return 4;
			case "5": return 5;
			case "6": return 6;
			case "7": return 7;
			case "8": return 8;
			case "9": return 9;
			case "T": return 10;
			case "J": return 11;
			case "Q": return 12;
			case "K": return 13;
			case "A": return 14;
			default: return 2;
		}
	}
}
