/*
 * @author Andronache Simone
 */
package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import server.Server;

public class Communication {
	
	/** The client. */
	public ClientNetwork client = null;
	
	/** The server. */
	public ServerNetwork server = null;
	
	/**
	 * Instantiates a new client communication link.
	 *
	 * @param host the host
	 * @param portNumber the port number
	 * @throws UnknownHostException the unknown host exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Communication(String host, int portNumber) throws UnknownHostException, IOException
	{
		this.client = new ClientNetwork(host, portNumber);
	}
	
	/**
	 * Instantiates a new server communication link.
	 *
	 * @param portNumber the port number
	 * @param server the server
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Communication(int portNumber, Server server) throws IOException
	{
		this.server = new ServerNetwork(portNumber, server);
	}
	
	
	/**
	 * Send a message to the server/client.
	 *
	 * @param outputStream the output stream
	 * @param message the message
	 */
	public static void sendMessage(ObjectOutputStream outputStream,  CustomNetMessage message)
	{
		if(outputStream != null)
		{
			try {
				outputStream.reset(); //reset the stream
				outputStream.writeObject(message);
			} catch (IOException e) {
			}
		}
	}
	
	/**
	 * Receive a message from server/client.
	 *
	 * @param inputStream the input stream
	 * @return the custom net message
	 */
	public static CustomNetMessage receiveMessage(ObjectInputStream inputStream)
	{
		CustomNetMessage message = null;

		if(inputStream != null)
		{
			try {
				message = (CustomNetMessage) inputStream.readObject();
			} catch (IOException e) {
			} catch (ClassNotFoundException e) {
			}
		}
		
		return message;
	}
	
}

