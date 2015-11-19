package Project6;

public class Chain{
    public enum Player {BLACK, WHITE}

    Player color;

    Collection<Coord> pieces;
    Collection<Coord> liberties;

    public Chain(){
	this.color = Player.BLACK;
	pieces = new HashSet<?>();
    }

    public Chain(Player color){
	this.color = color;
    }


    class Coord {
	public int x_coord, y_coord;
    }
}
