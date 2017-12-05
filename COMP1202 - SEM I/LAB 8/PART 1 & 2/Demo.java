import java.util.*;

public class Demo
{
	public static void main(String[] args)
	{
		//part 2
		ArrayList<Animal> animals = new ArrayList<Animal>();
		
		//create a newborn wolf
		Wolf wolf = new Wolf(); 
		//create a parrot object named Polly and age 10
		Parrot parrot = new Parrot(10); 
		//create a cow object named Ella and age 3
		Cow cow = new Cow(3, "Ella"); 
		
		animals.add(wolf);
		animals.add(parrot);
		animals.add(cow);
		
		System.out.println("Before sort:");
		for(Animal animal : animals)
		{
			System.out.println(animal.getName() + " - " + animal.getAge());
		}
		
		//Sorting the list using the collections sort
		Collections.sort(animals);
		
		System.out.println("After sort:");
		for(Animal animal : animals)
		{
			System.out.println(animal.getName() + " - " + animal.getAge());
		}
	}
}