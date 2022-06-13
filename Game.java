import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

public class Game {

    private Player player;
    private ArrayList<Card> playerOneHand = new ArrayList<Card>();
    private ArrayList<Card> playerTwoHand = new ArrayList<Card>();
    private int p1WinCounter = 0;
    private int p2WinCounter = 0;

    public Game() {
        this.player = new Player(this);
    }

    public static void main(String[] args) {
        Game g = new Game();
        // handles continuous user input
        while(g.createHands() == false) {
            g.sortHands();
            g.determineWinner();
        }
        System.out.println("Player 1: " + g.p1WinCounter);
        System.out.println("Player 2: " + g.p2WinCounter);
    }

    // get user input and return as string
    private String userInput() {
        System.out.print("");
        return In.nextLine();
    }

    // methods to handle user input
    // take user input, split and store into array
    public String[] getHandAsArray(String input) {
        String removeSpaceInInput = input.replace(" ", "");
        String[] handArray = removeSpaceInInput.split("");
		return handArray;
	}

    // get first 5 elements as player1 hand
    public String[] getPlayerOneHand(String[] arr) {
		String[] p1hand = Arrays.copyOfRange(arr, 0, arr.length/2);
		return p1hand;
	} 

    // get last 5 elements as player2 hand
	public String[] getPlayerTwoHand(String[] arr) {
		String[] p2hand = Arrays.copyOfRange(arr, arr.length/2, arr.length);
		return p2hand;
	} 

    // create card objects and add into global arraylists.
    private boolean createHands(){
        boolean exitGame = false;
        playerOneHand.clear();
        playerTwoHand.clear();
        // handles continuous user input
        String[] handAsArray = getHandAsArray(userInput());
        // if there is no more input, exit the game.
        if (handAsArray.length == 1) {
            exitGame = true;
        } else {
            // first 5 elements into p1HandAsArray
            String[] p1HandAsArray = getPlayerOneHand(handAsArray);
            // last 5 elements into p2HandAsArray
            String[] p2HandAsArray = getPlayerTwoHand(handAsArray);
    
            // for loops to create card objects and append to global arraylists.
            for (int i=0; i<p1HandAsArray.length; i+=2) {
                playerOneHand.add(new Card(player.readRank(p1HandAsArray[i]), p1HandAsArray[i+1]));
            }
            for (int i=0; i<p2HandAsArray.length; i+=2) {
                playerTwoHand.add(new Card(player.readRank(p2HandAsArray[i]), p2HandAsArray[i+1]));
            }
        }
        return exitGame;
    }

    // sort hands into ascending order.
    private void sortHands() {
        Collections.sort(playerOneHand);
        Collections.sort(playerTwoHand);
    }

    // method to evaluate hand, return integer value based on combination ranking
    private int evaluateHand(ArrayList<Card> playerHand) {
        if (player.isRoyalFlush(playerHand)==true) {return 10;}
        if (player.isStraightFlush(playerHand)==true) {return 9;}
        if (player.isFourOfAKind(playerHand)==true) {return 8;}
        if (player.isFullHouse(playerHand)==true) {return 7;}
        if (player.isFlush(playerHand)==true) {return 6;}
        if (player.isStraight(playerHand)==true) {return 5;}
        if (player.isThreeOfAKind(playerHand)==true) {return 4;}
        if (player.isTwoPair(playerHand)==true) {return 3;}
        if (player.isOnePair(playerHand)==true) {return 2;}
        if (player.isHighestCard(playerHand)==true) {return 1;}
        else {return 0;}
    }

    // determine hand winner based on combination ranking.
    private void determineWinner() {
        int playerOneResult = evaluateHand(playerOneHand);
        int playerTwoResult = evaluateHand(playerTwoHand);

        // player 1 has better combination
        if (playerOneResult > playerTwoResult) {
            p1WinCounter++;
            return;
        } 

        // player 2 has better combination
        if (playerTwoResult > playerOneResult){
            p2WinCounter++;
            return;
        }

        // combinations are the same.
        if (playerOneResult == playerTwoResult) {

            // if combinations are the same, look at the card numbers instead.
            // the combinations of the cards will always be at the front of the list
            // so just compare the first elements of hand1 and hand2 and the winner is the 
            // high card number
            if (playerOneHand.get(0).getRank() > playerTwoHand.get(0).getRank()) {
                p1WinCounter++;
                return;
            } else if (playerTwoHand.get(0).getRank() > playerOneHand.get(0).getRank()) {
                p2WinCounter++;
                return;

            // if combination and card number are the same, look to highest card.
            // method in Player class.
            } else {
                if (player.handleTie(playerOneHand, playerTwoHand)) {
                    p1WinCounter++;
                } else {
                    p2WinCounter++;
                }
                return;
            }
        }
    }
}
