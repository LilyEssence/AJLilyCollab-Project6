package project6;

import java.io.PrintStream;
import java.util.Scanner;

import project6.Chain.Player;

public class GoConsoleUI implements GoUI {

	private PrintStream stdout = System.out;
	private Scanner keyboard = new Scanner(System.in);
	private String move;
	
	private GoBoard goBoard = new GoBoard();
	private Player player = Player.BLACK;
	
	@Override
	public void takeTurn() {		
		if (isFinished()){
			return;
		}
		
		boolean isValidMove = false;
		while (!isValidMove){
			showBoard();
			System.out.println("Where would "+player.toString()+" like to go? Or Pass?");
			move = keyboard.nextLine();
			if (move.toLowerCase().matches("pass")){
				goBoard.takeTurn(Player.NEUTRAL, 0, 0);
				isValidMove = true;
			}
			else {
				Coord move = getCoordinates();
				System.out.println("X is: "+move.x_coord+". Y is: "+move.y_coord+".");
				isValidMove = goBoard.takeTurn(player, move.x_coord, move.y_coord);
			}
			if (!isValidMove){
				System.out.println("That's not a valid move. Try again.");
			}
		}
		switch(player){
		case BLACK: player = Player.WHITE; break;
		case WHITE: player = Player.BLACK; break;
		default: player = Player.BLACK; break;
		}
		System.out.println("Now it's "+player.toString()+" turn...");
	}

	@Override
	public Coord getCoordinates() {
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
		stdout.println(goBoard.getTextBoard());
	}

	@Override
	public void declareWinner() {
		if (isFinished()){
			float[] territory = goBoard.calculateTerritories();
			System.out.println("BLACK HAS: "+territory[0] + " WHITE HAS: "+territory[1]);
		}
	}

}
