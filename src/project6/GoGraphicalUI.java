package project6;

import javafx.scene.input.MouseEvent;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import project6.Chain.Player;

public class GoGraphicalUI extends Application implements GoUI{
	
	static GoBoard goBoard = new GoBoard();
	Player player = Player.BLACK;
	MouseEvent leClick;
	
	static GraphicsContext gc;
	static int width = 400;
	static int height = 400;
	static Label blkPlayerLbl;
	static Label whtPlayerLbl;	
	static Label status;
		
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Game of Go - Graphical User Interface");
		GridPane gridpane = new GridPane();
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setHgap(5);
		gridpane.setVgap(5);
		gridpane.setPadding(new Insets(5, 5, 5, 5));
		
		//QUIT BUTTON
		Button quitButton = new Button("Quit");
		quitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.close();
			}			
		});
		quitButton.setTextFill(Color.FIREBRICK);
		quitButton.setMaxWidth(Double.MAX_VALUE);
		gridpane.add(quitButton, 1, 0);
		
		//GO BOARD
		Canvas gridcanvas = new Canvas(width, height);
		gridcanvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle (MouseEvent mouseEvent) {
				setStatus("X: " + mouseEvent.getX() + " Y: " + mouseEvent.getY());
				leClick = mouseEvent;
				takeTurn();
			}
		});		
		gc = gridcanvas.getGraphicsContext2D();
		gridpane.add(gridcanvas, 0, 1, 1, 2);
		drawEmptyBoard();			
		
		//TURN LABELS
		VBox playerLblBox = new VBox();
		blkPlayerLbl = new Label("BLACK PLAYER");
		whtPlayerLbl = new Label("WHITE PLAYER");
		//PASS BUTTON
		Button passBtn = new Button("PASS");
		passBtn.setMaxWidth(Double.MAX_VALUE);
		passBtn.setAlignment(Pos.CENTER);
		passBtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				pass();
				if (isFinished()){
					declareWinner();
				}
			}
		});		
		playerLblBox.getChildren().addAll(blkPlayerLbl, whtPlayerLbl, passBtn);
		playerLblBox.setAlignment(Pos.CENTER_LEFT);
		
		playerLblBox.setSpacing(10);
		playerLblBox.setPadding(new Insets(0, 20, 10, 20)); 
		gridpane.add(playerLblBox, 1, 1, 1, 2);

		updateLblColor();
		
		//STATUS LABEL
		status = new Label("STATUS: No problems yet");
		status.setMaxWidth(Double.MAX_VALUE);
		status.setAlignment(Pos.CENTER);
		gridpane.add(status, 0, 3);
		
		//LE BUTTON
		Button testerBtn = new Button("LE GREAT BUTTON OF LE GREATNESS");
		testerBtn.setMaxWidth(Double.MAX_VALUE);
		testerBtn.setAlignment(Pos.CENTER);
		testerBtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				takeTurn();
			}
		});
		gridpane.add(testerBtn, 0, 4);		
		
		Scene scene = new Scene(gridpane, 800, 600);
		primaryStage.setScene(scene);

		primaryStage.show();	     
	}
	
	public void pass() {
		if (goBoard.hasFinished()){
			return;
		}
		
		goBoard.takeTurn(Player.NEUTRAL, 0, 0);
		switchPlayers();
		setStatus("Now it's "+player.toString()+" turn...");
		updateLblColor();
		
		if (goBoard.hasFinished()){
			setStatus("WE'RE DONE IT WORKED HORAH BOYAH");
		}
	}
	
	public void switchPlayers(){
		switch(player){
		case BLACK: player = Player.WHITE; break;
		case WHITE: player = Player.BLACK; break;
		default: player = Player.NEUTRAL; break;
		}
	}
	
	public void updateLblColor(){
		switch(player){
		case BLACK: blkPlayerLbl.setTextFill(Color.CRIMSON); 
					whtPlayerLbl.setTextFill(Color.GREY); break;
		case WHITE: blkPlayerLbl.setTextFill(Color.GREY);
					whtPlayerLbl.setTextFill(Color.CRIMSON); break;
		default: 	blkPlayerLbl.setTextFill(Color.BLACK);
					whtPlayerLbl.setTextFill(Color.BLACK); break;
		}
	}
	
	@Override
	public void takeTurn() {
		boolean isValidMove = false;
		showBoard();		
		updateLblColor();		
		while (!isValidMove){
			showBoard();		
			Coord move = getCoordinates();
			setStatus("X is: "+move.x_coord+". Y is: "+move.y_coord+".");
			isValidMove = goBoard.takeTurn(player, move.x_coord, move.y_coord);
			if (!isValidMove){
				setStatus("That's not a valid move. Try again.");
			}
		}
		showBoard();
		switchPlayers();
	}

	@Override
	public Coord getCoordinates() {
		int size = goBoard.getBoardSize();
		double slice = height/(size+1);
		double bit = slice/2;
		
		int x = (int)((leClick.getX()-bit)/slice); 
		int y = (int)((leClick.getY()-bit)/slice);
		return new Coord(x,y);
	}

	@Override
	public boolean isFinished() {
		return goBoard.hasFinished();
	}

	@Override
	public void showBoard() {
		drawEmptyBoard();		
		int size = goBoard.getBoardSize();
		double slice = height/(size+1);
		double bit = slice/2;
		
		for (int i = 0; i<size; i++){
			for (int j = 0; j<size; j++){
				switch (goBoard.boardArray[j][i].color){
				case BLACK: gc.setFill(Color.BLACK);
							gc.fillOval(bit+slice*j, bit+slice*i, slice, slice); break;
				case WHITE: gc.setFill(Color.WHITE);
							gc.fillOval(bit+slice*j, bit+slice*i, slice, slice); break;
				default: break;
				}
			}
		}
	}	
	
	public void drawEmptyBoard(){	
		gc.setFill(Color.MOCCASIN);   
		gc.fillRect(0,0,width,height);
		
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(3);
		gc.strokeRect(0, 0, width, height);
		
		int size = goBoard.getBoardSize();
		double slice = height/(size+1);
		
		gc.strokeRect(slice, slice, width-slice*2, height-slice*2);
		
		gc.setLineWidth(1);
		for (int i = 2; i<= size-1; i++){
			gc.strokeLine(slice*i, slice, slice*i, height-slice);
			gc.strokeLine(slice, slice*i, height-slice, slice*i);
		}		
	}
	
	private void setStatus(String update){
		status.setText("Status: "+update);
	}

	@Override
	public void declareWinner() {
		if (isFinished()){
			float[] territory = goBoard.calculateTerritories();
			setStatus("BLACK HAS: "+territory[0] + " WHITE HAS: "+territory[1]);
		}
	}
	
}
