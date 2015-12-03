package project6;

import java.io.PrintStream;
import java.util.Scanner;

import project6.Chain.Player;

public class GoConsoleUI implements GoUI {

	PrintStream stdout = System.out;
	Scanner keyboard = new Scanner(System.in);
	
	GoBoard goBoard = new GoBoard();
	Player player = Player.BLACK;
	
	@Override
	public void takeTurn() {		
		boolean isValidMove = false;
		while (!isValidMove){
			showBoard();
			System.out.println("Where would "+player.toString()+" like to go? Or Pass?");
			Coord move = getCoordinates();
			System.out.println("X is: "+move.x_coord+". Y is: "+move.y_coord+".");
			isValidMove = goBoard.takeTurn(player, move.x_coord, move.y_coord);
			if (!isValidMove){
				System.out.println("That's not a valid move. Try again.");
			}
		}
		//TODO: what to do if they pass?
		switch(player){
		case BLACK: player = Player.WHITE; break;
		case WHITE: player = Player.BLACK; break;
		default: player = Player.BLACK; break;
		}
		System.out.println("Now it's "+player.toString()+" turn...");
	}

	@Override
	public Coord getCoordinates() {
		// TODO Auto-generated method stub
		String move = keyboard.nextLine();
		if (move.matches("\\b[A-Z,a-z]\\d{1,2}\\b")){
			stdout.println("Processing this move: " + move);
			move = move.toUpperCase();
			char strx = move.charAt(0);
			String stry = move.substring(1);
			int x = strx-65;
			int y = Integer.valueOf(stry)-1;
			return new Coord(x,y);		
		}
		stdout.println("Move invalid");
		return new Coord(-1,-1);
	}

	@Override
	public boolean isFinished() {
		return goBoard.hasFinished();
	}

	@Override
	public void showBoard() {
		stdout.println(getTextBoard());
	}
	
	public String getTextBoard() {
		int size = goBoard.getBoardSize();
		// TODO Auto-generated method stub
		char[][] tester = new char[size][size];
		for (int i = 0; i<size; i++){
			for (int j = 0; j<size; j++){
				tester[i][j] = '+';
			}
		}
		
		String toReturn = "";
		for (int i = 0; i<size; i++){
			toReturn += (char)(65+i);
		}
		toReturn += '\n';
		for (int i = 0; i<size; i++){
			for (int j = 0; j<size; j++){
				Player single = goBoard.boardArray[j][i].color;
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
