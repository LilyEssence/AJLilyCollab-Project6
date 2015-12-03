package project6;

import java.io.PrintStream;
import java.util.Scanner;

import project6.Chain.Player;

public class GoRunner {

	public static void main1( String [] cheese){
		PrintStream stdout = System.out;
		System.out.println("Hello World! We're playing Go!");
		Scanner keyboard = new Scanner(System.in);
		String uiChoice = "";
		boolean isUIValid = false;
		
		while (!isUIValid){
			System.out.println("Do you want a text interface or a graphical interface?");
			System.out.println("Ack- you don't get a choice right now. We're going with text.");
			//uiChoice = keyboard.nextLine();
			uiChoice = "text";
			
			if (uiChoice.equals("text")){
				isUIValid = true;
				System.out.println("Okay, let's play text!");
				
				
			}
			else if (uiChoice.equals("graphical")){
				isUIValid = true;
				System.out.println("Okay, let's open a graphical interface!");
				
			}
			else {
				System.out.println("Sorry, that's an invalid choice");
			}
		}
		
		System.out.println("Goodbye!");
	}
	
	public static void main(String[] args){
		//strictly for testing
		GoBoard board = new GoBoard(10);
		//System.out.println(board.getTextBoard());
		board.takeTurn(Player.BLACK, 1, 0);
		board.takeTurn(Player.BLACK, 0, 1);
		board.takeTurn(Player.WHITE, 1, 1);
		board.takeTurn(Player.BLACK, 1, 2);
		board.takeTurn(Player.BLACK, 2, 1);
		System.out.println(board.getTextBoard());
		board.resolveLiberties();
		System.out.println(board.getTextBoard());
		System.out.println(Chain.allChains.toString());
	}
}
