public class FizzBuzz //declaring a new public class called FizzBuzz
{

	public static void main(String[] args) //declaring the main function
	{
		for(Integer i = new Integer(1);i < 61;i++) //looping through each number from 1 to 60
		{
			if(i % 3 != 0 && i % 5 != 0) //checking if the number is not divisible with 3 and 5
			{
				System.out.print(i); //printing the number to the screen
			} 
			if(i % 3 == 0) //checking if the number is divisible with 3
			{
				System.out.print("Fizz"); //printing "Fizz" to the screen
			}
			if(i % 5 == 0) //checking if the number is divisible with 5
			{ 
				System.out.print("Buzz"); //printing "Buzz" to the screen
			} 

			System.out.println(); //printing the '\n' element so we can access a new line
		}	
	}
}
