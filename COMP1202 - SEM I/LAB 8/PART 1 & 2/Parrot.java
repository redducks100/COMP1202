public class Parrot extends Omnivore {
	
	/**
		Public constructor of the Parrot class.
		@param age the age of the animal
		@param name the name of the animal
	*/
	public Parrot(int age, String name) {
		super(age, name); 
	}
	
	public Parrot(int age)
	{
		this(age,"Polly");
	}
	
	@Override
	public void makeNoise()
	{
		System.out.println("Chirp, Chirp");
	}
	
}
