package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Scanner;

import Application.Connect4ConnectedUser;
import Application.Connect4Host;
import Application.Connect4User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
/**
 * 
 * @author Quinn Montgomery
 * @author Nick Hale
 * @author Ian Seto
 * Driver class for connect 4 ahead
 */
public class BoardController extends Application implements Initializable{
	    
	    
	   
	    static Connect4User user;
	    static boolean host;
	    
       public static void main(String [] args) throws Exception 
       {	   
    	   launch(args);    
       }
   
    public void start(Stage PS) {
    	
    	final int width = 400;
    	final int hight = 200;
    	final Pane root = new Pane();
    	
    	final Button Btnhost = new Button();
    	final Button Btnjoin = new Button();
    	final Button Btnstart = new Button();
    	
    	final TextField Txtip = new TextField();
    	Txtip.setLayoutX(width/2);
    	Txtip.setLayoutY(hight/6);
    	Txtip.setVisible(false);
    	
    	final TextField Txtport = new TextField();
    	Txtport.setLayoutX(width/2);
    	Txtport.setLayoutY(hight/3);
    	Txtport.setVisible(false);
    	
    	final Label Lbip = new Label("enter the ip of the host");
    	Lbip.setLayoutX(width/7);
    	Lbip.setLayoutY(hight/6);
    	Lbip.setVisible(false);
    	
    	final Label Lbport = new Label("enter the port for the host");
    	Lbport.setLayoutX(width/7);
    	Lbport.setLayoutY(hight/3);
    	Lbport.setVisible(false);
    	
    	PS.setTitle("Login");
    	
    	
    	//host button
    	
    	Btnhost.setAlignment(Pos.CENTER);
    	Btnhost.setText("host a game");
    	Btnhost.setLayoutX(width/3);
    	Btnhost.setLayoutY(hight/6);
    	Btnhost.setOnAction(new EventHandler<ActionEvent>() {
    		
    		public void handle(ActionEvent event) {
    			
    			//host a game
    			
    			Btnhost.setVisible(false);
    			Btnjoin.setVisible(false);
    			
    			Btnstart.setVisible(true);
    			Txtip.setVisible(true);
    			Txtport.setVisible(true);
    			Lbip.setVisible(true);
    			Lbport.setVisible(true);
    			Lbip.setText("How many moves per turn? ");
    			
    			host = true;
    			
    		}
    		
		});
    	
    	
    	Btnjoin.setAlignment(Pos.CENTER);
    	Btnjoin.setText("Join a game");
    	Btnjoin.setLayoutX(width/3);
    	Btnjoin.setLayoutY(hight/3);
    	Btnjoin.setOnAction(new EventHandler<ActionEvent>() {
    		
    		public void handle(ActionEvent event) {
    			
    			//join a game
    			
    			Btnhost.setVisible(false);
    			Btnjoin.setVisible(false);
    			
    			Btnstart.setVisible(true);
    			Txtip.setVisible(true);
    			Txtport.setVisible(true);
    			Lbip.setVisible(true);
    			Lbport.setVisible(true);
    			
    			host = false;
    			
    		}
    		
		});
    	
    	
    	Btnstart.setAlignment(Pos.CENTER);
    	Btnstart.setText("Start");
    	Btnstart.setLayoutX(width/3);
    	Btnstart.setLayoutY(hight/1.2);
    	Btnstart.setVisible(false);
    	Btnstart.setOnAction(new EventHandler<ActionEvent>() {
    		
    		public void handle(ActionEvent event) {
    			
    			//start the game
    			
    			if(host) {
    				try {
    					
    				user = new Connect4Host(Integer.parseInt(Txtport.getText()));
    				
    				int mvNum = Integer.parseInt(Txtip.getText());
    				
    				host((Connect4Host) user, mvNum);
    				
    				} catch (Exception e){
    				user = null;	
    				}
    				
    			} else {
    				
    				
    				try {
    				user = new Connect4ConnectedUser(Txtip.getText(),Integer.parseInt(Txtport.getText()));
    				
    				connected((Connect4ConnectedUser) user);
    				
    				} catch (Exception e){
    				user = null;	
    				}
    				
    			}
    			
    			
    			//PS.hide();
    			PS.close();
    			
    		}
    		
		});
    	
    	root.getChildren().add(Btnhost);
    	root.getChildren().add(Btnjoin);
    	root.getChildren().add(Btnstart);
    	root.getChildren().add(Txtport);
    	root.getChildren().add(Txtip);
    	root.getChildren().add(Lbport);
    	root.getChildren().add(Lbip);
    	
//    	root.getChildren().addAll(
//    			
//    			Btnhost,
//    			Btnjoin,
//    			Btnstart,
//    			Txtip, 
//    			Txtip, 
//    			Lbhost, 
//    			Lbip
//    			
//    			);
    	
    	PS.setScene(new Scene(root, width, hight));
    	PS.show();
    }
    
    /**
     *  
     * The game loop for a connected player
     * @param user the user object that hold the connection and game data
     * @throws Exception
     * @throws Exception
     */
	private static void connected(Connect4ConnectedUser user) throws Exception, Exception {
		int numMoves = user.setNumMoves();
		int [] playerMoves = new int [numMoves];
		Scanner in = new Scanner(System.in);
		System.out.println("NumMoves is set to " + numMoves);
		do 
		{
			System.out.println("Waiting for player 1 to make moves");
			user.recieveMoves();
			System.out.println("Make your Moves!");
			for(int i = 0; i < playerMoves.length; i++) {
				playerMoves[i] = in.nextInt();
				if(playerMoves[i] <0 || playerMoves[i] > 6) {
					System.out.println("Invalid column, make sure your selection is from 0 to 6.");
					i--;
				}
			}
			user.makeMoves(playerMoves);
			System.out.println("Moves sent:");
			
			System.out.println("placing the pieces!");
			
		}while(user.game.placePiecesOnBoard());
		
	}

	/**
	 * The game loop for the host player
	 * @param user the user object that hold connection and game data
	 * @param numMoves
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private static void host(Connect4Host user, int numMoves) throws IOException, ClassNotFoundException {
		Scanner in = new Scanner(System.in);
//		System.out.println("How many moves per turn? ");
//		int numMoves = in.nextInt();
		
		
		int[] playerMoves = new int [numMoves];
		user.setNumMoves(numMoves);
		do{
			System.out.println("Make you moves");
			for(int i = 0; i < playerMoves.length; i++) {
				playerMoves[i] = in.nextInt();
				if(playerMoves[i] <0 || playerMoves[i] > 6) {
					System.out.println("Invalid column, make sure your selection is from 0 to 6.");
					i--;
				}
			}
			
			user.makeMoves(playerMoves);
			System.out.println("waiting for player 2 moves...");
			user.recieveMoves();
			System.out.println("player 2 moves recived");
			System.out.println("placing the pieces!");
			
		}while(user.game.placePiecesOnBoard());
		
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		
		
		
	}
}