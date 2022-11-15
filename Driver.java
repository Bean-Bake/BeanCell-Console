//********************************************************************
//  Driver.java       							Authors: Brandon Baker
//												Last Modified Date: 04/13/21
// Tests out the functionality DeckOfCards class.
//********************************************************************

public class Driver 
{
	// =============================================================
	// main method		Creates a new deck of cards, iterates through
	//					to print all the cards. Shuffles then prints
	//					them all. Then deals them all and prints them
	//					as they are dealt. Finally, prints how many 
	//					cards are left in the original deck after the
	//					deal.
	// =============================================================
	public static void main(String[] args) 
	{
		Game gameboy = new Game();
		gameboy.play();			
	}	
}