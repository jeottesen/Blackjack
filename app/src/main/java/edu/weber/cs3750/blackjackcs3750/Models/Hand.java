package edu.weber.cs3750.blackjackcs3750.Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jeremyottesen on 8/31/16.
 */
public class Hand {

    public List<Card> handCards;

    public Hand(){
        handCards = new ArrayList<>();
    }


    /*  If the hand has an Ace and the hand's total value comes to
        greater than 21, change the ace's value to 1 (by subtracting ten
        from the hand's value)   --Adam
     */
    public int getCardCount() {
        boolean hasAce = false;
        int cardCount = 0;
        for (Card card : handCards) {
            if (card.toString().contains("ace")){hasAce = true;}
            cardCount += card.getValue();
        }
        if (hasAce && cardCount > 21){
            cardCount -= 10;
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

    public ArrayList<String> toStringArrayList(){
        ArrayList<String> cardStrings = new ArrayList<>();
        for (Card card: handCards) {
            cardStrings.add(card.toString());
        }
        return cardStrings;
    }

    public Card getCard(int index){
        Card theCard = handCards.get(index);
        return theCard;
    }
}
