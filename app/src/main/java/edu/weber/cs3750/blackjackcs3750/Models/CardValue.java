package edu.weber.cs3750.blackjackcs3750.Models;

/**
 * Name: CardValue.java 
 * Author: Adam Persson 
 * Due Date: 6-25-2016
* Assignment: Game Of BlackJack
* Description: Expand existing classes to create the card game of war
 */

public enum CardValue {
	ACE(14, "Ace"), TWO(2, "2"), THREE(3, "3"), FOUR(4, "4"), FIVE(5, "5"),
	SIX(6, "6"), SEVEN(7, "7"), EIGHT(8,"8"), NINE(9, "9"), TEN(10, "10"),
	JACK(11, "Jack"), QUEEN(12, "Queen"), KING(13, "King");

	// Variables
	private final int num;
	private final String cardName;
	
	// Constructor
	private CardValue(int num, String cardName)
	{
		this.num = num;
		this.cardName = cardName;
	}
	
	// return name of card
	public String displayNum() 
	{
		return cardName;
	}

	// return card number
	public int getCardNum()
	{
		return num;
	}
	
}
