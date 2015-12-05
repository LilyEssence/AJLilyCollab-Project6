package project6;

import java.util.Set;

import project6.Chain.Player;

public class GoTester {
	
	public static void main(String [] cheese){
		System.out.println("Hello World! Welcome to the Go Tester!");
		//mergeTester();
		//addPieceTester();
		mergeNeutralsTester();
	}
	
	public static void mergeNeutralsTester(){
		GoBoard goBoard = new GoBoard();
		Chain b = new Chain(Player.WHITE, 0, 0);
		
		Set<Chain> chains = goBoard.debugger();
		System.out.println(chains);

		System.out.println(goBoard.getTextBoard());
		float[] scores = goBoard.calculateTerritories();
		System.out.println(scores[0]);
		
		goBoard.takeTurn(Player.BLACK, 0, 1);
		goBoard.takeTurn(Player.BLACK, 1, 0);
		goBoard.takeTurn(Player.BLACK, 1, 2);
		goBoard.takeTurn(Player.BLACK, 2, 1);

		System.out.println(goBoard.getTextBoard());
		
		chains = goBoard.debugger();
		System.out.println(chains);
		
		scores = goBoard.calculateTerritories();
		System.out.println(scores[0]);
	}
	
	public static void addPieceTester(){
		Chain a = Chain.addPiece(Player.BLACK, 0, 0);
		System.out.println(a);
		a = Chain.addPiece(Player.BLACK, 0, 1);
		System.out.println(a);
		
		Chain b = Chain.addPiece(Player.WHITE, 1, 0);
		System.out.println(a.allChains);
	}
	
	public static void mergeTester(){
		Chain a = new Chain(Player.BLACK, 0, 0);
		Chain b = new Chain(Player.BLACK, 0, 1);
		
		System.out.println(a.toString());
		System.out.println(b.toString());
		
		a.merge(b);
		System.out.println(a.toString());
	}
	
	public static void main1(String[] args){
		//strictly for testing
		GoBoard board = new GoBoard(10);
		//System.out.println(board.getTextBoard());
		board.takeTurn(Player.BLACK, 1, 0);
		board.takeTurn(Player.BLACK, 0, 1);
		board.takeTurn(Player.WHITE, 1, 1);
		board.takeTurn(Player.BLACK, 1, 2);
		board.takeTurn(Player.BLACK, 2, 1);
		//System.out.println(board.getTextBoard());
		//board.resolveLiberties();
		//System.out.println(board.getTextBoard());
		System.out.println(Chain.allChains.toString());
	}
}
