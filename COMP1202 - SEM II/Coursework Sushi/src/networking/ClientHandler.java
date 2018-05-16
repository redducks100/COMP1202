/*
 * @author Andronache Simone
 */
package networking;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import common.Dish;
import common.Order;
import common.Postcode;
import networking.CustomNetMessage.*;
import server.Server;
import server.ServerInterface.UnableToDeleteException;

public class ClientHandler extends Thread {
	
	/** The client socket. */
	private Socket clientSocket = null;
	
	/** The connected status. */
	private boolean isConnected = true;
	
	/** The client threads list. */
	private final ArrayList<ClientHandler> threads;
	
	/** The server object. */
	private Server server = null;
	
	/**
	 * Instantiates a new client handler.
	 *
	 * @param clientSocket the client socket
	 * @param threads the threads
	 * @param server the server
	 */
	public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> threads, Server server)
	{
		this.clientSocket = clientSocket;
		this.threads = threads;
		this.server = server;
	}
	
	public void run()
	{	
		try
		{	
			ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
			ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
			
			CustomNetMessage message = null;
			
			while(isConnected)
			{
				message = Communication.receiveMessage(inputStream);
				
				//CLIENT DISCONNECTS
				if(message == null)
				{
					isConnected = false;
					break;
				}
				//HANDLE REGISTER MESSAGES
				if(message instanceof RegisterUserMessage)
				{
					RegisterUserMessage registerMessage = (RegisterUserMessage) message;
					
					if(server.addUser(registerMessage.user))
					{
						Communication.sendMessage(outputStream, registerMessage);
					}
					else
					{
						registerMessage.user = null;
						Communication.sendMessage(outputStream, registerMessage);
					}
				} 
				//HANDLE LOGIN MESSAGES
				else if(message instanceof LoginUserMessage)
				{
					LoginUserMessage loginMessage = (LoginUserMessage) message;
					loginMessage.user = server.loginUser(loginMessage.user);
					
					Communication.sendMessage(outputStream, loginMessage);
				} 
				//HANDLE GETPOSTCODES MESSAGES
				else if(message instanceof GetPostcodesMessage)
				{
					GetPostcodesMessage getPostcodes = (GetPostcodesMessage) message;
					
					getPostcodes.postcodes = (ArrayList<Postcode>) server.getPostcodes();
					
					Communication.sendMessage(outputStream, getPostcodes);
				} 
				//HANDLE GETDISHES MESSAGES
				else if(message instanceof GetDishesMessage)
				{
					GetDishesMessage getDishes = (GetDishesMessage) message;
					
					getDishes.dishes = (ArrayList<Dish>) server.getDishes();
					
					Communication.sendMessage(outputStream, getDishes);
				}
				//HANDLE ADDORDER MESSAGES
				else if(message instanceof AddOrderMessage)
				{
					AddOrderMessage addOrderMessage = (AddOrderMessage) message;
					
					server.addOrder(addOrderMessage.order);
				}
				//HANDLE GETORDERS MESSAGES
				else if(message instanceof GetOrdersMessage)
				{
					GetOrdersMessage getOrdersMessage = (GetOrdersMessage) message;
					
					getOrdersMessage.orders = (ArrayList<Order>) server.getOrdersForUser(getOrdersMessage.user);
					
					Communication.sendMessage(outputStream, getOrdersMessage);
				}
				//HANDLE CANCELORDER MESSAGES
				else if(message instanceof CancelOrderMessage)
				{
					CancelOrderMessage cancelOrderMessage = (CancelOrderMessage) message;
					
					server.cancelOrder(cancelOrderMessage.order);
				}
			}
			
			//Clean up the stream and the socket
			outputStream.close();
			inputStream.close();
			clientSocket.close();
			
			//Remove the thread from the client handlers list
			synchronized(this)
			{
				this.threads.remove(this);
			}
		
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}