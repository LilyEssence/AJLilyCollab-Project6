package project6;

import java.util.Collection;
import java.util.HashSet;

import project6.Chain.Coord;
import project6.Chain.Player;

public class GoBoard {
    public int[][] boardArray;

    public GoBoard(){
	boardArray = new int[9][9];
    }

    public GoBoard(int boardSize){
	boardArray = new int[boardSize][boardSize];
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