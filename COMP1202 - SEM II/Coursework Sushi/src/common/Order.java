/*
 * @author Andronache Simone
 */
package common;

import java.util.HashMap;

public class Order extends Model {

	/** The user. */
	private User user;
	
	/** The dishes. */
	private HashMap<Dish, Number> dishes;
	
	/** The completed status. */
	private boolean completed;
	
	/**
	 * Instantiates a new order.
	 *
	 * @param user the user
	 * @param dishes the dishes
	 */
	public Order(User user, HashMap<Dish, Number> dishes)
	{
		this.name = "Order" + (1 + (int)(Math.random() * ((100000 - 1) + 1)));
		this.user = user;
		this.dishes = dishes;
		this.completed = false;
	}
	
	/**
	 * Instantiates a new order.
	 *
	 * @param user the user
	 * @param dishes the dishes
	 * @param completed the completed
	 */
	public Order(User user, HashMap<Dish, Number> dishes, boolean completed)
	{
		this.name = "Order" + (1 + (int)(Math.random() * ((100000 - 1) + 1)));
		this.user = user;
		this.dishes = dishes;
		this.completed = completed;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.notifyUpdate("user", this.user, user);
		this.user = user;
	}

	/**
	 * Gets the dishes.
	 *
	 * @return the dishes
	 */
	public HashMap<Dish, Number> getDishes() {
		return dishes;
	}

	/**
	 * Sets the dishes.
	 *
	 * @param dishes the dishes
	 */
	public void setDishes(HashMap<Dish, Number> dishes) {
		this.notifyUpdate("dishes", this.dishes, dishes);
		this.dishes = dishes;
	}

	/**
	 * Gets the completed status.
	 *
	 * @return the completed status
	 */
	public boolean getCompleted() {
		return this.completed;
	}

	/**
	 * Sets the completed status.
	 *
	 * @param completed the new completed status
	 */
	public void setCompleted(boolean completed) {
		this.notifyUpdate("completed", this.completed, completed);
		this.completed = completed;
		//Notify the persistance manager that the order status has changed
		this.notifyUpdate("persistanceCompleted", this.completed, this.completed);
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus()
	{
		if(this.getCompleted()) 
			return "Delivered";
		return "Preparing";
	}
	
	/**
	 * Gets the cost.
	 *
	 * @return the cost
	 */
	public Number getCost()
	{
		int sum = 0;
		
		for(Dish dish: this.dishes.keySet())
		{
			sum += dish.getPrice() * this.dishes.get(dish).intValue();
		}
		
		return sum;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the object data in a string format.
	 *
	 * @return the string
	 */
	public String toPersistanceString()
	{
		String newString = new String("ORDER:");
		newString += this.getUser().getUsername() + ":";
		
		if(this.getCompleted() == true)
		{
			newString += "true:";
		}
		
		for(Dish dish : this.dishes.keySet())
		{
			newString += this.dishes.get(dish).toString() + " * " + dish.getName() + ",";
		}
		newString = newString.substring(0, newString.length() - 1);
		
		return newString;
	}
}
