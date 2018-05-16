/*
 * @author Andronache Simone
 */
package common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import server.Server;

public class DataPersistance implements UpdateListener {

	/** The server. */
	private Server server;
	
	/** The backup file name. */
	private String backupFileName;
	
	/** The users. */
	private List<User> users;
	
	/** The postcodes. */
	private List<Postcode> postcodes;
	
	/** The dishes. */
	private List<Dish> dishes;
	
	/** The suppliers. */
	private List<Supplier> suppliers;
	
	/** The ingredients. */
	private List<Ingredient> ingredients;
	
	/** The staff. */
	private List<Staff> staff;
	
	/** The orders. */
	private List<Order> orders;
	
	/** The drones. */
	private List<Drone> drones;
	
	/**
	 * Instantiates a new data persistance object.
	 *
	 * @param backupFileName the backup file name
	 * @param server the server
	 */
	public DataPersistance(String backupFileName, Server server)
	{
		this.backupFileName = backupFileName;
		this.server = server;
		this.server.addUpdateListener(this);
	}
	
	/**
	 * Make a backup of the server to the given file.
	 * Create a new configuration file from the server data.
	 */
	private void makeBackup()
	{
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(backupFileName));
			StringBuilder sb = new StringBuilder();
			
			suppliers = new ArrayList<Supplier>(this.server.getSuppliers());
			for(Supplier supplier : suppliers)
			{
				sb.append(supplier.toPersistanceString());
				sb.append("\n");
			}
			sb.append("\n");
			
			ingredients = new ArrayList<Ingredient>(this.server.getIngredients());
			for(Ingredient ingredient : ingredients)
			{
				sb.append(ingredient.toPersistanceString());
				sb.append("\n");
			}
			sb.append("\n");
			
			dishes = new ArrayList<Dish>(this.server.getDishes());
			for(Dish dish : dishes)
			{
				sb.append(dish.toPersistanceString());
				sb.append("\n");
			}
			sb.append("\n");
			
			postcodes = new ArrayList<Postcode>(this.server.getPostcodes());
			for(Postcode postcode : postcodes)
			{
				sb.append(postcode.toPersistanceString());
				sb.append("\n");
			}
			sb.append("\n");
			
			users = new ArrayList<User>(this.server.getUsers());
			for(User user : users)
			{
				sb.append(user.toPersistanceString());
				sb.append("\n");
			}
			sb.append("\n");
			
			orders = new ArrayList<Order>(this.server.getOrders());
			for(Order order : orders)
			{
				sb.append(order.toPersistanceString());
				sb.append("\n");
			}
			sb.append("\n");
			sb.append(StockManagement.instance.toPersistanceString());
			sb.append("\n");
			
			staff = new ArrayList<Staff>(this.server.getStaff());
			for(Staff staf : staff)
			{
				sb.append(staf.toPersistanceString());
				sb.append("\n");
			}
			
			sb.append("\n");
			
			drones = new ArrayList<Drone>(this.server.getDrones());
			for(Drone drone : drones)
			{
				sb.append(drone.toPersistanceString());
				sb.append("\n");
			}
			sb.append("\n");
			
			writer.write(sb.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updated(UpdateEvent updateEvent) {
		if(updateEvent.property == null || updateEvent.property == "persistanceCompleted") //make sure to update the order status
			makeBackup();
	}

}
