package edu.weber.cs3750.blackjackcs3750.Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Kyle Richetti on 9/8/2016.
 */
public class Deck {
    private ArrayList<Card> cards;
    private int position;

    public Deck(){
        cards = new ArrayList<Card>();
        position = 0;

        //Minimum of two decks
        addDeck();
        addDeck();
    }

    //Function to draw a card from the deck
    public Card draw(){
        //Two options:
            //Create a temp card that holds the card at position
            //Increment position
            //Return the temp card
        //Or:
            //Increment position by 1
            //Return the card at position - 1

        position++;

        //Conditional to handle the deck running out of cards
        if(position > cards.size()){
            reset();
            position++;
        }
        return cards.get(position-1);
    }

    //Function to shuffle the deck
    public void shuffle(){
        long seed = System.nanoTime();
        Collections.shuffle(cards, new Random(seed));
    }

    //Reset function to be called either on starting a new game
    //or when the deck runs out of cards
    public void reset(){
        position = 0;
        shuffle();
    }

    //This function adds a full deck of cards to the dealer deck.
    //If there are multiple players, it will be easy to add more cards
    //It seems like it should be a private function, but I'm not sure
    //that would work terribly well
    public void addDeck(){
        //Create and load in the deck
        //Better way???

        //Hearts
        cards.add( new Card(CardValues.ONE, CardSuits.HEARTS));
        cards.add( new Card(CardValues.TWO, CardSuits.HEARTS));
        cards.add( new Card(CardValues.THREE, CardSuits.HEARTS));
        cards.add( new Card(CardValues.FOUR, CardSuits.HEARTS));
        cards.add( new Card(CardValues.FIVE, CardSuits.HEARTS));
        cards.add( new Card(CardValues.SIX, CardSuits.HEARTS));
        cards.add( new Card(CardValues.SEVEN, CardSuits.HEARTS));
        cards.add( new Card(CardValues.EIGHT, CardSuits.HEARTS));
        cards.add( new Card(CardValues.NINE, CardSuits.HEARTS));
        cards.add( new Card(CardValues.TEN, CardSuits.HEARTS));
        cards.add( new Card(CardValues.JACK, CardSuits.HEARTS));
        cards.add( new Card(CardValues.QUEEN, CardSuits.HEARTS));
        cards.add( new Card(CardValues.KING, CardSuits.HEARTS));

        //Diamonds
        cards.add( new Card(CardValues.ONE, CardSuits.DIAMONDS));
        cards.add( new Card(CardValues.TWO, CardSuits.DIAMONDS));
        cards.add( new Card(CardValues.THREE, CardSuits.DIAMONDS));
        cards.add( new Card(CardValues.FOUR, CardSuits.DIAMONDS));
        cards.add( new Card(CardValues.FIVE, CardSuits.DIAMONDS));
        cards.add( new Card(CardValues.SIX, CardSuits.DIAMONDS));
        cards.add( new Card(CardValues.SEVEN, CardSuits.DIAMONDS));
        cards.add( new Card(CardValues.EIGHT, CardSuits.DIAMONDS));
        cards.add( new Card(CardValues.NINE, CardSuits.DIAMONDS));
        cards.add( new Card(CardValues.TEN, CardSuits.DIAMONDS));
        cards.add( new Card(CardValues.JACK, CardSuits.DIAMONDS));
        cards.add( new Card(CardValues.QUEEN, CardSuits.DIAMONDS));
        cards.add( new Card(CardValues.KING, CardSuits.DIAMONDS));

        //Clubs
        cards.add( new Card(CardValues.ONE, CardSuits.CLUBS));
        cards.add( new Card(CardValues.TWO, CardSuits.CLUBS));
        cards.add( new Card(CardValues.THREE, CardSuits.CLUBS));
        cards.add( new Card(CardValues.FOUR, CardSuits.CLUBS));
        cards.add( new Card(CardValues.FIVE, CardSuits.CLUBS));
        cards.add( new Card(CardValues.SIX, CardSuits.CLUBS));
        cards.add( new Card(CardValues.SEVEN, CardSuits.CLUBS));
        cards.add( new Card(CardValues.EIGHT, CardSuits.CLUBS));
        cards.add( new Card(CardValues.NINE, CardSuits.CLUBS));
        cards.add( new Card(CardValues.TEN, CardSuits.CLUBS));
        cards.add( new Card(CardValues.JACK, CardSuits.CLUBS));
        cards.add( new Card(CardValues.QUEEN, CardSuits.CLUBS));
        cards.add( new Card(CardValues.KING, CardSuits.CLUBS));

        //Spades
        cards.add( new Card(CardValues.ONE, CardSuits.SPADES));
        cards.add( new Card(CardValues.TWO, CardSuits.SPADES));
        cards.add( new Card(CardValues.THREE, CardSuits.SPADES));
        cards.add( new Card(CardValues.FOUR, CardSuits.SPADES));
        cards.add( new Card(CardValues.FIVE, CardSuits.SPADES));
        cards.add( new Card(CardValues.SIX, CardSuits.SPADES));
        cards.add( new Card(CardValues.SEVEN, CardSuits.SPADES));
        cards.add( new Card(CardValues.EIGHT, CardSuits.SPADES));
        cards.add( new Card(CardValues.NINE, CardSuits.SPADES));
        cards.add( new Card(CardValues.TEN, CardSuits.SPADES));
        cards.add( new Card(CardValues.JACK, CardSuits.SPADES));
        cards.add( new Card(CardValues.QUEEN, CardSuits.SPADES));
        cards.add( new Card(CardValues.KING, CardSuits.SPADES));
    }
}
