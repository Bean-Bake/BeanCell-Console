import java.util.ArrayList;
import java.util.Scanner;

public class Game 
{
	public Player player;
	public DeckOfCards deck;
	
	public void play()
	{
		// creates player which has board inside itself
		// creates deck of cards for game
		player = new Player();
		deck = new DeckOfCards();
		int moveCount = 0;
		
		// shuffles and deals deck into the proper arrangement for a game of FreeCell
		deck.Shuffle();
		
		 for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				player.gameBoard.rowBoard.get(i).add(deck.Deal());
			}
		}
		for (int i = 4; i < 8; i++)
		{
			for (int j = 0; j < 6; j++)
			{
				player.gameBoard.rowBoard.get(i).add(deck.Deal());
			}
		}
			
		// creates a new bool called gameOver. when gameOver is true, game will end
		boolean gameOver = false;
		
		// asks what player wants to do. various text responses allowed. if ever there is an error, it returns to beginning
		// of prompt so the game does not break
		while (!gameOver)
		{
			player.gameBoard.PrintBoard();
			System.out.println("Moves so far: " + moveCount);
			System.out.println("Selected cards: " + player.selectList);
			System.out.println();
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("What would you like to do?");
			String control = scan.nextLine().toLowerCase();
			System.out.println(control);
			
			try 
			{
				if (control.equals("select"))
				{
					System.out.println("Column?");
					int column = scan.nextInt();
					System.out.println("Row?");
					int row = scan.nextInt();
					
					player.Select(player.gameBoard.rowBoard.get(column - 1).get(row - 1));
				}
				else if (control.equals("select graveyard"))
				{
					System.out.println("Which one in graveyard?");
					int index = scan.nextInt();
					player.Select(player.gameBoard.graveyard.get(index - 1));
				}
				
				else if (control.equals("select heaven"))
				{
					System.out.println("Which one in heaven?");
					int index = scan.nextInt();
					player.Select(player.gameBoard.heaven[index - 1].get(player.gameBoard.heaven[index - 1].size() - 1));
				}
				
				else if (control.equals("move"))
				{
					System.out.println("Which column?");
					int column = scan.nextInt();
					player.Move(player.gameBoard.rowBoard.get(column - 1));
					moveCount++;
					
				}
				
				else if (control.equals("graveyard"))
				{
					player.Move(player.gameBoard.graveyard);
					moveCount++;
				}
				
				else if (control.equals("heaven"))
				{
					player.Move(player.gameBoard.heaven);
					moveCount++;
				}
				
				else if (control.equals("quit"))
				{
					return;
				}
				
				else if (control.equals("undo"))
				{
					player.Undo();
					moveCount--;
				}
				
				else
				{
					System.out.println("Sorry, I didn't understand your request.");
					player.selectList.clear();
				}
			}
			catch (Exception e)
			{
				System.out.println("Sorry, I didn't understand your request.");
				player.selectList.clear();
			}
			
			// ends game if heavens are all full
			if (player.gameBoard.heaven[0].size() == 12 && player.gameBoard.heaven[1].size() == 12 && player.gameBoard.heaven[2].size() == 12 && player.gameBoard.heaven[3].size() == 12)
			{
				gameOver = true;
			}
			System.out.println();
			
			
			// loops through to see if cards are in order
			// if every row is in order, end game early so player doesn't have to manually finish a won game
			// if a single row is not in order, break out of the outer loop
			outerloop: for (int i = 0; i < player.gameBoard.rowBoard.size(); i++)
			{
				if (player.gameBoard.rowBoard.get(i).size() < 2)
				{
					gameOver = true;
				}
				
				else
				{
					for (int j = 0; j < player.gameBoard.rowBoard.get(i).size() - 1; j++)
					{
						if (player.gameBoard.rowBoard.get(i).get(j).isNextInLine(player.gameBoard.rowBoard.get(i).get(j + 1)) && 
								!player.gameBoard.rowBoard.get(i).get(j).isSameColor(player.gameBoard.rowBoard.get(i).get(j + 1)))
						{
							gameOver = true;
						}
						
						else
						{
							gameOver = false;
							break outerloop;
						}
					}
				}
			}
		}
		
		moveCount = moveCount + (52 - player.gameBoard.heaven[0].size() - player.gameBoard.heaven[1].size() - player.gameBoard.heaven[2].size() - player.gameBoard.heaven[3].size());
		player.gameBoard.PrintBoard();
		System.out.println();
		System.out.println("Congratulations, you win! It took " + moveCount + " moves!");
	}
}