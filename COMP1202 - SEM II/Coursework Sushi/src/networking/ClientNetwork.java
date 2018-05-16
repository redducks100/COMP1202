/*
 * @author Andronache Simone
 */
package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientNetwork {
	
	/** The client socket. */
	public Socket clientSocket = null;
	
	/** The output stream. */
	public ObjectOutputStream outputStream = null;
	
	/** The input stream. */
	public ObjectInputStream inputStream = null;
	
	/**
	 * Instantiates a new client network.
	 *
	 * @param host the host
	 * @param portNumber the port number
	 * @throws UnknownHostException the unknown host exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public ClientNetwork(String host, int portNumber) throws UnknownHostException, IOException
	{
		clientSocket = new Socket(host, portNumber);
		outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
		inputStream = new ObjectInputStream(clientSocket.getInputStream());
	}
	
	/**
	 * Close connection.
	 */
	public void closeConnection()
	{
		try {
			Communication.sendMessage(outputStream, null);
			outputStream.flush();
			outputStream.close();
			inputStream.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
