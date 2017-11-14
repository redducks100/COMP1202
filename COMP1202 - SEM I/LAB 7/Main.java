public class Main
{
	
	public static void main(String[] args)
	{
		TestCalculator testCalculator = new TestCalculator();
		boolean test1 = testCalculator.testParser();
		boolean test2 = testCalculator.testAdd();
		boolean test3 = testCalculator.testMultiplication();
		
		if(test1 & test2 & test3)
		{
			System.out.println("Congratulations, your Calculator appears to be working.");
		}
	}
	
}