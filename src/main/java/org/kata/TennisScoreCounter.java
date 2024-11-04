package org.kata;

public class TennisScoreCounter {

    private int scoreA = 0;
    private int scoreB = 0;
    private boolean isAdvantageA = false;
    private boolean isAdvantageB = false;

    /**
     * Plays a game of tennis based on a sequence of points won by players A and B, managing deuce and advantage states.
     * @param sequence A string of 'A' and 'B' characters indicating the points won by each player.
     */
    public void playGame(String sequence) {
        for (char point : sequence.toCharArray()) {
            if (point == 'A') {
                playerAWinsPoint();
            } else if (point == 'B') {
                playerBWinsPoint();
            }

            if (!isGameWon()) {
                printScore();
            } else {
                System.out.println("Player " + (isAdvantageA || scoreA > scoreB ? "A" : "B") + " wins the game");
                return;
            }
        }
    }

    /**
     * Updates the score when Player A wins a point, handling advantage and deuce states.
     */
    private void playerAWinsPoint() {
        if ((scoreA == 40 && scoreB == 40) && !isAdvantageA) { // Deuce scenario
            isAdvantageA = !isAdvantageB;
            isAdvantageB = false;
        } else if (isAdvantageA || (scoreA == 40 && !isAdvantageB)) { // Winning after advantage || No Deuce happens
            scoreA = 50; // Arbitrary winning score
        } else {
            scoreA = nextScore(scoreA);
        }
    }

    /**
     * Updates the score when Player B wins a point, handling advantage and deuce states.
     */
    private void playerBWinsPoint() {
        if ((scoreA == 40 && scoreB == 40) && !isAdvantageB) { // Deuce scenario
            isAdvantageB = !isAdvantageA;
            isAdvantageA = false;
        } else if (isAdvantageB || (scoreB ==40 && !isAdvantageA)) { // Winning after advantage || No Deuce happens
            scoreB = 50;
        } else {
            scoreB = nextScore(scoreB);
        }
    }

    /**
     * Determines the next score for a player.
     * @param score The current score.
     * @return The updated score.
     */
    private int nextScore(int score) {
        if (score < 30) return score + 15;
        else return 40;
    }

    /**
     * Prints the current scores, showing "Adv" for players with advantage.
     */
    private void printScore() {
        String scoreAString = isAdvantageA ? "Adv" : (scoreA == 0 ? "0" : scoreA == 50 ? "Win" : Integer.toString(scoreA));
        String scoreBString = isAdvantageB ? "Adv" : (scoreB == 0 ? "0" : scoreB == 50 ? "Win" : Integer.toString(scoreB));
        System.out.println("Player A: " + scoreAString + " / Player B: " + scoreBString);
    }

    /**
     * Checks if a player has won the game.
     * @return True if either player has won, false otherwise.
     */
    private boolean isGameWon() {
        return (scoreA == 50 || scoreB == 50);
    }

    public static void main(String[] args) {
        TennisScoreCounter game = new TennisScoreCounter();
        game.playGame("ABABAA");
    }
}

