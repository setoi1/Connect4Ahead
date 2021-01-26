package Application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 
 * @author Nick Hale
 * Host object that hold that data and stream necesary to host a game of connect 4 ahead
 */
public class Connect4Host implements Connect4User{
	private ServerSocket gameSocket;
	public Connect4Ahead game;
	private Socket connectedUser;
	private ObjectOutputStream serverOut;
	private ObjectInputStream serverIn;
	public int[] opponentsMoves;
	public int[] yourMoves;
	/**
	 * Contructor for a Connect4Host object
	 * @param port the port that the game will be hosted on
	 * @throws IOException
	 */
	public Connect4Host (int port) throws IOException{
		
		gameSocket = new ServerSocket(port);
		game = new Connect4Ahead();
		System.out.println("Hosting game on port: " + port + "\nWaiting for connection...");
		connectedUser = gameSocket.accept();
		serverOut = new ObjectOutputStream(connectedUser.getOutputStream());
		serverIn = new ObjectInputStream(connectedUser.getInputStream());
		System.out.println("Other User connected, Starting Game");
		
		
	}
	
	/**
	 *  sets the number of moves for the game and sends to opponent
	 * @param numMoves the number of moves per turn
	 * @throws IOException
	 */
	public void setNumMoves(int numMoves) throws IOException {
		game.setNumMoves(numMoves);
		serverOut.writeObject(numMoves);
		//serverOut.flush();
		
	}
	
	@Override
	public void makeMoves(int [] playerMoves) throws IOException {
		serverOut.reset();
		game.populateArray(playerMoves);
		serverOut.writeObject(playerMoves);
		
		
	}
	
	@Override
	public void recieveMoves() throws ClassNotFoundException, IOException {
		int [] otherPlayerMoves = (int[]) serverIn.readObject();
		game.populateArray(otherPlayerMoves);
		System.out.println("Other player Moves Recieved!");
		
	}
	
}


