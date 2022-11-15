import java.util.ArrayList;


public class Player 
{
	// board is inside player so it can be modified for game
	public Board gameBoard = new Board();
	
	// creates selectList for final selection and preSelectList for a shortlist of cards to be selected
	private ArrayList<Card> preSelectList = new ArrayList<Card>();
	public ArrayList<Card> selectList = new ArrayList<Card>();
	
	// tracks the rows and the cards moved for each move of the game. primarily for undo method
	public ArrayList<ArrayList<Card>> coordinateList = new ArrayList<ArrayList<Card>>();
	public ArrayList<ArrayList<Card>> moveList = new ArrayList<ArrayList<Card>>();
	private ArrayList<Card> container;
	
	// selects card through logic loop
	public void Select (Card card)
	{	
		
		// only so many cards can be legally selected in the game
		int selectAllowed = 5 - gameBoard.graveyard.size();
		
		// if any cards were already selected, drops them to select new cards
		selectList.clear();
		
		// checks if card is in graveyard. if it is, no problem selecting
		if (gameBoard.graveyard.contains(card))
		{
			selectList.add(card);
			container = gameBoard.graveyard;
			return;
		}
		
		// ditto for heaven. the logic is that you can always select at least 1 card
		for (int i = 0; i < gameBoard.heaven.length; i++)
		{
			if (gameBoard.heaven[i].contains(card))
			{
				selectList.add(card);
				container = gameBoard.heaven[i];
				return;
			}	
		}
		
		// if the card(s) is/are actually in one of the rows, checks to make sure it's a legal selection
		for (int i = 0; i < gameBoard.rowBoard.size(); i++)
		{
			if (gameBoard.rowBoard.get(i).contains(card))
			{
				container = gameBoard.rowBoard.get(i);
				int indexOf = container.indexOf(card);
				
				// 1) is the number of cards you're trying to select more than what's allowed based on graveyard?
				if ((container.size() - indexOf) <= selectAllowed)
				{
					// bool to check whether selection is legal
					Boolean legalMove = false;
					
					// for first card in selection, it'll always be legal because nothing to compare to for color/rank
					if (preSelectList.size() == 0)
					{
					legalMove = true;
					}
					
					// 2) are the cards actually in proper sequence? (alternating colors, in descending order)
					else
					{
						if (!card.isSameColor(preSelectList.get(preSelectList.size() - 1 )) && preSelectList.get(preSelectList.size() - 1 ).isNextInLine(card))
						{
							legalMove = true;
						}
					}
					
					// if it's a legal move, run this method recursively
					// if it's not the last card in a row, run this method on each card after
					// until the last card
					if (legalMove)
					{
						if (card == container.get(container.size() - 1))
						{
							preSelectList.add(card);
							selectList.addAll(preSelectList);
							preSelectList.clear();
						}
						else
						{
							preSelectList.add(card);
							Select(container.get(indexOf + 1));
						}
					}
					
					// if it's not legal, don't do anything but let them know
					else
					{
						System.out.println("Invalid selection!");
						preSelectList.clear();
						selectList.clear();
					}
				}
				
				// if it's too many cards, don't even check the legality. just let them know
				else
				{
					System.out.println("Too many cards selected.");
					preSelectList.clear();
					selectList.clear();
				}
			}
		}
		// if selection is invalid, no matter why, clear the select/preSelectLists so they don't duplicate
		// on the next legal selection
	}
	
