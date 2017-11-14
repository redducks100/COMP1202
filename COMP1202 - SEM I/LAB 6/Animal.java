public abstract class Animal {
	
	private int age;
	private String name;
	
	/**
		Public constructor for the Animal class.
		@param age integer representing the age of the animal
		@param name string representing the name of the animal
	*/
	public Animal(int age, String name)
	{
		this.age = age;
		this.name = name;
	}
	/**
		Getter for the age variable.
		@return int the age of the animal
	*/
	public int getAge()
	{
		return age;
	}
	/**
		Getter for the name variable.
		@return String the name of the animal
	*/
	public String getName()
	{
		return name;
	}
	/**
		Setter for the name variable.
		@param name the new name of the animal
	*/
	public void setName(String name)
	{
		this.name = name;
	}
	/**
		Setter for the age variable.
		@param age the new age of the animal
	*/
	public void setAge(int age)
	{
		this.age = age;
	}
	
	/* Abstract methods */
	public abstract void makeNoise();
	public abstract void eat(Food food) throws Exception;
	
}
