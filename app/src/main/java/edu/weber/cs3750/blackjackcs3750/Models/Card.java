package edu.weber.cs3750.blackjackcs3750.Models;

/**
 * Created by jeremyottesen on 8/31/16.
 */
public class Card {
    private CardValues value;
    private CardSuits suit;


    public Card(CardValues v, CardSuits s) {
        value = v;
        suit = s;
    }

    public char getSuit() {
        return suit.getValue();
    }

    public int getValue() {
        return value.getValue();
    }

    @Override
    public String toString() {
        return (value.getValue() + " " + suit.getValue());
    }
}
