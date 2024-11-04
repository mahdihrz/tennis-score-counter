package org.kata;

/**
 * Enum representing tennis scores, functioning similarly to a State pattern.
 * Each score has a next state.
 */
public enum Score {
    LOVE("0"), FIFTEEN("15"), THIRTY("30"), FORTY("40"), WIN("Win");

    private final String label;

    Score(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    /**
     * Moves to the next score state.
     * @return The next score in progression.
     */
    public Score next() {
        return switch (this) {
            case LOVE -> FIFTEEN;
            case FIFTEEN -> THIRTY;
            case THIRTY -> FORTY;
            case FORTY -> WIN;
            default -> this;
        };
    }
}
