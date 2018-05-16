/*
 * @author Andronache Simone
 */
import networking.Communication;
import server.*;

public class ServerApplication {
	
	/**
	 * Initialise.
	 *
	 * @return the server interface
	 */
	public static ServerInterface initialise() {
		ServerInterface sInterface = new Server();
		return sInterface;
	}
	
	/**
	 * Launch GUI.
	 *
	 * @param sInterface the s interface
	 */
	public static void launchGUI(ServerInterface sInterface)
	{
		ServerWindow window = new ServerWindow(sInterface);
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[])
	{
		ServerInterface sInterface = initialise();
		launchGUI(sInterface);
	}
}
