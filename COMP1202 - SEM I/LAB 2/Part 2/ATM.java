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
		menu();
	}
	
	private int readInt()
	{
		System.out.println("Enter your number:");
		return scanner.nextInt();
	}
	
	public void menu()
	{
		int number=0;
		System.out.println("What do you want to do?");
		System.out.println("1 : Withdraw");
		System.out.println("2 : Deposit");
		System.out.println("3 : Inquire");
		System.out.println("4 : Quit");
		number = readInt();
			
		if(number == 1)
		{
			withdraw();
		}
		else if(number == 2)
		{
			deposit();
		}
		else if(number == 3)
		{
			inquire();
		}
		else if(number == 4)
		{
			quit();
		}
	}
	
	public void withdraw()
	{
		System.out.println("******************************************");
		System.out.println("              Withdrawal");
		System.out.println("******************************************");
		
		int withdrawalNumber = 0;
		
		System.out.println("How much would you like to withdraw?");
		withdrawalNumber = readInt();
		currentBalance -= withdrawalNumber;
	}
	
	public void deposit()
	{
		System.out.println("******************************************");
		System.out.println("              Deposit");
		System.out.println("******************************************");
		
		int depositNumber = 0;
		System.out.println("How much would you like to deposit?");
		depositNumber = readInt();
		currentBalance += depositNumber;
	}
	
	public void inquire()
	{
		System.out.println("******************************************");
		System.out.println("          Your balance is " + currentBalance);
		System.out.println("******************************************");
	}
	
	public void quit()
	{
		System.out.println("******************************************");
		System.out.println("         GoodBye!");
		System.out.println("******************************************");
	}
}