public class Main {
	
	public static void main(String[] args)
	{
		Wolf wolf = new Wolf(5,"Wolfie"); //create a wolf object named Wolfie and age 5
		Parrot parrot = new Parrot(10, "Jimmy"); //create a parrot object named Jimmy and age 10
		Cow cow = new Cow(3, "Ella"); //create a cow object named Ella and age 3
		
		Meat meat = new Meat("Steak"); //create a meat object named Steak
		Plant plant = new Plant("Grass"); //create a plant object named Grass
		
		//how do this animals sound?
		wolf.makeNoise(); 
		parrot.makeNoise();
		cow.makeNoise();
		
		//surround the eat methods with try and catch (they might throw an exception)
		try {
			cow.eat(meat);
			wolf.eat(meat);
			parrot.eat(plant);
		}
		catch(Exception e)
		{
			System.err.println(e.toString()); //print the catched exception's message to the err stream
		}
	}
	
}
