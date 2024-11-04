package org.kata;

public class TennisScoreCounter {

    private int scoreA = 0;
    private int scoreB = 0;

    /**
     * Plays a game of tennis based on a sequence of points won by players A and B.
     * @param sequence A string of 'A' and 'B' characters indicating the points won by each player.
     */
    public void playGame(String sequence) {
        for (char point : sequence.toCharArray()) {
            if (point == 'A') {
                scoreA = nextScore(scoreA);
            } else if (point == 'B') {
                scoreB = nextScore(scoreB);
            }

            printScore();

            // Check if the game has been won
            if (isGameWon(scoreA, scoreB)) {
                System.out.println("Player " + (scoreA > scoreB ? "A" : "B") + " wins the game");
                return;
            }
        }
    }

    private int nextScore(int score) {
        if (score < 30) return score + 15;
        else return 40;
    }

    /**
     * Prints the current scores of both players.
     */
    private void printScore() {
        System.out.println("Player A: " + (scoreA == 0 ? "0" : scoreA) + " / Player B: " + (scoreB == 0 ? "0" : scoreB));
    }

    /**
     * Checks if a player has won the game.
     * @param scoreA The current score of Player A.
     * @param scoreB The current score of Player B.
     * @return True if either player has won, false otherwise.
     */
    private boolean isGameWon(int scoreA, int scoreB) {
        return (scoreA == 40 && scoreB < 40) || (scoreB == 40 && scoreA < 40);
    }

    public static void main(String[] args) {
        TennisScoreCounter game = new TennisScoreCounter();
        game.playGame("ABABAA");
    }
}

