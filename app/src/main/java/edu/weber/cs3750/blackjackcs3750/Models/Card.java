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

    public String getSuit() {
        return suit.getValue();
    }

    public int getValue() {
        return value.getValue();
    }


    /*
    Geese:  changing the toString() values so that they match the names of the images.
     */
    @Override
    public String toString() {
        String string;
        switch (value) {
            /*case BACK:
                string = "card_back";
                break;*/
            case ACE:
            //case ONE:
                string = "ace" + "_" + suit.getValue();
                break;
            case JACK:
                string = "jack" + "_"+ suit.getValue();
                break;
            case QUEEN:
                string = "queen" + "_"+ suit.getValue();
                break;
            case KING:
                string = "king" + "_" + suit.getValue();
                break;
            case TWO:
                string = "two" + "_" + suit.getValue();
                break;
            case THREE:
                string = "three" + "_" + suit.getValue();
                break;
            case FOUR:
                string = "four" + "_" + suit.getValue();
                break;
            case FIVE:
                string = "five" + "_" + suit.getValue();
                break;
            case SIX:
                string = "six" + "_" + suit.getValue();
                break;
            case SEVEN:
                string = "seven" + "_" + suit.getValue();
                break;
            case EIGHT:
                string = "eight" + "_" + suit.getValue();
                break;
            case NINE:
                string = "nine" + "_" + suit.getValue();
                break;
            case TEN:
                string = "ten" + "_" + suit.getValue();
                break;
            default:
                string = value.getValue() + "_" + suit.getValue();
        }
        return string;
    }
}
