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
        game.playGame("AAA");
        assertTrue(getOutput().endsWith("Player A wins the game"));

        game = new TennisScoreCounter();
        game.playGame("BBBB");
        assertTrue(getOutput().endsWith("Player B wins the game"));
    }
}