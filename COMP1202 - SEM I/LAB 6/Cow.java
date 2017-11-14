//A class which extends the Herbivore class.
public class Cow extends Herbivore{
	
	/**
		Public constructor of the Cow class.
		Calls the constructor from the super class.
		@param age the age of the animal
		@param name the name of the animal
	*/
	public Cow(int age, String name) {
		super(age, name);  //call the constructor from the super class (Herbivore class)
	}
	
	/**
		Print a message with the sound the animal makes.
	*/
	@Override
	public void makeNoise()
	{
		System.out.println("Moo, moo");
	}
}
