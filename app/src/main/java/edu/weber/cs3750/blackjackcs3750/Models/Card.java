package edu.weber.cs3750.blackjackcs3750.Models;

/**
 * Created by jeremyottesen on 8/31/16.
 */
public class Card {
    private CardValues value;
    private CardSuits suit;
    private boolean faceDown;

    public Card(CardValues v, CardSuits s) {
        value = v;
        suit = s;
    }

    public void setFacedown(boolean faceDown){
        this.faceDown = faceDown;
    }

    public char getSuit() {
        return suit.getValue();
    }

    public int getValue() {
        if (faceDown)
            return 0;
        else
            return value.getValue();
    }

    @Override
    public String toString() {
        String string;
        switch (value) {
            case ACE:
                string = "A" + suit.getValue();
                break;
            case JACK:
                string = "J" + suit.getValue();
                break;
            case QUEEN:
                string = "Q" + suit.getValue();
                break;
            case KING:
                string = "K" + suit.getValue();
                break;
            default:
                string = value.getValue() + " " + suit.getValue();
        }
        return string;
    }

}
