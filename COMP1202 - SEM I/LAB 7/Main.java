public class Main
{
	
	public static void main(String[] args)
	{
		TestCalculator testCalculator = new TestCalculator();
		//TEST 1
		boolean test1 = testCalculator.testParser();
		//TEST 2
		boolean test2 = testCalculator.testAdd();
		//TEST 3
		boolean test3 = testCalculator.testMultiplication();
		
		if(test1 && test2 && test3)
		{
			System.out.println("Congratulations, your Calculator appears to be working.");
		}
	}
	
}