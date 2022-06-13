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
        while(g.createHands() == false) {
         //   g.createHands();
            g.sortHands();
            //g.displayHand();
            g.determineWinner();
        }
        System.out.println("Player 1: " + g.p1WinCounter);
        System.out.println("Player 2: " + g.p2WinCounter);
    }

    private String userInput() {
        System.out.print("");
        return In.nextLine();
    }

    public String[] getHandAsArray(String input) {
        String removeSpaceInInput = input.replace(" ", "");
        String[] handArray = removeSpaceInInput.split("");
		return handArray;
	}

    public String[] getPlayerOneHand(String[] arr) {
		String[] p1hand = Arrays.copyOfRange(arr, 0, arr.length/2);
		return p1hand;
	} 

	public String[] getPlayerTwoHand(String[] arr) {
		String[] p2hand = Arrays.copyOfRange(arr, arr.length/2, arr.length);
		return p2hand;
	} 

    // private void createHands(){
    //     String[] handAsArray = getHandAsArray(userInput());
    //     String[] p1HandAsArray = getPlayerOneHand(handAsArray);
    //     String[] p2HandAsArray = getPlayerTwoHand(handAsArray);

    //     for (int i=0; i<p1HandAsArray.length; i+=2) {
    //         playerOneHand.add(new Card(player.readRank(p1HandAsArray[i]), p1HandAsArray[i+1]));
    //     }
    //     for (int i=0; i<p2HandAsArray.length; i+=2) {
    //         playerTwoHand.add(new Card(player.readRank(p2HandAsArray[i]), p2HandAsArray[i+1]));
    //     }

    // }

    private boolean createHands(){
        boolean exitGame = false;
        playerOneHand.clear();
        playerTwoHand.clear();
        String[] handAsArray = getHandAsArray(userInput());
        //System.out.println(handAsArray.length);
        if (handAsArray.length == 1) {
            exitGame = true;
        } else {
            String[] p1HandAsArray = getPlayerOneHand(handAsArray);
            String[] p2HandAsArray = getPlayerTwoHand(handAsArray);
    
            for (int i=0; i<p1HandAsArray.length; i+=2) {
                playerOneHand.add(new Card(player.readRank(p1HandAsArray[i]), p1HandAsArray[i+1]));
            }
            for (int i=0; i<p2HandAsArray.length; i+=2) {
                playerTwoHand.add(new Card(player.readRank(p2HandAsArray[i]), p2HandAsArray[i+1]));
            }
        }
        return exitGame;
    }

    private void sortHands() {
        Collections.sort(playerOneHand);
        Collections.sort(playerTwoHand);
    }

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

    // private void displayHand() {
    //     System.out.println("===========");
    //     for (Card c : playerOneHand) {
    //         System.out.println(c.createCard());
    //     }
    //     System.out.println("-----");
    //     for (Card c : playerTwoHand) {
    //         System.out.println(c.createCard());
    //     }
    //     System.out.println("===========");
    // }

    private void determineWinner() {
        int playerOneResult = evaluateHand(playerOneHand);
        int playerTwoResult = evaluateHand(playerTwoHand);

        // player 1 
        if (playerOneResult > playerTwoResult) {
            //System.out.println("Player 1 wins.");
            p1WinCounter++;
            return;
        } 
        
        if (playerTwoResult > playerOneResult){
            //System.out.println("Player 2 wins");
            p2WinCounter++;
            return;
        }

        if (playerOneResult == playerTwoResult) {
            if (playerOneHand.get(0).getRank() > playerTwoHand.get(0).getRank()) {
                //System.out.println("Player 1 wins.");
                p1WinCounter++;
                return;
            } else if (playerTwoHand.get(0).getRank() > playerOneHand.get(0).getRank()) {
                //System.out.println("Player 2 wins.");
                p2WinCounter++;
                return;
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
