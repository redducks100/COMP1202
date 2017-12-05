public abstract class Animal implements Comparable<Animal> {
	
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
	
	public Animal()
	{
		this(0, "newborn");
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
	
	@Override
	public int compareTo(Animal other)
	{
		return Integer.compare(this.age, other.age);
	}
	
	/* Abstract methods */
	/**
		Print a message with the sound the animal makes.
	*/
	public abstract void makeNoise();
	/**
		Eat an object of type Food.
		@param food object of type food or an extension of food
	*/
	public abstract void eat(Food food) throws Exception;
	
	/**
		Eat an object of type Food n-times.
		@param food object of type food or an extension of food
		@param times how many times to eat the food
	*/
	public void eat(Food food, Integer times) throws Exception
	{
		Integer i = new Integer(0);
		
		while(i != times)
		{
			eat(food);
			i++;
		}
	}
}
