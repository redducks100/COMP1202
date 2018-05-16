/*
 * @author Andronache Simone
 */
package common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Dish extends Model implements Restockable {
	
	/** The description. */
	private String description;
	
	/** The price. */
	private Integer price;
	
	/** The recipe. */
	private HashMap<Ingredient, Number> recipe;
	
	/** The restock threshold. */
	private Integer restockThreshold;
	
	/** The restock amount. */
	private Integer restockAmount;
	
	/**
	 * Instantiates a new dish.
	 *
	 * @param name the name
	 * @param description the description
	 * @param price the price
	 * @param restockThreshold the restock threshold
	 * @param restockAmount the restock amount
	 */
	public Dish(String name, String description, Integer price, Integer restockThreshold, Integer restockAmount)
	{
		this.name = name;
		this.description = description;
		this.price = price;
		this.recipe = new HashMap<Ingredient, Number>();
		this.restockThreshold = restockThreshold;
		this.restockAmount = restockAmount;
	}
	
	/**
	 * Instantiates a new dish.
	 *
	 * @param name the name
	 * @param description the description
	 * @param price the price
	 * @param recipe the recipe
	 * @param restockThreshold the restock threshold
	 * @param restockAmount the restock amount
	 */
	public Dish(String name, String description, Integer price, HashMap<Ingredient, Number> recipe, Integer restockThreshold, Integer restockAmount)
	{
		this.name = name;
		this.description = description;
		this.price = price;
		this.recipe = recipe;
		this.restockThreshold = restockThreshold;
		this.restockAmount = restockAmount;
	}
	
	/**
	 * Adds the ingredient to the recipe.
	 *
	 * @param ingredient the ingredient
	 * @param quantity the quantity
	 */
	public void addIngredient(Ingredient ingredient, Integer quantity)
	{
		HashMap<Ingredient, Number> oldRecipe = this.recipe;
		this.recipe.put(ingredient, quantity);
		this.notifyUpdate("recipe", oldRecipe, this.recipe);
	}
	
	/**
	 * Removes the ingredient from the recipe.
	 *
	 * @param ingredient the ingredient
	 */
	public void removeIngredient(Ingredient ingredient)
	{
		HashMap<Ingredient, Number> oldRecipe = this.recipe;
		this.recipe.remove(ingredient);
		this.notifyUpdate("recipe", oldRecipe, this.recipe);
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription()
	{
		return this.description;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description)
	{
		this.notifyUpdate("description", this.description, description);
		this.description = description;
	}
	
	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public Integer getPrice()
	{
		return this.price;
	}
	
	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(Integer price)
	{
		this.notifyUpdate("price", this.price, price);
		this.price = price;
	}
	
	/**
	 * Gets the recipe.
	 *
	 * @return the recipe
	 */
	public HashMap<Ingredient, Number> getRecipe()
	{
		return this.recipe;
	}
	
	/**
	 * Sets the recipe.
	 *
	 * @param recipe the recipe
	 */
	public void setRecipe(HashMap<Ingredient, Number>recipe)
	{
		this.notifyUpdate("recipe", this.recipe, recipe);
		this.recipe = recipe;
	}

	@Override
	public Integer getRestockThreshold() {
		return this.restockThreshold;
	}

	@Override
	public void setRestockThreshold(Integer restockThreshold) {
		this.notifyUpdate("restockThreshold", this.restockThreshold, restockThreshold);
		this.restockThreshold = restockThreshold;
	}

	@Override
	public Integer getRestockAmount() {
		return this.restockAmount;
	}

	@Override
	public void setRestockAmount(Integer restockAmount) {
		this.notifyUpdate("restockAmount", this.restockAmount, restockAmount);
		this.restockAmount = restockAmount;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		
		Dish dish = (Dish) o;
		
		if(!name.equals(dish.name)) return false;
		if(!description.equals(dish.description)) return false;
		if(!price.equals(dish.price)) return false;
		if(!restockAmount.equals(dish.restockAmount)) return false;
		if(!restockThreshold.equals(dish.restockThreshold)) return false;
		
		return true;
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hashCode(this.name);
	}
	
	/**
	 * Get the object data in string format
	 *
	 * @return the string
	 */
	public String toPersistanceString()
	{
		String newString = new String("DISH:");
		newString += this.getName() + ":";
		newString += this.getDescription() + ":";
		newString += this.getPrice().toString() + ":";
		newString += this.getRestockThreshold().toString() + ":";
		newString += this.getRestockAmount().toString() + ":";
		
		for(Ingredient ingr : this.recipe.keySet())
		{
			newString += this.recipe.get(ingr).toString() + " * " + ingr.getName() + ",";
		}
		
		newString = newString.substring(0, newString.length() - 1);
		
		return newString;
	}
}
