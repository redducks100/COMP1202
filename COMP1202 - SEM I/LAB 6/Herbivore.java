//An abstract class which extends Animal (can only eat Plant type objects)
public abstract class Herbivore extends Animal{

	public Herbivore(int age, String name) {
		super(age, name);
	}
	
	/**
		Eat an object of type Food.
		If the type is not Plant it will throw an exception.
		@param food object of type food or an extension of food
	*/
	@Override
	public void eat(Food food) throws Exception
	{
		if(food instanceof Plant) //check if the food's type is plant
		{	
			System.out.println(this.getName() + " is eating " + food.getName()); //print the message
		}
		else
		{
			throw new Exception(this.getName() + " cannot eat " + food.getName() + "! Herbivores can only eat plants!"); //the food's type is not plant, throw an exception
		}
	}
}
