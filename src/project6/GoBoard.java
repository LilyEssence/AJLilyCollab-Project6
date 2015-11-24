package project6;

import java.util.Collection;
import java.util.HashSet;

import project6.Chain.Coord;
import project6.Chain.Player;

public class GoBoard {
	int boardSize;
	public Player[][] boardArray;

	public GoBoard(){
		this(19);
	}

	public GoBoard(int boardSize){
		this.boardSize = boardSize;
		boardArray = new Player[boardSize][boardSize];
		
		for (int i = 0; i<boardSize; i++){
			for (int j = 0; j<boardSize; j++){
				boardArray[i][j] = Player.NEUTRAL;
			}
		}
	}

    public boolean hasFinished(){ 
    	//TODO
    	return false;
    }

	public boolean takeTurn(Player player, int x, int y) {
		//check to see if x, y are valid inputs to board size
		if ((x < 0) || (y < 0)){
			return false;
		}
		if ((x > boardSize) || (y > boardSize)){
			return false;
		}		

		//check to see if x, y are not occupied
		if (boardArray[x][y] != Player.NEUTRAL)
		{
			return false;
		}		
		
		//if it is not a valid turn, do not do anything, return false.
		//if it's a valid turn, do the move, and return true;
		boardArray[x][y] = player;
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
				Player single = boardArray[j][i];
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

    Player color;
    //We should use a set, I can't justify generalizing to a collection
    //there shouldn't be any duplicate coordinates so a list doesn't work
    Set<Coord> pieces;
    Set<Coord> liberties;

    public Chain(){
	this.color = Player.BLACK;
	pieces = new HashSet<Coord>();
    }

    public Chain(Player color){
	this.color = color;
    }

    public addPiece(int x, int y){
	//here we should add a piece to the chain?
    }

    public void updateLiberties(){
	//I'm not sure how this might work
	//or if it should even be apart of the chain
	//it may be the case that this functionality is only used when adding a piece
	//therefore should just be a part of addPiece
    }

    class Coord {
	public int x_coord, y_coord;
    }
}
