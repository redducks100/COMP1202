public abstract class Omnivore extends Animal{

	public Omnivore(int age, String name) {
		super(age, name);
	}
	
	@Override
	public void eat(Food food) throws Exception
	{
		System.out.println(this.getName() + " is eating " + food.getName());
	}
}
