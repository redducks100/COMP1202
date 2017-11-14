//A class which extends the Omnivore class.
public class Parrot extends Omnivore {
	
	/**
		Public constructor of the Parrot class.
		Calls the constructor from the super class.
		@param age the age of the animal
		@param name the name of the animal
	*/
	public Parrot(int age, String name) {
		super(age, name); //call the constructor from the super class (Omnivore class)
	}
	
	/**
		Print a message with the sound the animal makes.
	*/
	@Override
	public void makeNoise()
	{
		System.out.println("Chirp, Chirp");
	}
	
}
