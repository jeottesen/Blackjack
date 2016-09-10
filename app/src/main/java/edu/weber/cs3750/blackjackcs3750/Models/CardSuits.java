package edu.weber.cs3750.blackjackcs3750.Models;

/**
 * Created by jeremyottesen on 8/31/16.
 */
public enum CardSuits {
    /*SPADES('\u2660'),
    HEARTS('\u2665'),
    DIAMONDS('\u2666'),
    CLUBS('\u2663');*/

    //BACK("back"), //added by Gisela, not sure it'll be necessary
    SPADES("spades"),
    HEARTS("hearts"),
    DIAMONDS("diamonds"),
    CLUBS("clubs");

    private final String value;

    CardSuits(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
