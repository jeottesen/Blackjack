package edu.weber.cs3750.blackjackcs3750.Models;

/**
 * Created by jeremyottesen on 8/31/16.
 */
public enum CardSuits {
    SPADES('\u2660'),
    HEARTS('\u2665'),
    DIAMONDS('\u2666'),
    CLUBS('\u2663');

    private final char value;

    CardSuits(final char value) {
        this.value = value;
    }

    //public char getValue() {
    public char getValue() {
        return value;
    }

}
