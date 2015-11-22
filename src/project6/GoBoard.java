package project6;

import java.util.Collection;
import java.util.HashSet;

import project6.Chain.Coord;
import project6.Chain.Player;

public class GoBoard {
	int boardSize;
	public int[][] boardArray;

	public GoBoard(){
		boardArray = new int[9][9];
	}

	public GoBoard(int boardSize){
		this.boardSize = boardSize;
		boardArray = new int[boardSize][boardSize];
	}

    public boolean hasFinished(){ 
    	//TODO
    	return false;
    }
    
    public int getBoardSize(){
    	return boardSize;
    }

	public String getTextBoard() {
		// TODO Auto-generated method stub
		int boardsize = 9;
		char[][] tester = new char[boardsize][boardsize];
		for (int i = 0; i<boardsize; i++){
			for (int j = 0; j<boardsize; j++){
				tester[i][j] = '+';
			}
		}
		
//		for (int i = 0; i<boardsize; i++){
//			tester[0][i] = '━';
//			tester[boardsize-1][i] = '-';
//			tester[i][0] = '|';
//			tester[i][boardsize-1] = '|';
//		}		
		
		String toReturn = " ";
		for (int i = 0; i<boardsize; i++){
			toReturn += (char)(97+i);
		}
		toReturn += '\n';
		for (int i = 0; i<boardsize; i++){
			toReturn += (char)(97+i);
			for (int j = 0; j<boardsize; j++){
				toReturn += tester[i][j];
			}
			toReturn += "\n";
		}
		return toReturn;
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
		
		
		//if it is not a valid turn, do not do anything, return false.
		//if it's a valid turn, do the move, and return true;
		return true;		
	}
}

class Chain{
    public enum Player {BLACK, WHITE}

    Player color;

    Collection<Coord> pieces;
    Collection<Coord> liberties;

    public Chain(){
	this.color = Player.BLACK;
	pieces = new HashSet<Coord>();
    }

    public Chain(Player color){
	this.color = color;
    }


    class Coord {
	public int x_coord, y_coord;
    }
}