package Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * Object that hold connection data and stream for a connected user of connect4ahead
 * @author Nick Hale
 *
 */
public class Connect4ConnectedUser implements Connect4User{
	private Socket gameSocket;
	public Connect4Ahead game;
	private ObjectInputStream clientIn;
	private ObjectOutputStream clientOut;
	/**
	 * Constructor for a Connect4ConnectedUser object
	 * @param adress ip address of the host player
	 * @param port port that game is hosted on
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Connect4ConnectedUser (String adress, int port) throws UnknownHostException, IOException, ClassNotFoundException {
		gameSocket = new Socket(adress, port);
		clientIn = new ObjectInputStream(gameSocket.getInputStream());
		clientOut = new ObjectOutputStream(gameSocket.getOutputStream());
		game = new Connect4Ahead();
		System.out.println("gameDataRecieved, Starting Game...");
		
		
	}
	/**
	 * recieve the moves per turn from host and set the parameter for game board
	 * @return returns the number of moves per turn
	 * @throws Exception
	 * @throws IOException
	 */
	public int setNumMoves() throws Exception, IOException {
		int numMoves = (int) clientIn.readObject();
		//System.out.println(numMoves);
		game.setNumMoves(numMoves);
		return numMoves;
		
	}
	@Override
	public void recieveMoves() throws ClassNotFoundException, IOException {
		int [] otherPlayerMoves = (int[]) clientIn.readObject();
		game.populateArray(otherPlayerMoves);
		System.out.println("Other player Moves Recieved!");
		
	}
	@Override
	public void makeMoves(int[] playerMoves) throws IOException {
		clientOut.reset();
		game.populateArray(playerMoves);
		clientOut.writeObject(playerMoves);
		
	}

}
