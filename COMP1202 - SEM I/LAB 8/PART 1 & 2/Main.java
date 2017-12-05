public class Main {
	
	public static void main(String[] args)
	{
		
		//part 1
		
		Wolf wolf = new Wolf(); 
		Parrot parrot = new Parrot(10); 
		Cow cow = new Cow(3, "Ella"); 
		
		Meat meat = new Meat("Steak"); 
		Plant plant = new Plant("Grass"); 
		
		
		wolf.makeNoise(); 
		parrot.makeNoise();
		cow.makeNoise();
		
		try {
			wolf.eat(meat, 10);
		}
		catch(Exception e)
		{
			System.err.println(e.toString()); 
		}
	}
	
}
