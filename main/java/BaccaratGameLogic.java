import java.util.ArrayList;
public class BaccaratGameLogic {

    // creating a whoWon class that takes in an ArrayList of both the player's and the banker's
    // cards and returns a string of who won
    public String whoWon(ArrayList<Card> hand1,ArrayList<Card> hand2) {
        int hand1TotalScore = handTotal(hand1);
        int hand2TotalScore = handTotal(hand2);
        if(hand1TotalScore > hand2TotalScore) {
            return "Player";
        }
        else if(hand1TotalScore < hand2TotalScore) {
            return "Banker";
        }
        else if(hand1TotalScore == hand2TotalScore){
            return "Tie";
        }
        return "";
    }

    // calculating the total score based on the cards the player or the banker has
    public int handTotal (ArrayList<Card> hand) {
        int totalScore = 0;
        for(Card card : hand) {
            totalScore += card.getValue();
        }
        if(totalScore > 9) {
            totalScore = totalScore % 10;
        }
        return totalScore;
    }

    // evaluating whether the banker should draw another card based on the banker's current cards and the player's card
    public boolean evaluateBankerDraw (ArrayList<Card> hand, Card playerCard) {
        int bankerTotalScore = handTotal(hand);
        if(bankerTotalScore == 6 && playerCard.getValue() >= 6 && playerCard.getValue() <= 7) {
            return true;
        }
        if(bankerTotalScore == 5 && playerCard.getValue() >= 4 && playerCard.getValue() <= 7) {
            return true;
        }
        if(bankerTotalScore == 4 && playerCard.getValue() >= 2 && playerCard.getValue() <= 7) {
            return true;
        }
        if(bankerTotalScore == 3 && playerCard.getValue() != 8) {
            return true;
        }
        if(bankerTotalScore <= 2) {
            return true;
        }
        return false;
    }

    // evaluating whether the banker should draw another card based on the player's current cards
    public boolean evaluatePlayerDraw (ArrayList<Card> hand) {
        int playerTotalScore = handTotal(hand);
        if(playerTotalScore <= 5) {
            return true;
        }
        return false;
    }
}