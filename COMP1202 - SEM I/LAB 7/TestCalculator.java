public class TestCalculator
{
	/**
	 * @return true if the parser passed all tests
	 */
	public boolean testParser()
	{
		Calculator calculator = new Calculator();
		boolean passed = true;
		
		Double test1 = calculator.x("12 + 5");
		
		if(test1.equals(new Double(17)))
		{
			System.out.println("[ OK ] Parse adds correctly.");
		}
		else
		{
			passed = false;
			System.out.println("[FAIL] Basic parsing fails to add.");
		}
		
		Double test2 = calculator.x("12 x 5");
		if(test2.equals(new Double(60)))
		{
			System.out.println("[ OK ] Parse multiplies correctly.");
		}
		else
		{
			passed = false;
			System.out.println("[FAIL] Basic parsing fails to multiply.");
		}
		
		Double test3 = calculator.x("12 [ 3");
		if(test3 == null)
		{
			System.out.println("[ OK ] returns null for operators which are not supported.");
		}
		else
		{
			passed = false;
			System.out.println("[FAIL] Parser does not return null for operators which are not supported.");
		}
		
		return passed;
	}
	
	/**
	 * @return true if the calculator passed the add tests
	 */
	public boolean testAdd()
	{
		Calculator calculator = new Calculator();
		boolean passed = true;
		
		calculator.x = new Double(5);
		Double test1 = calculator.x(new Double(3));
		
		if(test1.equals(new Double(8)))
		{
			System.out.println("[ OK ] Calculator can add positive numbers.");
		}
		else
		{
			passed = false;
			System.out.println("[FAIL] Calculator adds incorrectly.");
		}
		
		calculator.x = new Double(-5);
		Double test2 = calculator.x(new Double(-3));
		
		if(test2.equals(new Double(-8)))
		{
			System.out.println("[ OK ] Calculator can add negative numbers.");
		}
		else
		{
			passed = false;
			System.out.println("[FAIL] Calculator adds with negative numbers incorrectly.");
		}
		
		return passed;
	}
	
	/**
	 * @return true if the calculator passed the multiplication tests
	 */
	public boolean testMultiplication()
	{
		Calculator calculator = new Calculator();
		boolean passed = true;
		
		calculator.x = new Double(5);
		Double test1 = calculator.x(3.0);
		
		if(test1.equals(new Double(15)))
		{
			System.out.println("[ OK ] Calculator can multiply positive numbers.");
		}
		else
		{
			passed = false;
			System.out.println("[FAIL] Calculator multiplies incorrectly.");
		}
		
		calculator.x = new Double(-5);
		Double test2 = calculator.x(-3.0);
		
		if(test2.equals(new Double(15)))
		{
			System.out.println("[ OK ] Calculator can multiply negative numbers.");
		}
		else
		{
			passed = false;
			System.out.println("[FAIL] Calculator multiplies by negative numbers incorrectly.");
		}
		
		return passed;
	}
}