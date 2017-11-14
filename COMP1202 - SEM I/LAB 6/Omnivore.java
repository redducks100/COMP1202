//An abstract class which extends Animal (can eat any type of food)
public abstract class Omnivore extends Animal{

	public Omnivore(int age, String name) {
		super(age, name);
	}
	
	/**
		Eat an object of type Food.
		@param food object of type food or an extension of food
	*/
	@Override
	public void eat(Food food) throws Exception
	{
		System.out.println(this.getName() + " is eating " + food.getName()); //print out the message
	}
}
