package org.kata;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TennisScoreCounterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    private String getOutput() {
        String output = outContent.toString().trim();
        outContent.reset();
        return output;
    }

    @Test
    void testBasicScoring() {
        TennisScoreCounter game = new TennisScoreCounter();
        game.playGame("A");
        // Check initial score
        assertEquals("Player A: 15 / Player B: 0", getOutput());

        game = new TennisScoreCounter();
        game.playGame("AB");
        // Check final score
        assertTrue(getOutput().endsWith("Player A: 15 / Player B: 15"));
    }

    @Test
    void testGameWinningConditions() {
        TennisScoreCounter game = new TennisScoreCounter();
        game.playGame("AAAA");
        assertTrue(getOutput().endsWith("Player A wins the game"));

        game = new TennisScoreCounter();
        game.playGame("BBBB");
        assertTrue(getOutput().endsWith("Player B wins the game"));
    }

    @Test
    void testDeuceScenario() {
        TennisScoreCounter game = new TennisScoreCounter();
        game.playGame("AAABBB"); // Both players should reach deuce at this point
        assertTrue(getOutput().endsWith("Player A: 40 / Player B: 40"));
    }

    @Test
    void testAdvantageAndBackToDeuce() {
        TennisScoreCounter game = new TennisScoreCounter();
        game.playGame("AAABBBAB"); // Player A gains advantage, then back to deuce
        String output = getOutput();
        assertTrue(output.contains("Player A: Adv / Player B: 40"));
        assertTrue(output.endsWith("Player A: 40 / Player B: 40"));

        game = new TennisScoreCounter();
        game.playGame("AAABBBABBA"); // Player B gains advantage, then back to deuce
        output = getOutput();
        assertTrue(output.contains("Player A: 40 / Player B: Adv"));
        assertTrue(output.endsWith("Player A: 40 / Player B: 40"));
    }

    @Test
    void testWinningFromAdvantage() {
        TennisScoreCounter game = new TennisScoreCounter();
        game.playGame("AAABBBAA"); // Player A wins after gaining advantage
        String output = getOutput();
        assertTrue(output.contains("Player A: Adv / Player B: 40"));
        assertTrue(output.endsWith("Player A wins the game"));
    }
}