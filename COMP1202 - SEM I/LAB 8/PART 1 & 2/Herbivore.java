public abstract class Herbivore extends Animal{

	public Herbivore(int age, String name) {
		super(age, name);
	}
	
	@Override
	public void eat(Food food) throws Exception
	{
		if(food instanceof Plant) 
		{	
			System.out.println(this.getName() + " is eating " + food.getName()); 
		}
		else
		{
			throw new Exception(this.getName() + " cannot eat " + food.getName() + "! Herbivores can only eat plants!"); 
		}
	}
}
