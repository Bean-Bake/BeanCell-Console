//********************************************************************
//  DeckOfCards.java       						Authors: Brandon Baker
//										Last Modified Date: 04/13/21
// Holds an array of Card objects called deckOfCards to simulate 
// a deck of playing cards. Has functions to shuffle, deal, and check 
// how many cards are left in the starting deck.
//********************************************************************
public class DeckOfCards
{ 
	// declares and instantiates an array called deckOfCards which holds all the cards in a typical playing card deck.
	private Card[] deckOfCards;
			
	// creates upToDeal counter outside of deal method so the counter does not reset
	private int upToDeal = 0;
	
	// the deck size of a normal playing card deck is a constant of 52.
	final private int DECKSIZE = 52;
	
	//-----------------------------------------------------------------
	//  Creates a deck of 52 playing cards. (CONSTRUCTOR)
	//-----------------------------------------------------------------
	public DeckOfCards()
	{
		// instantiate the array with the constant length of 52
		deckOfCards = new Card[DECKSIZE];
		
		// start at the 0th index
		int placeInDeck = 0;
		
		// loop through each face and suit value to create all 52 cards, then increment the index
		for (int i = 1; i < 14; i++)
		{
			for (int j = 1; j < 5; j++)
			{
				deckOfCards[placeInDeck] = new Card(i, j);
				placeInDeck++;
			}
		}
	}

	//-----------------------------------------------------------------
	//  Shuffles the deck of cards.
	//-----------------------------------------------------------------
	public void Shuffle()
	{
		// make sure the deck is full
		if (deckOfCards.length == DECKSIZE)
		{
			// do it 52 times (arbitrary)
			for (int i = 0; i < 300; i++)
			{
				// find two random indices of the array and create a temporary placeholder
				int randOne = (int)(Math.random() * DECKSIZE);
				int randTwo = (int)(Math.random() * DECKSIZE);
				Card temp = null;
				
				// swap cards at those two indices using that temporary placeholder
				temp = deckOfCards[randOne];
				deckOfCards[randOne] = deckOfCards[randTwo];
				deckOfCards[randTwo] = temp;
			}
		}
	}
	
	//-----------------------------------------------------------------
	//  Deals a single card from the deck into a new "deal deck" array.
	//-----------------------------------------------------------------
	public Card Deal()
	{	
		Card card = null;
		// can only deal if within the length of the deck itself.
		if (upToDeal < DECKSIZE)
		{	
			card = deckOfCards[upToDeal];
		}
		upToDeal++;
		return card;		
	}
	
	//-----------------------------------------------------------------
	//  Returns the number of cards left in the deckOfCards array.
	//-----------------------------------------------------------------
	public int getLeftInDeck()
	{
		// uses same upToDeal iterator from Deal
		return (DECKSIZE - upToDeal);	
	}
	
	//-----------------------------------------------------------------
	//  Returns the number of cards left in the deckOfCards array.
	//-----------------------------------------------------------------
	public String toString()
	{
		// starts as an empty string
		String cardName = "";
		
		// loop through each index of the deck array starting at upToDeal 
		// so that, if cards have been dealt, they will not be included
		// in the print statement
		for (int i = upToDeal; i < DECKSIZE; i++)
		{
			// add each card name and a new line to the empty string
			cardName = cardName + deckOfCards[i] + "\n";
		}
	
		// after every card has been added, return that string of each 
		// card on its own line
		return cardName;
	}
}