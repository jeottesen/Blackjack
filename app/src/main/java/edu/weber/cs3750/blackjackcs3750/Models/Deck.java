package edu.weber.cs3750.blackjackcs3750.Models;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by sethalumps on 9/7/2016.
 */
public class Deck {

    private ArrayList<Card> deckArrayList = new ArrayList<>();
    private Card[] inOrderDeck;
    private int topCardPosition = 0;

    public Deck(){
        inOrderDeck = new Card[] {new Card(CardValues.ACE,CardSuits.SPADES),new Card(CardValues.ACE,CardSuits.SPADES),
                new Card(CardValues.ACE,CardSuits.SPADES),new Card(CardValues.TWO,CardSuits.SPADES),
                new Card(CardValues.THREE,CardSuits.SPADES),new Card(CardValues.FOUR,CardSuits.SPADES),
                new Card(CardValues.FIVE,CardSuits.SPADES),new Card(CardValues.SIX,CardSuits.SPADES),
                new Card(CardValues.SEVEN,CardSuits.SPADES),new Card(CardValues.EIGHT,CardSuits.SPADES),
                new Card(CardValues.NINE,CardSuits.SPADES),new Card(CardValues.TEN,CardSuits.SPADES),
                new Card(CardValues.JACK,CardSuits.SPADES),new Card(CardValues.QUEEN,CardSuits.SPADES),
                new Card(CardValues.KING,CardSuits.SPADES),new Card(CardValues.ACE,CardSuits.HEARTS),
                new Card(CardValues.TWO,CardSuits.HEARTS), new Card(CardValues.THREE,CardSuits.HEARTS),
                new Card(CardValues.FOUR,CardSuits.HEARTS),new Card(CardValues.FIVE,CardSuits.HEARTS),
                new Card(CardValues.SIX,CardSuits.HEARTS),new Card(CardValues.SEVEN,CardSuits.HEARTS),
                new Card(CardValues.EIGHT,CardSuits.HEARTS),new Card(CardValues.NINE,CardSuits.HEARTS),
                new Card(CardValues.TEN,CardSuits.HEARTS),new Card(CardValues.JACK,CardSuits.HEARTS),
                new Card(CardValues.QUEEN,CardSuits.HEARTS),new Card(CardValues.KING,CardSuits.HEARTS),
                new Card(CardValues.ACE,CardSuits.CLUBS),new Card(CardValues.TWO,CardSuits.CLUBS),
                new Card(CardValues.THREE,CardSuits.CLUBS),new Card(CardValues.FOUR,CardSuits.CLUBS),
                new Card(CardValues.FIVE,CardSuits.CLUBS),new Card(CardValues.SIX,CardSuits.CLUBS),
                new Card(CardValues.SEVEN,CardSuits.CLUBS),new Card(CardValues.EIGHT,CardSuits.CLUBS),
                new Card(CardValues.NINE,CardSuits.CLUBS),new Card(CardValues.TEN,CardSuits.CLUBS),
                new Card(CardValues.JACK,CardSuits.CLUBS),new Card(CardValues.QUEEN,CardSuits.CLUBS),
                new Card(CardValues.KING,CardSuits.CLUBS),new Card(CardValues.ACE,CardSuits.DIAMONDS),
                new Card(CardValues.TWO,CardSuits.DIAMONDS), new Card(CardValues.THREE,CardSuits.DIAMONDS),
                new Card(CardValues.FOUR,CardSuits.DIAMONDS),new Card(CardValues.FIVE,CardSuits.DIAMONDS),
                new Card(CardValues.SIX,CardSuits.DIAMONDS),new Card(CardValues.SEVEN,CardSuits.DIAMONDS),
                new Card(CardValues.EIGHT,CardSuits.DIAMONDS),new Card(CardValues.NINE,CardSuits.DIAMONDS),
                new Card(CardValues.TEN,CardSuits.DIAMONDS),new Card(CardValues.JACK,CardSuits.DIAMONDS),
                new Card(CardValues.QUEEN,CardSuits.DIAMONDS),new Card(CardValues.KING,CardSuits.DIAMONDS)};
        for(int i = 0; i < 52; i++){
            deckArrayList.add(inOrderDeck[i]);
        }
        shuffle();
        resetTopCard();
    }

    public void shuffle(){
        Collections.shuffle(deckArrayList);
        resetTopCard();
    }

    public Card getTopCard(){
        Card topCard = deckArrayList.get(topCardPosition);
        topCardPosition++;
        return topCard;
    }

    public void resetTopCard(){
        topCardPosition = 0;
    }
}
