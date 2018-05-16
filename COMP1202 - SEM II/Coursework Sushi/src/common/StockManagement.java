/*
 * @author Andronache Simone
 */
package common;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class StockManagement {
	
	/** The dishes stock. */
	private HashMap<Dish, Number> dishes;
	
	/** The ingredients stock. */
	private HashMap<Ingredient, Number> ingredients;
	
	/** Queue to check for dishes needed */
	private Queue<Dish> preparingDishes;
	
	/** Queue to check for ingredients needed */
	private Queue<Ingredient> preparingIngredients;
	
	/** Dishes who are currently being prepared */
	private List<Dish> currentlyWorkingDishes;
	
	/** Static instance of the class */
	public static StockManagement instance;
	
	/**
	 * Instantiates a new stock management.
	 */
	public StockManagement()
	{
		StockManagement.instance = this;
		this.dishes = new HashMap<Dish, Number>();
		this.ingredients = new HashMap<Ingredient, Number>();
		this.preparingDishes = new ArrayDeque<Dish>();
		this.preparingIngredients = new ArrayDeque<Ingredient>();
		
		this.currentlyWorkingDishes = new ArrayList<Dish>();
	}
	
	/**
	 * Gets the dishes stock.
	 *
	 * @return the dishes
	 */
	public synchronized Map<Dish, Number> getDishes()
	{
		return this.dishes;
	}
	
	/**
	 * Gets the ingredients stock.
	 *
	 * @return the ingredients
	 */
	public synchronized Map<Ingredient, Number> getIngredients()
	{
		return this.ingredients;
	}
	
	/**
	 * Check if the dish type is currently being prepared.
	 *
	 * @param type the type
	 * @return true, if successful
	 */
	public synchronized boolean workingOnDish(Dish type)
	{
		return this.currentlyWorkingDishes.contains(type);
	}
	
	/**
	 * Adds a dish to the currently being prepared list.
	 *
	 * @param type the type
	 */
	public synchronized void addWorkingOnDish(Dish type)
	{
		this.currentlyWorkingDishes.add(type);
	}
	
	/**
	 * Removes a dish from the currently being prepared list.
	 *
	 * @param type the type
	 */
	public synchronized void removeWorkingOnDish(Dish type)
	{
		this.currentlyWorkingDishes.remove(type);
	}
	
	/**
	 * Sets the stock for a dish.
	 *
	 * @param dish the dish
	 * @param stock the stock
	 */
	public void setStockDish(Dish dish, Number stock)
	{
		synchronized(this.dishes)
		{
			this.dishes.put(dish, stock);
		}
	}
	
	/**
	 * Sets the stock for a ingredient.
	 *
	 * @param ingredient the ingredient
	 * @param stock the stock
	 */
	public void setStockIngredient(Ingredient ingredient, Number stock)
	{
		synchronized(this.ingredients)
		{
			this.ingredients.put(ingredient, stock);
		}
	}
	
	/**
	 * Gets the amount of dishes available in stock.
	 *
	 * @param type the type
	 * @return the amount dish
	 */
	public synchronized Number getAmountDish(Dish type)
	{
		if(dishes.containsKey(type))
		{
			return dishes.get(type);
		}
	
		return 0;
	}
	
	/**
	 * Gets the amount of ingredients available in stock.
	 *
	 * @param type the type
	 * @return the amount ingredient
	 */
	public synchronized Number getAmountIngredient(Ingredient type)
	{
		if(ingredients.containsKey(type))
		{
			return ingredients.get(type);
		}
	
		return 0;
	}
	
	/**
	 * Adds to the dish queue.
	 *
	 * @param type the dish type
	 */
	public synchronized void addDishQueue(Dish type)
	{
		preparingDishes.add(type);
	}
	
	/**
	 * Adds to the ingredient queue.
	 *
	 * @param type the ingredient type
	 */
	public synchronized void addIngredientQueue(Ingredient type)
	{
		preparingIngredients.add(type);
	}
	
	/**
	 * Checks if an ingredient is in need to be restocked
	 *
	 * @param type the type
	 * @return true, if successful
	 */
	public synchronized boolean ingredientQueueContains(Ingredient type)
	{
		return preparingIngredients.contains(type);
	}
	
	/**
	 * Checks if a dish is in need to be restocked.
	 *
	 * @param type the type
	 * @return true, if successful
	 */
	public synchronized boolean dishQueueContains(Dish type)
	{
		return preparingDishes.contains(type);
	}
	
	/**
	 * Remove the first element of the dish queue.
	 *
	 * @return the dish
	 */
	public synchronized Dish popDishQueue()
	{
		return preparingDishes.poll();
	}
	
	/**
	 * Remove the first element of the ingredients queue.
	 *
	 * @return the ingredient
	 */
	public synchronized Ingredient popIngredientQueue()
	{
		return preparingIngredients.poll();
	}
	
	/**
	 * Increase dish stock amount.
	 *
	 * @param type the type
	 * @param amount the amount
	 */
	public synchronized void increaseDishAmount(Dish type, int amount)
	{
		if(dishes.containsKey(type))
		{
			int oldAmount = (Integer) dishes.get(type);
			dishes.put(type, oldAmount + amount);
		}
		else
		{
			dishes.put(type, amount);
		}
		
		if(dishes.get(type).intValue() < type.getRestockThreshold())
		{
			addDishQueue(type);
		}
	}
	
	/**
	 * Decrease dish stock amount.
	 *
	 * @param type the type
	 * @param amount the amount
	 */
	public synchronized void decreaseDishAmount(Dish type, int amount)
	{
		int oldAmount = (Integer) dishes.get(type);
		int restockingThreshold = type.getRestockThreshold();
		
		if(oldAmount - amount < restockingThreshold)
		{
			addDishQueue(type);
		}
		else
		{
			dishes.put(type, oldAmount - amount);
		}
		
		if(dishes.get(type).intValue() < type.getRestockThreshold())
		{
			addDishQueue(type);
		}
	}
	
	/**
	 * Increase ingredient stock amount.
	 *
	 * @param type the type
	 * @param amount the amount
	 */
	public synchronized void increaseIngredientAmount(Ingredient type, int amount)
	{
		if(ingredients.containsKey(type))
		{
			int oldAmount = (Integer) ingredients.get(type);
			ingredients.put(type, oldAmount + amount);
		}
		else
		{
			ingredients.put(type, amount);
		}
		
		if(ingredients.get(type).intValue() < type.getRestockThreshold())
		{
			addIngredientQueue(type);
		}
	}
	
	/**
	 * Decrease ingredient stock amount.
	 *
	 * @param type the type
	 * @param amount the amount
	 */
	public synchronized void decreaseIngredientAmount(Ingredient type, int amount)
	{
		int oldAmount = (Integer) ingredients.get(type);
		
		if(oldAmount - amount < type.getRestockThreshold())
		{
			addIngredientQueue(type);
		}
		
		ingredients.put(type, oldAmount - amount);
	}
	
	/**
	 * Get the data of the object in string format.
	 *
	 * @return the string
	 */
	public String toPersistanceString()
	{
		String newStock = new String();
		
		synchronized(this.dishes)
		{
			for(Dish dish: this.dishes.keySet())
			{
				String newLine = "STOCK:" + dish.getName() + ":" + this.dishes.get(dish).toString() + "\n";
				newStock += newLine;
			}
		}
		synchronized(this.ingredients)
		{
			for(Ingredient ingredient: this.ingredients.keySet())
			{
				String newLine = "STOCK:" + ingredient.getName() + ":" + this.ingredients.get(ingredient).toString() + "\n";
				newStock += newLine;
			}
		}
		
		return newStock;
	}
}
