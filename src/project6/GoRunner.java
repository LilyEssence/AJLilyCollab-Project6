//Arjun Teh, agt495
//Linda Xie, LX775


package project6;

import java.util.Scanner;
import project6.Chain.Player;

public class GoRunner {

	public static void main( String [] cheese){
		System.out.println("Hello World! We're playing Go!");
		Scanner keyboard = new Scanner(System.in);
		String uiChoice = "";
		boolean isUIValid = false;
		GoUI goUI = new GoConsoleUI();

		while (!isUIValid){
			System.out.println("Do you want a text interface or a graphical interface?");
			//System.out.println("Ack- you don't get a choice right now. We're going with text.");
			uiChoice = keyboard.nextLine();
			//uiChoice = "graphical";

			if (uiChoice.equals("text")){
				isUIValid = true;
				System.out.println("Okay, let's play via text!");
				goUI = new GoConsoleUI();		
				while (!goUI.isFinished()){
					goUI.takeTurn();
				}
				goUI.declareWinner();
			}
			else if (uiChoice.equals("graphical")){
				isUIValid = true;
				System.out.println("Okay, let's open a graphical interface!");
				goUI = new GoGraphicalUI();
				GoGraphicalUI.launch(GoGraphicalUI.class);
			}
			else {
				System.out.println("Sorry, that's an invalid choice");
			}
		}
		System.out.println("Goodbye!");
	}
}
