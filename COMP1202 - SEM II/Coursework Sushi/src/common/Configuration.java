/*
 * @author Andronache Simone
 */
package common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Configuration {
	
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
	
	/** The stock management. */
	private StockManagement stockManagement;
	
	/** The delivery management. */
	private DeliveryManagement deliveryManagement;
	
	/**
	 * Instantiates a new configuration.
	 */
	public Configuration()
	{
		this.stockManagement = new StockManagement();
		this.deliveryManagement = new DeliveryManagement();
		
		this.users = new ArrayList<User>();
		this.postcodes = new ArrayList<Postcode>();
		this.dishes = new ArrayList<Dish>();
		this.suppliers = new ArrayList<Supplier>();
		this.ingredients = new ArrayList<Ingredient>();
		this.staff = new ArrayList<Staff>();
		this.orders = new ArrayList<Order>();
		this.drones = new ArrayList<Drone>();
	}
	
	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public List<User> getUsers()
	{
		return this.users;
	}
	
	/**
	 * Gets the postcodes.
	 *
	 * @return the postcodes
	 */
	public List<Postcode> getPostcodes()
	{
		return this.postcodes;
	}
	
	/**
	 * Gets the dishes.
	 *
	 * @return the dishes
	 */
	public List<Dish> getDishes()
	{
		return this.dishes;
	}
	
	/**
	 * Gets the suppliers.
	 *
	 * @return the suppliers
	 */
	public List<Supplier> getSuppliers()
	{
		return this.suppliers;
	}
	
	/**
	 * Gets the ingredients.
	 *
	 * @return the ingredients
	 */
	public List<Ingredient> getIngredients()
	{
		return this.ingredients;
	}
	
	/**
	 * Gets the staff.
	 *
	 * @return the staff
	 */
	public List<Staff> getStaff()
	{
		return this.staff;
	}
	
	/**
	 * Gets the orders.
	 *
	 * @return the orders
	 */
	public List<Order> getOrders()
	{
		return this.orders;
	}
	
	/**
	 * Gets the drones.
	 *
	 * @return the drones
	 */
	public List<Drone> getDrones()
	{
		return this.drones;
	}
	
	/**
	 * Gets the stock management.
	 *
	 * @return the stock management
	 */
	public StockManagement getStockManagement()
	{
		return this.stockManagement;
	}
	
	/**
	 * Gets the delivery management.
	 *
	 * @return the delivery management
	 */
	public DeliveryManagement getDeliveryManagement()
	{
		return this.deliveryManagement;
	}
	
	/**
	 * Load configuration from a given file.
	 *
	 * @param filename the filename
	 * @throws Exception the exception
	 */
	public void loadConfiguration(String filename) throws Exception
	{
		BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));    
		String line = bufferedReader.readLine();
		
		while (line != null) 
		{
			String[] object = line.split(":");
			String objectType = object[0];
		    switch(objectType)
		    {
			    case "SUPPLIER":
			    	Supplier newSupplier = parseSupplier(object);
			    	this.suppliers.add(newSupplier);
			    	break;
			    case "INGREDIENT":
			    	Ingredient newIngredient = parseIngredient(object);
			    	this.ingredients.add(newIngredient);
			    	break;
			    case "DISH":
			    	Dish newDish = parseDish(object);
			    	this.dishes.add(newDish);
			    	break;
			    case "USER":
			    	User newUser = parseUser(object);
			    	this.users.add(newUser);
			    	break;
			    case "POSTCODE":
			    	Postcode newPostcode = parsePostcode(object);
			    	this.postcodes.add(newPostcode);
			    	break;
			    case "STAFF":
			    	Staff newStaff = parseStaff(object);
			    	this.staff.add(newStaff);
			    	break;
			    case "DRONE":
			    	Drone newDrone = parseDrone(object);
			    	this.drones.add(newDrone);
			    	break;
			    case "ORDER":
			    	Order newOrder = parseOrder(object);
			    	this.orders.add(newOrder);
			    	if(newOrder.getCompleted() == false)
			    	{
			    		this.deliveryManagement.addOrderQueue(newOrder);
			    	}
			    	break;
			    case "STOCK":
			    	parseStock(object);
			    	break;
			    default:
			    	break;
		    }
		    line = bufferedReader.readLine();
		}
	}
	
	/**
	 * Parses the supplier object string.
	 *
	 * @param supplierString the supplier string
	 * @return the supplier
	 * @throws Exception the exception
	 */
	private Supplier parseSupplier(String[] supplierString) throws Exception
	{
		String name = supplierString[1];
		Integer distance = null;
		try {
			distance = Integer.parseInt(supplierString[2]);
		}
		catch(Exception e)
		{
			throw new Exception("Invalid file format.");
		}
		return new Supplier(name, distance);
	}
	
	/**
	 * Parses the ingredient object string.
	 *
	 * @param ingredientString the ingredient string
	 * @return the ingredient
	 * @throws Exception the exception
	 */
	private Ingredient parseIngredient(String[] ingredientString) throws Exception
	{
		String name = ingredientString[1];
		String unit = ingredientString[2];
		
		String supplierName = ingredientString[3];
		Supplier supplier = null;
		for(Supplier supp : this.suppliers)
		{
			if(supp.getName().equals(supplierName))
			{	
				supplier = supp;
				break;
			}
		}
		
		if(supplier == null)
		{
			throw new Exception("Invalid file format.");
		}
		
		Integer restockThreshold = Integer.parseInt(ingredientString[4]);
		Integer restockAmount = Integer.parseInt(ingredientString[5]);
		
		return new Ingredient(name, unit, supplier, restockThreshold, restockAmount);
	}
	
	/**
	 * Parses the dish object string.
	 *
	 * @param dishString the dish string
	 * @return the dish
	 * @throws Exception the exception
	 */
	private Dish parseDish(String[] dishString) throws Exception
	{
		String name = dishString[1];
		String description = dishString[2];
		Integer price = Integer.parseInt(dishString[3]);
		Integer restockThreshold = Integer.parseInt(dishString[4]);
		Integer restockAmount = Integer.parseInt(dishString[5]);
		HashMap<Ingredient, Number> recipe = new HashMap<Ingredient, Number>();
		
		if(dishString.length > 6)
		{
			dishString = dishString[6].split(",");
			
			for(int i=0;i<dishString.length; i++)
			{
				String[] ingredientString = dishString[i].split("\\*");
				Integer ingredientQuantity = Integer.parseInt(ingredientString[0].trim());
				String ingredientName = ingredientString[1].trim();
				
				Ingredient ingredient = null;
				for(Ingredient ingr : this.ingredients)
				{
					if(ingr.getName().equals(ingredientName))
					{
						ingredient = ingr;
						break;
					}
				}
				if(ingredient == null)
				{
					throw new Exception("Invalid file format.");
				}
				
				recipe.put(ingredient, ingredientQuantity);
			}
		}
		return new Dish(name, description, price, recipe, restockThreshold, restockAmount);
	}
	
	/**
	 * Parses the user object string.
	 *
	 * @param userString the user string
	 * @return the user
	 * @throws Exception the exception
	 */
	private User parseUser(String[] userString) throws Exception
	{
		String name = userString[1];
		String password = userString[2];
		String location = userString[3];
		String postcodeName = userString[4];
		Postcode postcode = null;
		for(Postcode post : this.postcodes)
		{
			if(post.getName().equals(postcodeName))
			{	
				postcode = post;
				break;
			}
		}
		
		if(postcode == null)
		{
			throw new Exception("Invalid file format.");
		}
		
		return new User(name, password, location, postcode);
	}
	
	/**
	 * Parses the postcode object string.
	 *
	 * @param postcodeString the postcode string
	 * @return the postcode
	 */
	private Postcode parsePostcode(String[] postcodeString)
	{
		String name = postcodeString[1];
		Integer distance = Integer.parseInt(postcodeString[2]);
		
		return new Postcode(name, distance);
	}
	
	/**
	 * Parses the staff object string.
	 *
	 * @param staffString the staff string
	 * @return the staff
	 */
	private Staff parseStaff(String[] staffString)
	{
		String name = staffString[1];
		
		return new Staff(name);
	}
	
	/**
	 * Parses the drone object string.
	 *
	 * @param droneString the drone string
	 * @return the drone
	 */
	private Drone parseDrone(String[] droneString)
	{
		Integer speed = Integer.parseInt(droneString[1]);
		
		return new Drone(speed);
	}
	
	/**
	 * Parses the order object string.
	 *
	 * @param orderString the order string
	 * @return the order
	 * @throws Exception the exception
	 */
	private Order parseOrder(String[] orderString) throws Exception
	{
		String username = orderString[1];
		
		User user = null;
		for(User us: this.users)
		{
			if(us.getUsername().equals(username))
			{
				user = us;
				break;
			}
		}
		
		if(user == null)
		{
			throw new Exception("Invalid file format.");
		}
		
		int nextIndex = 2;
		
		boolean completed = false;
		
		if(orderString.length > 3)
		{
			completed = Boolean.parseBoolean(orderString[nextIndex]);
			nextIndex++;
		}
		
		HashMap<Dish, Number> dishes = new HashMap<Dish, Number>();
		
		orderString = orderString[nextIndex].split(",");
		for(int i=0;i < orderString.length;i++)
		{
			String[] dishString = orderString[i].split("\\*");
			Integer dishQuantity = Integer.parseInt(dishString[0].trim());
			String dishName = dishString[1].trim();
			
			Dish dish = null;
			for(Dish di : this.dishes)
			{
				if(di.getName().equals(dishName))
				{
					dish = di;
					break;
				}
			}
			if(dish == null)
			{
				throw new Exception("Invalid file format.");
			}
			
			dishes.put(dish, dishQuantity);
		}
		Order newOrder = new Order(user,dishes,completed);
		return newOrder;
	}
	
	/**
	 * Parses the stock object string.
	 *
	 * @param stockString the stock string
	 * @throws Exception the exception
	 */
	private void parseStock(String[] stockString) throws Exception
	{
		String objectName = stockString[1];
		Integer quantity = Integer.parseInt(stockString[2]);
		
		Dish dish = null;
		for(Dish di : this.dishes)
		{
			if(di.getName().equals(objectName))
			{
				dish = di;
				break;
			}
		}
		
		Ingredient ingredient = null;
		for(Ingredient ingr : this.ingredients)
		{
			if(ingr.getName().equals(objectName))
			{
				ingredient = ingr;
				break;
			}
		}
		
		if(dish != null)
		{
			this.stockManagement.increaseDishAmount(dish, quantity);
		}
		else if(ingredient != null)
		{
			this.stockManagement.increaseIngredientAmount(ingredient, quantity);
		}
		else
		{
			throw new Exception("Invalid file format");
		}
	}
}
