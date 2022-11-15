import java.util.ArrayList;

public class Board 
{
	// create heaven ArrayLists to put in array (size 4) heaven
	private ArrayList<Card> diamondHeaven = new ArrayList<Card>();
	private ArrayList<Card> clubHeaven = new ArrayList<Card>();
	private ArrayList<Card> heartHeaven = new ArrayList<Card>();
	private ArrayList<Card> spadeHeaven = new ArrayList<Card>();
	public ArrayList<Card>[] heaven = new ArrayList[] {diamondHeaven, clubHeaven, heartHeaven, spadeHeaven};
	
	// create graveyard
	public ArrayList<Card> graveyard = new ArrayList<Card>();
	
	// create ArrayList to hold the rows of the main game board
	public ArrayList<ArrayList<Card>> rowBoard = new ArrayList<ArrayList<Card>>();
	public ArrayList<Card> rowOne = new ArrayList<Card>();
	public ArrayList<Card> rowTwo = new ArrayList<Card>();
	public ArrayList<Card> rowThree = new ArrayList<Card>();
	public ArrayList<Card> rowFour = new ArrayList<Card>();
	public ArrayList<Card> rowFive = new ArrayList<Card>();
	public ArrayList<Card> rowSix = new ArrayList<Card>();
	public ArrayList<Card> rowSeven = new ArrayList<Card>();
	public ArrayList<Card> rowEight = new ArrayList<Card>();
	
	/*
	// creates unicode text variables in order to show color for cards
	private static final String TEXT_RED = "\u001B[31m";
	private static final String TEXT_BLUE = "\u001B[34m";
	private static final String TEXT_RESET = "\u001B[0m";
	private String textColor;
	*/
	
	// instantiating board adds all the rows to the board
	public Board()
	{
		rowBoard.add(rowOne);
		rowBoard.add(rowTwo);
		rowBoard.add(rowThree);
		rowBoard.add(rowFour);
		rowBoard.add(rowFive);
		rowBoard.add(rowSix);
		rowBoard.add(rowSeven);
		rowBoard.add(rowEight);	
	}
	
	// prints the board, including all rows top down and the graveyard and heaven below
	public void PrintBoard()
	{
		// loops through rows to find the largest of them
		int max = rowBoard.get(0).size();
		int counter = 0;
		for (int i = 0; i < rowBoard.size(); i++)
		{
			if (rowBoard.get(i).size() > max)
			{
				max = rowBoard.get(i).size();
			}
		}
		
		// prints each index of each row on 1 line, defines color, uses try/catch to avoid error on rows shorter than max
		while (counter < max)
		{
			for (int i = 0; i < rowBoard.size(); i++)
			{
				try
				{
//					if (rowBoard.get(i).get(counter).getSuitColor() == "Red")
//					{
//						#textColor = TEXT_RED;
//					}
//					else
//					{
//						#textColor = TEXT_BLUE;
//					}
					
					//System.out.print(textColor + rowBoard.get(i).get(counter) + "\t\t" + TEXT_RESET);
					int stringLength = rowBoard.get(i).get(counter).toString().length();
					int margin = 20 - stringLength;
					String marginString = "";
					for (int j = 0; j < margin; j++)
					{
						marginString = marginString + " ";
					}
					System.out.print(rowBoard.get(i).get(counter) + marginString);
				}
				catch (Exception IndexOutOfBoundsException)
				{
					String marginString = "";
					for (int j = 0; j < 20; j++)
					{
						marginString = marginString + " ";
					}
					System.out.print(marginString);
				}
			}
			counter++;
			System.out.println();
		}
		
		// prints graveyard cards
		System.out.println();
		System.out.print("Graveyard: ");
		
		if (graveyard.size() > 0)
		{
			for (int i = 0; i < graveyard.size(); i++)
			{
//				if (graveyard.get(i).getSuitColor() == "Red")
//				{
//					textColor = TEXT_RED;
//				}
//				else
//				{
//					textColor = TEXT_BLUE;
//				}
				//System.out.print(textColor + graveyard.get(i) + "\t\t" + TEXT_RESET);
				System.out.print(graveyard.get(i) + "\t\t");
			}
			System.out.println();
		}
		else
		{
			System.out.println("Empty");
		}
		
		// loops through heaven and prints 0 (if empty) or the top card of each suit's heaven
		System.out.println();
		System.out.print("Heaven: ");
		for (int i = 0; i < heaven.length; i++)
		{
			if (heaven[i].size() > 0)
			{
//				if (heaven[i].get(0).getSuitColor() == "Red")
//				{
//					textColor = TEXT_RED;
//				}
//				else
//				{
//					textColor = TEXT_BLUE;
//				}
				//System.out.print(textColor + heaven[i].get(heaven[i].size() - 1) + "\t\t" + TEXT_RESET);
				System.out.print(heaven[i].get(heaven[i].size() - 1) + "\t\t");
			}
			else
			{
//				if (i % 2 == 0)
//				{
//					textColor = TEXT_RED;
//				}
//				else
//				{
//					textColor = TEXT_BLUE;
//				}
				//System.out.print(textColor + "0\t\t" + TEXT_RESET);
				System.out.print("0\t\t");
			}
		}
		System.out.println();
		System.out.println();
	}
}