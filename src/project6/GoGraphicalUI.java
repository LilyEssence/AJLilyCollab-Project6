package project6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GoGraphicalUI extends Application implements GoUI{
	
	static GoBoard goBoard = new GoBoard();
	
	static GraphicsContext gc;
	static int width = 300;
	static int height = 300;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Game of Go - Graphical User Interface");
		GridPane gridpane = new GridPane();
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setHgap(5);
		gridpane.setVgap(5);
		gridpane.setPadding(new Insets(5, 5, 5, 5));
		
		Canvas gridcanvas = new Canvas(width, height);
		gc = gridcanvas.getGraphicsContext2D();
		gc.setFill(Color.LIGHTGREY);   
		gc.fillRect(0,0,gridcanvas.getWidth(),gridcanvas.getHeight());
	}
	
	@Override
	public void takeTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getCoordinates() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
