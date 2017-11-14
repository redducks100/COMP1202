//An abstract class which extends Animal (can only eat meat type objects)
public abstract class Carnivore extends Animal{

	public Carnivore(int age, String name) {
		super(age, name);
	}
	
	/**
		Eat an object of type Food.
		If the type is not Meat it will throw an exception.
		@param food object of type food or an extension of food
	*/
	@Override
	public void eat(Food food) throws Exception
	{
		if(food instanceof Meat) //check if food's type is Meat 
		{
			System.out.println(this.getName() + " is eating " + food.getName()); //print the message
		}
		else
		{
			throw new Exception(this.getName() + " cannot eat " + food.getName() + "! Carnivores can only eat meat!"); //the food's type is not meat, throw an exception
		}
	}

}
