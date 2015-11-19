package project6;

public class GoBoard {
    public int[][] boardArray;

    public GoBoard(){
	boardArray = new int[9][9];
    }

    public GoBoard(int boardSize){
	boardArray = new int[boardSize][boardSize];
    }
}
