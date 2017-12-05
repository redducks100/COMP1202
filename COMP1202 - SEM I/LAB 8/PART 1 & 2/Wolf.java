public class Wolf extends Carnivore {
	
	/**
		Public constructor of the Wolf class.
		@param age the age of the animal
		@param name the name of the animal
	*/
	public Wolf(int age, String name) {
		super(age, name);
	}
	
	public Wolf()
	{
		super();
	}
	
	@Override
	public void makeNoise()
	{
		System.out.println("Woof, woof");
	}

}
