public abstract class Carnivore extends Animal{

	public Carnivore(int age, String name) {
		super(age, name);
	}
	
	public Carnivore()
	{
		super();
	}
	
	@Override
	public void eat(Food food) throws Exception
	{
		if(food instanceof Meat)
		{
			System.out.println(this.getName() + " is eating " + food.getName());
		}
		else
		{
			throw new Exception(this.getName() + " cannot eat " + food.getName() + "! Carnivores can only eat meat!"); 
		}
	}

}
