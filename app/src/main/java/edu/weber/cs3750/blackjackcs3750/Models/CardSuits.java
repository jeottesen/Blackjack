package edu.weber.cs3750.blackjackcs3750.Models;

/**
 * Created by jeremyottesen on 8/31/16.
 */
public enum CardSuits {
    SPADES('S'),
    HEARTS('H'),
    DIAMONDS('D'),
    CLUBS('C');

    private final char value;

    CardSuits(final char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

}
