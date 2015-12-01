package project6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import project6.Chain.Player;

public class GoBoard {
	static int boardSize;
	public static Chain[][] boardArray;
	public static ArrayList<Chain> playerPieces = new ArrayList<Chain>();//this doesn't include neutral pieces

	public GoBoard(){
		this(19);
	}

	public GoBoard(int boardSize){
		GoBoard.boardSize = boardSize;
		boardArray = new Chain[boardSize][boardSize];
		
		for (int i = 0; i<boardSize; i++){
			for (int j = 0; j<boardSize; j++){
				boardArray[i][j] = new Chain();
			}
		}
	}

    public boolean hasFinished(){ 
    	//TODO
    	return false;
    }

	public boolean takeTurn(Player player, int x, int y) {
		//check to see if x, y are valid inputs to board size
		if ((x < 0) || (y < 0)){ return false; }
		
		if ((x >= boardSize) || (y >= boardSize)){ return false; }		
		//check to see if x, y are not occupied
		if (boardArray[x][y].color != Player.NEUTRAL){ return false; }		
		
		//if it is not a valid turn, do not do anything, return false.
		//if it's a valid turn, do the move, and return true;
		Chain toAdd = Chain.addPiece(player, x, y);
		if(toAdd == null) return false;
		
		boardArray[x][y] = toAdd;
		return true;
	}
    
    public int getBoardSize(){
    	return boardSize;
    }

	public String getTextBoard() {
		// TODO Auto-generated method stub
		char[][] tester = new char[boardSize][boardSize];
		for (int i = 0; i<boardSize; i++){
			for (int j = 0; j<boardSize; j++){
				tester[i][j] = '+';
			}
		}
		
		String toReturn = "";
		for (int i = 0; i<boardSize; i++){
			toReturn += (char)(65+i);
		}
		toReturn += '\n';
		for (int i = 0; i<boardSize; i++){
			for (int j = 0; j<boardSize; j++){
				Player single = boardArray[j][i].color;
				switch(single){
				case BLACK:
					toReturn += "X"; break;
				case NEUTRAL:
					toReturn += "+"; break;
				case WHITE:
					toReturn += "O"; break;
				default:
					toReturn += "?"; break;				
				}
			}
			toReturn += " "+(i+1)+"\n";
		}
		return toReturn;
	}
}

class Chain{
    public enum Player {BLACK, WHITE, NEUTRAL}
    public static Set<Chain> allChains;
    
    Player color;
    //We should use a set, I can't justify generalizing to a collection
    //there shouldn't be any duplicate coordinates so a list doesn't work
    
    //the chain should include every piece that its a part of?
    Set<Coord> pieces;
    Set<Coord> liberties;

	public Chain() {
		this.color = Player.NEUTRAL;
		pieces = new HashSet<Coord>();
		allChains = new HashSet<Chain>();//I'm thinking a HashSet doesn't make sense here
	}

	public Chain(Player color) {
		this.color = color;
		allChains = new HashSet<Chain>();
	}
	
	public Chain(Player color, int x, int y){
		this.color = color;
		allChains = new HashSet<Chain>();
	}

	public static Chain addPiece(Player color, int x, int y) {
		// here we should add a piece to the chain?
		//First we check to see if there are any pieces around it.
		ArrayList<Chain> neighbors = new ArrayList<Chain>(); //top clockwise
		if(y < GoBoard.boardSize - 1) neighbors.add(GoBoard.boardArray[x][y + 1]);
		if(x < GoBoard.boardSize - 1) neighbors.add(GoBoard.boardArray[x + 1][y]);
		if(y > 0) neighbors.add(GoBoard.boardArray[x][y - 1]);
		if(x > 0) neighbors.add(GoBoard.boardArray[x - 1][y]);
		
		//now that I have the adjacent chains
		ArrayList<Chain> friends = new ArrayList<Chain>();
		ArrayList<Chain> enemies = new ArrayList<Chain>();
		for(Chain c : neighbors){
			if(c.color == color){
				friends.add(c);
			}
			
			if(c.color != color && c.color != Player.NEUTRAL) enemies.add(c);
		}
		
		//as it stands, this will only check if there are any similar adjacent pieces
		//it may be the case that all adjacencies are the opposite color,
		//	in which case it would not be valid.
		
		if(enemies.size() == 4) return null;//yea, no.
		
		for(Chain c : enemies){
			c.liberties.remove(new Coord(x, y));
		}
		
		Chain current = new Chain(color, x, y);
		
		if(friends.isEmpty()){
			GoBoard.boardArray[x][y] = current;
			return current;
		}
		
		for(Chain f : friends){
			current.merge(f);
		}
		
		//in this last case, we need to connect all the adjacent chains and add this piece
		return current;
	}

	/*
	 * What happens when we have a piece added to the board that connects 2
	 * chains? We will surely combine them, but should we add them all to the a
	 * new chain? Or would it be better to just update one and delete the other?
	 * After that, we will need to update all the game spaces.
	 */
	
	private void merge(Chain b){
		//it should be the case that there are no duplicates
		//we use sets so it shouldn't matter
		pieces.addAll(b.pieces);
		
		//update the liberties of a
		liberties.addAll(b.liberties);
		
		updateLiberties();
		
		//we need to change the identity of b on board into a
		for(Coord c : b.pieces){
			GoBoard.boardArray[c.x_coord][c.y_coord] = this;
		}
		
	}

	public void updateLiberties() {
		Iterator<Coord> libIterator = liberties.iterator();
		
		while(libIterator.hasNext()){
			Coord c = libIterator.next();
			if(pieces.contains(c))
				libIterator.remove();
		}
	}

}

class Coord {
	public int x_coord, y_coord;
	public Coord(){
		x_coord = 0;
		y_coord = 0;
	}
	public Coord(int x, int y){
		x_coord = x;
		y_coord = y;
	}
	
	@Override
	public boolean equals(Object c){
		if(!(c instanceof Coord)){
			return false;
		}
		
		Coord b = (Coord) c;
	
		return (x_coord == b.x_coord) && (y_coord == b.y_coord);
	}
	
	@Override
	public int hashCode(){
		return x_coord*21 + y_coord;
	}
}
