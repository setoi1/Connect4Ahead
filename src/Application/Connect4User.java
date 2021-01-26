package Application;

import java.io.IOException;
/**
 * Simple interface to define the make and receive moves methods for Connect4Host and Connect4ConnectedUser
 * @author Nick Hale
 *
 */
public interface Connect4User {
	/**
	 * Add your moves to the game board and then send them to your opponent
	 * @param moves
	 * @throws IOException
	 */
	public void makeMoves(int [] moves) throws IOException;
	
	/**
	 * Recieve moves from the other play and add them to your gameboard
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void recieveMoves() throws ClassNotFoundException, IOException;

}
