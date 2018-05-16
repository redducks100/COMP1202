/*
 * @author Andronache Simone
 */
import client.Client;
import client.ClientInterface;
import client.ClientWindow;
import networking.Communication;

public class ClientApplication {
	
	/**
	 * Initialise.
	 *
	 * @return the client interface
	 */
	public static ClientInterface initialise() {
		ClientInterface cInterface = new Client();
		return cInterface;
	}
	
	/**
	 * Launch GUI.
	 *
	 * @param cInterface the c interface
	 */
	public static void launchGUI(ClientInterface cInterface)
	{
		ClientWindow window = new ClientWindow(cInterface);
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[])
	{
		ClientInterface cInterface = initialise();
		launchGUI(cInterface);
		
	}
	
}
