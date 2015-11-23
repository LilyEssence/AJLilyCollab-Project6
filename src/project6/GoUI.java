package project6;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import project6.Chain.Player;

public class GoUI {
	public static void main( String [] cheese){
		PrintWriter stdout = new PrintWriter(
				new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true);
		
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
				GoBoard goBoard = new GoBoard();
				Player player = Player.BLACK;
				while(!goBoard.hasFinished()){
					boolean isValidMove = false;
					while (!isValidMove){
						stdout.println(goBoard.getTextBoard());
						System.out.println("Where would "+player.toString()+" like to go? Or Pass?");
						String move = keyboard.nextLine();
						char strx = move.charAt(0);
						String stry = move.substring(1);
						int x = strx-65;
						int y = Integer.valueOf(stry)-1;
						System.out.println("X is: "+x+". Y is: "+y+".");
						isValidMove = goBoard.takeTurn(player, x, y);
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
}
