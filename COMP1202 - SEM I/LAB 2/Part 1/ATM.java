import java.util.Scanner;

public class ATM
{
	private int currentBalance;
	private Scanner scanner = new Scanner(System.in);
	
	public void go()
	{
		System.out.println("Welcome to online ATM banking");
		System.out.println("How much do you want in your account?");
		currentBalance = readInt();
	}
	
	private int readInt()
	{
		System.out.println("Enter your number: ");
		return scanner.nextInt();
	}
}