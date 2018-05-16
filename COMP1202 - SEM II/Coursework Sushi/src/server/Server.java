/*
 * @author Andronache Simone
 */
package server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import common.*;
import networking.Communication;

public class Server extends Model implements ServerInterface {

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
	
	/** The communication link object. */
	private Communication communication = null;
	
	/** The persistance object. */
	private DataPersistance persistance;
	
	/**
	 * Instantiates a new server.
	 */
	public Server()
	{
		this.users = new ArrayList<User>();
		this.postcodes = new ArrayList<Postcode>();
		this.dishes = new ArrayList<Dish>();
		this.suppliers = new ArrayList<Supplier>();
		this.ingredients = new ArrayList<Ingredient>();
		this.staff = new ArrayList<Staff>();
		this.orders = new ArrayList<Order>();
		this.drones = new ArrayList<Drone>();
		
		this.stockManagement = new StockManagement();
		this.deliveryManagement = new DeliveryManagement();
		
		//Make sure the port is unused
		try {
			this.communication = new Communication(4444, this);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "The port is already in use!", "ERROR", JOptionPane.OK_OPTION);
			System.exit(0);
		}
		
		this.persistance = new DataPersistance("backup.txt",this);
		
		//try to load the backup file
		try {
			this.loadConfiguration("backup.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void loadConfiguration(String filename) throws FileNotFoundException {
		
		Configuration newConfiguration = new Configuration();
		
		boolean fileLoaded = true;
		
		try {
			newConfiguration.loadConfiguration(filename);
		} catch (Exception e) {
			fileLoaded = false;
			JOptionPane.showMessageDialog(null, "Couldn't find backup file. Starting a clean server.", "WARNING", JOptionPane.OK_OPTION);
		}
		
		if(fileLoaded == false)
			return;
		
		synchronized(this)
		{
			for(Staff staff : this.staff)
			{
				staff.stopWorking();
			}
			for(Drone drone : this.drones)
			{
				drone.stopWorking();
			}
			
			this.users = newConfiguration.getUsers();
			this.postcodes = newConfiguration.getPostcodes();
			this.dishes = newConfiguration.getDishes();
			this.suppliers = newConfiguration.getSuppliers();
			this.ingredients = newConfiguration.getIngredients();
			this.staff = newConfiguration.getStaff();
			this.orders = newConfiguration.getOrders();
			this.drones = newConfiguration.getDrones();
			this.stockManagement = newConfiguration.getStockManagement();
			
			//INITIALISE THE STOCK
			for(Dish dish : this.dishes)
			{
				this.stockManagement.increaseDishAmount(dish, 0);
			}
			for(Ingredient ingredient : this.ingredients)
			{
				this.stockManagement.increaseIngredientAmount(ingredient, 0);
			}
			
			//MAKE SURE THE PERSISTANCE CLASS IS LISTENING TO THE ORDER STATUS
			for(Order order : this.orders)
			{
				order.addUpdateListener(persistance);
			}
			
			//START THE DRONES AND STAFF
			for(Staff staff : this.staff)
			{
				staff.startWorking();
			}
			for(Drone drone : this.drones)
			{
				drone.startWorking();
			}
		}
		
		notifyUpdate();
	}

	@Override
	public void setRestockingIngredientsEnabled(boolean enabled) {
		// unused methods
	}

	@Override
	public void setRestockingDishesEnabled(boolean enabled) {
		// unused methods
		
	}

	@Override
	public void setStock(Dish dish, Number stock) {
		stockManagement.setStockDish(dish, stock);
		notifyUpdate();
	}

	@Override
	public void setStock(Ingredient ingredient, Number stock) {
		stockManagement.setStockIngredient(ingredient, stock);
		notifyUpdate();
	}

	@Override
	public List<Dish> getDishes() {
		return this.dishes;
	}

	@Override
	public Dish addDish(String name, String description, Number price, Number restockThreshold, Number restockAmount) {
		Dish newDish = new Dish(name, description, (Integer) price, (Integer) restockThreshold, (Integer) restockAmount);
		dishes.add(newDish);
		setStock(newDish, 0);
		notifyUpdate();
		return newDish;
	}

	@Override
	public void removeDish(Dish dish) throws UnableToDeleteException {
		boolean removed = dishes.remove(dish);
		if(removed == false) throw new UnableToDeleteException("The dish does not exist!");
		notifyUpdate();
	}

	@Override
	public void addIngredientToDish(Dish dish, Ingredient ingredient, Number quantity) {
		dish.addIngredient(ingredient, (Integer) quantity); 
		notifyUpdate();
	}

	@Override
	public void removeIngredientFromDish(Dish dish, Ingredient ingredient) {
		dish.removeIngredient(ingredient);
		notifyUpdate();
	}

	@Override
	public void setRecipe(Dish dish, Map<Ingredient, Number> recipe) {
		dish.setRecipe((HashMap<Ingredient, Number>) recipe);
		notifyUpdate();
	}

	@Override
	public void setRestockLevels(Dish dish, Number restockThreshold, Number restockAmount) {
		dish.setRestockThreshold((Integer) restockThreshold);
		dish.setRestockAmount((Integer) restockAmount);
		notifyUpdate();
	}

	@Override
	public Number getRestockThreshold(Dish dish) {
		return dish.getRestockThreshold();
	}

	@Override
	public Number getRestockAmount(Dish dish) {
		return dish.getRestockAmount();
	}

	@Override
	public Map<Ingredient, Number> getRecipe(Dish dish) {
		return dish.getRecipe();
	}

	@Override
	public Map<Dish, Number> getDishStockLevels() {
		return stockManagement.getDishes();
	}

	@Override
	public List<Ingredient> getIngredients() {
		return this.ingredients;
	}

	@Override
	public Ingredient addIngredient(String name, String unit, Supplier supplier, Number restockThreshold,
			Number restockAmount) {
		
		Ingredient newIngredient = new Ingredient(name, unit, supplier, (Integer) restockThreshold, (Integer) restockAmount);
		this.ingredients.add(newIngredient);
		notifyUpdate();
		return newIngredient;
	}

	@Override
	public void removeIngredient(Ingredient ingredient) throws UnableToDeleteException 
	{	
		for(Dish dish : this.dishes)
		{
			if(dish.getRecipe().keySet().contains(ingredient))
				throw new UnableToDeleteException("Cannot delete an ingredient already in use!");
		}
		
		boolean removed = this.ingredients.remove(ingredient);
		
		if(removed == false)
			throw new UnableToDeleteException("The ingredient doesn't exist!");
		
		notifyUpdate();
	}

	@Override
	public void setRestockLevels(Ingredient ingredient, Number restockThreshold, Number restockAmount) {
		ingredient.setRestockAmount((Integer) restockAmount);
		ingredient.setRestockThreshold((Integer) restockThreshold);
		
		notifyUpdate();
	}

	@Override
	public Number getRestockThreshold(Ingredient ingredient) {
		return ingredient.getRestockThreshold();
	}

	@Override
	public Number getRestockAmount(Ingredient ingredient) {
		return ingredient.getRestockAmount();
	}

	@Override
	public Map<Ingredient, Number> getIngredientStockLevels() {
		return stockManagement.getIngredients();
	}

	@Override
	public List<Supplier> getSuppliers() {
		return this.suppliers;
	}

	@Override
	public Supplier addSupplier(String name, Number distance) {
		Supplier newSupplier = new Supplier(name, (Integer) distance);
		this.suppliers.add(newSupplier);
		notifyUpdate();
		return newSupplier;
	}

	@Override
	public void removeSupplier(Supplier supplier) throws UnableToDeleteException {
		for(Ingredient ingredient: this.ingredients)
		{
			if(ingredient.getSupplier() == supplier)
				throw new UnableToDeleteException("Cannot delete a supplier already in use!");
		}
		
		boolean removed = this.suppliers.remove(supplier);
		
		if(removed == false)
			throw new UnableToDeleteException("The supplier doesn't exist!");
		
		notifyUpdate();
	}

	@Override
	public Number getSupplierDistance(Supplier supplier) {
		return supplier.getDistance();
	}

	@Override
	public List<Drone> getDrones() {
		return this.drones;
	}

	@Override
	public Drone addDrone(Number speed) {
		Drone drone = new Drone(speed);
		drone.startWorking();
		this.drones.add(drone);
		notifyUpdate();
		return drone;
	}

	@Override
	public void removeDrone(Drone drone) throws UnableToDeleteException {
		if(drone.getStatus() != "Idle")
			throw new UnableToDeleteException("Cannot delete a drone while it is working!");
		
		drone.stopWorking();
		boolean removed = this.drones.remove(drone);
		
		if(removed == false)
			throw new UnableToDeleteException("The drone doesn't exist!");
		
		notifyUpdate();
	}

	@Override
	public Number getDroneSpeed(Drone drone) {
		return drone.getSpeed();
	}

	@Override
	public String getDroneStatus(Drone drone) {
		return drone.getStatus();
	}

	@Override
	public List<Staff> getStaff() {
		return this.staff;
	}

	@Override
	public Staff addStaff(String name) {
		Staff newStaff = new Staff(name);
		newStaff.startWorking();
		this.staff.add(newStaff);
		notifyUpdate();
		return newStaff;
	}

	@Override
	public void removeStaff(Staff staff) throws UnableToDeleteException {
		if(staff.getStatus() != "Idle")
			throw new UnableToDeleteException("Cannot delete a staff while he/she is working!");
		
		staff.stopWorking();
		boolean removed = this.staff.remove(staff);
		
		if(removed == false)
			throw new UnableToDeleteException("The staff doesn't exist!");
		
		notifyUpdate();
	}

	@Override
	public String getStaffStatus(Staff staff) {
		return staff.getStatus();
	}

	@Override
	public List<Order> getOrders() {
		return this.orders;
	}
	
	/**
	 * Gets the orders for a given user.
	 *
	 * @param user the user
	 * @return the orders for user
	 */
	public List<Order> getOrdersForUser(User user)
	{
		synchronized(this.orders)
		{
			List<Order> newList = new ArrayList<Order>();
			
			if(user == null)
				return newList;
			
			for(Order order: this.orders)
			{
				if(order.getUser().getUsername().equals(user.getUsername()))
					newList.add(order);
			}
			
			return newList;
		}
	}
	
	/**
	 * Adds the order to the delivery queue.
	 *
	 * @param order the order
	 */
	public void addOrder(Order order)
	{
		synchronized(this.orders)
		{
			this.orders.add(order);
			this.deliveryManagement.addOrderQueue(order);
			order.addUpdateListener(this.persistance);
		}
		
		notifyUpdate();
	}
	
	/**
	 * Cancel order.
	 *
	 * @param order the order
	 */
	public void cancelOrder(Order order)
	{
		synchronized(this.orders)
		{
			for(Order eOrder : this.orders)
			{
				if(eOrder.getName().equals(order.getName()))
				{	
					order = eOrder;
					break;
				}
			}
			
			this.deliveryManagement.cancelOrder(order);
			this.orders.remove(order);
			
			notifyUpdate();
		}
	}
	
	@Override
	public void removeOrder(Order order) throws UnableToDeleteException {
		boolean removed = this.orders.remove(order);
		
		if(removed == false)
			throw new UnableToDeleteException("The order doesn't exist!");
		
		notifyUpdate();
	}

	@Override
	public Number getOrderDistance(Order order) {
		return order.getUser().getPostcode().getDistance();
	}

	@Override
	public boolean isOrderComplete(Order order) {
		return order.getCompleted();
	}

	@Override
	public String getOrderStatus(Order order) {
		return order.getStatus();
	}

	@Override
	public Number getOrderCost(Order order) {
		return order.getCost();
	}

	@Override
	public List<Postcode> getPostcodes() {
		return this.postcodes;
	}

	@Override
	public void addPostcode(String code, Number distance) {
		this.postcodes.add(new Postcode(code, distance));
		notifyUpdate();
	}

	@Override
	public void removePostcode(Postcode postcode) throws UnableToDeleteException {
		for(User user : this.users)
		{
			if(user.getPostcode() == postcode)
			{
				throw new UnableToDeleteException("Cannot remove a postcode already in use!");
			}
		}
		
		boolean removed = this.postcodes.remove(postcode);
		
		if(removed == false)
			throw new UnableToDeleteException("The postcode doesn't exist!");
		
		notifyUpdate();
	}

	@Override
	public List<User> getUsers() {
		return this.users;
	}
	
	/**
	 * Adds an user to the server list.
	 *
	 * @param user the user
	 * @return true, if username isn't in use already
	 */
	public boolean addUser(User user)
	{
		synchronized(this.users)
		{
			for(User eUser : this.users)
			{
				if(eUser.getUsername().equals(user.getUsername()))
				{
					return false;
				}
			}
			
			this.users.add(user);
			notifyUpdate();
			return true;
		}
	}
	
	/**
	 * Login user if the username and password match.
	 *
	 * @param user the user
	 * @return the user
	 */
	public User loginUser(User user)
	{
		synchronized(this.users)
		{
			for(User eUser: this.users)
			{
				if(eUser.getUsername().equals(user.getUsername()) && eUser.getPassword().equals(user.getPassword()))
				{
					return eUser;
				}
			}
			
			return null;
		}
	}

	@Override
	public void removeUser(User user) throws UnableToDeleteException {
		for(Order order : this.orders)
		{
			if(order.getUser() == user)
			{
				throw new UnableToDeleteException("Cannot remove a user already in use!");
			}
		}
		
		boolean removed = this.users.remove(user);
		
		if(removed == false)
			throw new UnableToDeleteException("The user doesn't exist!");
		
		notifyUpdate();
	}

	@Override
	public String getName() {
		return "Server";
	}

}
