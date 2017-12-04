import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	private Scanner scanner = new Scanner(System.in);
	public EnigmaMachine machine;
	
	public Main()
	{
		machine = initialise();
	}
	
	public int readInt()
	{
		int choice = scanner.nextInt();
		scanner.nextLine();
		return choice;
	}
	
	public String readString()
	{
		return scanner.nextLine();
	}
	
	public static void main(String[] args) throws Exception
	{
		Main main = new Main();
		main.menu();
	}
	
	public EnigmaMachine initialise()
	{
		EnigmaMachine machine = new EnigmaMachine();
		
		System.out.println("Input the new settings for the enigma:");
		System.out.println("Number of plugs: ");
		int numberOfPlugs = readInt();
		
		for(int i=0;i<numberOfPlugs;i++)
		{
			System.out.println("Input 2 letters (the ends of the plug):");
			String plug = readString();
			
			if(plug.length() != 2)
			{
				System.out.println("The plug should have only 2 ends!");
				i--;
			}
			else
			{
				try {
					machine.addPlug(plug.charAt(0), plug.charAt(1));
				} catch (Exception e) {
					System.out.println("This plug is colliding with another plug!");
					i--;
				}
			}
		}
		
		
		int rotorType = 0;
		
		while(rotorType < 1 || rotorType > 2)
		{
			System.out.println("Type of the rotors: ");
			System.out.println("1 - BasicRotors");
			System.out.println("2 - TurnoverRotors");
			System.out.println("Choose an option: ");
			rotorType = readInt();
		}
		String[] type = new String[] {"I","II","III","IV","V"};
		if(rotorType == 1)
		{
			System.out.println("Input the type of the BasicRotors (I-1, II-2, III-3, IV-4, V-5) and the position (0-25):");
			for(int i=0; i<3; i++)
			{
				System.out.println("The type: ");
				int rotor = readInt();
				while(rotor < 1 || rotor > 5)
				{
					System.out.println("The type should be between 1-5!");
					rotor = readInt();
				}
				System.out.println("The position: ");
				int position = readInt();
				while(position < 0 || rotor > 25)
				{
					System.out.println("The position should be between 0-25!");
					position = readInt();
				}
				
				try {
					machine.addRotor(new BasicRotor(type[rotor-1]), i);
					machine.setPosition(i, position);
				} catch (Exception e) {
					System.err.println(e.toString());
				}
			}
		}
		else if(rotorType == 2)
		{
			System.out.println("Input the type of the TurnoverRotor (I-1, II-2, III-3, IV-4, V-5) and the position (0-25):");
			for(int i=0; i<3; i++)
			{
				System.out.println("The type: ");
				int rotor = readInt();
				while(rotor < 1 || rotor > 5)
				{
					System.out.println("The type should be between 1-5!");
					rotor = readInt();
				}
				System.out.println("The position: ");
				int position = readInt();
				while(position < 0 || rotor > 25)
				{
					System.out.println("The position should be between 0-25!");
					position = readInt();
				}
				
				try {
					machine.addRotor(new TurnoverRotor(type[rotor-1]), i);
					machine.setPosition(i, position);
				} catch (Exception e) {
					System.err.println(e.toString());
				}
			}
		}
		
		int reflectorType = 0;
		
		while(reflectorType < 1 || reflectorType > 2)
		{
			System.out.println("Type of the reflector: ");
			System.out.println("1 - ReflectorI");
			System.out.println("2 - ReflectorII");
			System.out.println("Choose an option: ");
			reflectorType = readInt();
		}
		String[] reflectorTypes = new String[] {"ReflectorI","ReflectorII"};
		machine.addReflector(new Reflector());
		try {
			machine.getReflector().initialise(reflectorTypes[reflectorType-1]);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		
		return machine;
	}
	
	public void menu()
	{
		int choice = -1;
		while(choice != 0)
		{
			System.out.println("Main Menu: ");
			System.out.println("1 - Test 1&2");
			System.out.println("2 - Test 3");
			System.out.println("3 - Bombe Challenges");
			System.out.println("4 - Encode");
			System.out.println("5 - New settings");
			System.out.println("0 - Exit");
			System.out.println("Choose an option: ");
			choice = readInt();
			
			switch(choice)
			{
				case 1:
					EnigmaMachine testMachine = new EnigmaMachine();
					try {
						testMachine.start();
					} 
					catch (Exception e) {
						System.err.println(e.toString());
					}
					break;
				case 2:
					EnigmaMachine testMachine2 = new EnigmaMachine();
					try {
						testMachine2.addPlug('Q', 'F');
						testMachine2.addRotor(new TurnoverRotor("I"), 0);
						testMachine2.addRotor(new TurnoverRotor("II"), 1);
						testMachine2.addRotor(new TurnoverRotor("III"), 2);
						testMachine2.setPosition(0, 23);
						testMachine2.setPosition(1, 11);
						testMachine2.setPosition(2, 7);
						testMachine2.addReflector(new Reflector());
						testMachine2.getReflector().initialise("ReflectorI");
					}
					catch(Exception e)
					{
						System.err.println(e.toString());
					}
					EnigmaFile file = new EnigmaFile(testMachine2);
					file.encryptFile("testFile.txt", "testEncryptedFile.txt");
					System.out.println("Check the file under /src/ called \"testEncryptedFile.txt\" for the decrypted message!");
					break;
				case 3:
					//CHALLENGE 1
					EnigmaMachine testMachine3 = new EnigmaMachine();
					try {
						testMachine3.addRotor(new BasicRotor("IV"), 0);
						testMachine3.addRotor(new BasicRotor("III"), 1);
						testMachine3.addRotor(new BasicRotor("II"), 2);
						testMachine3.addReflector(new Reflector());
						testMachine3.getReflector().initialise("ReflectorI");
					}
					catch(Exception e)
					{
						System.err.println(e.toString());
					}
					Bombe bombe = new Bombe(testMachine3);
					ArrayList<String> decryptedStrings = new ArrayList<String>();
					try {
						decryptedStrings = bombe.crackEnigma(new BombeSettings(BombeSettingsType.MISSING_PLUGS,"DS",new int[] {8,4,21}), "JBZAQVEBRPEVPUOBXFLCPJQSYFJI", "ANSWER");
					} catch (Exception e) {
						System.out.println(e.toString());
					}
					System.out.println("Challenge 1 Possible Answers: ");
					for(String decryptedString : decryptedStrings)
					{
						System.out.println(decryptedString);
					}
					//CHALLENGE 2
					try {
						testMachine3.clearPlugboard();
						testMachine3.addPlug('H', 'L');
						testMachine3.addPlug('G', 'P');
						testMachine3.addRotor(new BasicRotor("V"), 0);
					}
					catch(Exception e)
					{
						System.err.println(e.toString());
					}
					
					try {
						decryptedStrings = bombe.crackEnigma(new BombeSettings(BombeSettingsType.MISSING_POSITION,null,null), "AVPBLOGHFRLTFELQEZQINUAXHTJMXDWERTTCHLZTGBFUPORNHZSLGZMJNEINTBSTBPPQFPMLSVKPETWFD", "ELECTRIC");
					} catch (Exception e) {
						System.out.println(e.toString());
					}
					System.out.println("Challenge 2 Possible Answers: ");
					for(String decryptedString : decryptedStrings)
					{
						System.out.println(decryptedString);
					}
					//CHALLENGE 3
					try {
						testMachine3.clearPlugboard();
						testMachine3.addPlug('M', 'F');
						testMachine3.addPlug('O', 'I');
					}
					catch(Exception e)
					{
						System.err.println(e.toString());
					}
					
					try {
						decryptedStrings = bombe.crackEnigma(new BombeSettings(BombeSettingsType.MISSING_TYPE,null,new int[] {22, 24, 23}), "WMTIOMNXDKUCQCGLNOIBUYLHSFQSVIWYQCLRAAKZNJBOYWW", "JAVA");
					} catch (Exception e) {
						System.out.println(e.toString());
					}
					System.out.println("Challenge 3 Possible Answers: ");
					for(String decryptedString : decryptedStrings)
					{
						System.out.println(decryptedString);
					}
					break;
				case 4:
					System.out.println("String to encode with current settings: ");
					String toEncode = readString();
					toEncode = toEncode.toUpperCase();
					System.out.println("Encoded String: " + machine.encodeString(toEncode));
					break;
				case 5:
					machine = initialise();
					break;
				case 0:
					System.out.println("Goodbye!");
					break;
				default:
					System.out.println("This option doesn't exist!");
					break;
			}
		}
	}
	
}
