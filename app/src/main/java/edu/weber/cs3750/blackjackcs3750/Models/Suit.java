package edu.weber.cs3750.blackjackcs3750.Models;

/**
* Name: Suit.java
* Author: Adam Persson
* Due Date: 8-18-16
* Assignment: Game Of BlackJack
* Description: Game to include in final graphics application
*/

public enum Suit {
	
	CLUBS("Clubs", 0), 
	HEARTS("Hearts", 1), 
	SPADES("Spades", 2), 
	DIAMONDS("Diamonds", 3);

	// Variables
	private final String suitName;
	private final int suitNum;

	// Constructor
	private Suit(String suitName, int numOfSuit)
	{
		this.suitName = suitName;
		this.suitNum = numOfSuit;
	}
	
	// Prints/returns number of suit
	public int getNumOfSuit()
	{
		return suitNum;
	}
	
	// Prints/returns name of suit
	public String displaySuit()
	{
		return suitName;
	}
}

