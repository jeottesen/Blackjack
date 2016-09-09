package edu.weber.cs3750.blackjackcs3750.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeremyottesen on 8/31/16.
 */
public class Hand {

    public List<Card> handCards;

    public Hand(){
        handCards = new ArrayList<>();
    }

    public int getCardCount() {
        int cardCount = 0;
        for (Card card : handCards) {
            cardCount += card.getValue();
        }
        return cardCount;
    }

    public void addCard(Card card) {
        handCards.add(card);
    }

    @Override
    public String toString() {
        String hand = "";
        for (Card card: handCards) {
            hand += card.toString() +" ";
        }
        return hand;
    }

    public ArrayList<String> toStringArrayList(){
        ArrayList<String> cardStrings = new ArrayList<>();
        for (Card card: handCards) {
            cardStrings.add(card.toString());
        }
        return cardStrings;
    }
}
