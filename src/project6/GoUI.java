package project6;

public interface GoUI {
	public abstract void takeTurn();
		//need to account for endgame, 
		//when one player passes, and when both players pass
	public abstract Coord getCoordinates();
	public abstract boolean isFinished();	
	
	public abstract void showBoard();
	public abstract void declareWinner();
}
