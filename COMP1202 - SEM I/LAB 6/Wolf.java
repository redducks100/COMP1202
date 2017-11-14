//A class which extends the Carnivore class.
public class Wolf extends Carnivore {
	
	/**
		Public constructor of the Wolf class.
		Calls the constructor from the super class.
		@param age the age of the animal
		@param name the name of the animal
	*/
	public Wolf(int age, String name) {
		super(age, name); //call the constructor from the super class (Carnivore class)
	}
	
	/**
		Print a message with the sound the animal makes.
	*/
	@Override
	public void makeNoise()
	{
		System.out.println("Woof, woof");
	}

}
