package org.kata;

public class TennisScoreCounter {

    private Score scoreA = Score.LOVE;
    private Score scoreB = Score.LOVE;
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
                System.out.println("Player " + (isAdvantageA || scoreA == Score.WIN ? "A" : "B") + " wins the game");
                return;
            }
        }
    }

    /**
     * Updates the score when Player A wins a point, handling advantage and deuce states.
     */
    private void playerAWinsPoint() {
        if ((scoreA == Score.FORTY && scoreB == Score.FORTY) && !isAdvantageA) { // Deuce scenario
            isAdvantageA = !isAdvantageB;
            isAdvantageB = false;
        } else if (isAdvantageA || (scoreA == Score.FORTY && !isAdvantageB)) { // Winning after advantage || No Deuce happens
            scoreA = Score.WIN;
        } else {
            scoreA = scoreA.next();
        }
    }

    /**
     * Updates the score when Player B wins a point, handling advantage and deuce states.
     */
    private void playerBWinsPoint() {
        if ((scoreA == Score.FORTY && scoreB == Score.FORTY) && !isAdvantageB) { // Deuce scenario
            isAdvantageB = !isAdvantageA;
            isAdvantageA = false;
        } else if (isAdvantageB || (scoreB == Score.FORTY && !isAdvantageA)) { // Winning after advantage || No Deuce happens
            scoreB = Score.WIN;
        } else {
            scoreB = scoreB.next();
        }
    }

    /**
     * Prints the current scores, showing "Adv" for players with advantage.
     */
    private void printScore() {
        String scoreAString = isAdvantageA ? "Adv" : scoreA.getLabel();
        String scoreBString = isAdvantageB ? "Adv" : scoreB.getLabel();
        System.out.println("Player A: " + scoreAString + " / Player B: " + scoreBString);
    }

    /**
     * Checks if a player has won the game.
     * @return True if either player has won, false otherwise.
     */
    private boolean isGameWon() {
        return (scoreA == Score.WIN || scoreB == Score.WIN);
    }

    public static void main(String[] args) {
        TennisScoreCounter game = new TennisScoreCounter();
        game.playGame("ABABAA");
    }
}

