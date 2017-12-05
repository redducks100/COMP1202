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
		@return String the name of the food
	*/
	public String getName()
	{
		return name;
	}
	
	/**
		@param name the new name of the food
	*/
	public void setName(String name)
	{
		this.name = name;
	}
	
}
