package edu.weber.cs3750.blackjackcs3750.Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jeremyottesen on 8/31/16.
 */
public class Hand {

    private List<Card> handCards;

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

    public HandStatus getHandStatus() {
        int cardCount = getCardCount();

        if (cardCount > 21)
            return HandStatus.BUST;
        if (cardCount == 21) {
            // a natural is a blackjack made of two cards and ace and a face card
            if (handCards.size() == 2)
                return HandStatus.NATURAL;
            else
                return HandStatus.BLACKJACK;
        }

        // cards less than 21 are safe
        return HandStatus.SAFE;
    }

    public void addCard(Card card) {
        handCards.add(card);
    }
    public void removeAllCards(){ handCards.clear(); }

    @Override
    public String toString() {
        String hand = "";
        for (Card card: handCards) {
            hand += card.toString() +" ";
        }
        return hand;
    }
}
