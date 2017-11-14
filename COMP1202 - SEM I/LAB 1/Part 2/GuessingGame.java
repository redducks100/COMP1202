public class GuessingGame {

	public static void main(String[] args)
	{
		Toolbox myToolbox = new Toolbox(); //declaring a new object of type Toolbox
		Integer numberToGuess, guessedNumber; //declaring 2 new objects of type Integer
		
		numberToGuess = myToolbox.getRandomInteger(10); //assigning a random integer to the variable numberToGuess
		
		//printing the menu
		System.out.println("Welcome to the Guessing Game! Hope you have fun!");
		System.out.println("Choose a number between 0-10!");
		
		//reading the integer from the console
		guessedNumber = myToolbox.readIntegerFromCmd();
		
		
		//checking if the guessedNumber is equal to the numberToGuess
		if(numberToGuess.equals(guessedNumber))
		{
			System.out.println("Winner winner chicken dinner!"); //they are equal
		}
		else
		{
			System.out.println("You lose!"); //they are not
		}
	}

}
