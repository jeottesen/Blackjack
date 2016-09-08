package edu.weber.cs3750.blackjackcs3750.Models;

import java.util.ArrayList;
import java.util.Collections;

/**
* Name: Deck.java
* Author: Adam Persson
* Due Date: 6-25-2016
* Assignment: Game Of BlackJack
* Description: Expand existing classes to create the card game of war
*/

public class Deck extends GroupOfCards {
	
	// ArrayList to hold cards a deck contains
	public ArrayList<Card> deck;
		
	// Constructor
	public Deck()
	{
		deck = new ArrayList<>();
	}
	
	/**
	 * Add cards to the deck
	 */
	public void addCard(Card card)
	{
		deck.add(card);
	}
	
	/**
	 * see the number of cards in the deck
	 */
	public int deckSize()
	{
		return this.deck.size();
	}
	
	/**
	 * Clear the deck of its contents 
	 */
	public void clear()
	{
		deck.clear();
	}
	
	/**
	 * Create or initialize the deck
	 */
	public void initialize()
	{
		for (CardSuits suitName: CardSuits.values())
		{
			for (CardValues num: CardValues.values())
			{
				Card card = new Card(num, suitName);
				this.addCard(card);
			}
		}
	}
	
	/**
	 * Shuffle deck
	 */
	public void shuffle()
	{
		Collections.shuffle(deck);
	}
	
	/**
	 * verifies the deck contains a card before dealing to a hand
	 */
	public boolean giveCard(Card card, Hand hand)
	{
		if(!deck.contains(card))
		{
			return false;
		}
		else
		{
			deck.remove(card);
			hand.addCard(card);
			return true;
		}
	}
	
	/**
	 * deal single card
	 */
	public void dealCard(Hand hand)
	{
		this.giveCard(deck.get(0), hand);
	}

	/**
	 * Deal multiple cards
	 */
	public void dealCard(Hand hand, int amount)
	{
		for (int i = 0; i < amount; i++) 
		{
			this.giveCard(deck.get(0), hand);
		}
	}
	
	/**
	 * toString for returning the contents of the deck
	 */
	@Override
	public String toString()
	{
		int num = 1;
		String display = "";
		Collections.reverse(cards);
		for(Card C: cards)
		{
			display += num + ": " + C.toString() + "\n";
			num++;
		}
		System.out.print(display);
		System.out.println();
		return display;
	
	}
	
	// used with the Lambda expression
	public void split(Runnable sd) 
	{
		sd.run();
	}
	
}
