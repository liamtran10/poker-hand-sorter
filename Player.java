import java.util.*;
import java.util.ArrayList;

public class Player {

    private Game game;

    public Player(Game game) {
        this.game = game;
    }

	public boolean isRoyalFlush(ArrayList<Card> playerHand) {
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

	public boolean isStraightFlush(ArrayList<Card> playerHand) {
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


	public boolean isFourOfAKind(ArrayList<Card> playerHand) {
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

	public boolean isFullHouse(ArrayList<Card> playerHand) {
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

	public boolean isFlush(ArrayList<Card> playerHand) {
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

	public boolean isStraight(ArrayList<Card> playerHand) {
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

    public boolean isThreeOfAKind(ArrayList<Card> playerHand) {
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

    public boolean isOnePair(ArrayList<Card> playerHand) {
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

    public boolean isHighestCard(ArrayList<Card> playerHand) {
		boolean max = false;
		Collections.sort(playerHand);
		playerHand.set(0, playerHand.get(4));
		max = true;
		return max;
    }

	public boolean handleTie(ArrayList<Card> handOne, ArrayList<Card> handTwo) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		ArrayList<Integer> b = new ArrayList<Integer>();
		for (int i=0; i<handOne.size(); i++) {
			int it1 = handOne.get(i).getRank();
			int it2 = handTwo.get(i).getRank();
			if (it1!=it2){
				a.add(handOne.get(i).getRank());
				b.add(handTwo.get(i).getRank());
			}
		}
		if ((a.get(a.size()-1) > (b.get(b.size()-1)))) {
			//System.out.println("Player 1 wins.");
			return true;
		} else {
			//System.out.println("Player 2 wins.");
			return false;
		}
	}

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
