package edu.weber.cs3750.blackjackcs3750.Models;

import java.util.ArrayList;
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
        if(cardCount > 21){
            for (Card card : handCards) {
                if(card.getValue() == 11){
                    cardCount = cardCount - 10;
                }
            }
        }
        return cardCount;
    }

    public void addCard(Card card) {
        handCards.add(card);
    }
    public void removeAllCards(){handCards.removeAll(handCards);}
    @Override
    public String toString() {
        String hand = "";
        for (Card card: handCards) {
            hand += card.toString() +" ";
        }
        return hand;
    }
}
