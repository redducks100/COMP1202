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
		while(currentBalance < 0)
		{
			System.out.println("The starting balance can't be negative!");
			System.out.println("Enter your new balance!");
			currentBalance = readInt();
		}
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
		while(true)
		{
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
	}
	
	public void withdraw()
	{
		System.out.println("******************************************");
		System.out.println("              Withdrawal");
		System.out.println("******************************************");
		
		int withdrawalNumber = 0;
		
		System.out.println("How much would you like to withdraw?");
		boolean correct = false;
		while(correct == false)
		{
			withdrawalNumber = readInt();
			if(withdrawalNumber < 0)
			{
				System.out.println("Error! You can't withdraw a negative value!");
			}
			else if(currentBalance - withdrawalNumber < 0)
			{
				System.out.println("Error! You can't withdraw that much money!");
			}
			else{
				correct = true;
				currentBalance -= withdrawalNumber;
			}
		}
	}
	
	public void deposit()
	{
		System.out.println("******************************************");
		System.out.println("              Deposit");
		System.out.println("******************************************");
		
		int depositNumber = 0;
		System.out.println("How much would you like to deposit?");
		boolean correct = false;
		while(correct == false)
		{
			depositNumber = readInt();
			
			if(depositNumber < 0)
			{
				System.out.println("Error! You can't deposit a negative value!");
			}
			else
			{
				correct = true;
				currentBalance += depositNumber;
			}
		}
	}
	
	public void inquire()
	{
		System.out.println("******************************************");
		System.out.println("          Your balance is " + Integer.toString(currentBalance) + ".");
		System.out.println("******************************************");
	}
	
	public void quit()
	{
		System.out.println("******************************************");
		System.out.println("         GoodBye!");
		System.out.println("******************************************");
		System.exit(0);	
	}
}