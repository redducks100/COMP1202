/*
 * @author Andronache Simone
 */
package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import server.Server;

public class ServerNetwork {
	
	/** The server socket. */
	public ServerSocket serverSocket = null;
	
	/** The client handlers list. */
	public static ArrayList<ClientHandler> clientHandlers = null;
	
	/** The server thread. */
	public Thread serverThread = null;
	
	/** The server is running status. */
	public static boolean isRunning = false;
	
	/** The server object. */
	private static Server server = null;
	
	/**
	 * Instantiates a new server network.
	 *
	 * @param portNumber the port number
	 * @param server the server
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public ServerNetwork(int portNumber, Server server) throws IOException
	{
		ServerNetwork.server = server;
		serverSocket = new ServerSocket(portNumber);
		clientHandlers = new ArrayList<ClientHandler>();
		runServer();
	}
	
	/**
	 * Run server.
	 * Start the server thread and accept clients.
	 */
	public void runServer()
	{
		isRunning = true;
		serverThread = new Thread(new Runnable(){
						public void run()
						{
							while(isRunning)
							{
								try {
									Socket clientSocket = serverSocket.accept();
									clientHandlers.add(new ClientHandler(clientSocket, clientHandlers, ServerNetwork.server));
									clientHandlers.get(clientHandlers.size() - 1).start();
								}
								catch(IOException e)
								{
									e.printStackTrace();
								}
							}
						}
					});
		
		serverThread.start();
	}
	
	/**
	 * Close the server.
	 */
	public void closeServer()
	{
		isRunning = false;
		try {
			serverThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
