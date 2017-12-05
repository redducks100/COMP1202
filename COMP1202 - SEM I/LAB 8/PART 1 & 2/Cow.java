public class Cow extends Herbivore{
	
	/**
		Public constructor of the Cow class.
		@param age the age of the animal
		@param name the name of the animal
	*/
	public Cow(int age, String name) {
		super(age, name); 
	}
	
	@Override
	public void makeNoise()
	{
		System.out.println("Moo, moo");
	}
}
