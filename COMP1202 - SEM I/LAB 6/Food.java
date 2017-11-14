public class Food {
	
	private String name;
	
	/**
		Public constructor for the Food class.
		@param name the name of the food
	*/
	public Food(String name)
	{
		this.name = name;
	}
	
	/**
		Getter for the name variable.
		@return String the name of the food
	*/
	public String getName()
	{
		return name;
	}
	
	/**
		Setter for the name variable
		@param name the new name of the food
	*/
	public void setName(String name)
	{
		this.name = name;
	}
	
}