	// moves the selected cards to an ArrayList of Cards. this means this method is only relevant to main board
	// and graveyard, NOT heaven as it is an array
	public void Move (ArrayList<Card> row)
	{
		// if the intended move is not to the graveyard
		if (row != gameBoard.graveyard)
		{
			// if either the row to be moved TO is empty or the highest card in the selected list
			// is in proper sequence (diff color and 1 rank below bottom card in the new row)
			if (row.size() == 0 || (row.get(row.size() - 1).isNextInLine(selectList.get(0)) && !row.get(row.size() - 1).isSameColor(selectList.get(0))))
			{
				coordinateList.add(container);
				container.removeAll(selectList);
				
				// uses lastMove placeholder (essentially a temp) in order to add 
				// to list of selected cards
				// because otherwise, clearing selectList will result in clearing 
				// lastMove as well
				ArrayList<Card> lastMove = new ArrayList<Card>();
				lastMove.addAll(selectList);
				moveList.add(lastMove);
				
				// moves cards to new row and clears selectList
				row.addAll(selectList);
				selectList.clear();
			}
			
			// if move is invalid, do nothing but keep the selectList as it is
			else
			{
				System.out.println("Invalid Move!");
			}
		}
		
		// can only move cards to graveyard 1 at a time
		else
		{
			if (selectList.size() == 1)
			{
				// graveyard cannot go over 4 as per rules of FreeCell
				if (gameBoard.graveyard.size() < 4)
				{
					
					coordinateList.add(container);
					container.removeAll(selectList);
					
					// hmmm, looks familiar. duplicated code means this should probably be its own method
					ArrayList<Card> lastMove = new ArrayList<Card>();
					lastMove.addAll(selectList);
					moveList.add(lastMove);
					row.addAll(selectList);
					selectList.clear();
					
				}
				
				// tells user why their move didn't work
				else
				{
					System.out.println("Only four cards allowed in graveyard.");
				}
			}
			
			// ditto
			else
			{
				System.out.println("Cannot move more than one card to graveyard at once! Sorry.");
			}
		}
	}
	
	// second overloaded method which is how cards are moved to heaven. array of arrayLists (of cards)
	// rather than just arrayLists
	public void Move(ArrayList[] heaven)
	{
		// all functions essentially the same as above
		// with the exception that the cards are funneled into the different indices of heaven
		// based on what their suit is. You can't have diamonds in the clubs heaven
		if (selectList.size() == 1)
		{
			if (gameBoard.heaven[selectList.get(0).getSuit() - 1].size() == selectList.get(0).getFace() - 1)
			{
				gameBoard.heaven[selectList.get(0).getSuit() - 1].add(selectList.get(0));
				
				coordinateList.add(container);
				container.removeAll(selectList);
				
				ArrayList<Card> lastMove = new ArrayList<Card>();
				lastMove.addAll(selectList);
				moveList.add(lastMove);
				selectList.clear();
			}
			else
			{
				System.out.println("This card is not ready to be sent to heaven!");
			}
		}
		else
		{
			System.out.println("Cannot move more than one card to heaven at once! Sorry.");
		}
	}
	
	
	// this uses the moveList and coordinateList to track the cards moved (not selections) and where they were moved
	// undoes last move and deletes those entries from each list
	// theoretically, you can undo all the way back to the beginning this way
	// which is how undo works anyway
	public void Undo ()
	{
		for (int i = 0; i < gameBoard.rowBoard.size(); i++)
		{
			if (gameBoard.rowBoard.get(i).contains(moveList.get(moveList.size() - 1).get(0)))
			{
				gameBoard.rowBoard.get(i).removeAll(moveList.get(moveList.size() - 1));
				break;
			}	
		}
		
		for (int i = 0; i < gameBoard.heaven.length; i++)
		{
			if (gameBoard.heaven[i].contains((moveList.get(moveList.size() - 1).get(0))))
			{
				gameBoard.heaven[i].removeAll(moveList.get(moveList.size() - 1));
				break;
			}	
		}
		
		if (gameBoard.graveyard.contains((moveList.get(moveList.size() - 1).get(0))))
		{
			gameBoard.graveyard.removeAll(moveList.get(moveList.size() - 1));
		}
		
		coordinateList.get(coordinateList.size() - 1).addAll(moveList.get(moveList.size() - 1));
		coordinateList.remove(coordinateList.size() - 1);
		moveList.remove(moveList.size() - 1);
	}
	
	// empty constructor, really no reason for this
	public Player ()
	{
		
	}
}